package com.nelioalves.cursomc.domain.DTO;

import com.nelioalves.cursomc.domain.Estado;

public class EstadoDTO {

    private Integer id;
    private String nome;

    public EstadoDTO(){}

    public EstadoDTO(Estado obj) {
        id = obj.getId();
        nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }
}
