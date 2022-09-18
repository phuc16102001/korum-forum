package vn.elca.training.repository.custom;

import vn.elca.training.model.entity.Project;

import java.util.List;

public interface ProjectRepositoryCustom {
    Project findProjectById(Long projectId);

    List<Project> getProjectsByCriteriaOrStatus(String criteria, String status);
}
