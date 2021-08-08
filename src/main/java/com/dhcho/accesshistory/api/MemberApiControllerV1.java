package com.dhcho.accesshistory.api;

import com.dhcho.accesshistory.dto.MemberRequest;
import com.dhcho.accesshistory.dto.MemberResponse;
import com.dhcho.accesshistory.dto.MemberSearchCond;
import com.dhcho.accesshistory.entity.Member;
import com.dhcho.accesshistory.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/member")
public class MemberApiControllerV1 {

    private final MemberService memberService;

    @GetMapping
    public Page<Member> findMembers(@Valid MemberSearchCond condition, Pageable pageable) {
        return memberService.findMembers(condition, pageable);
    }

    @GetMapping("/{id}")
    public MemberResponse findOne(@PathVariable("id") Long id) {
        Member findMember = memberService.findOne(id);
        return new MemberResponse(findMember);
    }

    @PostMapping
    public MemberResponse join(@RequestBody @Valid MemberRequest request) {
        Member joinMember = memberService.join(request);
        return new MemberResponse(joinMember);
    }

    @PutMapping("/{id}")
    public MemberResponse update(@PathVariable("id") Long id,
                         @RequestBody @Valid MemberRequest request) {
        Member updateMember = memberService.update(id, request);
        return new MemberResponse(updateMember);
    }

    @DeleteMapping("/{id}")
    public MemberResponse delete(@PathVariable("id") Long id) {
        Member deleteMember = memberService.delete(id);
        return new MemberResponse(deleteMember);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
