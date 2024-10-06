package com.friendfin.controller;

import com.friendfin.dto.contato.AtualizacaoContatoDto;
import com.friendfin.dto.contato.CadastroContatoDto;
import com.friendfin.dto.contato.ResponseContatoDto;
import com.friendfin.model.Contato;
import com.friendfin.repository.ContatoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("contatos")
public class ContatoController {
    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public ResponseEntity<List<ResponseContatoDto>> listarTodosContatos() {
        List<Contato> contatos = contatoRepository.findAll();
        List<ResponseContatoDto> responseContatos = new ArrayList<>();

        for (Contato contato : contatos) {
            responseContatos.add(new ResponseContatoDto(contato));
        }

        return ResponseEntity.ok(responseContatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContatoDto> buscarContatoPorId(@PathVariable int id) {
        Optional<Contato> optionalContato = contatoRepository.findById(id);

        if (optionalContato.isPresent()) {
            Contato contato = optionalContato.get();
            return ResponseEntity.ok(new ResponseContatoDto(contato));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseContatoDto> cadastrarContato(@RequestBody @Valid CadastroContatoDto cadastroContatoDto) {
        Contato contato = new Contato(cadastroContatoDto);

        contatoRepository.save(contato);

        return ResponseEntity.ok(new ResponseContatoDto(contato));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletarContato(@PathVariable("id") int id){
        contatoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseContatoDto> atualizarContato(@PathVariable("id") int id, @RequestBody @Valid AtualizacaoContatoDto atualizacaoContatoDto){
        Optional<Contato> optionalContato = contatoRepository.findById(id);

        if (optionalContato.isPresent()) {
            Contato contato = optionalContato.get();
            contato.atualizarInformacoes(atualizacaoContatoDto);
            return ResponseEntity.ok(new ResponseContatoDto(contato));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
