package com.dhcho.accesshistory.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    private String name;
}
