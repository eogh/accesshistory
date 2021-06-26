package com.dhcho.accesshistory.dto;

import com.dhcho.accesshistory.entity.Team;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamDto {
    private Long id;
    private String name;

    @QueryProjection
    public TeamDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TeamDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
    }
}