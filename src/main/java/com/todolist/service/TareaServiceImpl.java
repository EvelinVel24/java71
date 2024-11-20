package main.java.com.todolist.service;

import com.todolist.model.Tarea;
import com.todolist.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaServiceImpl implements TareaService {
    private final TareaRepository tareaRepository;

    public TareaServiceImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public Tarea save(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public void delete(Tarea tarea) {
        tareaRepository.delete(tarea);
    }

    @Override
    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }
}
