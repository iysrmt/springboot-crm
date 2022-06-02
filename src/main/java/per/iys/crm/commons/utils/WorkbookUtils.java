package per.iys.crm.commons.utils;

import org.apache.poi.ss.usermodel.Cell;

/**
 * 关于excel文件操作工具类
 */
public class WorkbookUtils {
    private WorkbookUtils() {
    }

    public static String getCellValueForString(Cell cell) {
        String ret = "";
        switch (cell.getCellType()) {
            case NUMERIC:
                ret = "" + cell.getNumericCellValue();
                break;
            case FORMULA:
                ret = cell.getCellFormula();
                break;
            case STRING:
                ret = cell.getStringCellValue();
                break;
            case BLANK:
                ret = "<BLANK>";
                break;
            case BOOLEAN:
                ret = "" + cell.getBooleanCellValue();
            case ERROR:
                ret = "" + cell.getErrorCellValue();
                break;
            default:
                ret = "" + cell.getCellType();
        }
        return ret;
    }
}
