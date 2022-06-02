package per.iys.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workbench")
public class WorkbenchIndexController {

    // 跳转到主页
    @GetMapping("/")
    public String index() {
        return "workbench/index";
    }
}
