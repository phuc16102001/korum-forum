package vn.elca.training.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupDto {
    private Long id;
    private Long version;
    private EmployeeDto leader;
}
