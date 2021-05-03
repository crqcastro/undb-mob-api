package br.com.ionicmelhorlinguagem.api.controller;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ionicmelhorlinguagem.api.exceptions.BusinessException;
import br.com.ionicmelhorlinguagem.api.exceptions.ExceptionsTypeEnum;
import br.com.ionicmelhorlinguagem.api.helpers.FileHelper;

@RestController
@RequestMapping(path = "/fatura")
public class FaturaController {

	private static final Long CONTRATO = 354799L;
	private static final String QUITADA_FILE = "equatorial.pdf"; 
	private static final String DEBITO_FILE = "equatorial_2.pdf";
	
	@GetMapping
	public ResponseEntity<String> get(){
		return ResponseEntity.ok("Ok");
	}
	
	
	@GetMapping(path = "/quitada/{contrato}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getFaturaquitada(@PathVariable("contrato") Long contrato) throws IOException{
		if(contrato.compareTo(CONTRATO)!=0) throw new BusinessException(ExceptionsTypeEnum.CONTRATO_NOT_FOUND);
		byte[] inFileBytes = Files.readAllBytes(FileHelper.getFile(QUITADA_FILE).toPath());
		byte[] encoded = java.util.Base64.getEncoder().encode(inFileBytes);
		return ResponseEntity.ok(encoded);
	}
	
	@GetMapping(path = "/debito/{contrato}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getFaturadebito(@PathVariable("contrato") Long contrato) throws IOException{
		if(contrato.compareTo(CONTRATO)!=0) throw new BusinessException(ExceptionsTypeEnum.CONTRATO_NOT_FOUND);
		byte[] inFileBytes = Files.readAllBytes(FileHelper.getFile(DEBITO_FILE).toPath());
		byte[] encoded = java.util.Base64.getEncoder().encode(inFileBytes);
		return ResponseEntity.ok(encoded);
	}
}
