package br.com.sampa.sampastore.entity;

import br.com.sampa.sampastore.enums.FormaPagamento;
import br.com.sampa.sampastore.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "endereco_entrega_id")
    private Endereco enderecoEntrega;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido = LocalDateTime.now();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.AGUARDANDO_PAGAMENTO;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private PagamentoCartao pagamentoCartao;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> itens = new ArrayList<>();

}
