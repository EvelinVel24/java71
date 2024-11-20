package main.java.com.todolist.service;

import com.todolist.model.Tarea;
import java.util.List;

public interface TareaService {
    Tarea save(Tarea tarea);
    void delete(Tarea tarea);
    List<Tarea> findAll();
}
