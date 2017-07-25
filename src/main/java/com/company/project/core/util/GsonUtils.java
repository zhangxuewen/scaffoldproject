package com.company.project.core.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


/**
 * GsonUtil
 * 
 * @author xuewen.zhangxuewen
 */
public class GsonUtils {
	final private static Logger logger = LoggerFactory.getLogger(GsonUtils.class);
	private static final GsonBuilder gb = new GsonBuilder();
	private static final Gson json = new Gson();
	private static Gson json2 = gb.create();
	/**
	 * test
	 */
	static {
		// gb.serializeNulls();
		gb.registerTypeAdapter(new TypeToken<List<Map<String, Object>>>() {
		}.getType(), new JsonDeserializer<List<Map<String, Object>>>() {

			@Override
			public List<Map<String, Object>> deserialize(JsonParser arg0,
					DeserializationContext arg1) throws IOException,
					JsonProcessingException {
				// TODO 自动生成的方法存根
				return null;
			}

		});
		gb.registerTypeAdapter(new TypeToken<Map<String, Object>>() {
		}.getType(), new JsonDeserializer<Map<String, Object>>() {

			@Override
			public Map<String, Object> deserialize(JsonParser arg0,
					DeserializationContext arg1) throws IOException,
					JsonProcessingException {
				// TODO 自动生成的方法存根
				return null;
			}
		});
	}

	/**
	 * 
	 * @param type
	 * @param typeAdapter
	 */
	public static void registerTypeAdapter(Type type, Object typeAdapter) {
		gb.registerTypeAdapter(type, typeAdapter);
		json2 = gb.create();
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String toJsonDefault(Object o) {
		if (o == null)
			return "{}";
		try {
			return json.toJson(o);
		} catch (Throwable ex) {
			logger.error("From Object[" + o.getClass() + "] to jsonDefault got exception", ex);
		}
		return null;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String toJson(Object o) {
		if (o == null)
			return "{}";
		try {
			return json2.toJson(o);
		} catch (Throwable ex) {
			logger.error("From Object[" + o.getClass() + "] to json got exception", ex);
		}
		return null;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String toJsonStructDefault(Object o) {
		if (o == null)
			return "{}";
		try {
			return json.toJson(o);
		} catch (Throwable ex) {
			logger.error("From Object[" + o.getClass() + "] to json got exception", ex);
		}
		return null;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String toJsonStruct(Object o) {
		if (o == null)
			return "{}";
		return json2.toJson(o);
	}

	
}
