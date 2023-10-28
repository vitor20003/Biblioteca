package br.unisc.loja.usuarios.service;

import br.unisc.loja.usuarios.dto.BibliotecaLivroDTO;
import br.unisc.loja.usuarios.dto.LivroEncontradoBibliotecaDTO;
import br.unisc.loja.usuarios.persistence.BibliotecaPersistencia;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GerenciarBibliotecaService {
    private final BibliotecaPersistencia persistencia;

    public void adicionarLivroNaBiblioteca(BibliotecaLivroDTO dto) {persistencia.adicionarLivroNaBiblioteca(dto);}

    public void deletarLivroDaBiblioteca(Integer bibliotecaId, Integer livroId) {persistencia.deletarLivroDaBiblioteca(bibliotecaId, livroId);}

    public List<LivroEncontradoBibliotecaDTO> buscarLivroPorTitulo(String titulo) {return persistencia.buscarLivroPorTitulo(titulo);}

    public Page<LivroEncontradoBibliotecaDTO> listarLivrosDaBiblioteca(Integer bibliotecaId, Pageable pageable) {return persistencia.listarLivrosDaBiblioteca(bibliotecaId, pageable);}
}
