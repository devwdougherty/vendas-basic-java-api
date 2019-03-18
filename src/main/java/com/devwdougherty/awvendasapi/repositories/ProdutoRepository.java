package com.devwdougherty.awvendasapi.repositories;

import com.devwdougherty.awvendasapi.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {

}
