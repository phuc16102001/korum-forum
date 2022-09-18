package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.elca.training.service.ProjectService;

import java.util.List;

/**
 * @author vlp
 */
@RestController
public class MainController extends AbstractApplicationController {
    private final ProjectService projectService;

    @Autowired
    public MainController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Value("${application.title}")
    private String title;

    @Value("${application.message}")
    private String message;


    @GetMapping("/main")
    public String main() {
        return title + ". " + String.format(message, projectService.count());
    }
}
