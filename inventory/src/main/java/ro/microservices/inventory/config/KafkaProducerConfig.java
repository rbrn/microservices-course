package ro.microservices.inventory.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;

@Configuration
@EnableBinding({KafkaChannels.class})
@IntegrationComponentScan(basePackages = "ro.microservices.inventory")
public class KafkaProducerConfig {
}
