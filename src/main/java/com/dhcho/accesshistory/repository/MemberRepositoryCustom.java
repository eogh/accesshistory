package com.dhcho.accesshistory.repository;

import com.dhcho.accesshistory.dto.MemberDto;
import com.dhcho.accesshistory.dto.MemberSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {
    Page<MemberDto> search(MemberSearchCond condition, Pageable pageable);
}
