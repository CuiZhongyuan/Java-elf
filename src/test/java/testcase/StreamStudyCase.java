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

}
