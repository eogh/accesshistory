package com.dhcho.accesshistory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "LOCATION_ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String desc;

    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private List<Access> accessList = new ArrayList<>();
}
