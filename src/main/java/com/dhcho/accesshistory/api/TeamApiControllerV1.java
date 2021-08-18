package com.dhcho.accesshistory.api;

import com.dhcho.accesshistory.dto.TeamRequest;
import com.dhcho.accesshistory.dto.TeamResponse;
import com.dhcho.accesshistory.dto.TeamSearchCond;
import com.dhcho.accesshistory.entity.Team;
import com.dhcho.accesshistory.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/team")
public class TeamApiControllerV1 {

    private final TeamService teamService;

    @GetMapping
    public Page<Team> findTeams(@Valid TeamSearchCond condition, Pageable pageable) {
        return teamService.search(condition, pageable);
    }

    @GetMapping("/{id}")
    public TeamResponse findOne(@PathVariable("id") Long id) {
        Team findTeam = teamService.findOne(id);
        return new TeamResponse(findTeam);
    }

    @PostMapping
    public TeamResponse create(@RequestBody @Valid TeamRequest request) {
        Team createTeam = teamService.create(request);
        return new TeamResponse(createTeam);
    }

    @PutMapping("/{id}")
    public TeamResponse update(@PathVariable("id") Long id,
                               @RequestBody @Valid TeamRequest request) {
        Team updateTeam = teamService.update(id, request);
        return new TeamResponse(updateTeam);
    }

    @DeleteMapping("/{id}")
    public TeamResponse delete(@PathVariable("id") Long id) {
        Team deleteTeam = teamService.delete(id);
        return new TeamResponse(deleteTeam);
    }
}
