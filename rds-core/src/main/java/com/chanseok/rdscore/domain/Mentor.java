package com.chanseok.rdscore.domain;

import com.chanseok.rdscore.domain.type.CertificateStatus;
import com.chanseok.rdscore.domain.type.GraduationStatus;
import jakarta.persistence.*;

@Entity
public class Mentor {

    @Id
    @Column(name = "mentor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String subject;
    private String school;
    private GraduationStatus graduationStatus;

    @Enumerated(EnumType.STRING)
    private CertificateStatus certificateStatus;
    private String possibleLocation;
    private Integer tutoringAmount;
    private String tutoringStyle;
    private String appeal;
    private String profilePicture;
    private String degreeCertificatePicture;
}
