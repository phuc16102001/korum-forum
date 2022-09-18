package vn.elca.training.model.constant;

import vn.elca.training.model.entity.Project;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constant {
    public static final Map<String, String> PROJECT_STATUS = Stream.of(new String[][]{
            {Project.Status.NEW.toString(), "New"},
            {Project.Status.PLA.toString(), "Planned"},
            {Project.Status.INP.toString(), "In progress"},
            {Project.Status.FIN.toString(), "Finished"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public static final Map<String, String> PROJECT_STATUS_CODE = Stream.of(new String[][]{
            {"New", Project.Status.NEW.toString()},
            {"Planned", Project.Status.PLA.toString()},
            {"In progress", Project.Status.INP.toString()},
            {"Finished", Project.Status.FIN.toString()}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
