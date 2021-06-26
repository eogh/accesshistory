package com.dhcho.accesshistory.repository;

import com.dhcho.accesshistory.dto.QTeamDto;
import com.dhcho.accesshistory.dto.TeamDto;
import com.dhcho.accesshistory.dto.TeamSearchCond;
import com.dhcho.accesshistory.entity.Team;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.dhcho.accesshistory.entity.QTeam.team;

@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepositoryCustom {

    public final JPAQueryFactory queryFactory;

    @Override
    public Page<TeamDto> search(TeamSearchCond condition, Pageable pageable) {

        List<TeamDto> content = queryFactory
                .select(new QTeamDto(
                        team.id,
                        team.name
                ))
                .from(team)
                .where(
                        teamNameEq(condition.getName())
                )
                .fetch();

        JPAQuery<Team> countQuery = queryFactory
                .selectFrom(team)
                .where(
                        teamNameEq(condition.getName())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount); // 페이지크기 < content크기 작거나, 마지막 페이지 호출시 count쿼리 생략함. (최적화);;
    }

    private BooleanExpression teamNameEq(String teamName) {
        return StringUtils.hasText(teamName) ? team.name.eq(teamName) : null;
    }
}
