package com.dhcho.accesshistory.dto;

import com.dhcho.accesshistory.entity.GenderType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MemberSearchCond {
    private String name;
    private GenderType gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDateTo;
    private String teamName;
}
