package com.example.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author gtn
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends KorumEntity implements Serializable {
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String username;

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String name;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
