package com.devwdougherty.awvendasapi.services;

import com.devwdougherty.awvendasapi.entities.Item;
import com.devwdougherty.awvendasapi.entities.Produto;
import com.devwdougherty.awvendasapi.entities.Venda;
import com.devwdougherty.awvendasapi.repositories.ProdutoRepository;
import com.devwdougherty.awvendasapi.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 */
@Service
public class VendasService {

    /**
     *
     */
    @Autowired
    private VendaRepository vendaRepository;

    /**
     *
     */
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     *
     * @param venda
     * @return
     */
    public Venda addVenda(Venda venda) {

        venda.setDataCadastro(LocalDateTime.now());

        venda.getItemList().forEach(i -> {
            i.setVenda(venda);
            i.setProduto(produtoRepository.findById(i.getProduto().getId()).get());
        });

        BigDecimal totalItens = venda.getItemList().stream()
                .map(i -> i.getProduto().getValor().multiply(new BigDecimal(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        venda.setValorTotal(totalItens.add(venda.getFrete()));

        return vendaRepository.save(venda);
    }

}
