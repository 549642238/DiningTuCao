package dao;
import vo.*;
public interface IUserDao {
    public boolean doCreate(User user) throws Exception;
    public User findByUserName(String name) throws Exception;
    public User findByUserNameandPasswd(String name,String passwd) throws Exception;
    public User findByUserId(String id) throws Exception;
    public User findByUserIdandPasswd(String id,String passwd) throws Exception;
    public boolean modifyUser(User user) throws Exception;
}
