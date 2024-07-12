package com.arslan.graphqlapp.controller;
import com.arslan.graphqlapp.model.Address;
import com.arslan.graphqlapp.model.Contact;
import com.arslan.graphqlapp.model.Merchant;
import com.arslan.graphqlapp.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantRepository merchantRepository;
    private Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @MutationMapping
    public Merchant createMerchant(@Argument("merchant") Merchant merchant) {
        logger.info("Merchant Creation" + merchant.getName());
        return merchantRepository.save(merchant);
    }

    @QueryMapping
    public List<Merchant> getMerchants() {
        logger.info("Merchants Get");
        return merchantRepository.findAll();
    }

    @QueryMapping
    public Merchant getMerchantById(@Argument("id") Integer id) {
        return merchantRepository.findById(Long.valueOf(id)).orElseThrow(() -> new RuntimeException("Merhcant not found with id: " + id));
    }

    @SchemaMapping
    public List<Contact> contacts(Merchant merchant) {
        return merchant.getContacts();
    }


    @SchemaMapping
    public Address address(Merchant merchant) {
        return merchant.getAddress();
    }
}
