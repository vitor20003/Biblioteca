package br.unisc.loja.usuarios.persistence;

import br.unisc.loja.usuarios.dto.BibliotecaDTO;
import br.unisc.loja.usuarios.dto.BibliotecaLivroDTO;
import br.unisc.loja.usuarios.dto.LivroEncontradoBibliotecaDTO;
import br.unisc.loja.usuarios.exception.Excessoes;
import br.unisc.loja.usuarios.infra.banco.BibliotecaEntidade;
import br.unisc.loja.usuarios.infra.banco.BibliotecaLivroEntidade;
import br.unisc.loja.usuarios.infra.banco.LivroEntidade;
import br.unisc.loja.usuarios.repository.BibliotecaLivroRepository;
import br.unisc.loja.usuarios.repository.BibliotecaRepository;
import br.unisc.loja.usuarios.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibliotecaPersistenciaAdaptador implements BibliotecaPersistencia {

    private final BibliotecaRepository repository;
    private final LivroRepository livroRepository;
    private final BibliotecaLivroRepository gerenciaRepository;

    @Override
    public void criar(BibliotecaDTO bibliotecaDTO) {
        BibliotecaEntidade bibliotecaExiste = repository.findByNome(bibliotecaDTO.getNome());
        if (bibliotecaExiste != null) {
            throw new Excessoes.BibliotecaDuplicadaException("Biblioteca já cadastrada.");
        }
        var bibliotecaEntidade = BibliotecaEntidade.criarEntidade(bibliotecaDTO);
        repository.save(bibliotecaEntidade);
    }

    @Override
    public Page<BibliotecaDTO> buscarBiblioteca(Pageable pageable) {
        return repository.findAll(pageable)
                .map(BibliotecaEntidade::converterEntidadeParaDto);
    }

    @Override
    public boolean updateBiblioteca(Integer id, BibliotecaDTO bibliotecaDTO) {
        var entidadeOptional = repository.findById(id);
        if (entidadeOptional.isEmpty()) {
            throw new Excessoes.BibliotecaVaziaException("Esta biblioteca não existe!");
        }
        var entidade = entidadeOptional.get();
        entidade.setNome(StringUtils.isBlank(bibliotecaDTO.getNome()) ? entidade.getNome() : bibliotecaDTO.getNome());
        repository.save(entidade);
        return true;
    }

    @Override
    public void adicionarLivroNaBiblioteca(BibliotecaLivroDTO dto) {
        Integer bibliotecaId = dto.getBibliotecaId();
        Integer livroId = dto.getLivroId();

        if (gerenciaRepository.existsByBibliotecaIdAndLivroId(bibliotecaId, livroId)) {
            throw new Excessoes.LivroExistsException("O livro já foi adicionado a esta biblioteca.");
        }
        BibliotecaEntidade biblioteca = repository.findById(dto.getBibliotecaId())
                .orElseThrow(() -> new Excessoes.BibliotecaNotFoundException("Biblioteca não encontrada"));

        LivroEntidade livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new Excessoes.LivroNotFoundException("Livro não encontrado"));

        BibliotecaLivroEntidade bibliotecaLivro = new BibliotecaLivroEntidade();
        bibliotecaLivro.setBiblioteca(biblioteca);
        bibliotecaLivro.setLivro(livro);

        gerenciaRepository.save(bibliotecaLivro);
    }

    @Override
    @Transactional
    public void deletarLivroDaBiblioteca(Integer bibliotecaId, Integer livroId) {
        if (!gerenciaRepository.existsByBibliotecaIdAndLivroId(bibliotecaId, livroId)) {
            throw new Excessoes.AssociationNotFoundException("A associação entre o livro e a biblioteca não existe.");
        }
        gerenciaRepository.deleteByBibliotecaIdAndLivroId(bibliotecaId, livroId);
    }

    @Override
    public List<LivroEncontradoBibliotecaDTO> buscarLivroPorTitulo(String titulo) {
        return gerenciaRepository.findAllByLivroTituloContaining(titulo)
                .stream().map(BibliotecaLivroEntidade::convertEntityToDTO)
                .toList();
    }

    @Override
    public Page<LivroEncontradoBibliotecaDTO> listarLivrosDaBiblioteca(Integer bibliotecaId, Pageable pageable) {

        Page<BibliotecaLivroEntidade> bibliotecaLivros = gerenciaRepository.findByBibliotecaId(bibliotecaId, pageable);
        return bibliotecaLivros.map(BibliotecaLivroEntidade::convertEntityToDTO);
    }

    @Override
    public boolean deleteBiblioteca(Integer id) {
        var entidadeOptional = repository.findById(id);
        if (entidadeOptional.isEmpty()) {
            throw new Excessoes.BibliotecaNotFoundException("Esta biblioteca não existe!");
        }
        repository.deleteById(id);
        return true;
    }
}