package com.sss.train.batch.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan("com.sss")
@MapperScan("com.sss.train.*.mapper")
@EnableFeignClients("com.sss.train.batch.feign")
public class BatchApplication {

    private static final Logger LOG = LoggerFactory.getLogger(BatchApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BatchApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！");
        LOG.info("网关地址:\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
