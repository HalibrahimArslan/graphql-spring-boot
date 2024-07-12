package com.arslan.graphqlapp.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "SURNAME", nullable = false, length = 50)
    private String surname;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "PHONE", nullable = false, length = 15)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "ID")
    private Merchant merchant;

}