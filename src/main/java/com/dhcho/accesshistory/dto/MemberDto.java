package com.dhcho.accesshistory.dto;

import com.dhcho.accesshistory.entity.Birth;
import com.dhcho.accesshistory.entity.GenderType;
import com.dhcho.accesshistory.entity.Member;
import com.dhcho.accesshistory.entity.Team;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private GenderType gender;
    private Birth birth;
    private String phone;
    private String address;
    private String qrcode;
    private LocalDateTime registerTime;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private TeamDto team;

    @QueryProjection
    public MemberDto(Long id, String name, GenderType gender, Birth birth, String phone, String address, String qrcode, LocalDateTime registerTime, LocalDateTime createTime, LocalDateTime modifyTime, Team team) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.address = address;
        this.qrcode = qrcode;
        this.registerTime = registerTime;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        if (team != null) {
            this.team = new TeamDto(team);
        }
    }

    public MemberDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.gender = member.getGender();
        this.birth = member.getBirth();
        this.phone = member.getPhone();
        this.address = member.getAddress();
        this.qrcode = member.getQrcode();
        this.registerTime = member.getRegisterTime();
        this.createTime = member.getCreateTime();
        this.modifyTime = member.getModifyTime();
        if (member.getTeam() != null) {
            this.team = new TeamDto(member.getTeam());
        }
    }
}
