package dao.impl;
import java.sql.*;

import vo.User;
import dao.IUserDao;

public class UserDaoImpl implements IUserDao{
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    public UserDaoImpl(Connection conn){
        this.conn = conn;
    }
    public boolean doCreate(User user)throws Exception{
        boolean flag = false;
        
        String sql = "insert into user(userid,username,userpasswd,usergrade,userbirth,usersex,useremail,userphone,userregistertime) values(?,?,?,?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
         this.pstmt.setString(1, user.getId());
         this.pstmt.setString(2, user.getName());
         this.pstmt.setString(3, user.getPasswd());
         this.pstmt.setString(4, user.getGrade());
         this.pstmt.setString(5,user.getBirth());
         this.pstmt.setString(6,user.getSex());
         this.pstmt.setString(7,user.getEmail());
         this.pstmt.setString(8,user.getPhone());
        // this.pstmt.setDate(9,new java.sql.Date(new java.util.Date().getTime()) );
         this.pstmt.setTimestamp(9,new java.sql.Timestamp(System.currentTimeMillis()));
         if(this.pstmt.executeUpdate()>0){
                 flag  = true;
         }
         return flag;
    }
    public User findByUserName(String name)throws Exception{
        User user = null;
        String sql = "select userid,username,userpasswd,usergrade,userbirth,usersex,useremail,userphone,userregistertime from user where username=?";
         this.pstmt = this.conn.prepareStatement(sql);
         this.pstmt.setString(1, name);
          ResultSet rs = this.pstmt.executeQuery();
          if(rs.next()){
              user = new User();
              user.setId(rs.getString(1));
              user.setName(rs.getString(2));
              user.setPasswd(rs.getString(3));
              user.setGrade(rs.getString(4));
              user.setBirth(rs.getString(5));
              user.setSex(rs.getString(6));
              user.setEmail(rs.getString(7));
              user.setPhone(rs.getString(8));
              user.setRegisterTime(rs.getTimestamp(9));
          }
       return user;
    }
    public User findByUserNameandPasswd(String name,String passwd)throws Exception{
        User user = null;
        String sql = "select userid,username,userpasswd,usergrade,userbirth,usersex,useremail,userphone,userregistertime from user where username=? and userpasswd=?";
         this.pstmt = this.conn.prepareStatement(sql);
         this.pstmt.setString(1, name);
         this.pstmt.setString(2,passwd);
         ResultSet rs = this.pstmt.executeQuery();
          if(rs.next()){
              user = new User();
              user.setId(rs.getString(1));
              user.setName(rs.getString(2));
              user.setPasswd(rs.getString(3));
              user.setGrade(rs.getString(4));
              user.setBirth(rs.getString(5));
              user.setSex(rs.getString(6));
              user.setEmail(rs.getString(7));
              user.setPhone(rs.getString(8));
              user.setRegisterTime(rs.getTimestamp(9));
          }
       return user;
    }
    public User findByUserId(String id)throws Exception{
        User user = null;
        String sql = "select userid,username,userpasswd,usergrade,userbirth,usersex,useremail,userphone,userregistertime from user where userid=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,id);
        
        ResultSet rs = this.pstmt.executeQuery();
        if(rs.next()){
            user = new User();
            user.setId(rs.getString(1));
            user.setName(rs.getString(2));
            user.setPasswd(rs.getString(3));
            user.setGrade(rs.getString(4));
            user.setBirth(rs.getString(5));
            user.setSex(rs.getString(6));
            user.setEmail(rs.getString(7));
            user.setPhone(rs.getString(8));
            user.setRegisterTime(rs.getTimestamp(9));
        }
        return user;
    }
    public User findByUserIdandPasswd(String id, String passwd)throws Exception {
        User user = null;
        String sql = "select userid,username,userpasswd,usergrade,userbirth,usersex,useremail,userphone,userregistertime from user where userid=? and userpasswd=?";
         this.pstmt = this.conn.prepareStatement(sql);
         this.pstmt.setString(1, id);
         this.pstmt.setString(2,passwd);
          ResultSet rs = this.pstmt.executeQuery();
          if(rs.next()){
              user = new User();
              user.setId(rs.getString(1));
              user.setName(rs.getString(2));
              user.setPasswd(rs.getString(3));
              user.setGrade(rs.getString(4));
              user.setBirth(rs.getString(5));
              user.setSex(rs.getString(6));
              user.setEmail(rs.getString(7));
              user.setPhone(rs.getString(8));
              user.setRegisterTime(rs.getTimestamp(9));
          }
       return user;
    }

    public boolean modifyUser(User user) throws Exception {
        boolean flag = false;
        String sql = "update user set username=?,userpasswd=?,usergrade=?,userbirth=?,usersex=?,useremail=?,userphone=? where userid=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, user.getName());
        this.pstmt.setString(2, user.getPasswd());
        this.pstmt.setString(3, user.getGrade());
        this.pstmt.setString(4,user.getBirth());
        this.pstmt.setString(5,user.getSex());
        this.pstmt.setString(6,user.getEmail());
        this.pstmt.setString(7,user.getPhone());
        this.pstmt.setString(8, user.getId());
        if(this.pstmt.executeUpdate()>0){
            flag  = true;
            }
            return flag;
     }
}
