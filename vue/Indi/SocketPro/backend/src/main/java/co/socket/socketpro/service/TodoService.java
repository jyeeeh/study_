package co.socket.socketpro.service;

import co.socket.socketpro.DTO.TodoDTO;
import co.socket.socketpro.Mapper.TodoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoMapper todoMapper;

    public TodoService(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public List<TodoDTO> getTodos() {
        return todoMapper.findAll();
    }

    public TodoDTO getTodoById(Long todoId) {
        return todoMapper.findById(todoId);
    }

    public void addTodo(TodoDTO todo) {
        todoMapper.insert(todo);
    }

    public void updateTodo(TodoDTO todo) {
        todoMapper.update(todo);
    }

    public void deleteTodoById(Long todoId) {
        todoMapper.delete(todoId);
    }
}
