package com.friendfin.controller;

import com.friendfin.dto.email.AtualizacaoEmailDto;
import com.friendfin.dto.email.CadastroEmailDto;
import com.friendfin.dto.email.ResponseEmailDto;
import com.friendfin.model.Email;
import com.friendfin.repository.EmailRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("emails")
public class EmailController {
    @Autowired
    private EmailRepository emailRepository;

    @GetMapping
    public ResponseEntity<List<ResponseEmailDto>> listarTodosEmails() {
        List<Email> emails = emailRepository.findAll();
        List<ResponseEmailDto> responseEmails = new ArrayList<>();

        for (Email email : emails) {
            responseEmails.add(new ResponseEmailDto(email));
        }

        return ResponseEntity.ok(responseEmails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEmailDto> buscarEmailPorId(@PathVariable int id) {
        Optional<Email> optionalEmail = emailRepository.findById(id);

        if (optionalEmail.isPresent()) {
            Email email = optionalEmail.get();
            return ResponseEntity.ok(new ResponseEmailDto(email));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseEmailDto> cadastrarEmail(@RequestBody @Valid CadastroEmailDto cadastroEmailDto) {
        Email email = new Email(cadastroEmailDto);

        emailRepository.save(email);

        return ResponseEntity.ok(new ResponseEmailDto(email));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletarEmail(@PathVariable("id") int id){
        emailRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseEmailDto> atualizarEmail(@PathVariable("id") int id, @RequestBody @Valid AtualizacaoEmailDto atualizacaoEmailDto){
        Optional<Email> optionalEmail = emailRepository.findById(id);

        if (optionalEmail.isPresent()) {
            Email email = optionalEmail.get();
            email.atualizarInformacoes(atualizacaoEmailDto);
            return ResponseEntity.ok(new ResponseEmailDto(email));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
