package ro.microservices.store.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(KafkaChannels.class)
public class KafkaConsumerConfig {
}
