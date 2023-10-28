package br.unisc.loja.usuarios.persistence;

import br.unisc.loja.usuarios.dto.LivroDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LivroPersistencia {

    void criar(LivroDto livroDto);
    Page<LivroDto> buscarTodos(Pageable pageable);

    boolean update(Integer id, LivroDto livroDto);

    boolean delete(Integer id);
}
