package com.dhcho.accesshistory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"team"})
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderType gender;

    @Embedded
    private Birth birth;

    @Column(unique = true)
    private String phone;

    private String address;

    @Column(unique = true)
    private String qrcode;

    @Column(name = "REGISTER_TIME")
    private LocalDateTime registerTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private List<Access> accessList = new ArrayList<>();

    @Builder
    public Member(String name, GenderType gender, Birth birth, String phone, String address, String qrcode, LocalDateTime registerTime, Team team) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.address = address;
        this.qrcode = qrcode;
        this.registerTime = registerTime;
        if (team != null) {
            this.team = team;
        }
    }

    public void update(String name, GenderType gender, Birth birth, String phone, String address, String qrcode, LocalDateTime registerTime, Team team) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.address = address;
        this.qrcode = qrcode;
        this.registerTime = registerTime;
        if (team != null) {
            this.team = team;
        }
    }
}
