 package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan 해당 패키지를 basePackages로 설정, 
//@ComponentScan(basePackages = "upload") 지정시 지정한 패키지로 설정 및 기본으로 되어있는 설정 X(생성자처럼))
@SpringBootApplication //내가 설정한 클래스 파일 + 스프링시작 tomcat 시작
@ComponentScan
@ComponentScan(basePackages ={"upload", "board.spring.mybatis"})
@ComponentScan(basePackages = "spring.mybatis")
@ComponentScan(basePackages = "websocket")
@ComponentScan(basePackages = "dbsecure")

@MapperScan(basePackages = {"spring.mybatis","board.spring.mybatis"})
public class SecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondApplication.class, args);
	}
}
