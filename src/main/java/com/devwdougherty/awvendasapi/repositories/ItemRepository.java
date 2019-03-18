package com.devwdougherty.awvendasapi.repositories;

import com.devwdougherty.awvendasapi.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
