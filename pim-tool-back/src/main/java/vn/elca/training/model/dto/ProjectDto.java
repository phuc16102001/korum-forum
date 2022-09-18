package vn.elca.training.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gtn
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDto {
    private Long id;
    private Long projectNumber;
    private String name;
    private String customer;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startingDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String finishingDate;
    private String group;
    private String members;
    private Set<EmployeeDto> employees = new HashSet<>();
    private Long version;
}
