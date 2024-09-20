<template>
  <div>
    <h1>Todo List</h1>
    <ul>
      <li v-for="todo in todos" :key="todo.todoId">
        {{ todo.item }} = {{ todo.date }}
        <button @click="deleteTodo(todo.todoId)">Delete</button>
<!--        <p>{{ todo.todoId }}</p> &lt;!&ndash; todoId 확인용 &ndash;&gt;-->
      </li>
    </ul>
    <input v-model="newTodo.item" placeholder="새로운 할 일 추가" />
    <button @click="addTodo">Add Todo</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const todos = ref([]);
const newTodo = ref({ item: '', date: new Date().toISOString().slice(0, 10) });

const fetchTodos = async () => {
  const response = await axios.get('/api/todo');
  console.log(response.data); // 테스트용
  todos.value = response.data;
};

// 할 일 추가
const addTodo = async () => {
  await axios.post('/api/todo', newTodo.value);
  fetchTodos(); // 최신 상태 유지
  newTodo.value = { item: '', date: new Date().toISOString().slice(0, 10) };
};

// 할 일 삭제
const deleteTodo = async (id) => {
  console.log("Deleting todo with ID:", id);  // 추가된 로그
  if (id) {
    // 삭제버튼 클릭 -> deleteTodo 함수 호출 -> 할 일 ID값 deleteTodo 함수에 전달
    // 응답 올 때까지 비동기 작업 수행
    await axios.delete(`/api/todo/${id}`);
    // 응답 O -> fetchTodos()로 최신 패치
    fetchTodos();
  } else {
    console.error("Invalid ID:", id);
  }
};

onMounted(fetchTodos);
</script>
