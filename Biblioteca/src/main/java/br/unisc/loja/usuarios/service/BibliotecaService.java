package br.unisc.loja.usuarios.service;

import br.unisc.loja.usuarios.dto.BibliotecaDTO;
import br.unisc.loja.usuarios.persistence.BibliotecaPersistencia;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BibliotecaService {

    private final BibliotecaPersistencia persistencia;
    private Integer id;

    public void create(BibliotecaDTO bibliotecaDTO) {persistencia.criar(bibliotecaDTO);}

    public Page<BibliotecaDTO> buscarBiblioteca(Pageable pageable){return persistencia.buscarBiblioteca(pageable);}


    public boolean deleteBiblioteca(Integer id) {return persistencia.deleteBiblioteca(id);}

    public boolean updateBiblioteca(Integer id, BibliotecaDTO bibliotecaDTO) {return persistencia.updateBiblioteca(id, bibliotecaDTO);}

}
