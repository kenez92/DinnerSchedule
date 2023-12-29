package pl.kenez.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kenez.service.admin.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(final AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/update/from/excel")
    public String updateFromExcel() {
        adminService.updateFromExcel();
        return "admin/admin";
    }

    @GetMapping
    public String index() {
        return "admin/admin";
    }
}
