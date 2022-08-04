package com.lin.facade.handle;

import com.lin.infrastructure.commons.BusinessException;
import com.lin.infrastructure.commons.ResultData;
import com.lin.infrastructure.commons.enums.BizErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 统一异常处理类
 *
 * @author linzihao
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 参数校验
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(WebExchangeBindException.class)
	public Object checkRequestBody(WebExchangeBindException e) {
		log.info(e.getMessage());
		BindingResult bindingResult = e.getBindingResult();
		List<ObjectError> errorList = bindingResult.getAllErrors();
		String message = errorList.stream().map(p -> ((FieldError) p).getDefaultMessage())
				.collect(Collectors.joining(","));
		log.warn("======参数校验异常======" + message);
		ResultData resultData = ResultData.builder().code(BizErrorCode.REQUEST_PARAM_ILLEGAL.getCode()).msg(message)
				.build();
		return Mono.just(resultData);
	}

	/**
	 * 参数校验
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public Object checkRequestParam(ConstraintViolationException e) {
		log.info(e.getMessage());
		Set<ConstraintViolation<?>> bindingResult = e.getConstraintViolations();
		String message = bindingResult.stream().map(p -> p.getMessage()).collect(Collectors.joining(","));
		log.warn("======参数校验异常======" + message);
		ResultData resultData = ResultData.builder().code(BizErrorCode.REQUEST_PARAM_ILLEGAL.getCode()).msg(message)
				.build();
		return Mono.just(resultData);
	}

	/**
	 * 参数验证统一处理
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		String msg = bindingResult.getFieldErrors().stream().sorted(Comparator.comparing(FieldError::getField))
				.map(e1 -> e1.getDefaultMessage()).collect(Collectors.joining(","));
		log.error("参数验证失败: {},", e.getMessage());
		ResultData resultData = ResultData.builder().code(BizErrorCode.REQUEST_PARAM_INVALID.getCode()).msg(msg)
				.build();
		return Mono.just(resultData);
	}

	/**
	 * 自定义异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	public Object handleBusinessException(BusinessException e) {
		log.error("业务失败: {},", e.getMessage());
		ResultData resultData = new ResultData(e.getCode(), e.getMessage());
		return Mono.just(resultData);
	}

	/**
	 * 请求超时
	 *
	 * @param runtimeException
	 * @return
	 */
	@ExceptionHandler(AsyncRequestTimeoutException.class)
	public Object runtimeExceptionHandler(AsyncRequestTimeoutException runtimeException) {
		log.error("请求超时:" + runtimeException.getMessage(), runtimeException);
		ResultData resultData = ResultData.setResultData(BizErrorCode.REQUEST_TIMEOUT);
		return Mono.just(resultData);
	}

	/**
	 * 空指针异常
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	public Object nullPointerExceptionHandler(NullPointerException ex) {
		log.error("空指针异常:" + ex.getMessage(), ex);
		ResultData resultData = ResultData.setResultData(BizErrorCode.REQUEST_SYSTEM_ERR);
		return Mono.just(resultData);
	}

	/**
	 * 类型转换异常
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ClassCastException.class)
	public Object classCastExceptionHandler(ClassCastException ex) {
		log.error("类型转换异常:" + ex.getMessage(), ex);
		ResultData resultData = ResultData.setResultData(BizErrorCode.REQUEST_SYSTEM_ERR);
		return Mono.just(resultData);
	}

	/**
	 * IO异常
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(IOException.class)
	public Object ioExceptionHandler(IOException ex) {
		log.error("IO异常:" + ex.getMessage(), ex);
		ResultData resultData = ResultData.setResultData(BizErrorCode.REQUEST_SYSTEM_ERR);
		return Mono.just(resultData);
	}

	/**
	 * 未知方法异常
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NoSuchMethodException.class)
	public Object noSuchMethodExceptionHandler(NoSuchMethodException ex) {
		log.error("未知方法异常:" + ex.getMessage(), ex);
		ResultData resultData = ResultData.setResultData(BizErrorCode.API_NO_FOUND);
		return Mono.just(resultData);
	}

	/**
	 * 数组越界异常
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public Object indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
		log.error("数组越界异常:" + ex.getMessage(), ex);
		ResultData resultData = ResultData.setResultData(BizErrorCode.REQUEST_SYSTEM_ERR);
		return Mono.just(resultData);
	}

	/**
	 * 以上异常之外的其他异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ Exception.class })
	public Object handleAuthorizationException(Exception e) {
		log.error("其他异常:", e);
		ResultData resultData = ResultData.setResultData(BizErrorCode.UNKNOWN_EXCEPTION);
		return Mono.just(resultData);
	}

}
