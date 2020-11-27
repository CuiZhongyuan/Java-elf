package testcase;

import com.javaelf.utils.JsonUtils;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import java.util.*;

public class Algorithm {
    int[] numss = {56,47,25,89,46,5,15,23};

    //===================================二分查找，使用了递归调用==========================

    @Test
    public int binarySearch(int[] nums,int start,int end,int findnum){
        if (start <= end) {
            //取出中间位置
            int binarynum = (start + end) / 2;
            //取中间位置的值
            int binarynumValue = nums[binarynum];
            //判断
            if (findnum == binarynumValue) {
                return binarynum;
            } else if (findnum < binarynumValue) {
                return binarySearch(nums, start, binarynum - 1, findnum);
            } else {
                return binarySearch(nums, binarynum + 1, end, findnum);
            }
        }
        return -1;
    }

    @Test
    public void test(){
        int[] nums = new int[100];
        for (int i=0;i<100;i++){
            nums[i] = (i+1);
        }
        int findnum = 25;
        int result = binarySearch(nums,0,nums.length-1,findnum);
        //查索引
        System.out.println(result+1);
    }



    @Test
    public void case1(){
        int[] nums = {5,4,9,24,7,21,11,64,45,42};
        //冒泡排序
        bubbleSort(nums);
        //选择排序
        selectSort(nums);
    }
    //==============================冒泡排序【重复走访要排序的数列，每次比较两个元素，如果顺序不对就进行交换，直到没有被交换的元素为止，这样就完成了一次冒泡排序】===========================
   //调用冒泡排序方法
    private void bubbleSort(int[] nums) {
        for (int i=1 ;i<nums.length;i++){
            for (int j=0; j<nums.length-i;j++){
                if (nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] =nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    //==============================选择排序【依次循环数组，每轮找出最小的值放到数组的最前面，直到循环结束就能得到一个有序数组】===========================
   //调用选择排序
    private void selectSort(int[] nums) {
        int index;
        int temp;
        for (int i =0;i<nums.length;i++){
            index =i;
            for (int j=i+1;j<nums.length;j++){
                if (nums[j] < nums[index]){
                    index=j;
                }
            }
            if (index !=i){
                temp=nums[i];
                nums[i]=nums[index];
                nums[index]=temp;
            }
            System.out.println("第"+i+"次排序");
            System.out.println(Arrays.toString(nums));
        }

    }

    //练手
    @Test
    public void  test1(){
        //定义初始数组
        int[] nums = new int[100];
        for (int i= 0 ; i<nums.length;i++){
            nums[i]=i+1;
        }
        //二分查到
        //要查找的数字
        int findNum = 56;
        //接收要查找的位置
        int result = binarySearch1(nums,0,nums.length-1,findNum);
        System.out.println(result+1);
        //冒泡排序
        int[] numss = {56,47,25,89,46,5,15,23};
        bubbleSort1(numss);
        //选择排序
        searchSort1(numss);
    }

    private void searchSort1(int[] numss) {
        int index;
        int temp;
        for (int i=0;i<numss.length;i++){
            index=i;
            for (int j =i+1;j<numss.length;j++){
                if (numss[j]<numss[index]){
                    index=i;
                }
            }
            if (index!=i){
                temp=numss[i];
                numss[i]=numss[index];
                numss[index]=temp;
            }
        }
        System.out.println(Arrays.toString(numss));

    }

    private int binarySearch1(int[] nums, int i, int i1, int findNum) {
        if (i<=i1) {
            int middlePosition = (i + i1) / 2;
            int middleNum = nums[middlePosition];
            if (middleNum == findNum) {
                return middlePosition;
            } else if (middleNum > findNum) {
                return binarySearch(nums, 0, middlePosition - 1, findNum);
            } else {
                return binarySearch(nums, middlePosition + 1, i1, findNum);
            }
        }
        return -1;
    }

    //冒泡排序
    public void bubbleSort1(int[] nums){
        int temp;
        for (int i=1;i<nums.length;i++){
            for (int j=0;j<nums.length-i;j++){
                if (nums[j]>nums[j+1]){
                    temp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void toLowerCase(){
        String str = "ACCjkOOlKjhG";
        System.out.println(str.toLowerCase(Locale.forLanguageTag(str)));
    }
    @Test
    public void toUpperCase(){
        String str = " ACCjkOOjlKjhG ";
        System.out.println(str.toUpperCase(Locale.forLanguageTag(str)));
        System.out.println(str.length());
        System.out.println(str.trim());
        System.out.println(str.replaceAll("A","D"));
        String[] resout= str.split("j");
        System.out.println(Arrays.toString(resout));
        System.out.println(Strings.join(",",resout));
    }


    //stream流去重
    @Test
    public void array(){
        int[] numss = {56,47,25,47,56};
        Set<String> stringSet = new HashSet<>();
        String num= "5,4,[1,2,4,4,5]";
        int[] ints = Arrays.stream(numss).distinct().toArray();
        System.out.println(Arrays.toString(ints));
        num.replace("[","");
        num.replace("]","");
        String[] s = num.split(",");
        Object[] objects = Arrays.stream(s).distinct().toArray();
        System.out.println(Arrays.toString(objects));
    }

    @Test
    public void binarySearc2(){
        int[] a = {1,2,4,4,5};
        int findnum = 4;
        int result = upper_bound_(5,findnum,a);
        System.out.println(result);
        int result1 = binarySearch(a,0,5,findnum);
        System.out.println(result1+1);
      }
    /**
         * 二分查找
         * @param n int整型 数组长度
         * @param v int整型 查找值
         * @param a int整型一维数组 有序数组
         * @return int整型
         */

        public int upper_bound_ (int n, int v, int[] a) {
            int right = n - 1;
            int left = 0;
            int middle = 0;

            // 循环不变量：
            // 若v能被找到，则保证v在[left, right]这个左闭右闭区间中
            while (left <= right) {
                int mid = left + (right - left) / 2;
                // mid处的值和v相等，但是它可能并不是数组中第一个v
                // 因此继续缩小查找范围，排除掉重复的元素
                if (a[mid] >= v) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 循环结束时left > right
            // 分为2种情况：
            // 情况1：找到v，此时left指向v
            // 情况2：未找到v，此时left指向v应该被插入的位置
            // 因为该题索引从1开始，因此返回值要加1
            return left + 1;
        }

        //利用Arrays中的Sort函数排序
        @Test
        public void ArraysSort() {
        Arrays.sort(numss); //默认升序排列
        for (int i = 0; i < numss.length; i++) {
            System.out.print(numss[i]+"、");
        }}
}
