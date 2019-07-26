package com.jay.spi;

import com.sun.org.apache.bcel.internal.generic.FADD;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
         * 调用只有一个参数的Class.forName方法, 该class会初始化, 即会调用static块方法(源码)
         *
         */
//        Class.forName("com.jay.spi.Person");

        /**
         * 调用forName方法时, 不初始化类, 因为指定了第二个参数initialize为false
         */
        Class<?> clazz = Class.forName("com.jay.spi.Person", false, ClassLoader.getSystemClassLoader());
        System.out.println("begin get a new instance");
        /**
         * 调用newInstance()方法时, 会执行初始化
         */
        Person person = (Person) clazz.newInstance();
    }

    @Test
    public void testSPI_JDBC() throws SQLException
    {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "phenix";
        Connection conn = null;
        try
        {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        String sql = "insert into t_student (name) values(?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "xiadijun");
        int i = pstmt.executeUpdate();
//        conn.commit();  Can't call commit when autocommit=true
        pstmt.close();
        conn.close();

    }


}
