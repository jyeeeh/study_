<template>
  <div>
    <h3>WebSocket Chat</h3>
    <!--  채팅 박스 -->
    <div class="chat-box">
      <p v-for="(msg, index) in messages" :key="index">{{ msg }}</p>
    </div>
    <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="메세지를 입력하세요." />
    <button @click="sendMessage">Send</button>
  </div>
</template>

<script>
import { ref, onMounted, onBeforeUnmount } from 'vue';

export default {
  name: 'ChatComponent',
  setup() {
    const messages = ref([]);
    const newMessage = ref('');
    let socket;
    let sessionId;

    const connectWebSocket = () => {
      socket = new WebSocket('ws://localhost:8787/myHandler');

      socket.onopen = () => {
        console.log('Connected to WebSocket server');

        // 서버에 session Id 요청
        // socket.send('GET_SESSION_ID');
      };

      socket.onmessage = (event) => {
        if (event.data.startsWith('SESSION_ID')) {
          sessionId = event.data.split(':')[1].trim(); // 세션 ID 저장
        } else{
          // 일반 메세지 추가
          messages.value.push(event.data);
        }
      };

      socket.onclose = () => {
        console.log('Connection closed');
      };

      socket.onerror = (error) => {
        console.error('Error:', error);
      };
    };

    const sendMessage = () => {
      // if (newMessage.value && socket.readyState === WebSocket.OPEN) {
      //   socket.send(newMessage.value);
      //   newMessage.value = '';
      // }

      // session 추가 후
      if (newMessage.value && socket.readyState === WebSocket.OPEN && sessionId) {
        // 세션 ID와 메시지를 함께 보내기
        const messageToSend = `[${sessionId}] ${newMessage.value}`;
        socket.send(messageToSend);
        messages.value.push(messageToSend); // 보낸 메시지를 메시지 목록에 추가
        newMessage.value = '';
      }
    };

    onMounted(() => {
      connectWebSocket();
    });

    onBeforeUnmount(() => {
      if (socket) {
        socket.close();
      }
    });

    return { messages, newMessage, sendMessage };
  },
};
</script>

<style>
.chat-box {
  width: 300px;
  height: 400px;
  border: 1px solid #ccc;
  overflow-y: scroll;
  margin-bottom: 10px;
}
</style>
