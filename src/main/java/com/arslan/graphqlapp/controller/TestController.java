package com.arslan.graphqlapp.controller;

import com.arslan.graphqlapp.model.Contact;
import com.arslan.graphqlapp.model.Merchant;
import com.arslan.graphqlapp.model.enums.CompanyType;
import com.arslan.graphqlapp.repository.MerchantRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/merchants")
@RequiredArgsConstructor
public class TestController {

    private final MerchantRepository merchantRepository;

    @GetMapping
    public List<MerchantDTO> getMerchants() {
        return mapTo(merchantRepository.findAll());
    }

    private List<MerchantDTO> mapTo(List<Merchant> merchants) {
        List<MerchantDTO> merchantDTOS = new ArrayList<>();
        merchants.forEach(item -> {
            merchantDTOS.add(
                    MerchantDTO.builder()
                            .id(item.getId())
                            .name(item.getName())
                            .surname(item.getSurname())
                            .taxNumber(item.getTaxNumber())
                            .identityNumber(item.getIdentityNumber())
                            .companyType(item.getCompanyType())
                            .addressDTO(
                                    AddressDTO.builder()
                                            .country(item.getAddress().getCountry())
                                            .county(item.getAddress().getCounty())
                                            .city(item.getAddress().getCity())
                                            .zipCode(item.getAddress().getZipCode())
                                            .build()
                            )
                            .contactDTOS(
                                    mapToContact(item.getContacts())
                            ).build()
            );
        });
        return merchantDTOS;
    }

    private List<ContactDTO> mapToContact(List<Contact> contacts) {
        List<ContactDTO> contactDTOS = new ArrayList<>();
        contacts.forEach(item -> {
            contactDTOS.add(
                    ContactDTO.builder()
                            .name(item.getName())
                            .surname(item.getSurname())
                            .email(item.getEmail())
                            .phone(item.getPhone())
                            .build()
            );
        });
        return contactDTOS;
    }


    @Builder
    @Getter
    @Setter
    public static class MerchantDTO {
        private Integer id;
        private String name;
        private String surname;
        private String taxNumber;
        private String identityNumber;
        private CompanyType companyType;

        private AddressDTO addressDTO;
        private List<ContactDTO> contactDTOS;
    }

    @Builder
    @Getter
    @Setter
    public static class AddressDTO {
        private String country;
        private String county;
        private String city;
        private String zipCode;

    }

    @Builder
    @Getter
    @Setter
    public static class ContactDTO {
        private String name;
        private String surname;
        private String email;
        private String phone;
    }
}
