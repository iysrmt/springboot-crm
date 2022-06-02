package per.iys.crm.workbench.web.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import per.iys.crm.commons.constants.Constants;
import per.iys.crm.commons.domain.ReturnObject;
import per.iys.crm.commons.utils.DateUtils;
import per.iys.crm.commons.utils.UUIDUtils;
import per.iys.crm.commons.utils.WorkbookUtils;
import per.iys.crm.settings.domain.User;
import per.iys.crm.settings.service.UserService;
import per.iys.crm.workbench.domain.Activity;
import per.iys.crm.workbench.domain.ActivityRemark;
import per.iys.crm.workbench.service.ActivityRemarkService;
import per.iys.crm.workbench.service.ActivityService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {

    private UserService userService;
    private ActivityService activityService;
    private ActivityRemarkService activityRemarkService;

    @Autowired
    public ActivityController(UserService userService, ActivityService activityService, ActivityRemarkService activityRemarkService) {
        this.userService = userService;
        this.activityService = activityService;
        this.activityRemarkService = activityRemarkService;
    }

    @GetMapping("/")
    public String index(Model model) {
        // 查询所有用户
        List<User> users = userService.queryAllUsers();
        // 把数据保存到请求作用域中
        model.addAttribute("users", users);
        return "workbench/activity/index";
    }

    // 创建市场活动
    @PostMapping("/")
    @ResponseBody
    public Object saveCreateActivity(HttpSession session, Activity activity) {
        // 继续封装参数
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.format(new Date()));
        activity.setCreateBy(user.getId());

        ReturnObject returnObject = new ReturnObject();
        try {
            // 调用service方法
            int ret = activityService.saveCreateActivity(activity);
            if (ret > 0) {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_SUCCESS);
            } else {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
                returnObject.setMessage("系统繁忙, 请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
            returnObject.setMessage("系统繁忙, 请稍后重试...");
        }
        return returnObject;
    }

    // 查询市场活动
    @PostMapping("/queryActivityByConditionForPage")
    @ResponseBody
    public Object queryActivityByConditionForPage(String name, String owner, String startDate, String endDate, int pageNo, int pageSize) {
        // 封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        // 调用service
        List<Activity> activities = activityService.queryActivityByConditionForPage(map);
        int totalRows = activityService.queryCountOfActivityByCondition(map);

        // 根据结果响应信息
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("activityList", activities);
        retMap.put("totalRows", totalRows);
        return retMap;
    }

    // 根据id删除市场活动
    @DeleteMapping("/")
    @ResponseBody
    public Object deleteActivityByIds(String[] id) {
        ReturnObject returnObject = new ReturnObject();
        try {
            // 调用service方法删除市场活动
            int ret = activityService.deleteActivityByIds(id);
            if (ret > 0) {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_SUCCESS);
            } else {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
                returnObject.setMessage("系统繁忙, 请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
            returnObject.setMessage("系统繁忙, 请稍后重试...");
        }
        return returnObject;
    }

    // 市场活动 修改按钮 第一步 查询市场活动信息 展示到模态窗口
    @PostMapping("/queryActivityById")
    @ResponseBody
    public Object queryActivityById(String id) {
        // 调用service方法查询市场活动
        return activityService.queryActivityById(id);
    }

    // 更新市场活动, 更新按钮, 更新数据.
    @PutMapping("/")
    @ResponseBody
    public Object saveEditActivityById(HttpSession session, Activity activity) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        activity.setEditTime(DateUtils.format(new Date()));
        activity.setEditBy(user.getId());
        ReturnObject returnObject = new ReturnObject();
        try {
            // 调用service方法
            int ret = activityService.saveEditActivityById(activity);
            if (ret > 0) {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_SUCCESS);
            } else {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
                returnObject.setMessage("系统繁忙, 请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
            returnObject.setMessage("系统繁忙, 请稍后重试...");
        }
        return returnObject;
    }

    // 下载市场活动为Excel表格
    @GetMapping(value = "/exportAllActivities", produces = "application/octet-stream;charset=utf-8")
    public void exportAllActivities(HttpServletResponse response) throws IOException {
        // 调用service方法获取所有市场活动
        List<Activity> activityList = activityService.queryAllActivities();

        // excel文件
        Workbook workbook = null;
        // 工作表
        Sheet sheet = null;
        // 行
        Row row = null;
        // 列
        Cell cell = null;

        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("市场活动");
        row = sheet.createRow(0); // 第1行
        cell = row.createCell(0); // 第1列列
        cell.setCellValue("id"); // 1行一列内容
        cell = row.createCell(1); // 第1列列
        cell.setCellValue("所有者"); // 1行2列内容...
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");

        Activity activity = null;
        if (activityList != null) {
            for (int i = 0; i < activityList.size(); i++) {
                activity = activityList.get(i);
                row = sheet.createRow(i + 1); // 第二行...第三行...
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }

        // response.setContentType("application/octet-stream;charset=utf-8");
        // 获取输出流
        ServletOutputStream out = null;
        /*
            浏览器接收到响应信息之后, 默认情况下会直接显示窗口打开响应信息, 即使不打开也会调用应用程序打开
            只有实在打不开的时候才会激活下载. 甚至现在的浏览器比较智能可以直接打开Excel表格
            关闭默认行为, 使浏览器始终激活下载.
         */
        response.addHeader("Content-Disposition", "attachment;filename=marketingActivity.xlsx");
        out = response.getOutputStream();
        // 根据workbook生成excel文件
        workbook.write(out);
        out.flush();
    }


    // 选择下载Excel表格
    @GetMapping(value = "/queryActivitiesByIds", produces = "application/octet-stream;charset=utf-8")
    public void queryActivitiesByIds(HttpServletResponse response, String[] id) throws IOException {
        List<Activity> activityList = activityService.queryActivitiesByIds(id);

        // excel文件
        Workbook workbook = null;
        // 工作表
        Sheet sheet = null;
        // 行
        Row row = null;
        // 列
        Cell cell = null;

        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("市场活动");
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("id");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");

        Activity activity = null;
        if (activityList != null) {
            for (int i = 0; i < activityList.size(); i++) {
                activity = activityList.get(i);
                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        ServletOutputStream out = null;
        response.addHeader("Content-Disposition", "attachment;filename=marketingActivity.xlsx");
        out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    @PostMapping("/importActivity")
    @ResponseBody
    public Object importActivity(MultipartFile activityFile, HttpSession session) {
        ReturnObject returnObject = new ReturnObject();
        // 获取当前操作用户信息
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        List<Activity> activityList = new ArrayList<>();

        // 获取文件名
        String filename = activityFile.getOriginalFilename();

        InputStream inputStream = null;
        try {
            inputStream = activityFile.getInputStream();
            // 解析excel文件, 获取文件中的数据, 并且封装成activityList

            // excel文件
            Workbook workbook = null;
            // 工作表
            Sheet sheet = null;
            // 行
            Row row = null;
            // 列
            Cell cell = null;
            if (filename.toLowerCase().endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (filename.toLowerCase().endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
                returnObject.setMessage("导入失败, 请检查文件扩展名");
                return returnObject;
            }

            sheet = workbook.getSheetAt(0);
            Activity activity = null;
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                row = sheet.getRow(i);
                activity = new Activity();
                // id
                activity.setId(UUIDUtils.getUUID());
                // 所有者
                activity.setOwner(user.getId());
                // 创建时间
                activity.setCreateTime(DateUtils.format(new Date()));
                // 创建者
                activity.setCreateBy(user.getId());

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    // 获取列中的数据
                    String cellValueForString = WorkbookUtils.getCellValueForString(cell);
                    switch (j) {
                        case 0:
                            // 第一列, 市场活动名称
                            activity.setName(cellValueForString);
                            break;
                        case 1:
                            // 第二列, 开始时间
                            activity.setStartDate(cellValueForString);
                            break;
                        case 2:
                            // 第三列, 结束时间
                            activity.setEndDate(cellValueForString);
                            break;
                        case 3:
                            // 第四列, 成本
                            activity.setCost(cellValueForString.split("\\.")[0]);
                            break;
                        case 4:
                            // 第五列, 描述
                            activity.setDescription(cellValueForString);
                    }
                }
                if (activity.getStartDate().compareTo(activity.getEndDate()) > 0) {
                    returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
                    returnObject.setMessage("导入失败, 请检查开始日期与结束日期. (开始日期不能大于结束日期)");
                    return returnObject;
                }
                // 把所有列 封装到list集合
                activityList.add(activity);
            }

            // 调用service层方法, 保存市场活动
            int ret = activityService.saveCreateActivityByList(activityList);
            returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_SUCCESS);
            returnObject.setRetData(ret);
        } catch (IOException e) {
            returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
            returnObject.setMessage("系统繁忙, 请稍后重试...");
            e.printStackTrace();
        }
        return returnObject;
    }

    // 下载市场活动模板
    /*public Object exportActivityTemplate(){
        ReturnObject returnObject = new ReturnObject();
        return returnObject;
    }*/

    // 市场活动明细
    @GetMapping("/detail")
    public String detailActivity(String id, Model model) {
        // 调用service层方法, 查询数据
        Activity activity = activityService.queryActivityForDetailById(id);
        List<ActivityRemark> remarkList = activityRemarkService.queryActivityRemarkForDetailByActivityId(id);
        // 把数据保存到作用域中
        model.addAttribute("activity", activity);
        model.addAttribute("remarkList", remarkList);
        // 请求转发
        return "workbench/activity/detail";
    }
}