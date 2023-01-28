package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.DTO.CategoriaDTO;
import com.nelioalves.cursomc.domain.DTO.ClienteDTO;
import com.nelioalves.cursomc.domain.DTO.ClienteNewDTO;
import com.nelioalves.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService service;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)

    private ResponseEntity<Cliente> find(@PathVariable Integer id){
        Cliente obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid  @RequestBody ClienteNewDTO objDTO) {
        Cliente obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid  @RequestBody ClienteDTO objDTO, @PathVariable Integer id) {
        Cliente obj = service.fromDTO(objDTO);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> lista = service.findAll();
        List<ClienteDTO> listDTO = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction) {
        Page<Cliente> lista = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listDTO = lista.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }
    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
        URI uri = service.uploadProfilePicture(file);
        return ResponseEntity.created(uri).build();
}
}

