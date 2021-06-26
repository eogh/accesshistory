package com.dhcho.accesshistory.dto;

import com.dhcho.accesshistory.entity.Birth;
import com.dhcho.accesshistory.entity.GenderType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class MemberRequest {
    @NotEmpty
    private String name;
    @NotNull
    private GenderType gender;
    private Birth birth;
    private String phone;
    private String address;
    private String qrcode;
    private LocalDateTime registerTime;
    private Long teamId;
}
