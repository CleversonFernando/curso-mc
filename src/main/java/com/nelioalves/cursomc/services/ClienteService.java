package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public  Cliente buscar(Integer id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "  + id + ", tipo " + Cliente.class.getName()));
    }
}
