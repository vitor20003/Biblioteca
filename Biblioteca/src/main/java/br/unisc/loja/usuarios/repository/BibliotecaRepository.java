package br.unisc.loja.usuarios.repository;

import br.unisc.loja.usuarios.infra.banco.BibliotecaEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaRepository extends JpaRepository<BibliotecaEntidade, Integer> {
    BibliotecaEntidade findByNome(String nome);
}