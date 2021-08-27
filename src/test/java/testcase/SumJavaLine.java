package testcase;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * 面试被问到统计一个文件有效代码行数的实现
 * */
public class SumJavaLine {
    private static int i;//代码总行数
    private static int j;//文件个数

    @Test
    public static void Run() throws IOException {
        File file = new File("D:\\work\\java-elf\\src\\main\\java\\com\\javaelf\\controller");//需要统计行数的文件夹路径
        traverseFiles(file);//调用递归方法查看.java文件，用于统计行数
        System.out.println("所写文件个数："+j);
        System.out.println("所写代码总行数："+i);
    }
    public static void traverseFiles(File file) throws IOException{
        if(!file.exists()){//文件不存在
            return;
        }

        if(!file.isDirectory()){//判断是否为文件
            String filename = file.getName();
            if(filename.endsWith(".java")){//判断是否是.java文件
                j++;
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String string =null;
                while ((string = bufferedReader.readLine()) != null) {
                    i++;//读取行数
                }
            }else
                return;
        }

        File[] files =file.listFiles();//读取文件夹的子文件或子文件夹
        if (files == null || files.length == 0) {
            return;
        }

        for(File file2 : files){//如果是文件夹递归调用方法遍历文件
            traverseFiles(file2);
        }
    }
}
