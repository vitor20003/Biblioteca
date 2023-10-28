package br.unisc.loja.usuarios.infra.banco;

import br.unisc.loja.usuarios.dto.BibliotecaDTO;
import br.unisc.loja.usuarios.dto.BibliotecaDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bibliotecas")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BibliotecaEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String nome;

    public static BibliotecaDTO converterEntidadeParaDto(BibliotecaEntidade entidade) {
        return new BibliotecaDTO(entidade.getId(), entidade.getNome());
    }
    public static BibliotecaEntidade criarEntidade(BibliotecaDTO dto) {
        return BibliotecaEntidade.builder()
                .nome(dto.getNome())
                .build();
    }
}