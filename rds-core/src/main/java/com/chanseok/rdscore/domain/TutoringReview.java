package com.chanseok.rdscore.domain;

import jakarta.persistence.*;

@Entity
public class TutoringReview {

    @Id
    @Column(name = "tutoring_review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutoring_match_id")
    private TutoringMatch tutoringMatch;

    private Integer tutoringScore;
    private String tutoringReviewContent;
}
