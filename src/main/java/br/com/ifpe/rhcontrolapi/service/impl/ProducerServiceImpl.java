package br.com.ifpe.rhcontrolapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.ifpe.rhcontrolapi.service.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String topico, String message) {
		kafkaTemplate.send(topico, message);
	}

}
