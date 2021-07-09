package com.dhcho.accesshistory.repository;

import com.dhcho.accesshistory.dto.TeamDto;
import com.dhcho.accesshistory.dto.TeamSearchCond;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeamRepositoryCustom {
    List<TeamDto> search(TeamSearchCond condition, Pageable pageable);
}
