package br.unisc.loja.usuarios.persistence;

import br.unisc.loja.usuarios.exception.Excessoes;
import br.unisc.loja.usuarios.infra.banco.LivroEntidade;
import br.unisc.loja.usuarios.dto.LivroDto;
import br.unisc.loja.usuarios.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class LivroPersistenciaAdaptador implements LivroPersistencia {

    private final LivroRepository repository;

    Excessoes excessoes = new Excessoes();

    @Override
    public void criar(LivroDto livroDto) {
        LivroEntidade livroExistente = repository.findByCodigoisbn(livroDto.getCodigoisbn());
        if (livroExistente != null) {
            throw new Excessoes.LivroDuplicadoException("Livro já cadastrado.");
        }
        var livroEntidade = LivroEntidade.criarEntidade(livroDto);
        repository.save(livroEntidade);
    }

    @Override
    public Page<LivroDto> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(LivroEntidade::converterEntidadeParaDto);
    }

    @Override
    public boolean update(Integer id, LivroDto livroDto) {
        var entidadeOptional = repository.findById(id);
        if (entidadeOptional.isEmpty()) {
            throw new Excessoes.LivroNotFoundException("Este livro não existe!");
        }
        var entidade = entidadeOptional.get();
        entidade.setTitulo(StringUtils.isBlank(livroDto.getTitulo()) ? entidade.getTitulo() : livroDto.getTitulo());
        entidade.setCategoria(StringUtils.isBlank(livroDto.getCategoria()) ? entidade.getCategoria() : livroDto.getCategoria());
        entidade.setAutor(StringUtils.isBlank(livroDto.getAutor()) ? entidade.getAutor() : livroDto.getAutor());
        entidade.setAnopublicacao(livroDto.getAnopublicacao());
        entidade.setCodigoisbn(livroDto.getCodigoisbn());

        repository.save(entidade);
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        var entidadeOptional = repository.findById(id);
        if (entidadeOptional.isEmpty()) {
            throw new Excessoes.LivroNotFoundException("Este livro não existe!");
        }
        repository.deleteById(id);
        return false;
    }

}