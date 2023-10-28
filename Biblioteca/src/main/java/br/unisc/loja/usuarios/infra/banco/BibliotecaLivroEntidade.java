package br.unisc.loja.usuarios.infra.banco;

import br.unisc.loja.usuarios.dto.BibliotecaLivroDTO;
import br.unisc.loja.usuarios.dto.LivroEncontradoBibliotecaDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "livro_biblioteca")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BibliotecaLivroEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn (name ="biblioteca_FK", nullable = false)
    private BibliotecaEntidade biblioteca;

    @ManyToOne
    @JoinColumn (name ="livro_FK", nullable = false)
    private LivroEntidade livro;


    public static LivroEncontradoBibliotecaDTO convertEntityToDTO(BibliotecaLivroEntidade entidade){
        var dto = new LivroEncontradoBibliotecaDTO();
        dto.setNomeBiblioteca(entidade.getBiblioteca().getNome());
        dto.setTituloLivro(entidade.getLivro().getTitulo());
        dto.setCodigoisbn(entidade.getLivro().getCodigoisbn());
        return dto;
    }

}
