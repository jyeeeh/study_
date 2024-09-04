<!-- 정렬과 필터가 있는 그리드 -->
<script setup>
// 상태 관리, 계산된 속성 만드는데 사용
// ref : 반은형 데이터 정의 할 때 사용
// computed : 반응형 데이터에서 파생된 값 계산할 때 사용
import {ref, computed} from 'vue'

// props 객체 반환
const props = defineProps({
  data: Array,
  columns: Array,
  filterKey: String
})

// 테이블 열을 정렬할 때 사용될 수 있는 구조 설정 -> sortOrders에서 관리
const sortKey = ref('') // 현재 정렬 기준이 되는 열 저장
const sortOrders = ref( // 각 열의 정렬 순서 저장
    // reduce : 배열 순회하면서 콜백 함수 호출
    // 현재 열 이름(key)를 객체 o의 키로 사용하고, 해당 키의 값을 1 로 설정(1은 오름차순 정렬을 나타내는 값으로 사용)
    // , o (쉼표 연산자) 사용하여 (o[key]=1)의 결과(즉, 1)와 함께 최종적으로 객체 o 반환
    // {} 초기값으로 빈 객체 제공
    props.columns.reduce((o, key) => ((o[key]=1), o), {})
)

// Data 필터 역할
const filteredData = computed(() => {
  let { data, filterKey } = props
  if (filterKey) {
    filterKey = filterKey.toLowerCase()
    data=data.filter((row) => {
      // 각 객체의 키를 배열로 얻음
      return Object.keys(row).some((key)=>{
        return
        // 현재 키의 값의 문자열 형태에 포함되어 있는지 확인
        String(row[key]).toLowerCase().indexOf(filterKey) > -1
      })
    })
  }
  // sortKey가 있을 때 정렬
  const key = sortKey.value
  // key 존재 시 키 정렬 순서 가져옴
  if (key) {
    const order = sortOrders.value[key]
    // data를 slice() 하여 정렬
    data = data.slice().sort((a, b) => {
      a = a[key]
      b = b[key]
      return (a === b ? 0 : a > b ? 1 : -1) * order
    })
  }
  return data
})

function sortBy(key) {
  sortKey.value=key
  sortOrders.value[key] *= -1
}

// 주어진 문자열의 첫 글자를 대문자로 변환함
function capitalize(str) {
  // str.charAt(0) : 문자열 str의 첫 번째 문자 반환, str.slice(1) : 여기서는 1부터 끝까지~ 부분 문자열 반환 ex) hello -> ello 
  // -> H + ello => Hello 
  return str.charAt(0).toUpperCase() + str.slice(1)
}
</script>

<template>

</template>

<style>

</style>
