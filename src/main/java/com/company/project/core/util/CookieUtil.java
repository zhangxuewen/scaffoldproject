/**
 * 
 */
package com.company.project.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangxuewen
 *
 */
public class CookieUtil {

	private static final int COOKIE_MAX_AGE = 7 * 24 * 3600;

	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		if (null == name) {
			return;
		}
		Cookie cookie = getCookie(request, name);
		if (null != cookie) {
			cookie.setPath("/");
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	/**
	 * ���Cookie��Ƶõ�Cookie���󣬲����ڸö����򷵻�Null
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies || null == name || name.length() == 0) {
			return null;
		}
		Cookie cookie = null;
		for (Cookie c : cookies) {
			if (name.equals(c.getName())) {
				cookie = c;
				break;
			}
		}
		return cookie;
	}

	/**
	 * ���һ���µ�Cookie��Ĭ��7�����ʱ��(��λ����)
	 * 
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, COOKIE_MAX_AGE);
	}

	/**
	 * ���һ���µ�Cookie������ָ������ʱ��(��λ����)
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxValue
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxValue) {
		if (null == name) {
			return;
		}
		if (null == value) {
			value = "";
		}
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxValue != 0) {
			cookie.setMaxAge(maxValue);
		} else {
			cookie.setMaxAge(COOKIE_MAX_AGE);
		}
		response.addCookie(cookie);
	}
}