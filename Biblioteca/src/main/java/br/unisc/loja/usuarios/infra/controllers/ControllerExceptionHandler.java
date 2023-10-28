package br.unisc.loja.usuarios.infra.controllers;

import br.unisc.loja.usuarios.dto.ExceptionDto;
import br.unisc.loja.usuarios.exception.Excessoes;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Excessoes.BibliotecaDuplicadaException.class)
    public ResponseEntity<?> bibliotecaDuplicada() {
        ExceptionDto dto = new ExceptionDto("Biblioteca já cadastrada.",400);
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(Excessoes.LivroDuplicadoException.class)
    public ResponseEntity<?> livroDuplicado() {
       ExceptionDto dto = new ExceptionDto("Livro já cadastrado.", 400);
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(Excessoes.LivroNotFoundException.class)
    public ResponseEntity<?> livroNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Excessoes.BibliotecaNotFoundException.class)
    public ResponseEntity<?> bibliotecaNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Excessoes.LivroExistsException.class)
    public ResponseEntity<?> livroExists() {
        ExceptionDto dto = new ExceptionDto("O livro já existe nesta biblioteca.", 400);
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(Excessoes.AssociationNotFoundException.class)
    public ResponseEntity<?> associationNotFound() {
        ExceptionDto dto = new ExceptionDto("A associação entre o livro e a biblioteca não existe.", 400);
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(Excessoes.TituloNotFoundException.class)
    public ResponseEntity<?> tituloNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Excessoes.BibliotecaVaziaException.class)
    public ResponseEntity<?> bibliotecaEmpy() {
        ExceptionDto dto = new ExceptionDto("Biblioteca vazia ou não encontrada.", 400);
        return ResponseEntity.badRequest().body(dto);
    }
}

