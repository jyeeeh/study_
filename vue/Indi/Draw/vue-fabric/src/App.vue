<template>
  <div>
    <canvas id="canvas" />
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import * as fabric from 'fabric'; // fabric을 default가 아닌 모든 것을 import

onMounted(() => {
  const canvas = new fabric.Canvas('canvas');
  canvas.add(new fabric.Rect({
    left: 200,
    top: 100,
    fill: 'red',
    width: 300,
    height: 200
  }));
});
</script>

<style>
canvas {
  border: 5px solid black;
}
</style>
