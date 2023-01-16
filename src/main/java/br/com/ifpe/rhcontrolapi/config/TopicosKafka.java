package br.com.ifpe.rhcontrolapi.config;

public enum TopicosKafka {

    SEND_MAIL("enviar-email");

	private String descricao;
	
	TopicosKafka(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

