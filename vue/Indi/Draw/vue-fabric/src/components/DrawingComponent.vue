<template>
    <canvas id="canvas"></canvas>
     <!-- 해당 버튼 새로고침 후 다시 눌러보면 이전 버전 text 남아있음 (**) -->
      <button id="drawing-mode" class="btn btn-info">그리기 모드 취소</button><br>
      <button id="clear-canvas" class="btn btn-info">지우기</button><br>
    <div>
      <!--  해당 div canvas 오른쪽으로 이동시키기 (**)-->
      <div id="drawing-mode-options" style="display: inline-block; margin-left: 10px">
        <label for="drawing-mode-selector">Mode:</label>
        <select id="drawing-mode-selector">
          <option>Pencil</option>
          <option>Circle</option>
          <option>Spray</option>
          <option>Pattern</option>
          <option>hline</option>
          <option>vline</option>
          <option>square</option>
          <option>diamond</option>
          <option>texture</option>
        </select><br>

        <label for="drawing-line-width">Line width:</label>
        <span class="info">{{ lineWidth }}</span><input type="range" v-model="lineWidth" value="30" min="0" max="150" id="drawing-line-width"><br>

        <label for="drawing-color">Line color:</label>
        <input type="color" value="#ffa2d8" id="drawing-color"><br>

        <label for="drawing-shadow-color">Shadow color:</label>
        <input type="color" value="#ff89cd" id="drawing-shadow-color"><br>

        <label for="drawing-shadow-width">Shadow width:</label>
        <span class="info">{{ shadowWidth }}</span><input type="range" v-model="shadowWidth" value="0" min="0" max="50" id="drawing-shadow-width"><br>

        <label for="drawing-shadow-offset">Shadow offset:</label>
        <span class="info">{{ shadowOffset }}</span><input type="range" v-model="shadowOffset"  value="0" min="0" max="50" id="drawing-shadow-offset"><br>
      </div>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import * as fabric from 'fabric';

const canvasId = 'canvas';
// const brushOptions = {
//   vline: 'Vertical Line',
//   hline: 'Horizontal Line',
//   square: 'Square',
//   diamond: 'Diamond',
//   texture: 'Texture'
// };

const canvas = ref(null);
const drawingModeEl = ref(null);
const drawingOptionsEl = ref(null);
const drawingColorEl = ref(null);
const drawingShadowColorEl = ref(null);
const drawingLineWidthEl = ref(null);
const drawingShadowWidth = ref(null);
const drawingShadowOffset = ref(null);
const clearEl = ref(null);
const drawingModeSelector = ref(null);

const lineWidth = ref(1);
const shadowWidth = ref(0);
const shadowOffset = ref(0);

onMounted(() => {
  const fabricCanvas = new fabric.Canvas(canvasId, {
    isDrawingMode: true
  });

  fabric.Object.prototype.transparentCorners = false;

  const $ = id => document.getElementById(id);

  canvas.value = fabricCanvas;
  drawingModeEl.value = $(`drawing-mode`);
  drawingOptionsEl.value = $(`drawing-mode-options`);
  drawingColorEl.value = $(`drawing-color`);
  drawingShadowColorEl.value = $(`drawing-shadow-color`);
  drawingLineWidthEl.value = $(`drawing-line-width`);
  drawingShadowWidth.value = $(`drawing-shadow-width`);
  drawingShadowOffset.value = $(`drawing-shadow-offset`);
  clearEl.value = $(`clear-canvas`);
  drawingModeSelector.value = $(`drawing-mode-selector`);

  const setPatternBrush = (name, patternFunction) => {
    const brush = new fabric.PatternBrush(fabricCanvas);
    brush.getPatternSrc = patternFunction;
    return brush;
  };

  const vLinePatternBrush = setPatternBrush('vline', () => {
    const patternCanvas = document.createElement('canvas');
    patternCanvas.width = patternCanvas.height = 10;
    const ctx = patternCanvas.getContext('2d');
    ctx.strokeStyle = fabricCanvas.freeDrawingBrush.color;
    ctx.lineWidth = 5;
    ctx.beginPath();
    ctx.moveTo(0, 5);
    ctx.lineTo(10, 5);
    ctx.closePath();
    ctx.stroke();
    return patternCanvas;
  });

  const hLinePatternBrush = setPatternBrush('hline', () => {
    const patternCanvas = document.createElement('canvas');
    patternCanvas.width = patternCanvas.height = 10;
    const ctx = patternCanvas.getContext('2d');
    ctx.strokeStyle = fabricCanvas.freeDrawingBrush.color;
    ctx.lineWidth = 5;
    ctx.beginPath();
    ctx.moveTo(5, 0);
    ctx.lineTo(5, 10);
    ctx.closePath();
    ctx.stroke();
    return patternCanvas;
  });

  const squarePatternBrush = setPatternBrush('square', () => {
    const patternCanvas = document.createElement('canvas');
    patternCanvas.width = patternCanvas.height = 12;
    const ctx = patternCanvas.getContext('2d');
    ctx.fillStyle = fabricCanvas.freeDrawingBrush.color;
    ctx.fillRect(0, 0, 10, 10);
    return patternCanvas;
  });

  const diamondPatternBrush = setPatternBrush('diamond', () => {
    const patternCanvas = document.createElement('canvas');
    const rect = new fabric.Rect({
      width: 10,
      height: 10,
      angle: 45,
      fill: fabricCanvas.freeDrawingBrush.color
    });
    const canvasWidth = rect.getBoundingRect().width;
    patternCanvas.width = patternCanvas.height = canvasWidth;
    rect.set({ left: canvasWidth / 2, top: canvasWidth / 2 });
    const ctx = patternCanvas.getContext('2d');
    rect.render(ctx);
    return patternCanvas;
  });

  const img = new Image();
  img.src = '../assets/honey_im_subtle.png';

  const texturePatternBrush = new fabric.PatternBrush(fabricCanvas);
  texturePatternBrush.source = img;

  const brushMap = {
    vline: vLinePatternBrush,
    hline: hLinePatternBrush,
    square: squarePatternBrush,
    diamond: diamondPatternBrush,
    texture: texturePatternBrush
  };

  const updateBrush = () => {
    const brushType = drawingModeSelector.value.value;
    if (brushMap[brushType]) {
      fabricCanvas.freeDrawingBrush = brushMap[brushType];
    } else {
      fabricCanvas.freeDrawingBrush = new fabric[brushType + 'Brush'](fabricCanvas);
    }

    if (fabricCanvas.freeDrawingBrush) {
      const brush = fabricCanvas.freeDrawingBrush;
      brush.color = drawingColorEl.value.value;
      if (brush.getPatternSrc) {
        brush.source = brush.getPatternSrc.call(brush);
      }
      brush.width = parseInt(drawingLineWidthEl.value.value, 10) || 1;
      brush.shadow = new fabric.Shadow({
        blur: parseInt(drawingShadowWidth.value.value, 10) || 0,
        offsetX: parseInt(drawingShadowOffset.value.value, 10) || 0,
        offsetY: parseInt(drawingShadowOffset.value.value, 10) || 0,
        affectStroke: true,
        color: drawingShadowColorEl.value.value,
      });
    }
  };

  clearEl.value.onclick = () => fabricCanvas.clear();

  drawingModeEl.value.onclick = () => {
    fabricCanvas.isDrawingMode = !fabricCanvas.isDrawingMode;
    drawingModeEl.value.innerHTML = fabricCanvas.isDrawingMode ? 'Cancel drawing mode' : 'Enter drawing mode';
    drawingOptionsEl.value.style.display = fabricCanvas.isDrawingMode ? '' : 'none';
  };

  drawingModeSelector.value.onchange = updateBrush;
  drawingColorEl.value.onchange = updateBrush;
  drawingShadowColorEl.value.onchange = () => {
    if (fabricCanvas.freeDrawingBrush) {
      fabricCanvas.freeDrawingBrush.shadow.color = drawingShadowColorEl.value.value;
    }
  };
  drawingLineWidthEl.value.onchange = () => {
    if (fabricCanvas.freeDrawingBrush) {
      fabricCanvas.freeDrawingBrush.width = parseInt(drawingLineWidthEl.value.value, 10) || 1;
    }
  };
  drawingShadowWidth.value.onchange = () => {
    if (fabricCanvas.freeDrawingBrush) {
      fabricCanvas.freeDrawingBrush.shadow.blur = parseInt(drawingShadowWidth.value.value, 10) || 0;
    }
  };
  drawingShadowOffset.value.onchange = () => {
    if (fabricCanvas.freeDrawingBrush) {
      fabricCanvas.freeDrawingBrush.shadow.offsetX = parseInt(drawingShadowOffset.value.value, 10) || 0;
      fabricCanvas.freeDrawingBrush.shadow.offsetY = parseInt(drawingShadowOffset.value.value, 10) || 0;
    }
  };

  updateBrush(); // Initialize brush settings
});
</script>


<style>
/* 필요한 스타일 추가 */

#canvas {
  width:1000px;
  height:1000px;
  border: 1px solid rgb(170, 170, 170);
  position: absolute;
  touch-action: none;
  user-select: none;
  cursor: crosshair;
}
</style>
