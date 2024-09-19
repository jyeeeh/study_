package co.socket.socketpro.DTO;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class TodoDTO {

    private Long todoId;
    private Boolean completed;
    private String date;
    private String item;
    private String time;
    private LocalDateTime updateDate;
    private String writeDate;
    private LocalDateTime useYn;

}

// 작성, 수정시간 달라질 수 있음
