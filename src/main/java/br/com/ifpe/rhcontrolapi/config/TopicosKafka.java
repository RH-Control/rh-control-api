package br.com.ifpe.rhcontrolapi.config;

public enum TopicosKafka {

    TOPICO_TESTE("teste-topico");

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

