package br.com.ionicmelhorlinguagem.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpErrorResponseDto {
    private Integer httpStatus;
    private Integer code;
    private String message;
    private Object data;
    private String date;
}
