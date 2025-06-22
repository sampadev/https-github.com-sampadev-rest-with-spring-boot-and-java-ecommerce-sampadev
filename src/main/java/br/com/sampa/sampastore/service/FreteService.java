package br.com.sampa.sampastore.service;

import br.com.sampa.sampastore.entity.Endereco;
import br.com.sampa.sampastore.utils.Coordenadas;

import java.math.BigDecimal;

public interface FreteService {
    BigDecimal calcularFretePorUF(String uf);
    Endereco obterEnderecoPorCep(String cep);
}
