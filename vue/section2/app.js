const app = Vue.createApp({
    //값으로 함수를 갖음
    // data 프로퍼티에 저장된 값이 함수임

    //{{}} -> 여닫는 HTML 태그에 대해서만 사용 가능
    //data는 그 자체로 함수or메서드
    data() {
        return {
            courseGoal: 'Finished Vue',
            vueLink: 'https://ko.vuejs.org/guide/quick-start'
        };
    },

    //Js객체 전달
    //메서드 및 함수로 만들어진 객체를 갖음
    methods: {
        //이 객체에서 정의하는 프로퍼티는 함수여야함
        outputGoal(){
            const randomNumber = Math.random();

            if(randomNumber<0.5){
                return 'Learn Vue';
            }else{
                return 'Master Vue';
            }
        }

    }
});

app.mount('#user-goal');