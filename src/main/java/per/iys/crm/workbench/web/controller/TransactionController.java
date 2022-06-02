package per.iys.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workbench/transaction")
public class TransactionController {
    @GetMapping("/")
    public String index() {
        return "workbench/transaction/index";
    }
}
