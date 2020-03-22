package scau.lzl.rest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import scau.lzl.rest.entity.User;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    int insert(User user);

    List<User> listAll();
}
