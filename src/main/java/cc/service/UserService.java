package cc.service;

import cc.pojo.User;
import cc.pojo.UserQuery;
import com.github.pagehelper.PageInfo;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserService {

    public List<User> listUser();


    public User queryUserById(Integer id);

    public PageInfo<User> listByName(UserQuery userQuery);

    public boolean deleteUserById(Integer id);

    public boolean updateUser(User user);


    public boolean addUser(User user);
}
