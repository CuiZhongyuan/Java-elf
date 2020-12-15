package testcase;


import com.javaelf.entity.TestStreamEntity;
import com.javaelf.utils.JsonUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream流的操作练习
 * Stream（流）是一个来自数据源的元素队列并支持聚合操作
 * 元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
 * 数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
 * 聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
 *-----------------------------------------------------------------------------
 * 操作元素
 * （1）中间操作：流中间操作在应用到流上，返回一个新的流；
 * map：通过一个 Function 把一个元素类型为 T的流转换成元素类型为 R的流。
 * flatMap：扁平化流，通过一个 Function 把一个元素类型为 T 的流中的每个元素转换成一个元素类型为 R 的流，再把这些转换之后的流合并。
 * filter：过滤流中的元素，只保留满足由 Predicate 所指定的条件的元素。
 * distinct：筛选，通过流所生成元素的hashCode（）和equals去除重复元素
 * limit：截断流使其最多只包含指定数量的元素。
 * skip：跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit（n）互补
 * sorted：对流进行排序。
 * peek：返回的流与原始流相同。当原始流中的元素被消费时，会首先调用 peek 方法中指定的 Consumer 实现对元素进行处理。
 * dropWhile：从原始流起始位置开始删除满足指定 Predicate 的元素，直到遇到第一个不满足 Predicate 的元素。
 * takeWhile：从原始流起始位置开始保留满足指定 Predicate 的元素，直到遇到第一个不满足 Predicate 的元素。
 * ……
 * （2）终结操作：终结操作产生最终的结果；
 * forEach：遍历，自定义操作
 * allMatch：检查是否匹配所有元素
 * anyMatch：检查是否至少匹配一个元素
 * noneMatch：检查是否没有匹配所有元素
 * findFirst：返回第一个元素
 * findAny：返回当前流中的任意元素
 * count：返回流中元素的总个数
 * max：返回流中最大值
 * min：返回流中最小值
 *  reduce：归约，递归操作，将流的每个元素反复结合形成得到新值
 *  collect：收集，汇总操作（返回新集合、分组），接收一个Collector接口实现
 */
public class StreamStudy {
        /**
         * -Stream操作流，list+Array都可转换成流，操作方法雷同
         * 		转换流方式：
         * 			（1）集合：list.stream()
         * 			（2）数组：Stream.of(arr) 或  Arrays.stream(strArray);
         * @author hqc
         * @date 2020年11月20日
         */
        @Test
        public void tem() {
            /**
             * -初始化测试数据
             */
            List<Map<String,Object>> list = new ArrayList<>();
            List<TestStreamEntity> listModel = new ArrayList<>();

            //初始化map
            Map<String,Object> map1 = new HashMap<>();
            map1.put("name", "孙悟空");
            map1.put("age", 4);
            map1.put("sex", "女");
            map1.put("grade", 89);
            Map<String,Object> map2 = new HashMap<>();
            map2.put("name", "sixmonth");
            map2.put("age", 3);
            map2.put("sex", "男");
            map2.put("grade", 72);
            Map<String,Object> map3 = new HashMap<>();
            map3.put("name", "sixmonth");
            map3.put("age", 2);
            map3.put("sex", "女");
            map3.put("grade", 66);
            list.add(map1);
            list.add(map2);
            list.add(map3);
            //初始化实体对象
            listModel.add(new TestStreamEntity("sixmonth", "男",23,99));
            listModel.add(new TestStreamEntity("猪八戒", "男",678,56));
            listModel.add(new TestStreamEntity("孙悟空", "男",567,88));

            //初始化数组
            String[] arr = new String[] {"孙悟空","猪八戒","唐僧","唐僧"};
            Integer[] arrInt = new Integer[] {88,45,23,78};

            /**
             * -集合操作
             */
            //listMap>filter：过滤，返回新集合
            List<Map<String,Object>> resu = list.stream().filter(f -> f.get("sex").equals("女")).collect(Collectors.toList());
            System.out.println("resu:"+ JsonUtils.obj2json(resu));

            //listMap>map：修改对象值，返回新集合
            List<String[]> resu1 = list.stream().map(ma->((String) ma.get("name")).split("")).collect(Collectors.toList());
            System.out.println("resu1:"+JsonUtils.obj2json(resu1));

            //listMap>flatMap：修改对象值，扁平化处理（即map操作的所有单个流，都会扁平化合并成一个流，映射成流的内容），返回新集合
            List<String> resu2 = list.stream().map(ma->((String) ma.get("name")).split("")).flatMap(Arrays::stream).collect(Collectors.toList());
            System.out.println("resu2:"+JsonUtils.obj2json(resu2));

            //listMap>map：修改对象值，过滤重复对象值，返回新集合
            List<String> resu3 = list.stream().map(ma->((String)ma.get("name"))).distinct().collect(Collectors.toList());
            System.out.println("过滤重复值，resu3:"+JsonUtils.obj2json(resu3));

            //listModel>map+forEach：修改对象值，遍历集合
            listModel.stream().map(TestStreamEntity::getName).forEach(System.out::println);

            //listModel>sort：根据分数排序，升序，返回新集合
            List<TestStreamEntity> resu5 = listModel.stream().sorted(Comparator.comparing(TestStreamEntity::getGrade)).collect(Collectors.toList());
            System.out.println("根据分数排序，升序，返回新集合，resu5:"+JsonUtils.obj2json(resu5));
            //listModel>sort：根据分数排序，倒序，返回新集合
            List<TestStreamEntity> resu6 = listModel.stream().sorted(Comparator.comparing(TestStreamEntity::getGrade).reversed()).collect(Collectors.toList());
            System.out.println("根据分数排序，倒序，返回新集合，resu6:"+JsonUtils.obj2json(resu6));
            //listMap>sort：根据分数排序，升序，返回新集合
            List<Map<String,Object>> resu7 = list.stream().sorted((o1, o2) -> ((Integer) o1.get("grade")).compareTo((Integer)o2.get("grade"))).collect(Collectors.toList());
            System.out.println("根据分数排序，升序，返回新集合，resu7:"+JsonUtils.obj2json(resu7));

            //listModel>findFirst：查找符合条件的第一个元素，返回元素
            Map<String,Object>  resu11= list.stream().filter(x -> x.get("sex").equals("女")).findFirst().orElseGet(null);
            System.err.println("查找符合条件的第一个元素，返回元素，resu11:"+(resu11==null?"null":resu11.get("name")));

            //listModel>count：查找符合条件的元素，返回个数
            long  resu12= list.stream().filter(x -> x.get("sex").equals("女")).count();
            System.err.println("查找符合条件的元素，返回个数，resu12:"+resu12);

            //listModel>max：查找符合条件的最大元素，返回元素
            Integer maxGrade = listModel.stream().map(x->x.getGrade()).max(Integer::compareTo).orElse(0);
            System.err.println("查找符合条件的最大元素，返回元素，maxGrade:"+maxGrade);
            TestStreamEntity maxGradeModel = listModel.stream().max(Comparator.comparing(TestStreamEntity::getGrade)).orElse(null);
            System.err.println("查找符合条件的最大元素，返回元素，maxGradeModel:"+(maxGradeModel==null?"无":maxGradeModel.getName()));

            //listModel>min：查找符合条件的最小元素，返回元素
            Integer minGrade = listModel.stream().map(x->x.getGrade()).min(Integer::compareTo).orElse(0);
            System.err.println("查找符合条件的最小元素，返回元素，minGrade:"+minGrade);

            //listMap>max：查找符合条件的最大元素，返回元素
            Map<String,Object> maxGradeMap = list.stream().max((o1, o2) -> ((Integer) o1.get("grade")).compareTo((Integer)o2.get("grade"))).orElse(null);
            System.out.println("查找符合条件的最大元素，返回元素，maxGradeMap:"+(maxGradeMap==null?"无":maxGradeMap.get("name")));

            //listMap>reduce：计算分数总数，返回总数
//		Integer gradeSum = list.stream().map(x -> (Integer)x.get("grade")).reduce(Integer::sum).orElse(0);
            Integer gradeSum = listModel.stream().map(TestStreamEntity::getGrade).reduce(Integer::sum).orElse(0);
            System.err.println("计算分数总和，返回总数，gradeSum:"+gradeSum);

            //listMap>collect>groupBy：分组统计，返回分组集合
//		Map<Object, List<Map<String,Object>>> sexMap = list.stream().collect(Collectors.groupingBy(x->x.get("sex")));
            Map<Object, List<TestStreamEntity>> sexMap = listModel.stream().collect(Collectors.groupingBy(TestStreamEntity::getSex));
            System.err.println("根据性别分组，返回每种性别对应对象集合，sexMap:"+JsonUtils.obj2json(sexMap));

            //listMap>collect>groupBy：分组统计，返回分组总分数
//		Map<Object, Integer> sexMapSum = list.stream().collect(Collectors.groupingBy(x->x.get("sex"),Collectors.summingInt (x->(Integer)x.get("grade"))));
            Map<Object, Integer> sexMapSum = listModel.stream().collect(Collectors.groupingBy(TestStreamEntity::getSex,Collectors.summingInt (TestStreamEntity::getGrade)));
            System.err.println("根据性别分组，返回分组总分数，sexMapSum:"+JsonUtils.obj2json(sexMapSum));

            /**
             * -map操作
             */
            //Map>forEach：遍历map
            map1.forEach((k,v)->System.out.println("遍历map："+k+","+v));

            //Map>forEach：流遍历map
            map1.entrySet().stream().forEach(x -> System.out.println("流遍历map："+x.getValue()));

            /**
             * -数组操作
             */
            //Array>forEach：遍历数组
            Stream.of(arr).forEach(System.out::println);

            //Array>distinct+forEach：过滤重复值，遍历集合
            Stream.of(arr).distinct().forEach(x ->{System.out.println("过滤重复值："+x);});

            //Array>limit+forEach：限制输出对象数量，遍历集合
            Stream.of(arr).limit(2).forEach(x ->{System.out.println("限制输出对象："+x);});

            //Array>limit+forEach：跳过n个对象数量，返回集合
            List<String> resu4 = Stream.of(arr).skip(2).collect(Collectors.toList());
            System.out.println("跳过n个对象，返回集合，resu4:"+JsonUtils.obj2json(resu4));

            //Array>allMatch：是否匹配所有元素，返回boolean
            boolean resu8 = Stream.of(arr).allMatch(x -> x.equals("孙悟空"));
            System.out.println("是否匹配所有元素，返回boolean，resu8:"+resu8);

            //Array>anyMatch：是否存在元素，返回boolean
            boolean resu9 = Stream.of(arr).anyMatch(x -> x.equals("孙悟空"));
            System.out.println("是否匹配所有元素，返回boolean，resu9:"+resu9);

            //Array>noneMatch：是否不存在元素，返回boolean
            boolean resu10 = Stream.of(arr).noneMatch(x -> x.equals("白骨精"));
            System.out.println("是否匹配所有元素，返回boolean，resu10:"+resu10);

            //Array>reduce：是否不存在元素，返回boolean
            Integer sum = Stream.of(arrInt).reduce(0, Integer::sum);
            System.out.println("计算分数总和，返回总数，sum:"+sum);

            //Array>max：查找最大值，返回最大值
            Integer arrMax = Stream.of(arrInt).max(Integer::compareTo).orElse(0);
            System.out.println("查找最大值，返回最大值，arrMax:"+arrMax);
        }

}
