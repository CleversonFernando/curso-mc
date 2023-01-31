package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;

    public List<Cidade> findByEstado(Integer estadoId){
        return repo.findCidades(estadoId);
    }
}
