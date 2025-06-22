package br.com.sampa.sampastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SampaStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampaStoreApplication.class, args);
        System.out.println("running");

	}

}
