package br.com.ifpe.rhcontrolapi.service;

import java.util.List;

import br.com.ifpe.rhcontrolapi.model.Pagamento;

public interface PagamentoService {

	List<Pagamento> listarTodosPagamentos();

	List<Pagamento> listarPagamentos(Long codigoFuncionario);

	Pagamento gerarPagamento(Long codigoFuncionario, String data) throws Exception;

}
