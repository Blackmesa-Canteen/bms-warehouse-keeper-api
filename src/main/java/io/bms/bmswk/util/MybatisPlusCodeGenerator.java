package io.bms.bmswk.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author 996worker
 */
@Deprecated
public class MybatisPlusCodeGenerator {

    public static void main0(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/bms_warehouse_keeper", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("996worker")
                            .enableSwagger()
                            .outputDir("/Users/shaotienlee/Code/bms-warehouse-keeper/src/main/java");
                })
                .packageConfig(builder -> {
                    builder.parent("io.bms")
                            .moduleName("bmswk")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/shaotienlee/Code/bms-warehouse-keeper/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_category", "t_category_param", "t_city", "t_consume", "t_permission", "t_purchase", "t_role", "t_role_permission", "t_sku", "t_spu", "t_user", "t_warehouse", "t_warehouse_sku")
                            .addTablePrefix("t_");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
