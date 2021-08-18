package com.dhcho.accesshistory.service;

import com.dhcho.accesshistory.dto.TeamRequest;
import com.dhcho.accesshistory.dto.TeamSearchCond;
import com.dhcho.accesshistory.entity.Team;
import com.dhcho.accesshistory.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public Page<Team> search(TeamSearchCond condition, Pageable pageable) {
        return teamRepository.search(condition, pageable);
    }

    public Team findOne(Long id) {
        return validateTeam(id);
    }

    public Team create(TeamRequest request) {
        validateDuplicateName(request.getName());

        Team createTeam = Team.builder()
                .name(request.getName())
                .parent_id(request.getParent_id())
                .build();
        teamRepository.save(createTeam);

        return createTeam;
    }

    public Team update(Long id, TeamRequest request) {
        Team findTeam = validateTeam(id);

        findTeam.update(
                request.getName(),
                request.getParent_id()
        );
        teamRepository.flush();

        return findTeam;
    }

    public Team delete(Long id) {
        Team deleteTeam = validateTeam(id);
        teamRepository.deleteById(id);
        return deleteTeam;
    }

    private Team validateTeam(Long id) {
        if (id != null) {
            return teamRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 team 입니다."));
        } else {
            return null;
        }
    }

    private void validateDuplicateName(String name) {
        if (name != null) {
            List<Team> findTeams = teamRepository.findByName(name);

            if (!findTeams.isEmpty()) {
                throw new IllegalStateException("이미 존재하는 teamName 입니다.");
            }
        }
    }
}
