package dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Window;
import dao.*;
public class WindowDaoImpl implements IWindowDao {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    public WindowDaoImpl(Connection conn){
        this.conn = conn;
    }
    public ArrayList<String> findBuildName() throws Exception {
        ArrayList<String> buildsname = new ArrayList<String> (); 
        String sql = "select distinct buildname from window";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
      
        while(rs.next()){
            buildsname.add(rs.getString(1));
        }
        return buildsname;
    }

    @Override
    public ArrayList<String> findLayers(String buildname) throws Exception {
        ArrayList<String> layers = new ArrayList<String>();
        String sql = "select distinct layer from window where buildname=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,buildname);
        ResultSet rs = this.pstmt.executeQuery();
        while(rs.next()){
            layers.add(rs.getString(1))  ;
        }
        return layers;
    }

    @Override
    public ArrayList<String> findWindowsbyBuildandLayer(String buildname, String layer) throws Exception {
        ArrayList<String> windows = new ArrayList<String>();
        String sql = "select distinct windowname from window where buildname=? and layer=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,buildname);
        this.pstmt.setString(2, layer);
        ResultSet rs = this.pstmt.executeQuery();
        //System.out.println("windows name:&&&&&&&&&");
        while(rs.next()){
           // System.out.println(rs.getString(1));
                windows.add(rs.getString(1));
        }
        return windows;
    }
    @Override
    public Window findWindowbyBuildLayerandWindowname(String build,String layer, String windowname) throws Exception {
        Window window = new Window();
       
        String sql = "select windowid,windowscore from window where buildname=? and layer=? and windowname=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,build);
        this.pstmt.setString(2, layer);
        this.pstmt.setString(3, windowname);
        ResultSet rs = this.pstmt.executeQuery();
        if(rs.next()){
            window.setBuildName(build);
            window.setLayer(layer);
            window.setWindowId(rs.getInt(1));
            window.setWindowScore(rs.getDouble(2));
            return window;
        }else{
            return null;
        }//if
    }
    @Override
    public ArrayList<Window> tuiJianWindows(int count) throws Exception {//每日推荐最多count=15个窗口,按照窗口平均分数高低排序
        ArrayList<Window> windows = new ArrayList<Window>();
        String sql = "select distinct windowid,buildname,layer,windowname,windowscore from window order by windowscore desc,layer asc; ";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        //System.out.println("windows name:&&&&&&&&&");
        int i = 0;
        while(rs.next() && i< count){
            i++;
            Window tempwindow = new Window();
            tempwindow.setWindowId(rs.getInt(1));
            tempwindow.setBuildName(rs.getString(2));//
            tempwindow.setLayer(rs.getString(3));
            tempwindow.setWindowName(rs.getString(4));
            tempwindow.setWindowScore(rs.getDouble(5));
            //System.out.println(tempwindow.getWindowName());
            windows.add(tempwindow);
        }
        
        return windows;
    }
    @Override
    public Window findWindowbyWindowId(int windowid) throws Exception {
        Window window = new Window();
        String sql = "select windowid,windowname,layer,buildname,windowscore from window where windowid=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1,windowid);
        ResultSet rs = this.pstmt.executeQuery();
        if(rs.next()){
            window.setWindowId(windowid);
            window.setWindowName(rs.getString(2));
            window.setLayer(rs.getString(3));
            window.setBuildName(rs.getString(4));
            window.setWindowScore(rs.getDouble(5));
            return window;
        }else{
            return null;
        }//if
    }
   // 窗口员工可以编辑窗口的名称
    public boolean updateWindowName(int windowid,String windowname) throws Exception{
        boolean flage = false;
        String sql = "update window set windowname=? where windowid=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, windowname);
        this.pstmt.setInt(2,windowid);
        if(this.pstmt.executeUpdate()>0){
            flage = true;
        }//if
        return flage;
    }

}
