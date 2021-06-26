package com.dhcho.accesshistory.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Birth {

    @Column(name = "BIRTH_DATE")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "BIRTH_TYPE")
    private BirthType type;
}
