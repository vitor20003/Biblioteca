package br.unisc.loja.usuarios.repository;

import br.unisc.loja.usuarios.infra.banco.LivroEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntidade, Integer> {

    LivroEntidade findByCodigoisbn(Integer codigoisbn);
}