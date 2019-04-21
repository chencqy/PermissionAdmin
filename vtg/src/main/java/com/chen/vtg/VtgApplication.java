package com.chen.vtg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.chen.vtg.mapper")
public class VtgApplication {

	public static void main(String[] args) {
		SpringApplication.run(VtgApplication.class, args);
	}
}
