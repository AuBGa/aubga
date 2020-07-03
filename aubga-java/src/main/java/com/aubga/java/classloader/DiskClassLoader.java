/*package com.aubga.java.classloader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

import sun.misc.Resource;

public class DiskClassLoader extends URLClassLoader {

    public DiskClassLoader(URL path) throws MalformedURLException {
        super(new URL[]{path});
    }

    public DiskClassLoader(URL path, ClassLoader parent) throws MalformedURLException {
        super(new URL[]{path}, parent);
    }
    
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        final Class<?> result;
        try {
            result = AccessController.doPrivileged(
                new PrivilegedExceptionAction<Class<?>>() {
                    public Class<?> run() throws ClassNotFoundException {
                        // 类文件全路径
                        String path = name.replace('.', '/').concat(".class");
                        // 指定资源目录下查找
                        Resource res = ucp.getResource(path, false);
                        if (res != null) {
                            try {
                                // 调用defineClass生成类
                                return defineClass(name, res);
                            } catch (IOException e) {
                                throw new ClassNotFoundException(name, e);
                            }
                        } else {
                            return null;
                        }
                    }
                }, acc);
        } catch (java.security.PrivilegedActionException pae) {
            throw (ClassNotFoundException) pae.getException();
        }
        if (result == null) {
            throw new ClassNotFoundException(name);
        }
        return result;
    }
}*/