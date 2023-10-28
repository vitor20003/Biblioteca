package br.unisc.loja.usuarios.service;

import br.unisc.loja.usuarios.persistence.LivroPersistencia;
import br.unisc.loja.usuarios.dto.LivroDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroPersistencia persistencia;
    private Integer id;

    public Page<LivroDto> buscarTodos(Pageable pageable) {
        return persistencia.buscarTodos(pageable);
    }
    public void criar(LivroDto livroDto) {
        persistencia.criar(livroDto);
    }

    public boolean update(Integer id, LivroDto livroDto) { return persistencia.update(id, livroDto);}

    public boolean delete(Integer id) {return persistencia.delete(id);}
}