package com.nelioalves.cursomc.domain.DTO;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteUpdate
public class ClienteDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80")
    private String nome;
    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Email(message = "Email inválido!")
    private String Email;

    public ClienteDTO(Cliente obj){
        id = obj.getId();
        nome = obj.getNome();
        Email = obj.getEmail();
    }
    public ClienteDTO(){}

    public ClienteDTO(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        Email = email;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
