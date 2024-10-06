package com.friendfin.controller;

import com.friendfin.dto.usuario.AtualizacaoUsuarioDto;
import com.friendfin.dto.usuario.CadastroUsuarioDto;
import com.friendfin.dto.usuario.ResponseUsuarioDto;
import com.friendfin.model.Usuario;
import com.friendfin.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<ResponseUsuarioDto>> listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<ResponseUsuarioDto> responseUsuarios = new ArrayList<>();

        for (Usuario bairro : usuarios) {
            responseUsuarios.add(new ResponseUsuarioDto(bairro));
        }

        return ResponseEntity.ok(responseUsuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUsuarioDto> buscarUsuarioPorId(@PathVariable int id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return ResponseEntity.ok(new ResponseUsuarioDto(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseUsuarioDto> cadastrarUsuario(@RequestBody @Valid CadastroUsuarioDto cadastroUsuarioDto, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = new Usuario(cadastroUsuarioDto);

        usuarioRepository.save(usuario);

        var uri = uriComponentsBuilder.path("usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseUsuarioDto(usuario));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") int id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseUsuarioDto> put(@PathVariable("id")int id,
                                        @RequestBody @Valid AtualizacaoUsuarioDto atualizacaoUsuarioDto){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizarInformacoesUsuario(atualizacaoUsuarioDto);
        return ResponseEntity.ok(new ResponseUsuarioDto(usuario));
    }
}
