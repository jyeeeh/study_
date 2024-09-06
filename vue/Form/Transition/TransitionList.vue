<script setup>
import { shuffle as _shuffle } from 'lodash-es'
import {ref} from 'vue'

// 초기 배열 함수
const getInitialItems = () => [1,2,3,4,5]
const items = ref(getInitialItems())
// items 자체가 ref 객체이기 때문에 배열의 길이 같은 속성을 직접적으로 접근 불가능
// ref 객체는 내부의 값에 접근할 때는 항상 .value 값으로 사용 (배열의 길이를 사용하고 싶으면)
// items.length 으로 호출하면 ref 객체에는 length 속성이 없어 -> 'undefined' 반환

// 새로운 항목 추가할 때 사용할 고유 식별자
// insert 에서 id++ 해서 고유한 값 유지
let id = items.value.length+1

// 랜덤 값 insert
function insert(){
  // Math.random = 0 이상 1 미만 -> 값에 배열 길이를 곱하고, round() => 정수로 변환
  // 결과적으로 0부터 items 배열의 길이까지의 랜덤한 정수를 얻음
  const i = Math.round(Math.random() * items.value.length)
  // splice - 해당 위치에 새로운 값 삽입 ( '0'은 아무 항목도 삭제하지 않고 추가함)
  // splice ( 삽입할 위치, 삭제할 항목의 개수, 삽입할 값 )

  // 처음에는 items.value=[1,2,3,4,5] 이기 때문에 id는 6
  items.value.splice(i, 0, id++)
}

// 리셋
function reset(){
  items.value = getInitialItems()
  // 초기 배열의 길이에 맞게 재설정
  id = items.value.length+1
}

// 섞기
function shuffle(){
  items.value = _shuffle(items.value)
}

// 특정 항목을 삭제하는 기능
function remove(item) {
  // indexOf: 배열에서 특정 항목(item) 인덱스 반환
  // item 배열 존재 안하면 -1 반환

  // ex) indexOf(3) -> return '2' (3은 배열의 인덱스 2에 위치함)
  const i = items.value.indexOf(item)
  // 배열이 존재하는 경우에만 삭제 작업 수행
  // ex) splice(2, 1) 인덱스 2에 있는 3을 1개 삭제 -> items.value = [1,2,4,5]
  if(i>-1){
    // 배열에서 특정 인덱스(i) 항목 삭제, 두번째 인수는 삭제할 항목의 개수(한개만 삭제할거기 때문에 : 1 )
    items.value.splice(i,1)
  }
}
</script>

<template>
  <button @click="insert">Insert New Random Index</button>
  <button @click="reset">Reset</button>
  <button @click="shuffle">Shuffle</button>

<!--  TransitionGroup: Vue에서 여러 요소의 전환을 처리하는데 사용되는 컴포넌트-->
<!--  TransitionGroup이 ul 요소로 렌더링되게 함, name="fade" (fade-enter-active, fade-leave-active 등의 클래스가 자동으로 생성-->
  <TransitionGroup tag="ul" name="fade" class="container">
    <li v-for="item in items" class="item" :key="item">
      {{ item }}
      <button @click="remove(item)" class="xbox">x</button>
    </li>
  </TransitionGroup>
</template>

<style scoped>
.container {
  position: relative;
  padding: 0;
  list-style-type: none;
}

.item {
  width: 100%;
  height: 30px;
  background-color: #ffc6cf;
  border: 1px solid #666;
  box-sizing: border-box;
}

/* 1. declare transition */
.fade-move,
.fade-enter-active,
.fade-leave-active {
  transition: all 0.5s cubic-bezier(0.55, 0, 0.1, 1);
}

/* 2. declare enter from and leave to state */
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: scaleY(0.01) translate(30px, 0);
}

.xbox {
  background-color: #cc8199
}

/* 3. ensure leaving items are taken out of layout flow so that moving
      animations can be calculated correctly. */
.fade-leave-active {
  position: absolute;
}
</style>
