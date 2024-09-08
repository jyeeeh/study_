<script setup>
// watchEffect: Vue의 반은형 사이드 이펙트 관리 함수
// 반응형 상태가 변경 될 때 자동으로 호출 => todos 배열이 변경될 때 마다 스토리지에 업데이트할 수 있음
import { ref, computed, watchEffect} from "vue"

// 로컬 스토리지에 저장할 데이터의 키 역할
const STORAGE_KEY = 'vue-todomvc'

// 할 일 목록을 필터링하는 함수 포함
const filters = {
  // 모든 할 일 반환 ( todos 배열 그대로 반환 )
  all: (todos) => todos,
  // 완료되지 않은 할 일만 반환
  active: (todos) => todos.filter((todo) => !todo.completed),
  // 완료된 할 일만 반환
  completed: (todos) => todos.filter((todo) => todo.completed)
}

// 할 일 목록을 로컬 스토리지에서 불러옴, 저장된 할 일 목록이 없으면 빈 배열을 기본값으로 설정
const todos = ref(JSON.parse(localStorage.getItem(STORAGE_KEY) || '[]'))
// 현재 필터 상태 저장 - 초기값 'all'로 설정 => 모든 할 일 목록이 보임
const visibility = ref('all')
// 수정 중인 할 일을 저장하는 ref (선택X => 'undefined' 상태)
const editedTodo = ref()

// visibility.value - 현재의 필터 상태( all, active, completed ) 값을 키로 하여 필터 함수 선택
// ex) filters['active'](todos.value), filters['completed'](todos.value)
const filteredTodos = computed(() => filters[visibility.value](todos.value))
// 완료되지 않은 할 일을 필터링하는 함수 (completed 속성이 false 인 항목들만 반환)
const remaining =computed(() => filters.active(todos.value).length)

// hashchange 이벤트가 발생할 때 지정된 콜백 함수 호출
// hashchange(브라우저 주소창의 url 해시가 변경될 때 발생하는 이벤트)
window.addEventListener('hashchange' , onHashChange)
// 한 번 바로 실행, 페이지가 처음 열리거나 새로고침 되었을 때도 해시 값에 맞는 필터 상태 즉시 적용하기 위한 코드
onHashChange()

// localStorage에 todos.value 저장(할 일 목록 배열을 문자열로 변환한 후, STORAGE_KEY로 저장)
// 할 일 목록, 변경될 때 마다 로컬 스토리지에 자동으로 저장(새로고침 후에도 상태 유지 가능)
watchEffect(() => { localStorage.setItem(STORAGE_KEY, JSON.stringify(todos.value))})

// 모든 할 일의 completed 상태를 변경하는 함수
function toggleAll(e){
  // input type="checkbox" 요소의 checked 상태를 기반으로 동작함
  // 사용자가 모든 할 일 완료 여부를 일괄적으로 바꾸는 '전체 선택' 체크 박스에 체크하거나 체크 해제할 때 함수 호출됨
  todos.value.forEach((todo) => (todo.completed = e.target.checked))
}

// 할 일 추가
function addTodo(e){
  // e.target <- HTML(input element)
  const value = e.target.value.trim()

  if(value){
    todos.value.push({
      id: Date.now(),
      title: value,
      // 새로운 할 일은 기본적으로 완료되지 않은 상태이므로 false 처리
      completed: false
     })
    // 사용자가 입력한 input 값 빈 문자열로 설정하여 추가된 후 입력 필드 비우기
    e.target.value=''
  }
}
// 특정 할 일 배열에서 삭제
function removeTodo(todo){
  // splice(인덱스, 삭제할 개수)
  // todos.value.indexOf(todo) < 배열에서 todo 객체의 인덱스 위치 찾기
  todos.value.splice(todos.value.indexOf(todo), 1)
}

let beforeEditCache=''

// 수정 작업

// todo 객체를 받아서 객체 수정
// 1. 할 일 목록 수정 시작 할 때 editTodo 함수 호출 -> 2. 수정 전의 제목을 beforEditCache에 저장해두고
// 3. 수정할 객체를 editedTodo.value 에 할당해서 수정할 수 있는 상태로 만듬
function editTodo(todo){
  beforeEditCache = todo.title
  editedTodo.value=todo
}
// 수정 취소
function cancleEdit(todo){
  editedTodo.value=null
  // 다시 집어넣음
  todo.title=beforeEditCache
}
// 수정 완료
function doneEdit(todo){
  if(editedTodo.value){
    // 할 일 수정 완료 후 null 로 설정
    editedTodo.value=null
    todo.title = todo.title.trim()
    // 공백만 입력한 경우 'todo.title' 빈 문자열이 됨
    // 할 일의 제목을 모두 지우고 완료버튼을 누르면 할 일 삭제되는 처리 기능
    if(!todo.title) removeTodo(todo)
  }
}

function removeCompleted(){
  todos.value=filters.active(todos.value)
}

// window.location.hash < 현재 URL 해시 부분(#all, #completed) 가져옴
// .replace(/#\/?/, '')는 해시에서 #과 선택적인 /를 제거 ex) #all -> all 로 변환
// 1. URL의 해시 값 가져와서 route 로 변환 -> 2. route 값이 필터로 유효하면 업데이트
// 3. else URL 해시 제거, 기본 필터 상태(all)로 설정

// 해시 변경에 따라 필터링된 할 일 목록을 표시하는 기능 구현
function onHashChange() {
  const route = window.location.hash.replace(/#\/?/, '')
  // const filters = {
  //   all: (todos) => todos,
  //   active: (todos) => todos.filter((todo) => !todo.completed),
  //   completed: (todos) => todos.filter((todo) => todo.completed)
  // }
  if (filters[route]) {
    visibility.value = route
  } else {
    window.location.hash = ''
    visibility.value = 'all'
  }
}
</script>

<template>

</template>

<style scoped>

</style>
