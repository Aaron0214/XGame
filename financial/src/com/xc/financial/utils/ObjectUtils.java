package com.xc.financial.utils;

import java.lang.reflect.Field;
import java.util.Vector;

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
	    	o = new Object[obj.getClass().getDeclaredFields().length + 1];
	    	o[0] = Boolean.FALSE;
	    	int i = 1;
	    	for (Field f : obj.getClass().getDeclaredFields()) {
	    	    f.setAccessible(true);
	    	    //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
	    	    if(null != f.get(obj)){
	    	    	 o[i] = f.get(obj);
	    	    }else{
	    	    	 o[i] = "";
	    	    }
	    	    i++;
	    	}
		} catch (IllegalArgumentException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
		return o;
	}
	
	/**
     * 转化为Vector对象
     * @param source
     * @param destinationObject
     */
	public static <T> Vector<Object> createNewVecto(T obj){
		Vector<Object> o = null;
		 try {
	    	if(null == obj){
	    		return null;
	    	}
	    	o = new Vector<Object>();
	    	o.add(Boolean.FALSE);
	    	for (Field f : obj.getClass().getDeclaredFields()) {
	    	    f.setAccessible(true);
	    	    //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
	    	    if(null != f.get(obj)){
	    	    	 o.add(f.get(obj));
	    	    }else{
	    	    	 o.add("");
	    	    }
	    	}
		} catch (IllegalArgumentException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
		return o;
	}
	
	/**
     * int[]排序
     * @param source
     * @param destinationObject
     */
	public static void sort(int[] a){
		int size  = a.length;
		for(int i = 0;i< size; i++){
			for(int j = i+1;j< size;j++){
				if(a[i] < a[j]){
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args){
		int[] a = {1,3,2,4,6};
		sort(a);
		for(int i :a){
			System.out.println(i);
		}
	}
}
