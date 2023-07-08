package cc.dao;

import cc.pojo.User;
import cc.pojo.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    public List<User> listUser();

    public User queryUserById(Integer id);


    public List<User> listByName(UserQuery userQuery);

    public int deleteUserById(Integer id);

    public int updateUser(User user);

    public boolean addUser(User user);
}
