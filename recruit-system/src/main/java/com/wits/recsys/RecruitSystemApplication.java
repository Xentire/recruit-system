package com.wits.recsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 改成你自己的 mapper 包路径
@MapperScan("com.wits.recsys.mapper")
@SpringBootApplication
public class RecruitSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(RecruitSystemApplication.class, args);
	}
}