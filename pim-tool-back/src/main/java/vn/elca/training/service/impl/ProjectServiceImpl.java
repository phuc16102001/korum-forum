package vn.elca.training.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.model.constant.Constant;
import vn.elca.training.model.dto.EmployeeDto;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.DuplicateProjectNumberException;
import vn.elca.training.model.exception.EndDateBeforeOrEqualStartDate;
import vn.elca.training.model.exception.MemberNotExistException;
import vn.elca.training.model.exception.ProjectNotFoundException;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.EmployeeService;
import vn.elca.training.service.ProjectService;
import vn.elca.training.util.ApplicationMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author vlp
 */
@Service("projectServiceImpl")
@Primary
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final EmployeeService employeeService;
    private final ApplicationMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeService employeeService, ApplicationMapper mapper) {
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public long count() {
        return projectRepository.count();
    }

    @Override
    public ProjectDto findOne(Long id) {
        Project project = projectRepository.findProjectById(id);
        if (ObjectUtils.isNotEmpty(project)) {
            Set<EmployeeDto> employees = project.getEmployees().stream().map(mapper::employeeToEmployeeDto).collect(Collectors.toSet());

            ProjectDto projectDto = mapper.projectToProjectDto(project);
            projectDto.setEmployees(employees);

            projectDto.setStatus(Constant.PROJECT_STATUS.get(project.getStatus().toString()));
            return projectDto;
        }

        throw new ProjectNotFoundException(id);
    }

    @Override
    public List<ProjectDto> getProjectsByCriteriaOrStatus(String criteria, String status) {
        List<ProjectDto> prjList = projectRepository.getProjectsByCriteriaOrStatus(criteria, status)
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());

        prjList.forEach(prj -> {
            prj.setStatus(Constant.PROJECT_STATUS.get(prj.getStatus()));
        });

        return prjList;
    }

    @Override
    public Project insert(ProjectDto projectDto) throws MemberNotExistException, DuplicateProjectNumberException, EndDateBeforeOrEqualStartDate {
        validateInsertProject(projectDto);
        Project project = mapper.projectDtoToProject(projectDto);

        if (ObjectUtils.isNotEmpty(projectDto.getMembers())) {
            mapMembersToEmployees(projectDto, project);
        }

        return projectRepository.save(project);
    }

    @Override
    public Project update(ProjectDto projectDto) throws MemberNotExistException, EndDateBeforeOrEqualStartDate {
        validateUpdateProject(projectDto);
        Project project = mapper.projectDtoToProject(projectDto);

        if (ObjectUtils.isNotEmpty(projectDto.getMembers())) {
            mapMembersToEmployees(projectDto, project);
        }

        if (ObjectUtils.isEmpty(project.getFinishingDate())) {
            project.setFinishingDate(null);
        }

        return projectRepository.save(project);
    }

    @Override
    public Project findByProjectNumber(Long number) {
        return projectRepository.findProjectByProjectNumber(number);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Long> idList) {
        for (Long id : idList) {
            delete(id);
        }
    }

    private void mapMembersToEmployees(ProjectDto projectDto, Project project) {
        Set<Employee> employees = new HashSet<>();

        String[] members = projectDto.getMembers().split(",");

        for (String member : members) {
            Employee employee = employeeService.findOne(member.trim().toUpperCase());
            employees.add(employee);
        }

        project.setEmployees(employees);
    }

    @Override
    public void validateInsertProject(ProjectDto project) {
        checkDuplicateProjectNumber(project);
        checkMember(project);
        checkEndDate(project.getStartingDate(), project.getFinishingDate());
    }

    @Override
    public void validateUpdateProject(ProjectDto project) {
        checkMember(project);
        checkEndDate(project.getStartingDate(), project.getFinishingDate());
    }

    private String findInvalidMembers(ProjectDto projectDto) {
        if (ObjectUtils.isNotEmpty(projectDto.getMembers())) {
            String[] members = projectDto.getMembers().split(",");
            List<String> invalidMembers = new ArrayList<>();

            for (String member : members) {
                Employee employee = employeeService.findOne(member.trim().toUpperCase());
                if (employee == null) {
                    invalidMembers.add(member.trim());
                }
            }

            return String.join(", ", invalidMembers);
        }
        return null;
    }

    private void checkDuplicateProjectNumber(ProjectDto project) {
        Project resultProject = findByProjectNumber(project.getProjectNumber());

        if (resultProject != null) {
            logger.error(String.format("%s - DuplicateProjectNumberException #%d", LocalTime.now(), project.getProjectNumber()));
            throw new DuplicateProjectNumberException("The project number already existed. Please select a different project number.");
        }
    }

    private void checkMember(ProjectDto project) {
        String invalidMembers = findInvalidMembers(project);
        if (ObjectUtils.isNotEmpty(invalidMembers)) {
            logger.error(String.format("%s - MemberNotExistException #%s", LocalTime.now(), invalidMembers));
            throw new MemberNotExistException(invalidMembers);
        }
    }

    private void checkEndDate(String start, String end) {
        if (ObjectUtils.isNotEmpty(end)) {
            logger.error(String.format("%s - EndDateBeforeOrEqualStartDate %s", LocalTime.now(), end));
            LocalDate startLocal = LocalDate.parse(start);
            LocalDate endLocal = LocalDate.parse(end);
            if (startLocal.compareTo(endLocal) > 0) {
                logger.error("The start date must be before the end date");
                throw new EndDateBeforeOrEqualStartDate(startLocal, endLocal);
            }
        }
    }
}
