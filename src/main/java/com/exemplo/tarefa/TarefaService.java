package com.exemplo.tarefa;

import java.util.List;

public interface TarefaService {
    public TarefaResponseDTO insert(TarefaDTO dto);
    public TarefaResponseDTO update(TarefaDTO dto, Long id);
    public void delete(Long id);
    public TarefaResponseDTO findById(Long id);
    public List<TarefaResponseDTO> findByAll();
}
