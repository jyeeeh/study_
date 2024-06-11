const app = Vue.createApp({
  data() {
    return {
      counter: 0,
    };
  },

  methods: {
    add() {
      //counter update
      this.counter++;
    },
    reduce(){
      this.counter--;
    }
  }
});

app.mount('#events');
