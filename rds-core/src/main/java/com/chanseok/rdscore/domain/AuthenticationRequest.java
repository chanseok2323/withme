package com.chanseok.rdscore.domain;

import com.chanseok.rdscore.domain.type.AuthenticationRequestType;
import com.chanseok.rdscore.domain.type.CertificateStatus;
import jakarta.persistence.*;

@Entity
public class AuthenticationRequest {

    @Id
    @Column(name = "authentication_request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private AuthenticationRequestType authenticationRequestType;
    private CertificateStatus authenticationRequestStatus;
    private String authenticationRequestDescription;
}
