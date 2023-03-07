package io.bms.bmswk;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"io.bms.bmswk.mapper"})
@EnableSwagger2Doc
public class BMSWKApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BMSWKApiApplication.class, args);
    }

}
