package br.unisc.loja.usuarios.persistence;

import br.unisc.loja.usuarios.dto.BibliotecaDTO;
import br.unisc.loja.usuarios.dto.BibliotecaLivroDTO;
import br.unisc.loja.usuarios.dto.LivroEncontradoBibliotecaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BibliotecaPersistencia {
    void criar(BibliotecaDTO bibliotecaDTO);

    Page<BibliotecaDTO> buscarBiblioteca(Pageable pageable);

    boolean deleteBiblioteca(Integer id);

    boolean updateBiblioteca(Integer id, BibliotecaDTO bibliotecaDTO);

    void adicionarLivroNaBiblioteca(BibliotecaLivroDTO dto);

    void deletarLivroDaBiblioteca(Integer bibliotecaId, Integer livroId);

    List<LivroEncontradoBibliotecaDTO> buscarLivroPorTitulo(String titulo);

    Page<LivroEncontradoBibliotecaDTO> listarLivrosDaBiblioteca(Integer bibliotecaId, Pageable pageable);
}
