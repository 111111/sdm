package com.sdm.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 * Created by qinyun.
 * 2017/12/7 14:35
 */
public class ExcelUtil {

    public static List<List<Object>> readExcel(String path) throws IOException {
        File file = new File(path);
        return readExcel(file);
    }

    public static List<List<Object>> readExcel(File file) throws IOException {
        String fName = file.getName();
        String extension = fName.lastIndexOf(".") == -1 ? "" : fName
                .substring(fName.lastIndexOf(".") + 1);
        if ("xls".equals(extension)) {// 2003
            System.err.println("读取excel2003文件内容");
            return read2003Excel(file);
        } else if ("xlsx".equals(extension)) {// 2007
            System.err.println("读取excel2007文件内容");
            return read2007Excel(file);
        } else {
            throw new IOException("不支持的文件类型:" + extension);
        }
    }
    /**
     * 读取2003excel
     *
     * @param file
     * @return
     */
    private static List<List<Object>> read2003Excel(File file)
            throws IOException {
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        Object val = null;
        DecimalFormat df = new DecimalFormat("0");// 格式化数字
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
        for (int i = sheet.getFirstRowNum(); i < sheet
                .getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> objList = new ArrayList<Object>();
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    val = null;
                    objList.add(val);
                    continue;
                }
                switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_STRING:
                        val = cell.getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            val = df.format(cell.getNumericCellValue());
                        } else if ("General".equals(cell.getCellStyle()
                                .getDataFormatString())) {
                            val = df.format(cell.getNumericCellValue());
                        } else {
                            val = sdf.format(HSSFDateUtil.getJavaDate(cell
                                    .getNumericCellValue()));
                        }
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        val = cell.getBooleanCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_BLANK:
                        val = "";
                        break;
                    default:
                        val = cell.toString();
                        break;
                }
                objList.add(val);
            }
            dataList.add(objList);
        }
        return dataList;
    }

    /**
     * 读取excel表头
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String[] readExcelHead(File file) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        row = sheet.getRow(0);
        String[] buff = new String[row.getLastCellNum()];
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            cell = row.getCell(i);
            buff[i] = cell.getStringCellValue();
        }
        return buff;
    }
    /**
     * 读取2007excel
     *
     * @param file
     * @return
     */

    private static List<List<Object>> read2007Excel(File file)
            throws IOException {
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = xwb.getSheetAt(0);
        XSSFRow row = null;
        XSSFCell cell = null;
        Object val = null;
        DecimalFormat df = new DecimalFormat("0");// 格式化数字
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
        for (int i = sheet.getFirstRowNum(); i < sheet
                .getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> objList = new ArrayList<Object>();
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    val = null;
                    objList.add(val);
                    continue;
                }
                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        val = cell.getStringCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            val = df.format(cell.getNumericCellValue());
                        } else if ("General".equals(cell.getCellStyle()
                                .getDataFormatString())) {
                            val = df.format(cell.getNumericCellValue());
                        } else {
                            val = sdf.format(HSSFDateUtil.getJavaDate(cell
                                    .getNumericCellValue()));
                        }
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        val = cell.getBooleanCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        val = "";
                        break;
                    default:
                        val = cell.toString();
                        break;
                }
                objList.add(val);
            }
            dataList.add(objList);
        }
        return dataList;
    }

    public static void main(String...args)throws Exception{
        String path = "I:\\行政区划清单 V3.0 9.03.xlsx";
        List<List<Object>> list = readExcel(path);
        for(List<Object> list1 : list){
            Object[] o = list1.toArray();

            System.out.println(o[0] + "," + o[1] + "," + o[2] + "," + o[3] + "," + o[4] + "," + o[5] + "," + o[6]);
        }
    }
}
