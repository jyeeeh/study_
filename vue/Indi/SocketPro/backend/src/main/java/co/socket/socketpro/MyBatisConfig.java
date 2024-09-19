package co.socket.socketpro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

// MyBatis 설정 파일

@Configuration
@MapperScan("co.socket.socketpro.Mapper")
public class MyBatisConfig {

}
