package com.arslan.graphqlapp.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CITY", nullable = false, length = 50)
    private String city;

    @Column(name = "COUNTRY", nullable = false, length = 50)
    private String country;

    @Column(name = "COUNTY", nullable = false, length = 50)
    private String county;

    @Column(name = "ZIP_CODE", nullable = false, length = 10)
    private String zipCode;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Merchant merchant;
}
