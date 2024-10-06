package com.friendfin.controller;

import com.friendfin.dto.denuncia.AtualizacaoDenunciaDto;
import com.friendfin.dto.denuncia.CadastroDenunciaDto;
import com.friendfin.dto.denuncia.ResponseDenunciaDto;
import com.friendfin.model.Denuncia;
import com.friendfin.repository.DenunciaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("denuncias")
public class DenunciaController {
    @Autowired
    private DenunciaRepository denunciaRepository;

    @GetMapping
    public ResponseEntity<List<ResponseDenunciaDto>> listarTodasDenuncias() {
        List<Denuncia> denuncias = denunciaRepository.findAll();
        List<ResponseDenunciaDto> responseDenuncias = new ArrayList<>();

        for (Denuncia denuncia : denuncias) {
            responseDenuncias.add(new ResponseDenunciaDto(denuncia));
        }

        return ResponseEntity.ok(responseDenuncias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDenunciaDto> buscarDenunciaPorId(@PathVariable int id) {
        Optional<Denuncia> optionalDenuncia = denunciaRepository.findById(id);

        if (optionalDenuncia.isPresent()) {
            Denuncia denuncia = optionalDenuncia.get();
            return ResponseEntity.ok(new ResponseDenunciaDto(denuncia));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseDenunciaDto> cadastrarDenuncia(@RequestBody @Valid CadastroDenunciaDto cadastroDenunciaDto) {
        Denuncia denuncia = new Denuncia(cadastroDenunciaDto);

        denunciaRepository.save(denuncia);

        return ResponseEntity.ok(new ResponseDenunciaDto(denuncia));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletarDenuncia(@PathVariable("id") int id){
        denunciaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseDenunciaDto> atualizarDenuncia(@PathVariable("id") int id, @RequestBody @Valid AtualizacaoDenunciaDto atualizacaoDenunciaDto){
        Optional<Denuncia> optionalDenuncia = denunciaRepository.findById(id);

        if (optionalDenuncia.isPresent()) {
            Denuncia denuncia = optionalDenuncia.get();
            denuncia.atualizarInformacoes(atualizacaoDenunciaDto);
            return ResponseEntity.ok(new ResponseDenunciaDto(denuncia));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
