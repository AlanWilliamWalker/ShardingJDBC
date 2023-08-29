package com.example.readwrite.model;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * AESUtil工具
 *
 * @author admin
 */
@Slf4j
public class AesUtil {


	private static final byte[] KEY_BYTES;

	private static final String KEY_STR = "qwertyuiopasdfghjkl1234567890zxcvbnm";

	static {
		KEY_BYTES = new byte[16];
		int i = 0;
		for (byte b : KEY_STR.getBytes()) {
			KEY_BYTES[i++ % 16] ^= b;
		}
	}

	public static String encrypt(String content) {
		int len = StringUtils.length(content);
		if (len < 1) {
			return content;
		}
		if (len > 2048) {
			return content;
		}
		try {
			return HexUtil.encodeHexStr(SecureUtil.aes(KEY_BYTES).encrypt(content), false);
		} catch (Exception e) {
			log.info("CryptoTypeHandlerUtil.encrypt, content:{}, e:{}", content, ExceptionUtil.getRootCauseMessage(e));
		}
		return content;
	}

	public static String decrypt(String content) {
		int len = StringUtils.length(content);
		if (len < 30) {
			return content;
		}
		try {
			return SecureUtil.aes(KEY_BYTES).decryptStr(content);
		} catch (Exception e) {
			log.info("CryptoTypeHandlerUtil.decrypt, content:{}, e:{}", content, ExceptionUtil.getRootCauseMessage(e));
		}
		return content;
	}
}

