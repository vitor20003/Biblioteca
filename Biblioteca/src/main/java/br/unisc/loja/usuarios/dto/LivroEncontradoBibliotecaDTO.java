package br.unisc.loja.usuarios.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LivroEncontradoBibliotecaDTO {
    String nomeBiblioteca;
    String tituloLivro;
    Integer codigoisbn;
}
