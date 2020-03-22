package scau.lzl.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.lzl.rest.entity.User;
import scau.lzl.rest.mapper.UserMapper;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void insertUser(User user) {
        userMapper.insert(user);
    }

    public List<User> listUser() {
        return userMapper.listAll();
    }
}
