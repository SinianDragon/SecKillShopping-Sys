package com.sks.secondkillstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sks.secondkillstore.mapper")
public class SecondKillStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondKillStoreApplication.class, args);
	}

}
