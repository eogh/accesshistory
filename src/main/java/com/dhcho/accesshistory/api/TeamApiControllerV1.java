package com.dhcho.accesshistory.api;

import com.dhcho.accesshistory.dto.TeamDto;
import com.dhcho.accesshistory.dto.TeamRequest;
import com.dhcho.accesshistory.dto.TeamSearchCond;
import com.dhcho.accesshistory.entity.Team;
import com.dhcho.accesshistory.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/team")
public class TeamApiControllerV1 {

    private final TeamRepository teamRepository;

    @GetMapping("")
    public Page<TeamDto> list(@Valid TeamSearchCond condition, Pageable pageable) {
        return teamRepository.search(condition, pageable);
    }

    @GetMapping("/{id}")
    public TeamDto one(@PathVariable("id") Long id) {

        Team findTeam = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 team 입니다."));

        return new TeamDto(findTeam);
    }

    @PostMapping("")
    public TeamDto create(@RequestBody @Valid TeamRequest request) {

        Team createTeam = Team.builder()
                .name(request.getName())
                .build();

        return new TeamDto(teamRepository.save(createTeam));
    }
}
