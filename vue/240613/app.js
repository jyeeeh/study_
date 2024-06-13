const app = Vue.createApp({
    //값으로 함수를 갖음
    // data 프로퍼티에 저장된 값이 함수임

    //{{}} -> 여닫는 HTML 태그에 대해서만 사용 가능
    data() {
        return {
            courseGoal: 'Finished Vue',
            vueLink: 'https://ko.vuejs.org/guide/quick-start'
        };
    }
});

app.mount('#user-goal');