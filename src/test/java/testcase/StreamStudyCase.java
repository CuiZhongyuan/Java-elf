package testcase;

import com.javaelf.entity.TestStreamEntity;
import com.javaelf.utils.JsonUtils;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class StreamStudyCase {
    List<TestStreamEntity> listModel = new ArrayList<>();
    //初始化数组
    String[] arr = new String[] {"孙悟空","猪八戒","唐僧","唐僧"};
    Integer[] arrInt = new Integer[] {88,45,23,78};
    String[] strList = new String[]{"test01","test002","test0003","test00004"};

    List<Map<String,Object>> list = new ArrayList<>();
    /**
     * listMap ->filter 使用stream流的filter过滤，返回新的集合；
     * */
    @Test
    public void streamCase1(){
        Map map1 = new HashMap();
        map1.put("sex","男");
        map1.put("age",22);
        Map map2 = new HashMap();
        map2.put("sex","女");
        map2.put("age",20);
        list.add(map1);
        list.add(map2);
        List<Map<String,Object>> listFilter = list.stream().filter(f -> f.get("sex").equals("女"))
                .collect(toList());
        System.out.println(JsonUtils.obj2json(listFilter));
        System.out.println(JsonUtils.obj2json(list));
    }
    /**
     * listMap ->map 修改对象值返回新的集合(split)切割
     *
     * listMap>flatMap：修改对象值，扁平化处理（即map操作的所有单个流，都会扁平化合并成一个流，映射成流的内容），返回新集合
     * */
   @Test
    public void streamCase2(){
        Map map1 = new HashMap();
        map1.put("sex","男");
        map1.put("age",22);
        Map map2 = new HashMap();
        map2.put("sex","女");
        map2.put("age",20);
        list.add(map1);
        list.add(map2);
        List<String[]> listMap = list.stream().map(m ->
                        ((String)m.get("sex")).split("")).collect(Collectors.toList());
        List<String> listString = list.stream().map(ma->
                ((String) ma.get("sex")).split("")).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println(JsonUtils.obj2json(list));
        System.out.println(JsonUtils.obj2json(listMap));
        System.out.println(listString);
    }
    /**
     * 过滤map的value参数值相同对象，返回值新的集合
     * */
    @Test
    public void streamCase3(){
        Map map1 = new HashMap();
        map1.put("sex","男");
        map1.put("age",22);
        Map map2 = new HashMap();
        map2.put("sex","女");
        map2.put("age",20);
        Map map3 = new HashMap();
        map3.put("sex","女");
        map3.put("age",20);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        List<String> listDistinct = list.stream().map(m ->
                ((String)m.get("sex"))).distinct().collect(Collectors.toList());
        System.out.println(JsonUtils.obj2json(listDistinct));
    }
    /**
     * 修改对象值，遍历集合
     * */
    @Test
    public void streamCase4(){
        Map<String,Object> resultMap = new HashMap<>();
        List<Map<String,Object>> listMap = new ArrayList<>();
        //初始化实体对象
        listModel.add(new TestStreamEntity("sixmonth", "男",23,99));
        listModel.add(new TestStreamEntity("猪八戒", "男",678,56));
        listModel.add(new TestStreamEntity("孙悟空", "男",567,88));
        // JDK8语法糖----类名::方法名
         listModel.stream().forEach( m ->{
             String str = m.getSex().replace("男","女");
             resultMap.put("sex",str);
             resultMap.put("name",m.name);
             resultMap.put("age",m.age);
             listMap.add(resultMap);
         });
        System.out.println(JsonUtils.obj2json(listMap));
    }
    //Lsit集合是可重复、有序存入取出
    @Test
    public void case5(){
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("A");
        System.out.println(list);
    }
    //Set集合，没有重复元素，存取无序
    @Test
    public void case6(){
        int[] num = {2,5,7,5};
        Set<String> set = new HashSet<>();
        for (int i =0 ; i<num.length;i++){
            set.add(String.valueOf(num[i]));
        }
        String str = "anah454";
        String str1 = str.replace("a","bb");
        String[] str2 = str.split("h");
        System.out.println(Arrays.toString(str2));
        System.out.println(str1);
        System.out.println(set);

//        set.add("a");
//        set.add("c");
//        set.add("b");
//        System.out.println(set);
    }
    //遍历Map的方法
    @Test
    public void mapFor(){
        //方法1：通过键找值遍历
        Map<String,Object> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        for (String key : map.keySet()){
            System.out.println(key+":"+map.get(key));
        }
    }
    @Test
    public void mapFor1(){
        //方法1：通过foreach循环遍历
        Map<String,Object> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.forEach((key,value) ->{
            System.out.println(key + ":"+ value);
        });
    }

    @Test
    public void mapFor2(){
        //方法1：通过stream流遍历
        Map<String,Object> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.entrySet().stream().forEach((entry) ->{
            System.out.println(entry.getKey() + ":" + entry.getValue());
        });
    }

    @Test
    public void arrayList(){
        int a = 0;
        int length = 2;
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>(length);
        List<Map> list2 = new ArrayList<>();
         long start = System.currentTimeMillis();
        for (int i = 0 ; i<length;i++) {
            if (a == 0) {
                list.add(length);
                list.add(11);
            }
        }
         long end = System.currentTimeMillis();
        System.out.println(list.toString()+":"+(end-start));
        Map<String,Object> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.forEach((key,value)->{
            System.out.println(key+value);
        });
        list2.add(map);
        System.out.println(list2.toString());
    }
    /**
     * 使用lambda 奥义一个接一个处理，直到业务处理完成
     * */
    @Test
    public void lambadTest(){
        List list = new ArrayList(Arrays.asList(strList));
        list.stream().filter(s -> s.toString().length() > 7).forEach(System.out::println);
    }

    /**
     * 使用collector 重新生产一个新的list
     * */
    @Test
    public void lambdaList(){
        List list = new ArrayList(Arrays.asList(strList));
        List<String> list1 = (List<String>) list.stream().filter(s -> s.toString().length() > 7).collect(Collectors.toList());
        list1.forEach(System.out::println);
    }

}
