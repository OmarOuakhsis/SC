package org.sid.customerservice;

import org.sid.customerservice.repository.CustomerRepository;
import org.sid.customerservice.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CustomerRepository customerRepository,RepositoryRestConfiguration restConfiguration) {
		return args -> {
			restConfiguration.exposeIdsFor(Customer.class);
			customerRepository.saveAll(
					List.of(
							Customer.builder().name("Hassan").email("hassan@gmail.com").build(),
							Customer.builder().name("Omar").email("omar@gmail.com").build(),
							Customer.builder().name("Ahmed").email("ahmed@gmail.com").build()
					)
			);
			customerRepository.findAll().forEach(c -> {
				System.out.println(c);
			});
		};
	}
}
