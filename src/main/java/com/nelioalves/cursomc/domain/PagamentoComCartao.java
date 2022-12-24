package com.nelioalves.cursomc.domain;


import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;

import javax.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento{

    private Integer numeroDeParcelas;

    public PagamentoComCartao(){}

    public PagamentoComCartao(Integer id, Pedido pedido, EstadoPagamento estadoPagamento, Integer numeroDeParcelas) {
        super(id, pedido, estadoPagamento);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
