package co.socket.socketpro.controller;

import co.socket.socketpro.DTO.TodoDTO;
import co.socket.socketpro.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoDTO> getTodos() {
        return todoService.getTodos();
    }

    @GetMapping("/{id}")
    public TodoDTO getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @PostMapping
    public void addTodo(@RequestBody TodoDTO todo) {
        todoService.addTodo(todo);
    }

    @PutMapping("/{id}")
    public void updateTodo(@PathVariable Long id, @RequestBody TodoDTO todo) {
        todo.setTodoId(id);
        todoService.updateTodo(todo);
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodoById(@PathVariable("todoId") Long id) {
        todoService.deleteTodoById(id);
    }

}
