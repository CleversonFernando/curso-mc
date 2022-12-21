package com.nelioalves.cursomc.domain.DTO;

import com.nelioalves.cursomc.domain.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private Integer id;
    private String name;

    public CategoriaDTO(Categoria obj){
        id = obj.getId();
        name = obj.getNome();
    }

    public CategoriaDTO(){}

    public CategoriaDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
