package br.com.sampa.sampastore.service;

import br.com.sampa.sampastore.entity.Carrinho;

import java.math.BigDecimal;

public interface CarrinhoService {
    Carrinho adicionarItem(Carrinho carrinho, Long produtoId, int quantidade);
    Carrinho atualizarQuantidade(Carrinho carrinho, Long produtoId, int quantidade);
    Carrinho removerItem(Carrinho carrinho, Long produtoId);
    BigDecimal calcularTotal(Carrinho carrinho);
    int contarItens(Carrinho carrinho);
}
