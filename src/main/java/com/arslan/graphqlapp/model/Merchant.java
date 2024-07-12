package com.arslan.graphqlapp.model;
import com.arslan.graphqlapp.model.enums.CompanyType;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "merchants")
public class Merchant extends BaseEntity{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "SURNAME", nullable = false, length = 50)
    private String surname;

    @Column(name = "TAX_NUMBER", nullable = false, length = 100)
    private String taxNumber;

    @Column(name = "IDENTITY_NUMBER", nullable = false, length = 11)
    private String identityNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "COMPANY_TYPE", nullable = false)
    private CompanyType companyType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID", nullable = false)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Contact> contacts;

    @PrePersist
    void prePersist() {
        this.ipAddress = "11.33.44.55";
        this.createdAt = OffsetDateTime.now();
    }

}
