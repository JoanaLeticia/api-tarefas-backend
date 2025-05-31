package com.exemplo.tarefa;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TarefaRepository implements PanacheRepository<Tarefa> {
    public List<Tarefa> findByTitulo(String titulo) {
        return find("UPPER(titulo) LIKE UPPER(?1) ", "%" + titulo + "%").list();
    }
}
