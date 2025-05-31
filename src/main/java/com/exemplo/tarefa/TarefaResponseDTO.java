package com.exemplo.tarefa;

public record TarefaResponseDTO (
    Long id,
    String titulo,
    String descricao,
    String status
) {
    public static TarefaResponseDTO valueOf(Tarefa tarefa){
        return new TarefaResponseDTO(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getStatus());
    }
}
