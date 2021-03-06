package com.pkg;

import com.pkg.Utils.ExecutorUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PackageApiApplication {

	public static void main(String[] args) {
		ExecutorUtil.init();
		SpringApplication.run(PackageApiApplication.class, args);
	}
}
