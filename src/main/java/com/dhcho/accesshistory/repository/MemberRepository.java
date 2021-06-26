package com.dhcho.accesshistory.repository;

import com.dhcho.accesshistory.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    List<Member> findByQrcode(String qrcode);
}
