package com.dhcho.accesshistory.repository;

import com.dhcho.accesshistory.dto.MemberDto;
import com.dhcho.accesshistory.dto.MemberSearchCond;
import com.dhcho.accesshistory.dto.QMemberDto;
import com.dhcho.accesshistory.entity.GenderType;
import com.dhcho.accesshistory.entity.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

import static com.dhcho.accesshistory.entity.QMember.member;
import static com.dhcho.accesshistory.entity.QTeam.team;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    public final JPAQueryFactory queryFactory;

    @Override
    public Page<MemberDto> search(MemberSearchCond condition, Pageable pageable) {
        List<MemberDto> content = queryFactory
                .select(new QMemberDto(
                        member.id,
                        member.name,
                        member.gender,
                        member.birth,
                        member.phone,
                        member.address,
                        member.qrcode,
                        member.registerTime,
                        member.createTime,
                        member.modifyTime,
                        member.team
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        memberNameEq(condition.getName()),
                        teamNameEq(condition.getTeamName()),
                        memberGenderEq(condition.getGender()),
                        birthDateBetween(condition.getBirthDateFrom(), condition.getBirthDateTo())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch(); // fetchResults는 count용 Qeury도 같이 날리면서 페이징 정보를 가져온다.

        JPAQuery<Member> countQuery = queryFactory
                .selectFrom(member)
                .leftJoin(member.team, team)
                .where(
                        memberNameEq(condition.getName()),
                        teamNameEq(condition.getTeamName()),
                        memberGenderEq(condition.getGender()),
                        birthDateBetween(condition.getBirthDateFrom(), condition.getBirthDateTo())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount); // 페이지크기 < content크기 작거나, 마지막 페이지 호출시 count쿼리 생략함. (최적화);
    }

    private BooleanExpression memberNameEq(String name) {
        return StringUtils.hasText(name) ? member.name.eq(name) : null;
    }

    private BooleanExpression teamNameEq(String teamName) {
        return StringUtils.hasText(teamName) ? team.name.eq(teamName) : null;
    }

    private BooleanExpression memberGenderEq(GenderType gender) {
        return gender != null ? member.gender.eq(gender) : null;
    }

    private BooleanExpression birthDateBetween(LocalDate birthDateFrom, LocalDate birthDateTo) {
        return (birthDateFrom != null && birthDateTo != null) ? member.birth.date.between(birthDateFrom, birthDateTo) : null;
    }
}
