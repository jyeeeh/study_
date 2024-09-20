package co.socket.socketpro;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyHandler extends TextWebSocketHandler {

    // session id<- key로 갖는 map (session 관리)
    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    // 최초 연결
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 최초 연결 시 session 객체 저장 -> 다른 session 객체들에게 알림 보내기
        final String sessionId = session.getId();
        final String enteredMessage = sessionId + " 학생이 입장했습니다.";
        // 세션 객체 저장
        sessions.put(sessionId, session);

        sessions.values().forEach((s)->{
            try{
                // 현재 연결된 세션 ID && 다른 세션 ID
                // 세션이 열려있을 경우 메세지 전송
                if(!s.getId().equals(sessionId) && s.isOpen()){
                    s.sendMessage(new TextMessage(enteredMessage));
                }
            }
            catch(IOException e){}
        });
    }

    // 양방향 데이터 통신할 떄 해당 메서드 call
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
       final String sessionId = session.getId();
       sessions.values().forEach((s)->{
           if(!s.getId().equals(sessionId) && s.isOpen()) {
               try {
                   s.sendMessage(message);
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
           }
       });
    }

    // 웹소켓 종료 (다른 접속자에게 leave message 전송)
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        final String sessionId = session.getId();
        final String leaveMessage = sessionId + " 학생 로그아웃";
        sessions.remove(sessionId); // map에 저장되어있는 객체 remove

        //메세지 전송
        sessions.values().forEach((s)->{
            if(!s.getId().equals(sessionId) && s.isOpen()) {
                try {
                    s.sendMessage(new TextMessage(leaveMessage));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // 에러 발생 시
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

}
