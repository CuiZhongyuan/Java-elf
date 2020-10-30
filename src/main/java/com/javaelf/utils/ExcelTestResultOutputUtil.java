package com.javaelf.utils;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.javaelf.dto.InterfaceMessageDto;
import com.javaelf.dto.TestCaseDto;
import com.javaelf.dto.VariableDto;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelTestResultOutputUtil {
    /**
     * 功能描述：把同一个表格多个sheet测试结果重新输出，如果后续增加多个List<Map<String, Object>>对象，需要后面继续追加
     * @ExcelEntiry sheet表格映射的实体对象
     * @return
     */
    public static String exportSheet( Object...objects){

        Workbook workBook = null;
        try {
            /**
             * 第一个sheet表设置
             */
            // 创建参数对象（用来设定excel得sheet得内容等信息）
            ExportParams deptExportParams = new ExportParams();
            // 设置sheet1得名称
            deptExportParams.setSheetName("被测接口信息");
            // 设置sheet表头名称
            deptExportParams.setTitle("被测接口信息");
            // 创建sheet1使用得map
            Map<String, Object> deptExportMap = new HashMap<>();
            // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
            deptExportMap.put("title", deptExportParams);
            // 模版导出对应得实体类型
            deptExportMap.put("entity", InterfaceMessageDto.class);
            // sheet2中要填充得数据
            deptExportMap.put("data", objects[0]);
            /**
             * 第二个sheet表设置
             */
            ExportParams empExportParams = new ExportParams();
            empExportParams.setTitle("被测接口覆盖用例集");
            empExportParams.setSheetName("接口测试用例");
            // 创建sheet2使用得map
            Map<String, Object> empExportMap = new HashMap<>();
            empExportMap.put("title", empExportParams);
            empExportMap.put("entity", TestCaseDto.class);
            empExportMap.put("data", objects[1]);

            /**
             * 第三个sheet表设置
             */
            ExportParams variExportParams = new ExportParams();
            variExportParams.setTitle("变量信息");
            variExportParams.setSheetName("变量");
            // 创建sheet2使用得map
            Map<String, Object> variempExportMap = new HashMap<>();
            variempExportMap.put("title", variExportParams);
            variempExportMap.put("entity", VariableDto.class);
            variempExportMap.put("data", objects[2]);

            // 将sheet1、sheet2使用得map进行包装
            List<Map<String, Object>> sheetsList = new ArrayList<>();
            sheetsList.add(deptExportMap);
            sheetsList.add(empExportMap);
            sheetsList.add(variempExportMap);
            // 执行方法
            workBook = com.javaelf.utils.EasyPoiUtil.exportExcel(sheetsList, ExcelType.HSSF);
            //String fileName = URLEncoder.encode("test", "UTF-8");
            String filepath = (String) LoadStaticConfigUtil.getCommonYml( "testcaseexcel.path");
            FileOutputStream fos = new FileOutputStream(filepath);
            workBook.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(workBook != null) {
                try {
                    workBook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }

}
