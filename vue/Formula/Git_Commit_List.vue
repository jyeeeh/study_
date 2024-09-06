<!--깃허브에 있는 데이터 가져와서 commits 보여주기-->

<script setup>
import { ref, watchEffect} from "vue"

const Github_Api_URL = `https://api.github.com/repos/vuejs/core/commits?per_page=3&sha=`
const branches = ['main', 'v2-compat']
const currentBranch = ref(branches[0]) // 현재 브랜치 [0]
const commits = ref(null) // 커밋 정보 저장하기 위한 반응형 상태, 초깃값=null

    // 비동기 작업 처리 : 어떤 작업이 완료될 때 까지 기다리지 않고, 다른 작업을 계속해서 수행할 수 있도록 하는 방식
    // (동시에 여러 작업을 처리)
  watchEffect(async () => {
    // async 함수 내에서 await 사용하여 비동기 작업 순차적으로 처리
    const url = `${Github_Api_URL}${currentBranch.value}`
    // fetch(url) -> 지정된 URL로 http 요청 보내고 응답 기다리기
    commits.value = await (await fetch(url)).json()
  })

function truncate(v){
// 새로운 줄(\n) 포함 되면 첫 번째 줄까지만 잘라서 반환
  const newline = v.indexOf('\n') // 줄바꿈 문자 없으면 indexOf는 -1 반환
  // 문자열에서 특정 하위 문자열이나 문자의 첫번째 발생 위치 찾는데 사용( 없으면 -1 반환 )
  return newline > 0 ? v.slice(0, newline) : v
}

function formatDate(v) {
  return v.replace(/T|Z/g, ' ') // T|Z - 문자 찾고, g 플래그는 전역 검색 의미(문자열 전체에서 일치하는 모든 항목 찾음)
//   formatDate("2024-09-04T12:30:00Z");
// // 결과: "2024-09-04 12:30:00 "
// 가독성 향상(다음과 같은 형식의 날짜는 T와 Z로 구분되어 해당 문자를 공백으로 대체해 일반적인 날짜 형식으로 만들어서 가독성을 높임ㄷ

}
</script>

<template>
  <h3> Latest Vue Pract Commits List</h3>
  <h4> Per_page : 3 </h4>
  <template v-for="branch in branches">
    <!--  :value (branch 변수의 값이 라디오 버튼의 value 속성으로 설정됨) name(모든 라디오 버튼에 동일한 name 속성을 부여하여 같은 그룹으로 묶임)    -->
    <input type="radio" :id="branch" :value="branch" name="branch" v-model="currentBranch">
    <label :for="branch"> {{ branch }}</label>
  </template>
  <p> currentBranch@{{ currentBranch }}</p>

  <!-- commit 내역 나오는 구역 -->
  <!-- target : 새로운 창 -->
  <ul>
    <!-- sha : git 커밋 해시 (secure hash algorithm) -->
    <li v-for="{ html_url, sha, author, commit } in commits">
      <a :href="html_url" target="_blank" class="commit"> {{ sha.slice(0,7) }}</a>
      -
      <span class="message"> {{ truncate(commit.message) }}</span>
      <br> by
      <span class="author"><a :href="author.html_url" target="_blank"> {{ commit.author.name }}</a></span>
      at
      <span class="date"> {{ formatDate(commit.author.date) }}</span>/
    </li>
  </ul>
</template>

<style>
a {
  text-decoration: none;
  color: #f1948a;
}

li{
  line-height: 1.5em;
  margin-bottom: 20px;
}

.author, .date{
  font-weight: bold;
}
</style>
