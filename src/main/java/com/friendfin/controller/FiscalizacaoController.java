package com.friendfin.controller;

import com.friendfin.dto.fiscalizacao.AtualizacaoFiscalizacaoDto;
import com.friendfin.dto.fiscalizacao.CadastroFiscalizacaoDto;
import com.friendfin.dto.fiscalizacao.ResponseFiscalizacaoDto;
import com.friendfin.model.Fiscalizacao;
import com.friendfin.repository.FiscalizacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("fiscalizacoes")
public class FiscalizacaoController {
    @Autowired
    private FiscalizacaoRepository fiscalizacaoRepository;

    @GetMapping
    public ResponseEntity<List<ResponseFiscalizacaoDto>> listarTodasFiscalizacoes() {
        List<Fiscalizacao> fiscalizacoes = fiscalizacaoRepository.findAll();
        List<ResponseFiscalizacaoDto> responseFiscalizacoes = new ArrayList<>();

        for (Fiscalizacao fiscalizacao : fiscalizacoes) {
            responseFiscalizacoes.add(new ResponseFiscalizacaoDto(fiscalizacao));
        }

        return ResponseEntity.ok(responseFiscalizacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFiscalizacaoDto> buscarFiscalizacaoPorId(@PathVariable int id) {
        Optional<Fiscalizacao> optionalFiscalizacao = fiscalizacaoRepository.findById(id);

        if (optionalFiscalizacao.isPresent()) {
            Fiscalizacao fiscalizacao = optionalFiscalizacao.get();
            return ResponseEntity.ok(new ResponseFiscalizacaoDto(fiscalizacao));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseFiscalizacaoDto> cadastrarFiscalizacao(@RequestBody @Valid CadastroFiscalizacaoDto cadastroFiscalizacaoDto) {
        Fiscalizacao fiscalizacao = new Fiscalizacao(cadastroFiscalizacaoDto);

        fiscalizacaoRepository.save(fiscalizacao);

        return ResponseEntity.ok(new ResponseFiscalizacaoDto(fiscalizacao));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletarFiscalizacao(@PathVariable("id") int id){
        fiscalizacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseFiscalizacaoDto> atualizarFiscalizacao(@PathVariable("id") int id, @RequestBody @Valid AtualizacaoFiscalizacaoDto atualizacaoFiscalizacaoDto){
        Optional<Fiscalizacao> optionalFiscalizacao = fiscalizacaoRepository.findById(id);

        if (optionalFiscalizacao.isPresent()) {
            Fiscalizacao fiscalizacao = optionalFiscalizacao.get();
            fiscalizacao.atualizarInformacoes(atualizacaoFiscalizacaoDto);
            return ResponseEntity.ok(new ResponseFiscalizacaoDto(fiscalizacao));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
