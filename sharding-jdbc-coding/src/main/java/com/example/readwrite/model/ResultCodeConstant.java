
package com.example.readwrite.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务代码枚举
 *
 * @author admin
 */
@Getter
@AllArgsConstructor
public enum ResultCodeConstant {

	/**
	 * 操作成功
	 */
	SUCCESS(0, "SUCCESS"),

	/**
	 * 业务异常
	 */
	ERROR(-1, "系统异常"),

	/**
	 * 缺少参数
	 */
	PARAM(-2, "缺少参数"),

	/**
	 * 404 没找到请求
	 */
	AUTH_FAILURE(-3, "auth failure!!");

	/**
	 * code编码
	 */
	private int code;
	/**
	 * 中文信息描述
	 */
	private String msg;

}
