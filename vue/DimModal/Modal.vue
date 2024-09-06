<script setup>
const props = defineProps({
  show: Boolean
})
</script>

<template>
  <!-- Transition: 애니메이션이나 전환 효과 적용할 수 있게 해줌
   name="modal"은 Transition 컴포넌트에 전달되는 prop으로 전환 효과 이름 지정 역할-->
  <Transition name="modal">
    <div v-if="show" class="modal-mask">
      <div class="modal-container">
        <!-- 헤더 부분 -->
        <div class="modal-header">
          <slot name="header">Header</slot>
        </div>

        <!-- 바디 부분 -->
        <div class="modal-body">
          <slot name="body">default body</slot>
        </div>

        <!-- 바닥 부분 -->
        <div class="modal-footer">
          <slot name="footer">
            default footer
            <button class="modal-default-button" @click="$emit('close')">
              OK
            </button>
          </slot>
        </div>
      </div>
    </div>
  </Transition>
</template>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #a9a8a8;
  display: flex;
  transition: opacity 0.3s ease;
}

.modal-container {
  width: 300px;
  margin: auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter-from {
  opacity: 0;
}

.modal-leave-to {
  opacity: 0;
}

.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
