package dao.proxy;
import vo.*;
import dao.*;
import dao.impl.*;
import dbc.*;
public class UserDaoProxy implements IUserDao {
    private DatabaseConnection dbc = null;
    private IUserDao dao = null;
    public UserDaoProxy() throws Exception{
        this.dbc = new DatabaseConnection();
        this.dao = new UserDaoImpl(this.dbc.getConnection());
    }
    
    public boolean doCreate(User user) throws Exception {
       boolean flag = false;
       try{
           if(this.dao.findByUserName(user.getName())==null) {
               flag = this.dao.doCreate(user);
               }
       }catch(Exception e){
               throw e;
       }finally{
              this.dbc.close();
         }
       return flag;
    }

    public User findByUserName(String name) throws Exception {
        User user = null;
        try{
            user = this.dao.findByUserName(name);
        }catch(Exception e){
            throw e;
        }finally{
            this.dbc.close();
        }
        return user;
    }

    public User findByUserNameandPasswd(String name, String passwd)throws Exception {
        User user = null;
        try{
            user = this.dao.findByUserNameandPasswd(name, passwd);
        }catch(Exception e){
            throw e;
        }finally{
            this.dbc.close();
        }
        return user;
    }
    public User findByUserId(String id)throws Exception{
        User user = null;
        try{
            user = this.dao.findByUserId(id);
        }catch(Exception e){
            throw e;
        }finally{
          this.dbc.close();
        }
        return user;
              
    }

    @Override
    public User findByUserIdandPasswd(String id, String passwd)throws Exception {
        User user = null;
        try{
            user = this.dao.findByUserIdandPasswd(id, passwd);
        }catch(Exception e){
            throw e;
        }finally{
            this.dbc.close();
        }
        return user;
    }

    @Override
    public boolean modifyUser(User user) throws Exception {
        boolean flag = false;
        try{
                flag = this.dao.modifyUser(user);
                
        }catch(Exception e){
                throw e;
        }finally{
               this.dbc.close();
          }
        return flag;
    }
}
