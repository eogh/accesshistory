package com.dhcho.accesshistory.dto;

import com.dhcho.accesshistory.entity.Team;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamDto {
    private Long id;
    private String name;
    private Long parent_id;

    @QueryProjection
    public TeamDto(Long id, String name, Long parent_id) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
    }

    public TeamDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.parent_id = team.getParent_id();
    }
}