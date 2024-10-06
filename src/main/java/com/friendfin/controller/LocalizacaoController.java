package com.friendfin.controller;

import com.friendfin.dto.localizacao.AtualizacaoLocalizacaoDto;
import com.friendfin.dto.localizacao.CadastroLocalizacaoDto;
import com.friendfin.dto.localizacao.ResponseLocalizacaoDto;
import com.friendfin.model.Localizacao;
import com.friendfin.repository.LocalizacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("localizacoes")
public class LocalizacaoController {
    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @GetMapping
    public ResponseEntity<List<ResponseLocalizacaoDto>> listarTodasLocalizacoes() {
        List<Localizacao> localizacoes = localizacaoRepository.findAll();
        List<ResponseLocalizacaoDto> responseLocalizacoes = new ArrayList<>();

        for (Localizacao localizacao : localizacoes) {
            responseLocalizacoes.add(new ResponseLocalizacaoDto(localizacao));
        }

        return ResponseEntity.ok(responseLocalizacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseLocalizacaoDto> buscarLocalizacaoPorId(@PathVariable int id) {
        Optional<Localizacao> optionalLocalizacao = localizacaoRepository.findById(id);

        if (optionalLocalizacao.isPresent()) {
            Localizacao localizacao = optionalLocalizacao.get();
            return ResponseEntity.ok(new ResponseLocalizacaoDto(localizacao));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseLocalizacaoDto> cadastrarLocalizacao(@RequestBody @Valid CadastroLocalizacaoDto cadastroLocalizacaoDto) {
        Localizacao localizacao = new Localizacao(cadastroLocalizacaoDto);

        localizacaoRepository.save(localizacao);

        return ResponseEntity.ok(new ResponseLocalizacaoDto(localizacao));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletarLocalizacao(@PathVariable("id") int id){
        localizacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseLocalizacaoDto> atualizarLocalizacao(@PathVariable("id") int id, @RequestBody @Valid AtualizacaoLocalizacaoDto atualizacaoLocalizacaoDto){
        Optional<Localizacao> optionalLocalizacao = localizacaoRepository.findById(id);

        if (optionalLocalizacao.isPresent()) {
            Localizacao localizacao = optionalLocalizacao.get();
            localizacao.atualizarInformacoes(atualizacaoLocalizacaoDto);
            return ResponseEntity.ok(new ResponseLocalizacaoDto(localizacao));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
