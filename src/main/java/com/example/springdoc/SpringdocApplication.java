package com.example.springdoc;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.springdoc.utils.AppConstants;
import com.example.springdoc.entity.Role;
import com.example.springdoc.repository.RoleRepository;

@SpringBootApplication
public class SpringdocApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringdocApplication.class, args);
	}

	@Autowired 
	private RoleRepository roleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		try {
			
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER_ID_HARDCODED_ID_VALUE);
			role.setName("ADMIN_USER");
			
			Role role2 = new Role();
			role2.setId(AppConstants.NORMAL_USER_ID_HARDCODED_ID_VALUE);
			role2.setName("NORMAL_USER");
			
			List<Role> roles = List.of(role,role2);
			
			List<Role> rolesResult	 = this.roleRepository.saveAll(roles);
			
			rolesResult.forEach(r->{
				System.out.println(r.getName());  
			});
			
		} catch (Exception e) {
			
		}
	}

}