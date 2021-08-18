package com.dhcho.accesshistory.repository;

import com.dhcho.accesshistory.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom {
    List<Team> findByName(String name);
}
