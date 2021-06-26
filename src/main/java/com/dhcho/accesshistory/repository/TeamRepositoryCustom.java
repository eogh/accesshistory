package com.dhcho.accesshistory.repository;

import com.dhcho.accesshistory.dto.TeamDto;
import com.dhcho.accesshistory.dto.TeamSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamRepositoryCustom {
    Page<TeamDto> search(TeamSearchCond condition, Pageable pageable);
}
