package vn.elca.training.util;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.elca.training.model.constant.Constant;
import vn.elca.training.model.dto.EmployeeDto;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.Project;
import vn.elca.training.repository.GroupRepository;

import java.time.LocalDate;

/**
 * @author gtn
 */
@Component
public class ApplicationMapper {
    public ApplicationMapper() {
        // Mapper utility class
    }

    @Autowired
    GroupRepository groupRepository;

    public ProjectDto projectToProjectDto(Project entity) {
        ProjectDto dto = new ProjectDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setProjectNumber(entity.getProjectNumber());
        dto.setCustomer(entity.getCustomer());
        dto.setStartingDate(entity.getStartingDate().toString());
        if (ObjectUtils.isNotEmpty(entity.getFinishingDate())) {
            dto.setFinishingDate(entity.getFinishingDate().toString());
        }
        dto.setGroup(entity.getGroup().getId().toString());
        dto.setStatus(entity.getStatus().toString());
        dto.setVersion(entity.getVersion());

        return dto;
    }

    public Project projectDtoToProject(ProjectDto entity) {
        Project prj = new Project();
        prj.setId(entity.getId());
        prj.setProjectNumber(entity.getProjectNumber());
        prj.setName(entity.getName());
        prj.setCustomer(entity.getCustomer());
        prj.setStartingDate(LocalDate.parse(entity.getStartingDate()));
        if (ObjectUtils.isNotEmpty(entity.getFinishingDate())) {
            prj.setFinishingDate(LocalDate.parse(entity.getFinishingDate()));
        }
        prj.setGroup(groupRepository.getOne(Long.parseLong(entity.getGroup())));
        prj.setStatus(Project.Status.valueOf(Constant.PROJECT_STATUS_CODE.get(entity.getStatus())));
        prj.setVersion(entity.getVersion());

        return prj;
    }


    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setVisa(employee.getVisa());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setVersion(employee.getVersion());
        return dto;
    }

    public GroupDto groupToGroupDto(Group group) {
        GroupDto dto = new GroupDto();
        dto.setId(group.getId());
        dto.setVersion(group.getVersion());
        dto.setLeader(employeeToEmployeeDto(group.getLeader()));
        return dto;
    }
}
