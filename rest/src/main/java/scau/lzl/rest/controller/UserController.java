package scau.lzl.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import scau.lzl.rest.constant.ResponseResult;
import scau.lzl.rest.entity.User;
import scau.lzl.rest.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping(path = "/create")
    public ResponseResult createUser(@RequestBody User user, HttpSession session) {
        session.setAttribute("user", user.getName());

        userService.insertUser(user);
        return ResponseResult.builder(true, null).description("233").build();
    }

    @GetMapping(path = "/list")
    public ResponseResult listUser(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return ResponseResult.builder(false, null).description("访问list之间要先创建用户").build();
        }

        log.debug("UserController.listUser");

        List<User> userList = userService.listUser();
        return ResponseResult.builder(true, userList).description("233").build();
    }

    @PostMapping(path = "/testSession")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult testSession(@RequestParam String name,
                                      @RequestParam String password,
                                      @RequestParam Long phone,
                                      @RequestParam String email) throws Exception {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);

        userService.insertUser(user);
        userService.insertUser(user);
        userService.insertUser(user);

        if (user.getName().equals("test")) {
            throw new Exception("test session");
        }
        userService.insertUser(user);
        userService.insertUser(user);

        return ResponseResult.builder(true, null).build();
    }
}
