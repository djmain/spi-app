package com.jay.spi;

import com.sun.org.apache.bcel.internal.generic.FADD;
import org.junit.Test;

import java.util.ServiceLoader;

/**
 * created by Jay on 2019/7/26
 */
public class SPITest
{
    public static void main(String[] args)
    {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
//        serviceLoader.forEach(Robot::sayHello);
        for (Robot robot : serviceLoader)
        {
            robot.sayHello();
        }
    }

    @Test
    public void testClassForName() throws Exception
    {
        /**
         * 调用只有一个参数的Class.forName方法, 该class会初始化, 即会调用static块方法
         */
//        Class.forName("com.jay.spi.Person");

        /**
         * 调用forName方法时, 不初始化类, 指定第二个参数initialize为false
         */
        System.out.println("====");
        Class.forName("com.jay.spi.Person", false, ClassLoader.getSystemClassLoader());
    }

}
