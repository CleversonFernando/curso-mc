package com.nelioalves.cursomc.services;

import com.amazonaws.services.sns.model.AuthorizationErrorException;
import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.DTO.ClienteDTO;
import com.nelioalves.cursomc.domain.DTO.ClienteNewDTO;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.security.UserSS;
import com.nelioalves.cursomc.services.exception.DataIntegrityException;
import com.nelioalves.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private S3Service s3Service;

    public  Cliente find(Integer id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "  + id + ", tipo " + Cliente.class.getName()));
    }
    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
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
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null ,null, null);
    }
    public Cliente fromDTO(ClienteNewDTO objDTO){
        Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj() , TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()));
        Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDTO.getLogradouro(),objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDTO.getTelefone1());
        if (objDTO.getTelefone2()!=null){
            cli.getTelefones().add(objDTO.getTelefone2());
        }if (objDTO.getTelefone3()!=null){
            cli.getTelefones().add(objDTO.getTelefone3());
        }
        return cli;
    }
    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
    public URI uploadProfilePicture(MultipartFile multipartFile){
        UserSS user = UserService.authenticated();
        if (user == null){
            throw new AuthorizationErrorException("Acesso negado");
        }
        URI uri = s3Service.uploadFile(multipartFile);

        Cliente cli = find(user.getId());
        cli.setImageUrl(uri.toString());
        repo.save(cli);

        return uri;
    }
}

