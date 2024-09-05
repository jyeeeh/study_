<!-- 정렬과 필터가 있는 그리드 -->

<script setup>
import DemoGrid from '/Grid.vue'
import {ref} from 'vue'

const searchQuery = ref('')
const gridColumns = ['name', 'power']
const gridData = [
  { name: 'Chuck Norris', power: Infinity },
  { name: 'Bruce Lee', power: 9000 },
  { name: 'Jackie Chan', power: 7000 },
  { name: 'Jet Li', power: 8000 }
]
</script>

<template>
  <form id="search">
    Search <input name="query" type="text" v-model="searchQuery" />
  </form>
  <DemoGrid
    :data="gridData"
    :columns="gridColumns"
    :filter-key="searchQuery">

  </DemoGrid>
</template>

<style>

</style>
