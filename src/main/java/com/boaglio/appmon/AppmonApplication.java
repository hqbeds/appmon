package com.boaglio.appmon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.boaglio")
public class AppmonApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppmonApplication.class,args);

		System.out.println("args length = " + args.length);
		int cont = 1;
		for (String parameter : args) {
			System.out.println("Parameter " + cont + " = " + parameter);
			cont++;
		}

		if (args.length == 0) {
			System.out.println("Parameters:");
			System.out.println("---------------");
			System.out.println("-Dappmon.data=/tmp          : directory to store appmon data");
			System.out.println("-Dappmon.config=appmon.json : configuration file name ");
			System.out.println("-Dappmon.debug=false        : turn on or off debug mode");
		}

	}
}
