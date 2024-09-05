<!-- 트리 뷰 -->

<script setup>
import TreeItem from '/frontend/src/components/prac/TreeItem.vue'
import {ref} from 'vue'

// 트리구조 데이터
const treeData = ref({
      name: 'My Tree',
      children: [
        {name: 'first'},
        {name: 'second'},
        {name: 'third'},
        {
          name: 'have child',
          children: [
            {name: 'first'},
            {name: 'second'},
            {name: 'third'},
            {
              name: 'have child',
              children: [
                {name: 'first'},
                {name: 'second'}
              ]
            }
          ]
        }
      ]
    })
</script>

<template>
  <ul>
    <TreeItem class="item" :model="treeData"></TreeItem>
  </ul>
</template>

<style>
.item {
  cursor: pointer;
  line-height: 1.5;
}
.bold {
  font-weight: bold;
}
</style>
