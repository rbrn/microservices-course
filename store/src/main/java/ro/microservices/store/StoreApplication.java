package ro.microservices.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;
import ro.microservices.store.entities.Category;
import ro.microservices.store.entities.Product;
import ro.microservices.store.repositories.CategoryRepository;
import ro.microservices.store.repositories.ProductRepository;

import java.math.BigDecimal;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}


	@Component
	public class DummyData implements CommandLineRunner{

		@Autowired
		private CategoryRepository categoryRepository;

		@Autowired
		private ProductRepository productRepository;


		@Override
		public void run(String... strings) throws Exception {
			Category category = categoryRepository.save( Category.builder().name("Test").build());
			Product product = productRepository.save(Product.builder()
					.category(category)
					.code("prod2")
					.price(new BigDecimal(4))
					.stock(45)
					.build());
		}
	}
}
