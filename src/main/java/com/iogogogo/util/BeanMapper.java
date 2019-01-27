package com.iogogogo.util;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tao.zeng on 2019/1/27.
 */
public class BeanMapper {

    private final static Object _LOCK = new Object();


    private static volatile DozerBeanMapper dozer = null;

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    public static DozerBeanMapper getDozer() {
        if (dozer == null) {
            synchronized (_LOCK) {
                if (dozer == null) {
                    dozer = new DozerBeanMapper();
                }
            }
        }
        return dozer;
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <T> List<T> map(Collection source, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<>();
        for (Object sourceObject : source) {
            T destinationObject = getDozer().map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        getDozer().map(source, destinationObject);
    }
}
