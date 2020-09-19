package com.se.axiom.search.custom.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(value = MobileSerachException.class)

	public Mono<CustomErrorResponse> handleGenericNotFoundException(MobileSerachException e) {
		CustomErrorResponse error = new CustomErrorResponse(e.getMessage(), e.errorCode);
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((HttpStatus.INTERNAL_SERVER_ERROR.value()));
		return Mono.just(error);
	}
}
