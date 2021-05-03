package br.com.ionicmelhorlinguagem.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ExceptionsTypeEnum {
	USER_NOT_FOUND("USER NOT FOUND", 401),
	CONTRATO_NOT_FOUND("CONTRATO NAO ENCONTRADO PARA O CLIENTE LOGADO", 404),
	HTTP_CLIENT_ERROR_EXCEPTION("HTTP_CLIENT_ERROR_EXCEPTION", 400),
	IO_EXCEPTION("IO_EXCEPTION", 500),
	METHOD_ARGUMENT_NOT_VALID_EXCEPTION("METHOD_ARGUMENT_NOT_VALID_EXCEPTION", 400),
	RUNTIME_EXCEPTION("RUNTIME_EXCEPTION", 500),
	INVALID_TOKEN_EXCEPTION("EXPIRE OR INVALID JWT TOKEN", 401);
	
	@Getter
	private String mensagem;
	@Getter
	private Integer code;
}
