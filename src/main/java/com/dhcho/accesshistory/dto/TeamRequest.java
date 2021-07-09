package com.dhcho.accesshistory.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TeamRequest {
    @NotEmpty
    private String name;
    private Long parent_id;
}
