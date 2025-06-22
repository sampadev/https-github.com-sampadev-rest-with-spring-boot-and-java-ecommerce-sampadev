package br.com.sampa.sampastore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EnderecoViaCepDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;

    @JsonProperty("localidade")
    private String cidade;

    private String uf;
}
