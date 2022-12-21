package com.nelioalves.cursomc.domain.DTO;

import javax.validation.constraints.NotEmpty;
import com.nelioalves.cursomc.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80")
    private String nome;

    public CategoriaDTO(Categoria obj){
        id = obj.getId();
        nome = obj.getNome();
    }

    public CategoriaDTO(){}

    public CategoriaDTO(Integer id, String name) {
        this.id = id;
        this.nome = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }
}
