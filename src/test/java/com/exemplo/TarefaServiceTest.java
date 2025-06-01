package com.exemplo;

import org.junit.jupiter.api.Test;

import com.exemplo.tarefa.TarefaDTO;
import com.exemplo.tarefa.TarefaResponseDTO;
import com.exemplo.tarefa.TarefaService;


import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TarefaServiceTest {
    
    @Inject
    TarefaService tarefaService;

    @Test
    public void testNovaTarefaComTitulo() {
        TarefaDTO t = new TarefaDTO("Teste", "Descrição Teste", "Pendente");
        
        TarefaResponseDTO criada = tarefaService.insert(t);

        assertNotNull(criada.id());
        assertEquals("Teste", criada.titulo());
    }
}
