package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.DTO.CategoriaDTO;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
    @Autowired
    private PedidoService service;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity<Pedido> find(@PathVariable Integer id){
        Pedido obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
