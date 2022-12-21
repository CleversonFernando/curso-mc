package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.DTO.ClienteDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exception.DataIntegrityException;
import com.nelioalves.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public  Cliente find(Integer id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "  + id + ", tipo " + Cliente.class.getName()));
    }
    public Cliente update(Cliente obj) {
       Cliente newObj = find(obj.getId());
       updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        try {
            repo.deleteById(id);
        } catch ( DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Cliente que há entidades relacionadas");
        }
    }
    public List<Cliente> findAll() {
        return repo.findAll();
    }
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }
    public Cliente fromDTO(ClienteDTO objDTO){
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null ,null);
    }
    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}

