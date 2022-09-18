package vn.elca.training.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "PIM_GROUP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group extends PimEntity {
    @OneToOne
    @JoinColumn(name = "GROUP_LEADER_ID")
    private Employee leader;
}
