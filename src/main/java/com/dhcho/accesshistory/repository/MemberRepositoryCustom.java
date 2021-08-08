package com.dhcho.accesshistory.repository;

import com.dhcho.accesshistory.dto.MemberSearchCond;
import com.dhcho.accesshistory.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {
    Page<Member> search(MemberSearchCond condition, Pageable pageable);
}
