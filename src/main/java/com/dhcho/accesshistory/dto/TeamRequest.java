package com.dhcho.accesshistory.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TeamRequest {
    @NotEmpty
    private String name;
}
