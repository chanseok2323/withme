package com.chanseok.rdscore.domain;

import jakarta.persistence.*;

@Entity
public class TutoringMatch {

    @Id
    @Column(name = "tutoring_match_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;
    private String tutoringStartAt;
    private Integer tutoringAmount;
}
