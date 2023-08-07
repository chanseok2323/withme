package com.chanseok.rdscore.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String email;

    private String nickname;

    private String provider;

}
