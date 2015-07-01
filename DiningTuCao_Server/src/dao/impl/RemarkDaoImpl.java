package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.*;
import dao.*;
import factory.DaoFactory;

public class RemarkDaoImpl implements IRemarkDao{
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    public RemarkDaoImpl(Connection conn){
        this.conn = conn;
    }
    public ArrayList<Remark> findRemark(int count) throws Exception {//首页面的评论信息
       ArrayList<Remark> remarks = new ArrayList<Remark>();
        String sql = "select username,windowname,dishname,buildname,layer,timeid,content,dishscore,zancount,caicount from view_remark order by timeid desc ";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        int i = 0;
        while(rs.next() && i < count){
            count++;
           Remark re = new Remark();
           re.setUserName(rs.getString(1));
           re.setWindowName(rs.getString(2));
           re.setDishName(rs.getString(3));
           re.setBuildName(rs.getString(4));
           re.setLayer(rs.getString(5));
           re.setTimeId(rs.getTimestamp(6));
           re.setContent(rs.getString(7));
           re.setDishScore(rs.getInt(8));
           re.setZanCount(rs.getInt(9));
           re.setCaiCount(rs.getInt(10));
           remarks.add(re);
        }
       return remarks;
    }
    @Override
    public boolean insertRemark(Remark remark) throws Exception {
        
        boolean flag = false;
        User user = DaoFactory.getUserInstance().findByUserName(remark.getUserName());
        Window window = DaoFactory.getWindowInstance().findWindowbyBuildLayerandWindowname(remark.getBuildName(),remark.getLayer(),remark.getWindowName());
        Recipe recipe = DaoFactory.getRecipeInstance().findRecipeIdbyWindowDishname(window, remark.getDishName()); 
        
         String sql = "insert into remark(timeid,userid,recipeid,content,dishscore)  values(?,?,?,?,?)";
         this.pstmt = this.conn.prepareStatement(sql);
         this.pstmt.setTimestamp(1,new java.sql.Timestamp(System.currentTimeMillis()));//reamrk的id 就是当时评论的时间
         this.pstmt.setString(2,user.getId());
         this.pstmt.setInt(3,recipe.getRecipeId());
         this.pstmt.setString(4,remark.getContent());
         this.pstmt.setInt(5,remark.getDishScore());
       
         if(this.pstmt.executeUpdate()>0){
                 flag  = true;
         }//if
         return flag;
    }
    @Override
    public boolean insertRemark(String username, String buildname,String layer, String windowname, String dishname,String content,int dishscore) throws Exception {
        boolean flag = false;
        User user = DaoFactory.getUserInstance().findByUserName(username);
        Window window = DaoFactory.getWindowInstance().findWindowbyBuildLayerandWindowname(buildname,layer,windowname);
        Recipe recipe = DaoFactory.getRecipeInstance().findRecipeIdbyWindowDishname(window,dishname); 
        
         String sql = "insert into remark(timeid,userid,recipeid,content,dishscore)  values(?,?,?,?,?)";
         this.pstmt = this.conn.prepareStatement(sql);
         this.pstmt.setTimestamp(1,new java.sql.Timestamp(System.currentTimeMillis()));
         this.pstmt.setString(2,user.getId());
         this.pstmt.setInt(3,recipe.getRecipeId());
         this.pstmt.setString(4,content);
         this.pstmt.setInt(5,dishscore);
       
         if(this.pstmt.executeUpdate()>0){
                 flag  = true;
         }//if
         return flag;
    }
    @Override
    public ArrayList<Remark> findRemarkbyDishName(String dishname) throws Exception {
        ArrayList<Remark> remarks = new ArrayList<Remark>();
        String sql = "select username,windowname,dishname,buildname,layer,timeid,content,dishscore,zancount,caicount from view_remark where dishname=? order by timeid desc";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, dishname);
        ResultSet rs = this.pstmt.executeQuery();
        while(rs.next()){
           Remark re = new Remark();
           re.setUserName(rs.getString(1));
           re.setWindowName(rs.getString(2));
           re.setDishName(rs.getString(3));
           re.setBuildName(rs.getString(4));
           re.setLayer(rs.getString(5));
           re.setTimeId(rs.getTimestamp(6));
           re.setContent(rs.getString(7));
           re.setDishScore(rs.getInt(8));
           re.setZanCount(rs.getInt(9));
           re.setCaiCount(rs.getInt(10));
           remarks.add(re);
        }
       return remarks;
    }
    @Override
    public ArrayList<Remark> findRemarkbyBLWD(String buildname, String layer,String windowname, String dishname) throws Exception {
        ArrayList<Remark> remarks = new ArrayList<Remark>();
        String sql = "select username,windowname,dishname,buildname,layer,timeid,content,dishscore,zancount,caicount from view_remark where buildname=? and layer=? and windowname=? and dishname=? order by timeid desc";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, buildname);
        this.pstmt.setString(2, layer);
        this.pstmt.setString(3, windowname);
        this.pstmt.setString(4, dishname);
        ResultSet rs = this.pstmt.executeQuery();
        while(rs.next()){
           Remark re = new Remark();
           re.setUserName(rs.getString(1));
           re.setWindowName(rs.getString(2));
           re.setDishName(rs.getString(3));
           re.setBuildName(rs.getString(4));
           re.setLayer(rs.getString(5));
           re.setTimeId(rs.getTimestamp(6));
           re.setContent(rs.getString(7));
           re.setDishScore(rs.getInt(8));
           re.setZanCount(rs.getInt(9));
           re.setCaiCount(rs.getInt(10));
           remarks.add(re);
        }
       return remarks;
    }
    @Override
    public ArrayList<Remark> findRemarkbyBLW(String buildname, String layer,String windowname) throws Exception {
        ArrayList<Remark> remarks = new ArrayList<Remark>();
        String sql = "select username,windowname,dishname,buildname,layer,timeid,content,dishscore,zancount,caicount from view_remark where buildname=? and layer=? and windowname=? order by timeid desc";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, buildname);
        this.pstmt.setString(2, layer);
        this.pstmt.setString(3, windowname);
        ResultSet rs = this.pstmt.executeQuery();
        while(rs.next()){
           Remark re = new Remark();
           re.setUserName(rs.getString(1));
           re.setWindowName(rs.getString(2));
           re.setDishName(rs.getString(3));
           re.setBuildName(rs.getString(4));
           re.setLayer(rs.getString(5));
           re.setTimeId(rs.getTimestamp(6));
           re.setContent(rs.getString(7));
           re.setDishScore(rs.getInt(8));
           re.setZanCount(rs.getInt(9));
           re.setCaiCount(rs.getInt(10));
           remarks.add(re);
        }
       return remarks;
    }

}
