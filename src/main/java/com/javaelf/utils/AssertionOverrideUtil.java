package com.javaelf.utils;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 重写断言方法，捕获异常程序继续执行
 *
 * @author czy
 * @date 2020/7/15
 */
public class AssertionOverrideUtil {

        public static boolean flag = true;

        public static List<Error> errors = new ArrayList<Error>();

        public static void verifyEquals(Object actual, Object expected){
            try{
                Assert.assertEquals(actual, expected);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(Object actual, Object expected, String message){
            try{
                Assert.assertEquals(actual, expected, message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyTrue(Boolean actual ){
            try{
                Assert.assertTrue(actual);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyTrue(Boolean actual,String message ){
            try{
                Assert.assertTrue(actual,message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyFalse(Boolean actual ){
            try{
                Assert.assertFalse(actual);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyFalse(Boolean actual ,String message ){
            try{
                Assert.assertFalse(actual,message );
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyNotNull(Object actual ){
            try{
                Assert.assertNotNull(actual);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyNotNull(Object actual  ,String message){
            try{
                Assert.assertNotNull(actual,message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyNull(Object actual ){
            try{
                Assert.assertNull(actual);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyNull(Object actual,String message ){
            try{
                Assert.assertNull(actual,message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(String  actual,String expected ){
            try{
                Assert.assertEquals(actual,expected);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(String  actual,String expected,String message ){
            try{
                Assert.assertEquals(actual,expected,message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(int actual,int expected ){
            try{
                Assert.assertEquals(actual,expected);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(int actual,int expected,String message ){
            try{
                Assert.assertEquals(actual,expected,message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }



        public static void verifyEquals(long actual,long expected ){
            try{
                Assert.assertEquals(actual,expected);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(long actual,long expected,String message ){
            try{
                Assert.assertEquals(actual,expected,message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(boolean actual,boolean expected ){
            try{
                Assert.assertEquals(actual,expected);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(boolean actual,boolean expected ,String message){
            try{
                Assert.assertEquals(actual,expected,message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(short actual,short expected ){
            try{
                Assert.assertEquals(actual,expected);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(short actual,short expected,String message ){
            try{
                Assert.assertEquals(actual,expected,message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(byte actual,byte expected ){
            try{
                Assert.assertEquals(actual,expected);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(byte actual,byte expected ,String message){
            try{
                Assert.assertEquals(actual,expected,message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(char actual,char expected ){
            try{
                Assert.assertEquals(actual,expected);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyEquals(char actual,char expected,String message ){
            try{
                Assert.assertEquals(actual,expected,message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyNotEquals(String actual,String expected ){
            try{
                Assert.assertNotEquals(actual,expected);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyNotEquals(String actual,String expected,String message ){
            try{
                Assert.assertNotEquals(actual,expected,message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }

        public static void verifyfail(String message ){
            try{
                Assert.fail(message);
            }catch(Error e){
                errors.add(e);
                flag = false;
            }
        }
}
