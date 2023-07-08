package cc.controller;

import cc.pojo.User;
import cc.pojo.UserQuery;
import cc.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model, UserQuery userQuery) {
        PageInfo<User> userPageInfo = userService.listByName(userQuery);
        model.addAttribute("page", userPageInfo);
        return "index";
    }


    @PostMapping("/")
    public String listByName(Model model, UserQuery userQuery) {
        PageInfo<User> userPageInfo = userService.listByName(userQuery);
        model.addAttribute("page", userPageInfo);
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        boolean b = userService.deleteUserById(id);
        if(b) {
            redirectAttributes.addAttribute("message", "删除用户成功");
            return "redirect:/";
        }
        redirectAttributes.addAttribute("message", "删除用户失败");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String toEdit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.queryUserById(id));
        return "editUser";
    }

    @PostMapping("/edit")
    public String edit(User user, UserQuery userQuery, RedirectAttributes redirectAttributes) {
        userQuery.setName(user.getName());
        PageInfo<User> userPageInfo = userService.listByName(userQuery);
        Integer id = user.getId();
        if(id != null) {
            if (userPageInfo.getSize() == 0) {
                boolean b = userService.updateUser(user);
                if (b) {
                    redirectAttributes.addAttribute("message", "更新用户成功");
                } else {
                    redirectAttributes.addAttribute("message", "更新用户失败");
                }
                return "redirect:/";
            } else {
                redirectAttributes.addAttribute("message", "用户已存在");
                return "redirect:/edit/" + user.getId();
            }
        } else {
            if (userPageInfo.getSize() == 0) {
                boolean b = userService.addUser(user);
                if (b) {
                    redirectAttributes.addAttribute("message", "添加用户成功");
                } else {
                    redirectAttributes.addAttribute("message", "添加用户失败");
                }
                return "redirect:/";
            } else {
                redirectAttributes.addAttribute("message", "用户已存在");
                return "redirect:/";
            }
        }
    }



    @GetMapping("/update")
    public String toUpdate(Model model, User user) {
        model.addAttribute(new User());
        return "editUser";
    }

}
