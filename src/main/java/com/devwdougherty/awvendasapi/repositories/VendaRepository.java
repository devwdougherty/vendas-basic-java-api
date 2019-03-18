package com.devwdougherty.awvendasapi.repositories;

import com.devwdougherty.awvendasapi.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
