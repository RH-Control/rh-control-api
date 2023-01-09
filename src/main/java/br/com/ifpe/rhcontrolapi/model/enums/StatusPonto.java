package br.com.ifpe.rhcontrolapi.model.enums;

public enum StatusPonto {
	OK("OK"),
	ATESTADO("ATESTADO"),
	FALTOU("FALTOU"),
	ATESTADO_EM_ANALISE("ATESTADO EM ANALISE");

	String status;
	
	StatusPonto(String status) {
		this.status = status;
	}
	
	
}
