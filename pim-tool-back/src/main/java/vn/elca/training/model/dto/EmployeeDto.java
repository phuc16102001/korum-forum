package vn.elca.training.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author gtn
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDto {
    private Long id;
    private String visa;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getVisa(), that.getVisa()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getBirthDate(), that.getBirthDate()) && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVisa(), getFirstName(), getLastName(), getBirthDate(), getVersion());
    }
}
