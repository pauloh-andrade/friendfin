package com.friendfin.controller;

import com.friendfin.dto.autoridade.AtualizacaoAutoridadeDto;
import com.friendfin.dto.autoridade.CadastroAutoridadeDto;
import com.friendfin.dto.autoridade.ResponseAutoridadeDto;
import com.friendfin.model.Autoridade;
import com.friendfin.repository.AutoridadeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("autoridades")
public class AutoridadeController {
    @Autowired
    private AutoridadeRepository autoridadeRepository;

    @GetMapping
    public ResponseEntity<List<ResponseAutoridadeDto>> listarTodasAutoridades() {
        List<Autoridade> autoridades = autoridadeRepository.findAll();
        List<ResponseAutoridadeDto> responseAutoridades = new ArrayList<>();

        for (Autoridade autoridade : autoridades) {
            responseAutoridades.add(new ResponseAutoridadeDto(autoridade));
        }

        return ResponseEntity.ok(responseAutoridades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAutoridadeDto> buscarAutoridadePorId(@PathVariable int id) {
        Optional<Autoridade> optionalAutoridade = autoridadeRepository.findById(id);

        if (optionalAutoridade.isPresent()) {
            Autoridade autoridade = optionalAutoridade.get();
            return ResponseEntity.ok(new ResponseAutoridadeDto(autoridade));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseAutoridadeDto> cadastrarAutoridade(@RequestBody @Valid CadastroAutoridadeDto cadastroAutoridadeDto) {
        Autoridade autoridade = new Autoridade(cadastroAutoridadeDto);

        autoridadeRepository.save(autoridade);

        return ResponseEntity.ok(new ResponseAutoridadeDto(autoridade));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletarAutoridade(@PathVariable("id") int id){
        autoridadeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseAutoridadeDto> atualizarAutoridade(@PathVariable("id")int id,
                                                                     @RequestBody @Valid AtualizacaoAutoridadeDto atualizacaoAutoridadeDto){
        Autoridade autoridade = autoridadeRepository.findById(id).orElse(null);
        if (autoridade == null) {
            return ResponseEntity.notFound().build();
        }
        autoridade.atualizarInformacoes(atualizacaoAutoridadeDto);
        autoridadeRepository.save(autoridade);
        return ResponseEntity.ok(new ResponseAutoridadeDto(autoridade));
    }
}
