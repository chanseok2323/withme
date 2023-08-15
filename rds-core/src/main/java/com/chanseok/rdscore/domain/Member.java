package com.chanseok.rdscore.domain;

import com.chanseok.rdscore.domain.type.CertificateStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nickname;
    private String occupation;
    private Integer age;
    private String phone;
    private String location;

    @Enumerated(EnumType.STRING)
    private CertificateStatus identificationStatus;
    private String identificationPicture;

    protected Member() {}

    @Builder
    public Member(
            String email,
            String nickname,
            String occupation,
            Integer age,
            String phone,
            String location,
            CertificateStatus identificationStatus,
            String identificationPicture
    ) {
        this.email = email;
        this.nickname = nickname;
        this.occupation = occupation;
        this.age = age;
        this.phone = phone;
        this.location = location;
        this.identificationStatus = identificationStatus;
        this.identificationPicture = identificationPicture;
    }
}
