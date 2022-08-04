package com.lin.infrastructure.commons;

import com.lin.infrastructure.commons.enums.BizErrorCode;
import com.lin.infrastructure.utils.JsonUtil;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author linzihao
 */
@Data
@Builder
public class ResultData implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 处理成功
	 */
	public static final String OK = "0000";

	public static final String OK_MSG = "操作成功";
	/**
	 * 其他错误
	 */
	public static final String ERR = "9999";

	public static final String ERR_MSG = "操作失败";

	private static final ResultData SUCESS = new ResultData(OK, OK_MSG);

	@Builder.Default
	private String code = ERR;
	@Builder.Default
	private String msg = "";
	private Object data;

	public static ResultData getFailResult() {

		return new ResultData(ERR, ERR_MSG);
	}

	public static ResultData getFailResult(String message) {

		return new ResultData(ERR, message);
	}

	public ResultData(Object result) {
		this(OK, OK_MSG, result);
	}

	public static ResultData setResultData(BizErrorCode bizErrorCode) {
		return new ResultData(bizErrorCode.getCode(), bizErrorCode.getMsg());
	}

	public static ResultData setResultData(BizErrorCode bizErrorCode, Object data) {
		return new ResultData(bizErrorCode.getCode(), bizErrorCode.getMsg(), data);
	}

	/**
	 * 成功
	 *
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static ResultData getSuccessData(Object data) {
		return new ResultData(OK, OK_MSG, data);
	}

	/**
	 * 成功
	 *
	 * @param data
	 * @param message
	 * @return
	 */
	public static ResultData getSuccessResult(Object data, String message) {

		return new ResultData(OK, message, data);
	}

	public static ResultData getSuccessResult(String message) {

		return new ResultData(OK, message);
	}

	public static ResultData getSuccessResult() {

		return ResultData.SUCESS;
	}

	public ResultData() {
	}

	public ResultData(String code, String message) {
		this.code = code;
		this.msg = message;
	}

	public ResultData(String code, String message, Object result) {
		this.code = code;
		this.msg = message;
		this.data = result;
	}

	public Object getData() {
		return data;
	}

	public ResultData setData(Object data) {
		this.data = data;
		return this;
	}

	public static void main(String[] args) {
		ResultData data = ResultData.builder().build();
		System.err.println(JsonUtil.toJson(data));
	}
}
