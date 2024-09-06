<!-- Dim 처리 모달창 -->
<script setup>
import Modal from './components/Modal.vue'
import {ref} from 'vue'

const showModal = ref(false)
</script>

<template>
  <button id="show-modal" @click="showModal=true">
    Show Modal
  </button>
<!-- 자식 컴포넌트를 현재 DOM 트리의 다른 위치로 렌더링-->
  <Teleport to="body">
<!-- Modal.vue 에서 Transition name: modal 해놓은 커스텀 컴포넌트   -->
    <modal :show="showModal" @close="showModal=false">
<!-- slot name="header"  -->
      <template #header>
        <h4> Custom Header</h4>
      </template>
    </modal>/
  </Teleport>
</template>

<!--
1. show modal -> showModal: truㄷ 설정되어서 모달 열림
2. Teleport를 통해 DOM 트리에서 <body>에 삽입되며, HTML 구조에 관계없이
원하는 위치에서 모달을 표시할 수 있음
3. 모달 표시 : modal 컴포넌트는 [show] prop을 받음 -> close 는 모달 닫힘
-->
