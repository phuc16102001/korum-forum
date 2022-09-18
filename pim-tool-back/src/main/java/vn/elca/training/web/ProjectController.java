package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.model.constant.Constant;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.service.ProjectService;
import vn.elca.training.util.ApplicationMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gtn
 */
@RestController
@RequestMapping("/projects")
public class ProjectController extends AbstractApplicationController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService, ApplicationMapper mapper) {
        this.projectService = projectService;
        this.mapper = mapper;
    }

    @GetMapping("/status")
    public List<String> getProjectStatus() {
        return new ArrayList<>(Constant.PROJECT_STATUS.values());
    }

    @GetMapping("/search")
    public List<ProjectDto> getALlProjectsByCriteria(@RequestParam String criteria, @RequestParam String status) {
        return projectService.getProjectsByCriteriaOrStatus(criteria, status);
    }

    @GetMapping("/detail/{id}")
    public ProjectDto detail(@PathVariable Long id) {
        return projectService.findOne(id);
    }

    @PostMapping("/create")
    public Project create(@RequestBody ProjectDto projectDto) {
        return projectService.insert(projectDto);
    }

    @PostMapping("/update")
    public Project update(@RequestBody ProjectDto projectDto) {
        return projectService.update(projectDto);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }

    @PostMapping("/multiple-delete")
    public void multipleDelete(@RequestBody List<Long> idList) {
        projectService.deleteAll(idList);
    }
}
