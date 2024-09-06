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
  todos.value.forEach((todos) => (todo.completed = e.target.checked))
}

</script>

<template>

</template>

<style scoped>

</style>
