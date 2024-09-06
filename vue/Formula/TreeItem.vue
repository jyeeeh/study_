<!-- 트리 뷰 -->

<script setup>
// 상태 관리, 계산된 속성 만드는데 사용
// ref : 반응형 데이터 정의 할 때 사용
// computed : 반응형 데이터에서 파생된 값 계산할 때 사용
import {ref, computed} from 'vue'

// props 객체 반환
const props = defineProps({
  model: Object
})

const isOpen = ref(false)

// -- 특정 객체(model)가 폴더인지(자식 요소를 가지고 있는지) 판단
// computed를 사용해서 isFolder라는 계산된 속성 정의
// props.model 객체가 자식 요소를 가진 폴더인지 여부 판단
const isFolder = computed(() => {  // 종속된 데이터가 변경될 때만 다시 계산
  // props - 부모 컴포넌트로부터 받은 데이터를 담고있는 객체
  // model - props객체 안의 속성(폴더 구조를 표현하는 데이터 객체)
  return props.model.children && props.model.children.length
})

function toggle(){
  isOpen.value = !isOpen.value;
}

// 자식요소 추가
function addChild(){
  props.model.children.push({ name: 'new children'})
}

function changeType(){
  // 폴더가 안열려있으면
  if(!isOpen.value){
    // 자식 요소를 비우고
    props.model.children =[]
    addChild()
    // 자식요소 추가 후 폴더가 열려 있는 상태로 변경
    isOpen.value=true
  }
}

</script>

<template>
  <li>
    <div class="{ bold: isFolder }"
         @click="toggle"
         @dblclick="changeType"> <!-- @dblclick (더블 클릭 이벤트)-->
      {{ model.name }}
      <span v-if="isFolder">[{{ isOpen ? '접기' : '추가'}}]</span>
    </div>

    <ul v-show="isOpen" v-if="isFolder">
      <TreeItem class="item" v-for="model in model.children" :model="model"></TreeItem>
      <li class="add" @click="addChild">
        [추가]
      </li>
    </ul>
  </li>
</template>

<style>

</style>
