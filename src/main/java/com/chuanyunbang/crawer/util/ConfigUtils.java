package com.chuanyunbang.crawer.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Luffy
 * @date 2018/7/12
 * @description 本地配置load
 */
public class ConfigUtils extends PropertyPlaceholderConfigurer {

    private static Map<String, String> properties = new HashMap<String, String>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String strKey = key.toString();
            String value = props.getProperty(strKey);
            properties.put(strKey, value);
        }
    }
    public static String getString(String key){
        if(properties == null){
            return null;
        }
        return  properties.get(key);
    }
    public static Integer getInt(String key){
        String value = properties.get(key);
        if(value == null){
            return null;
        }
        try{
            return Integer.parseInt(value);
        }catch(Exception e){
            return null;
        }
    }
    public static Map<String, String> getProperties() {
        return properties;
    }
}
