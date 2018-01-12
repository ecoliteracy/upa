package com.upa.service.security;

import java.util.Map;

public class UserContext {
	private static ThreadLocal<Map<ContextType, Object>> uctx = new ThreadLocal<Map<ContextType, Object>>();

	public static Map<ContextType, Object> getContext() {
		return uctx.get();
	}

	public static void setContext(Map<ContextType, Object> ctx) {
		uctx.set(ctx);
	}
	
	public static void remove(){
		uctx.remove();
	}
}
