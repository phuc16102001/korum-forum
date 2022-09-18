package vn.elca.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.model.entity.Group;
import vn.elca.training.repository.GroupRepository;
import vn.elca.training.service.GroupService;
import vn.elca.training.util.ApplicationMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author vlp
 */
@Service("groupServiceImpl")
@Primary
@Transactional
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final ApplicationMapper mapper;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, ApplicationMapper mapper) {
        this.groupRepository = groupRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public long count() {
        return groupRepository.count();
    }

    @Override
    public GroupDto findOne(Long id) {
        List<GroupDto> list = groupRepository.findAll()
                .stream()
                .map(mapper::groupToGroupDto)
                .filter(s -> Objects.equals(s.getId(), id))
                .collect(Collectors.toList());

        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
