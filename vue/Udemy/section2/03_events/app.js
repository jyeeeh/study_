const app = Vue.createApp({
  data() {
    return {
      counter: 0,
      name: ''
    };
  },

  methods: {
    //이름 작성
    //브라우저가 기본 이벤트 객체를 제공하도록 함
    //기본 이벤트 객체가 이벤트 정보 다량
    setName(event, lastName) {
      this.name = event.target.value+' '+lastName;
    },
    add(num) {
      //counter update
      this.counter=this.counter+num;
    },
    reduce(num){
      this.counter=this.counter-num;
    }
  }
});

app.mount('#events');
