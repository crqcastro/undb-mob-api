package br.com.ionicmelhorlinguagem.api.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import br.com.ionicmelhorlinguagem.api.dto.HttpErrorResponseDto;
import br.com.ionicmelhorlinguagem.api.exceptions.BusinessException;
import br.com.ionicmelhorlinguagem.api.exceptions.ExceptionsTypeEnum;
import lombok.extern.slf4j.Slf4j;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class MainExceptionHandler {


    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

	@ExceptionHandler({BusinessException.class})
    public ResponseEntity<HttpErrorResponseDto> handleServiceException(BusinessException ex) {
		log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.valueOf(ex.getErrorCode()))
                .body(HttpErrorResponseDto.builder()
                		.date(LocalDateTime.now().format(FORMAT))
                        .httpStatus(ex.getErrorCode())
                        .code(ex.getErrorCode())
                        .data("---")
                        .message(ex.getMessage()).build());
    }

	
	
    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<HttpErrorResponseDto> handleHttpException(HttpClientErrorException ex) {
    	log.error(ex.getMessage());
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(HttpErrorResponseDto.builder()
                		.date(LocalDateTime.now().format(FORMAT))
                		.data("----")
                        .httpStatus(ex.getStatusCode().value())
                        .message(ExceptionsTypeEnum.HTTP_CLIENT_ERROR_EXCEPTION.getMensagem()).build());
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity<HttpErrorResponseDto> handleCustomInternalServerError(Exception ex) {
    	log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(HttpErrorResponseDto.builder()
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .code(ExceptionsTypeEnum.IO_EXCEPTION.getCode())
                        .date(LocalDateTime.now().format(FORMAT))
                        .data("-----")
                        .message(ex.getMessage()).build());
    }
    
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<HttpErrorResponseDto> handleBadCredentialsException(Exception ex) {
    	log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(HttpErrorResponseDto.builder()
                        .httpStatus(ExceptionsTypeEnum.INVALID_TOKEN_EXCEPTION.getCode())
                        .code(ExceptionsTypeEnum.INVALID_TOKEN_EXCEPTION.getCode())
                        .date(LocalDateTime.now().format(FORMAT))
                        .data("------")
                        .message(ExceptionsTypeEnum.INVALID_TOKEN_EXCEPTION.getMensagem()).build());
    }

  
}