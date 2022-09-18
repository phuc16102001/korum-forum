package vn.elca.training.service;

import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;

import java.util.List;

/**
 * @author vlp
 */
public interface ProjectService {
    List<Project> findAll();

    long count();

    ProjectDto findOne(Long id);

    Project insert(ProjectDto prj);

    Project update(ProjectDto prj);

    Project findByProjectNumber(Long number);

    List<ProjectDto> getProjectsByCriteriaOrStatus(String criteria, String status);

    void delete(Long id);

    void deleteAll(List<Long> idList);

    void validateInsertProject(ProjectDto project);

    void validateUpdateProject(ProjectDto project);
}
