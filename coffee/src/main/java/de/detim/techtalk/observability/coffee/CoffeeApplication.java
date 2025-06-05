package de.detim.techtalk.observability.coffee;

import de.detim.techtalk.observability.coffee.service.CoffeeService;
import de.detim.techtalk.observability.coffee.service.DefaultCoffeeService;
import de.detim.techtalk.observability.coffee.service.ObservedCoffeeService;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class CoffeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeApplication.class, args);
    }

    @Bean
    CoffeeService coffeeService(@Qualifier("coffeeTypeClient") RestClient coffeeTypeClient,
                                @Qualifier("coffeeSizeClient") RestClient coffeeSizeClient,
                                ObservationRegistry registry) {
        return new ObservedCoffeeService(new DefaultCoffeeService(coffeeTypeClient, coffeeSizeClient),
                                         registry);
    }

}
