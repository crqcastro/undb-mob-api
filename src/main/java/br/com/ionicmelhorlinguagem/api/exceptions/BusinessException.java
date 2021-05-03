package br.com.ionicmelhorlinguagem.api.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

		private static final long serialVersionUID = 1L;
		private final Integer errorCode;

	    public BusinessException(String message, Integer errorCode){
	        super(message);
	        this.errorCode = errorCode;
	    }

		public BusinessException(ExceptionsTypeEnum type) {
			super(type.getMensagem());
			this.errorCode = type.getCode();			
		}
	    
}
