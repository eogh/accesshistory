package com.dhcho.accesshistory.dto;

import com.dhcho.accesshistory.entity.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamResponse {
    private Long id;
    private String name;
    private Long parent_id;

    public TeamResponse(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.parent_id = team.getParent_id();
    }
}
