package org.epam.resttask.cloud.config;

import org.epam.resttask.cloud.jpa.SubscriptionRepository;
import org.epam.resttask.cloud.jpa.UserRepository;
import org.epam.resttask.dto.Subscription;
import org.epam.resttask.dto.User;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner initDatabase(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        return args -> {
            User firstUser = new User( "James", "Bond", LocalDate.of(1965, 10, 15));
            User secondUser = new User( "Martin", "Scott", LocalDate.of(1998, 8, 26));
            userRepository.save(firstUser);
            userRepository.save(secondUser);
            userRepository.save(new User("Elsa", "Taylor", LocalDate.of(2000, 3, 8)));

            subscriptionRepository.save(new Subscription(firstUser, LocalDate.of(2023, 10, 10)));
            subscriptionRepository.save(new Subscription(firstUser, LocalDate.of(2023, 9, 26)));
            subscriptionRepository.save(new Subscription(secondUser, LocalDate.of(2023, 10, 27)));
        };
    }
}
