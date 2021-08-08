package com.dhcho.accesshistory.dto;

import com.dhcho.accesshistory.entity.Birth;
import com.dhcho.accesshistory.entity.GenderType;
import com.dhcho.accesshistory.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MemberResponse {
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

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.gender = member.getGender();
        this.birth = member.getBirth();
        this.phone = member.getPhone();
        this.address = member.getQrcode();
        this.qrcode = member.getQrcode();
        this.registerTime = member.getRegisterTime();
        this.createTime = member.getCreateTime();
        this.modifyTime = member.getModifyTime();
    }
}
