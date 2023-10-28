package br.unisc.loja.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LivroDto {
    Integer id;
    String titulo;
    String categoria;
    Integer anopublicacao;
    String autor;
    Integer codigoisbn;
}
