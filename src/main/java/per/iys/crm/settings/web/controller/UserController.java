package per.iys.crm.settings.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.iys.crm.commons.constants.Constants;
import per.iys.crm.commons.domain.ReturnObject;
import per.iys.crm.commons.utils.DateUtils;
import per.iys.crm.settings.domain.User;
import per.iys.crm.settings.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/settings/qx/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 跳转到登陆页面
    @GetMapping("/login")
    public String toLogin(HttpServletRequest request, Model model) {
        // 如果用户已经登陆, 重新定向到工作页
        if (request.getSession().getAttribute(Constants.SESSION_USER) != null) {
            return "redirect:/workbench/";
        }

        // 未登陆, 获取cookie请求转发到登陆页面
        model.addAttribute("remember", false);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("loginAct".equals(cookie.getName())) {
                model.addAttribute("loginActCookie", cookie.getValue());
            }
            if ("loginPwd".equals(cookie.getName())) {
                model.addAttribute("loginPwdCookie", cookie.getValue());
                model.addAttribute("remember", true);
            }
        }
        // 未登录跳转到登陆页面
        return "settings/qx/user/login";
    }

    // 登陆验证
    @PostMapping("/login")
    public @ResponseBody
    Object login(HttpServletRequest request, HttpServletResponse response, String loginAct, String loginPwd, String isRemPwd) {
        Map<String, Object> map = new HashMap<>();
        ReturnObject returnObject = new ReturnObject();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);

        User user = userService.queryUserByLoginActAndLoginPwd(map);

        // 判断帐号密码
        if (user == null) {
            // 登陆失败, 用户名或密码错误
            returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
            returnObject.setMessage("用户名或密码错误!");
            return returnObject;
        }

        String expireTime = user.getExpireTime();
        String nowTime = DateUtils.format(new Date());
        // 判断帐号期限
        if (nowTime.compareTo(expireTime) > 0) {
            // 登陆失败, 帐号过期
            returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
            returnObject.setMessage("帐号已过期!");
            return returnObject;
        }

        // 判断帐号状态
        if ("0".equals(user.getLockState())) {
            // 登陆失败, 状态锁定
            returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
            returnObject.setMessage("帐号已锁定!");
            return returnObject;
        }

        // 判断IP
        String remoteAddr = request.getRemoteAddr();
        if (!user.getAllowIps().contains(remoteAddr)) {
            // 登陆失败, IP受限
            returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
            returnObject.setMessage("IP受限!");
        }

        // 登陆成功
        returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_SUCCESS);
        request.getSession().setAttribute(Constants.SESSION_USER, user);

        if ("true".equals(isRemPwd)) {
            // 十天免密登陆
            Cookie loginActCookie = new Cookie("loginAct", loginAct);
            Cookie loginPwdCookie = new Cookie("loginPwd", loginPwd);
            loginActCookie.setMaxAge(864000);
            loginPwdCookie.setMaxAge(864000);
            response.addCookie(loginActCookie);
            response.addCookie(loginPwdCookie);
        } else {
            // 没有勾选免登陆, 清除cookie
            Cookie loginActCookie = new Cookie("loginAct", "");
            Cookie loginPwdCookie = new Cookie("loginPwd", "");
            loginActCookie.setMaxAge(0);
            loginPwdCookie.setMaxAge(0);
            response.addCookie(loginActCookie);
            response.addCookie(loginPwdCookie);
        }

        return returnObject;
    }

    // 退出登陆
    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpSession session) {
        // 清空cookie
        Cookie loginActCookie = new Cookie("loginAct", "");
        Cookie loginPwdCookie = new Cookie("loginPwd", "");
        loginActCookie.setMaxAge(0);
        loginPwdCookie.setMaxAge(0);
        response.addCookie(loginActCookie);
        response.addCookie(loginPwdCookie);

        // 销毁session
        session.invalidate();

        // 跳转到登陆页
        return "redirect:/";
    }
}
