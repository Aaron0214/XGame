package com.xc.financial.utils;

import java.lang.reflect.Field;

import org.dozer.DozerBeanMapper;

public class ObjectUtils {
	
	private static final DozerBeanMapper DOZER = new DozerBeanMapper();

	/**
     * @param source
     * @param destinationClass
     * @return
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return DOZER.map(source, destinationClass);
    }
    
    /**
     * @param source
     * @param destinationObject
     */
    public static void copy(Object source, Object destinationObject) {
        DOZER.map(source, destinationObject);
    }
	
    /**
     * 转化为Object对象
     * @param source
     * @param destinationObject
     */
	public static <T> Object changeToObject(T obj){
		Object[] o = null;
		 try {
	    	if(null == obj){
	    		return null;
	    	}
	    	o = new Object[obj.getClass().getDeclaredFields().length];
	    	int i = 0;
	    	for (Field f : obj.getClass().getDeclaredFields()) {
	    	    f.setAccessible(true);
	    	    //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
	    	    o[i] = f.get(obj);
	    	    i++;
	    	}
		} catch (IllegalArgumentException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
		return o;
	}
}
