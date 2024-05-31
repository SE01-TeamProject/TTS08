package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

@Entity
@Getter
@Setter
@Table(name = "userAssign", uniqueConstraints = {@UniqueConstraint(columnNames= {"pid", "uid"})})
@NoArgsConstructor
public class UserAssignProj {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "pid")
    private Integer pid;
    @Column(name="uid")
    private Integer uid;
    @Column(name="userlevel")
    private int level;

    @Builder
    public UserAssignProj(Integer pid, Integer uid, int level) {
        this.pid = pid;
        this.uid = uid;
        this.level = level;
    }
    public static UserAssignProj createUserAssignProj(Integer pid, Integer uid, int level) {
        return new UserAssignProj(pid, uid, level);
    }
}
