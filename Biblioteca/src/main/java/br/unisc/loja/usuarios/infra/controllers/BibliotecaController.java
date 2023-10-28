package br.unisc.loja.usuarios.infra.controllers;

import br.unisc.loja.usuarios.dto.BibliotecaDTO;
import br.unisc.loja.usuarios.dto.BibliotecaLivroDTO;
import br.unisc.loja.usuarios.dto.LivroEncontradoBibliotecaDTO;
import br.unisc.loja.usuarios.service.BibliotecaService;
import br.unisc.loja.usuarios.service.GerenciarBibliotecaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bibliotecas")
@RequiredArgsConstructor
public class BibliotecaController {
    private final BibliotecaService bibliotecaService;
    private final GerenciarBibliotecaService gerenciarService;

    @PostMapping
    public ResponseEntity <?> create (@RequestBody BibliotecaDTO bibliotecaDTO){
        bibliotecaService.create(bibliotecaDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping
    public ResponseEntity <Page<BibliotecaDTO>>buscarBiblioteca(Pageable pageable){
        return ResponseEntity
                .ok(bibliotecaService.buscarBiblioteca(pageable));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteBiblioteca(@PathVariable Integer id){
        bibliotecaService.deleteBiblioteca(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBiblioteca(@RequestBody BibliotecaDTO bibliotecaDTO, @PathVariable Integer id) {
        bibliotecaService.updateBiblioteca(id, bibliotecaDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/adicionarLivroNaBiblioteca")
    public ResponseEntity<?> adicionarLivroNaBiblioteca (@RequestBody BibliotecaLivroDTO dto){
        gerenciarService.adicionarLivroNaBiblioteca(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
    @DeleteMapping("/{bibliotecaId}/livros/{livroId}")
    public ResponseEntity <?> deletarLivroDaBiblioteca(@PathVariable Integer bibliotecaId, @PathVariable Integer livroId){
        gerenciarService.deletarLivroDaBiblioteca(bibliotecaId, livroId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/buscarLivroPorTitulo")
    public ResponseEntity <List<LivroEncontradoBibliotecaDTO>> buscarLivroPorTitulo(@RequestParam("titulo") String titulo){
        return ResponseEntity
                .ok(gerenciarService.buscarLivroPorTitulo(titulo));
    }

    @GetMapping("/listarLivrosDaBiblioteca/{bibliotecaId}")
    public ResponseEntity<Page<LivroEncontradoBibliotecaDTO>> listarLivrosDaBiblioteca(@PathVariable Integer bibliotecaId, Pageable pageable){
        return ResponseEntity
                .ok(gerenciarService.listarLivrosDaBiblioteca(bibliotecaId, pageable));
    }
}