package per.iys.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workbench/contacts")
public class ContactsController {
    @GetMapping("/")
    public String index() {
        return "workbench/contacts/index";
    }
}
