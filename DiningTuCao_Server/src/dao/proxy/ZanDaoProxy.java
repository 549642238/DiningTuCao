package dao.proxy;

import java.sql.Timestamp;

import vo.Zan;
import dao.IZanDao;
import dao.impl.ZanDaoImpl;
import dbc.DatabaseConnection;

public class ZanDaoProxy implements IZanDao  {
    private DatabaseConnection dbc = null;
    private IZanDao dao = null;
    public ZanDaoProxy()throws Exception{
        this.dbc = new DatabaseConnection();
        this.dao = new ZanDaoImpl(this.dbc.getConnection());
    }
    public boolean setZanState(Timestamp remarkid, String userid, int zanstate) throws Exception {
       boolean flage = false;
       try{
           flage = this.dao.setZanState(remarkid, userid, zanstate);    
       }catch(Exception e){
           e.printStackTrace();
       }finally{
           this.dbc.close();
       }//try
       
       return flage;
    }//setZanState

    @Override
    public boolean setCaiState(Timestamp remarkid, String userid,int caistate) throws Exception {
        boolean flage = false;
        try{
            flage = this.dao.setCaiState(remarkid, userid, caistate);    
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }//try
        
        return flage;
    }

    @Override
    public boolean insertZan(Timestamp remarkid, String userid, int zanstate) throws Exception {
        boolean flage = false;
        try{
            flage = this.dao.insertZan(remarkid, userid, zanstate);    
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }//try
        
        return flage;
    }
    public boolean insertCai(Timestamp remarkid, String userid, int caistate) throws Exception {
        boolean flage = false;
        try{
            flage = this.dao.insertCai(remarkid, userid, caistate);    
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }//try
        
        return flage;
    }
    
    

    @Override
    public Zan getZan(Timestamp remarkid, String userid) throws Exception {
        Zan zan = null;
        try{
            zan = this.dao.getZan(remarkid, userid);    
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }//try
        
        return zan;
    }
    
    public Zan getCai(Timestamp remarkid, String userid) throws Exception {
        Zan zan = null;
        try{
            zan = this.dao.getCai(remarkid, userid);    
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }//try
        
        return zan;
    }

}
