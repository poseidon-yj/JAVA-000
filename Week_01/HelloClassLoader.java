//package JAVA000.JVM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class HelloClassLoader extends ClassLoader {
  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    File f = new File(this.getClass().getResource("Hello.xlass").getPath());
    int length = (int)f.length();
    byte[] bytes = new byte[length];
    try {
      new FileInputStream(f).read(bytes);
    }catch (IOException e){
      e.printStackTrace();
    }
    for (int i = 0; i < bytes.length ; i++) {
      bytes[i] = (byte)(255 - bytes[i]);
    }
    return defineClass(name, bytes, 0, bytes.length);
  }

  public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException,
          IllegalAccessException, InstantiationException, NoSuchMethodException{
    HelloClassLoader helloClassLoader = new HelloClassLoader();
    Class<?> helloClass = helloClassLoader.findClass("Hello");
    Object obj = helloClass.newInstance();
    helloClass.getMethod("hello").invoke(obj);

  }
}
