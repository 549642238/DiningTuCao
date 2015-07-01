package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import java.sql.Connection;

import vo.Zan;
import dao.IZanDao;

public class ZanDaoImpl implements IZanDao{
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    public ZanDaoImpl(Connection conn){
        this.conn = conn;
    }
    public boolean setZanState(Timestamp remarkid, String userid, int zanstate) throws Exception {
        String sql = "update zan set zanstate=? where remarkid=? and userid=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setTimestamp(2, remarkid);
        this.pstmt.setString(3,userid);
        this.pstmt.setInt(1, zanstate);
        this.pstmt.execute();
        if(this.pstmt.executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean setCaiState(Timestamp remarkid, String userid,int caistate) throws Exception {
        String sql = "update cai set caistate=? where remarkid=? and userid=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setTimestamp(2, remarkid);
        this.pstmt.setString(3,userid);
        this.pstmt.setInt(1, caistate);
        this.pstmt.execute();
        if(this.pstmt.executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean insertZan(Timestamp remarkid, String userid, int zanstate)throws Exception {
        String sql = "insert into zan(remarkid,userid,zanstate) values(?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setTimestamp(1, remarkid);
        this.pstmt.setString(2,userid);
        this.pstmt.setInt(3, zanstate);
        if(this.pstmt.executeUpdate()>0){
            return true;
        }else{
            return false;
        }     
    }
    
    public boolean insertCai(Timestamp remarkid, String userid, int caistate)throws Exception {
        String sql = "insert into cai(remarkid,userid,caistate) values(?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setTimestamp(1, remarkid);
        this.pstmt.setString(2,userid);
        this.pstmt.setInt(3, caistate);
        if(this.pstmt.executeUpdate()>0){
            return true;
        }else{
            return false;
        }     
    }
    


    @SuppressWarnings("null")
    public Zan getZan(Timestamp remarkid, String userid) throws Exception {
        Zan zan = null;
        String sql = "select zanid,remarkid,userid,zanstate from zan where remarkid=? and userid=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setTimestamp(1, remarkid);
        this.pstmt.setString(2, userid);
        ResultSet rs = this.pstmt.executeQuery();
        if(rs.next()){
            zan = new Zan();
            zan.setZanId(rs.getInt(1));
            zan.setReamrkId(rs.getTimestamp(2));
            zan.setUserId(rs.getString(3));
            zan.setZanState(rs.getInt(4));
           // zan.setCaiState(rs.getInt(5));
        }
        
        return zan;
    }
    public Zan getCai(Timestamp remarkid, String userid) throws Exception {
        Zan zan = null;
        String sql = "select caiid,remarkid,userid,caistate from cai where remarkid=? and userid=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setTimestamp(1, remarkid);
        this.pstmt.setString(2, userid);
        ResultSet rs = this.pstmt.executeQuery();
        if(rs.next()){
            zan = new Zan();
            zan.setZanId(rs.getInt(1));
            zan.setReamrkId(rs.getTimestamp(2));
            zan.setUserId(rs.getString(3));
            zan.setCaiState(rs.getInt(4));
        }
        
        return zan;
    }

}
