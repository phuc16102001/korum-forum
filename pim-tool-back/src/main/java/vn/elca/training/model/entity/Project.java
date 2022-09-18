package vn.elca.training.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author vlp
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@BatchSize(size = 5)
@ToString
public class Project extends PimEntity {
    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Group group;

    @Column(nullable = false, columnDefinition = "NUMBER(4,0)")
    private Long projectNumber;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String customer;

    @Column(nullable = false, columnDefinition = "CHAR(3)")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startingDate;
    @Column(name = "END_DATE")
    private LocalDate finishingDate;

    @ManyToMany
    @JoinTable(name = "PROJECT_EMPLOYEE", joinColumns = @JoinColumn(name = "PROJECT_ID"), inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
    @ToString.Exclude
    private Set<Employee> employees;

    public enum Status {
        NEW, INP, PLA, FIN;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Project(Project prj) {
        super();
        this.group = prj.getGroup();
        this.projectNumber = prj.getProjectNumber();
        this.name = prj.getName();
        this.customer = prj.getCustomer();
        this.status = prj.getStatus();
        this.startingDate = prj.getStartingDate();
        this.finishingDate = prj.getFinishingDate();
        this.employees = prj.getEmployees();
    }
}
