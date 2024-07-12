package com.arslan.graphqlapp.repository;

import com.arslan.graphqlapp.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
}
