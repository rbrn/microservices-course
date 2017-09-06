package ro.microservices.store.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KafkaChannels {

    @Output
    MessageChannel stockChannel();

}
