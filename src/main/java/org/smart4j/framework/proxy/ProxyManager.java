package org.smart4j.framework.proxy;

import java.lang.reflect.Method;
import java.util.List;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理管理器
 *
 * @author huangyong
 * @since 1.0.0
 */
public class ProxyManager {

    /**
     *  提供创建代理对象的方法
     *
     * @param targetClass 目标类
     * @param proxyList 代理接口
     * @return 代理对象
     */
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {
        //使用cglib提供的Enhancer.create方法来创建代理对象
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList).doProxyChain();
            }
        });
    }
}