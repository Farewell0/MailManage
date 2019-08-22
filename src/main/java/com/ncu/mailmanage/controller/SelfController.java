package com.ncu.mailmanage.controller;

import com.ncu.mailmanage.global.Constant;
import com.ncu.mailmanage.global.ResponseCode;
import com.ncu.mailmanage.global.ServerResponse;
import com.ncu.mailmanage.pojo.User;
import com.ncu.mailmanage.service.UserService;
import com.ncu.mailmanage.utils.PasswordUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * IndexController
 *
 * @author sean
 * @date 2019/8/19
 **/
@Controller
@RequestMapping("/self")
public class SelfController {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String self(HttpSession session){
        User user=(User)session.getAttribute(Constant.CURRENT_USER);

        return "self";
    }

    @PostMapping("/updatePwd")
    public String updatePwd(@RequestParam("oldpwd") String oldpwd, @RequestParam("newpwd1") String newpwd1,
                            HttpSession session ,Model model){
        User user=(User) session.getAttribute(Constant.CURRENT_USER);
        String check=userService.findByUsername(user.getName()).getPassword();
        if (PasswordUtil.getMd5Password(oldpwd).equals(check)){
            user.setPassword(PasswordUtil.getMd5Password(newpwd1));
            userService.updatePassword(user);
            model.addAttribute("message","修改成功,请重新登录");
            SecurityUtils.getSubject().logout();
            return "login";
        }
        model.addAttribute("message","密码错误,修改失败");
        return "self";

    }

    @PostMapping("/updateInfo")
    @ResponseBody
    public ServerResponse updateInfo(User info, HttpSession session){
        User user=(User)session.getAttribute(Constant.CURRENT_USER);
        user.setMailAddress(info.getMailAddress());
        user.setBirthday(info.getBirthday());
        user.setPosition(info.getPosition());
        user.setIntroduction(info.getIntroduction());
        LOG.info("修改用户信息：\n" + user.toString());

        return userService.updateByUserId(user);
    }

    @PostMapping("/updateAvatar")
    @ResponseBody
    public ServerResponse updateAvatar(String avatar, HttpSession session){
        User user=(User)session.getAttribute(Constant.CURRENT_USER);
        user.setAvatar(avatar);
        LOG.info("修改头像：\n" + user.getAvatar());

        return userService.updateByUserId(user);
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

    }
}
