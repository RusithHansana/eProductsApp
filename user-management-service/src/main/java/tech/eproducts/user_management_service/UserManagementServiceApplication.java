/**
 * User Management Service Application
 * 
 * This class serves as the entry point for the User Management Service.
 * It is a Spring Boot application that enables service discovery.
 */
package tech.eproducts.user_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main application class for the User Management Service.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserManagementServiceApplication {

	/**
	 * The main method which serves as the entry point for the application.
	 * 
	 * @param args Command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserManagementServiceApplication.class, args);
	}

}
