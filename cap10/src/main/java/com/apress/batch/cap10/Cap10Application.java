package com.apress.batch.cap10;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class Cap10Application {

	public static void main(String[] args) {
//		String[] realArgs = {"customerUpdateFile=file:///Users/mminella/Documents/IntelliJWorkspace/def-guide-spring-batch/chapter10/src/main/resources/data/customer_update_shuffled.csv",
//				"transactionFile=file:///Users/mminella/Documents/IntelliJWorkspace/def-guide-spring-batch/chapter10/src/main/resources/data/transactions.xml",
//				"outputDirectory=file:///Users/mminella/Documents/IntelliJWorkspace/def-guide-spring-batch/chapter10/target/statements/"};
		String [] realArgs = {
				"customerUpdateFile=/data/customer_update.csv",
				"transactionFile=/data/test/transactions.xml",
				"outputDirectory=file:///home/g3no/Escritorio/Batch - administrable/cap10/target/xddddd"
		};
		SpringApplication.run(Cap10Application.class, realArgs);
	}

}
