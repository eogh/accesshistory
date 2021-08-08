package com.dhcho.accesshistory.service;

import com.dhcho.accesshistory.dto.MemberRequest;
import com.dhcho.accesshistory.dto.MemberSearchCond;
import com.dhcho.accesshistory.entity.Member;
import com.dhcho.accesshistory.entity.Team;
import com.dhcho.accesshistory.repository.MemberRepository;
import com.dhcho.accesshistory.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public Page<Member> findMembers(MemberSearchCond condition, Pageable pageable) {
        return memberRepository.search(condition, pageable);
    }

    public Member findOne(Long id) {
        return validateMember(id);
    }

    public Member join(MemberRequest request) {
        Team findTeam = validateTeam(request.getTeamId());
        validateDuplicateQrCode(request.getQrcode());

        Member joinMember = Member.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .phone(request.getPhone())
                .address(request.getAddress())
                .qrcode(request.getQrcode())
                .registerTime(request.getRegisterTime())
                .team(findTeam)
                .build();
        memberRepository.save(joinMember);

        return joinMember;
    }

    public Member update(Long id, MemberRequest request) {
        Member findMember = validateMember(id);
        Team findTeam = validateTeam(request.getTeamId());
        validateDuplicateQrCode(request.getQrcode());

        findMember.update(
                request.getName(),
                request.getGender(),
                request.getBirth(),
                request.getPhone(),
                request.getAddress(),
                request.getQrcode(),
                request.getRegisterTime(),
                findTeam);
        memberRepository.flush();

        return findMember;
    }

    public Member delete(Long id) {
        Member deleteMember = validateMember(id);
        memberRepository.delete(deleteMember);
        return deleteMember;
    }

    private Member validateMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 member 입니다."));
    }

    private Team validateTeam(Long id) {
        if (id != null) {
            return teamRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 team 입니다."));
        } else {
            return null;
        }
    }

    private void validateDuplicateQrCode(String qrcode) {
        if (qrcode != null) {
            List<Member> findMembers = memberRepository.findByQrcode(qrcode);

            if (!findMembers.isEmpty()) {
                throw new IllegalStateException("이미 존재하는 QR코드 입니다.");
            }
        }
    }
}
