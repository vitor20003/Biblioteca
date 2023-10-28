package br.unisc.loja.usuarios.repository;

import br.unisc.loja.usuarios.dto.LivroEncontradoBibliotecaDTO;
import br.unisc.loja.usuarios.infra.banco.BibliotecaLivroEntidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BibliotecaLivroRepository extends JpaRepository<BibliotecaLivroEntidade, Integer> {
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BibliotecaLivroEntidade b WHERE b.livro.id = :livroId AND b.biblioteca.id = :bibliotecaId")
    boolean existsByBibliotecaIdAndLivroId(@Param("bibliotecaId") Integer bibliotecaId, @Param("livroId") Integer livroId);


    void deleteByBibliotecaIdAndLivroId(Integer bibliotecaId, Integer livroId);

    List<BibliotecaLivroEntidade> findAllByLivroTituloContaining(String titulo);

    Page<BibliotecaLivroEntidade> findByBibliotecaId(Integer bibliotecaId, Pageable pageable);
}