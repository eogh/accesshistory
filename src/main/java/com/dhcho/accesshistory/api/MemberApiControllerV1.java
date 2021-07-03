package com.dhcho.accesshistory.api;

import com.dhcho.accesshistory.dto.MemberDto;
import com.dhcho.accesshistory.dto.MemberRequest;
import com.dhcho.accesshistory.dto.MemberSearchCond;
import com.dhcho.accesshistory.entity.Member;
import com.dhcho.accesshistory.entity.Team;
import com.dhcho.accesshistory.repository.MemberRepository;
import com.dhcho.accesshistory.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/member")
public class MemberApiControllerV1 {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @GetMapping("")
    public Page<MemberDto> list(@Valid MemberSearchCond condition, Pageable pageable) {
        return memberRepository.search(condition, pageable);
    }

    @GetMapping("/{id}")
    public MemberDto one(@PathVariable("id") Long id) {

        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 member 입니다."));

        return new MemberDto(findMember);
    }

    @PostMapping("")
    public MemberDto create(@RequestBody @Valid MemberRequest request) {
        Team findTeam = null;

        if (request.getTeamId() != null) {
            findTeam = teamRepository.findById(request.getTeamId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Team ID 입니다."));
        }


        if (request.getQrcode() != null) {
            if (!memberRepository.findByQrcode(request.getQrcode()).isEmpty()) {
                throw new IllegalStateException("이미 존재하는 QR코드 입니다.");
            }
        }

        Member createMember = Member.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .phone(request.getPhone())
                .address(request.getAddress())
                .qrcode(request.getQrcode())
                .registerTime(request.getRegisterTime())
                .team(findTeam)
                .build();

        return new MemberDto(memberRepository.save(createMember));
    }

    @PutMapping("/{id}")
    public MemberDto update(@PathVariable("id") Long id,
                            @RequestBody @Valid MemberRequest request) {
        Team findTeam = null;

        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 member 입니다."));

        if (request.getTeamId() != null) {
            findTeam = teamRepository.findById(request.getTeamId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Team ID 입니다."));
        }


        if (request.getQrcode() != null && findMember.getQrcode() != null && !findMember.getQrcode().equals(request.getQrcode())) {
            if (!memberRepository.findByQrcode(request.getQrcode()).isEmpty()) {
                throw new IllegalStateException("이미 존재하는 QR코드 입니다.");
            }
        }

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

        return new MemberDto(findMember);
    }

    @DeleteMapping("/{id}")
    public Result remove(@PathVariable("id") Long id) {

        memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));

        memberRepository.deleteById(id);

        return new Result(id);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
