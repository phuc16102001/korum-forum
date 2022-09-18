package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.service.GroupService;
import vn.elca.training.util.ApplicationMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gtn
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GroupController extends AbstractApplicationController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService, ApplicationMapper mapper) {
        this.groupService = groupService;
        this.mapper = mapper;
    }

    @GetMapping("/groups")
    public List<Long> getGroups() {
        List<GroupDto> ls = groupService.findAll()
                .stream()
                .map(mapper::groupToGroupDto)
                .collect(Collectors.toList());

        List<Long> idLs = new ArrayList<>(ls.size());
        ls.forEach(
                group -> {
                    idLs.add(group.getId());
                }
        );
        return idLs;
    }
}
