package scau.lzl.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.lizl.forever.entity.User;
import scau.lizl.forever.mapper.UserMapper;

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
