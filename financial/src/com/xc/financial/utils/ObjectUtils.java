package com.xc.financial.utils;

import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
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
     * 判断Object是否为空
     * @param <T>
     * @param source
     * @param destinationObject
     */
	public static <T> boolean isEmpty(T obj){
		 try {
	    	if(null == obj){
	    		return true;
	    	}
	    	boolean flag = false;
	    	for (Field f : obj.getClass().getDeclaredFields()) {
	    	    f.setAccessible(true);
	    	    //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
	    	    if(!f.getName().equals("serialVersionUID")){
	    	    	 if(null == f.get(obj)){
	 	    	    	flag = true;
	 	    	    }else{
	 	    	    	if(f.getType().equals(List.class)){
	 	    	    		if(CollectionUtils.isEmpty((Collection<?>) f.get(obj))){
	 	    	    			flag = true;
	 	    	    		}else{
	 	    	    			flag = false;
	 	    	    			break;
	 	    	    		}
	 	    	    	}else{
	 	    	    		flag = false;
	 	    	    		break;
	 	    	    	}
	 	    	    }
	    	    }
	    	}
	    	if(flag){
	    		return true;
	    	}else{
	    		return false;
	    	}
		} catch (IllegalArgumentException e) {
			return false;
		} catch (IllegalAccessException e) {
			return false;
		}
	}
    
	/**
     * 判断Object是否不为空
     * @param <T>
     * @param source
     * @param destinationObject
     */
	public static <T> boolean isNotEmpty(T obj){
		return !isEmpty(obj);
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
     * 执行类中的方法
     * @param Object ,class ,functionName
     * @return value
     */
	public static <T> Object excuteGetFunction(Object obj,T a,String functionName){
		try {
			Method m = obj.getClass().getDeclaredMethod(functionName);
			m.setAccessible(true);
			return m.invoke(a);
		} catch (IllegalAccessException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		} catch (InvocationTargetException e) {
			return null;
		} catch (NoSuchMethodException e) {
			return null;
		} catch (SecurityException e) {
			return null;
		}
	}
	
	/**
     * 执行类中的方法
     * @param Object ,class ,functionName,Object value
     * @return value
     */
	public static <T> void excuteSetFunction(Object obj,T a,String functionName,Object value){
		try {
			Method m = obj.getClass().getDeclaredMethod(functionName);
			m.setAccessible(true);
			m.invoke(a,value);
		} catch (IllegalAccessException e) {
			
		} catch (IllegalArgumentException e) {
			
		} catch (InvocationTargetException e) {
			
		} catch (NoSuchMethodException e) {
			
		} catch (SecurityException e) {
			
		}
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
		
		String s = "|字符串123456哈哈441";
		String chara = s;
		System.out.println("汉字:" + chara.replaceAll("[a-zA-Z0-9|]", ""));
        System.out.println("数字:" + s.replaceAll("[a-zA-Z0-9]", ""));
        System.out.println("数字:" + s.replaceAll("[a-zA-Z|]", ""));
        System.out.println("字符:" + s.replaceAll("[0-9|]", ""));
        
		int[] a = {1,3,2,4,6};
		sort(a);
		System.out.println(Math.ceil(0.0));
	}
}
