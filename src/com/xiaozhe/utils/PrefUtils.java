package com.xiaozhe.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
	private static final String PREF_NAME ="config";
	
	public static final String IS_USER_GUIDER_SHOWED ="is_user_guider_showed";
	
	
	
	/**
	 * 获取保存在PREF_NAME中的boolean值
	 * @param ctx
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Boolean getBoolean(Context ctx ,String key ,Boolean defaultValue){
		
		SharedPreferences preference 
				= ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Boolean result = preference.getBoolean(key, defaultValue);
		return result;
	}
	
	/**
	 * 保存Boolean值
	 * @param ctx
	 * @param key
	 * @param value
	 */
	public static void setBoolean(Context ctx ,String key ,Boolean value){
		SharedPreferences preference 
			= ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		preference.edit().putBoolean(key, value).commit();
	}
}
