package com.jay.spi;

import java.util.ServiceLoader;

/**
 * created by Jay on 2019/7/26
 */
public class SPITest
{
    public static void main(String[] args)
    {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        serviceLoader.forEach(Robot::sayHello);
    }
}
