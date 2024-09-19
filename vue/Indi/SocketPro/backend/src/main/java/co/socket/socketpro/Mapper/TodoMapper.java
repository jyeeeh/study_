package co.socket.socketpro.Mapper;

import co.socket.socketpro.DTO.TodoDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoMapper {
    @Select("SELECT todo_id, completed, date, item, time, update_date, write_date, use_yn FROM todo WHERE todo_id = #{todoId}")
    TodoDTO findById(Long todoId);

    @Select("SELECT * FROM todo")
    @Results({
            @Result(property = "todoId", column = "todo_id"),
            @Result(property = "completed", column = "completed"),
            @Result(property = "date", column = "date"),
            @Result(property = "item", column = "item"),
            @Result(property = "time", column = "time"),
            @Result(property = "updateDate", column = "update_date"),
            @Result(property = "writeDate", column = "write_date"),
            @Result(property = "useYn", column = "use_yn")
    })
    List<TodoDTO> findAll();

    @Insert("INSERT INTO todo (completed, date, item, time, update_date, write_date, use_yn) " +
            "VALUES (#{completed}, #{date}, #{item}, #{time}, #{updateDate}, #{writeDate}, #{useYn})")
    void insert(TodoDTO todo);

    @Update("UPDATE todo SET completed=#{completed}, date=#{date}, item=#{item}, time=#{time}, " +
            "update_date=#{updateDate}, write_date=#{writeDate}, use_yn=#{useYn} WHERE todo_id=#{todoId}")
    void update(TodoDTO todo);

    @Delete("DELETE FROM todo WHERE todo_id = #{todoId}")
    void delete(Long todoId);

}
