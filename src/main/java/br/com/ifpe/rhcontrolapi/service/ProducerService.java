package br.com.ifpe.rhcontrolapi.service;

public interface ProducerService {

	void sendMessage(String topico, String message);
}
