package br.com.ifpe.rhcontrolapi.config;

import java.util.HashMap;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaAdminConfig {

    @Autowired
    public KafkaProperties properties;

    @Bean
    KafkaAdmin kafkaAdmin() {

	var configs = new HashMap<String, Object>();
	configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
	return new KafkaAdmin(configs);
    }

    @Bean
    KafkaAdmin.NewTopics topics() {
	return new KafkaAdmin.NewTopics(
		TopicBuilder.name(TopicosKafka.SEND_MAIL.getDescricao()).partitions(2).replicas(1).build()
	);
    }

}

