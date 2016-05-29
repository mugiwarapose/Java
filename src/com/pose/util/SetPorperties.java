package com.pose.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.pose.po.Person;
/**
 * 
 * @author zhangdd
 * JavaBean ���ò���
 */
public class SetPorperties {
	/**
	 * 
	 * @param object
	 * @param params ��Ա�������ƺ�ֵ�Լ�ֵ����ʽ�洢
	 */
	public static void setPro(Object object,Map<String,Object> params){
		/*String[] array  =new String[params.size()];
		params.keySet().toArray(array);*/
		
		Class clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String[] fieldsName = new String[fields.length];
		for(int i=0;i<fields.length;i++){
			fieldsName[i] = fields[i].getName();
		}
		
		for(Map.Entry<String, Object> entry :params.entrySet()){
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			for(int i=0;i<fieldsName.length;i++){
				//�����ֵ��object��Ա����ƥ��
				if(entry.getKey().equalsIgnoreCase(fieldsName[i])){
					try {
						//����ֵ
						PropertyDescriptor descriptor =new PropertyDescriptor(fieldsName[i], clazz);
						Method method = descriptor.getWriteMethod();
						method.invoke(object, entry.getValue());
					} catch (IntrospectionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
		}
		
		
	}
	
	/**
	 * Test
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String,Object> params = new HashMap<String ,Object>();
		params.put("name", "Json");
		params.put("age",11);
		params.put("address", "american shengdiyage");
		
		setPro(new Person(), params);
	}
}
