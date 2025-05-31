package com.exemplo.tarefa;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TarefaServiceImpl implements TarefaService {
    @Inject
    TarefaRepository repository;

    @Override
    @Transactional
    public TarefaResponseDTO insert(TarefaDTO dto) {
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setTitulo(dto.titulo());
        novaTarefa.setDescricao(dto.descricao());
        novaTarefa.setStatus(dto.status());

        repository.persist(novaTarefa);

        return TarefaResponseDTO.valueOf(novaTarefa);
    }

    @Override
    @Transactional
    public TarefaResponseDTO update(TarefaDTO dto, Long id) {

        Tarefa novaTarefa = repository.findById(id);

        novaTarefa.setTitulo(dto.titulo());
        novaTarefa.setDescricao(dto.descricao());
        novaTarefa.setStatus(dto.status());

        return TarefaResponseDTO.valueOf(novaTarefa);
    }

    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public TarefaResponseDTO findById(Long id) {
        Tarefa tarefa = repository.findById(id);
        if (tarefa == null) {
            throw new EntityNotFoundException("Tarefa não encontrado com ID: " + id);
        }
        return TarefaResponseDTO.valueOf(tarefa);
    }

    @Override
    public List<TarefaResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> TarefaResponseDTO.valueOf(e)).toList();
    }
}
