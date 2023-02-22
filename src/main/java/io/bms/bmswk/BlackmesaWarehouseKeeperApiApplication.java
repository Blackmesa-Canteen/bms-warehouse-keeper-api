package io.bms.bmswk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.")
public class BlackmesaWarehouseKeeperApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackmesaWarehouseKeeperApiApplication.class, args);
    }

}
