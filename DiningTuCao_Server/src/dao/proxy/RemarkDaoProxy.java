package dao.proxy;

import java.util.ArrayList;

import vo.Remark;
import dao.IRemarkDao;
import dao.impl.RemarkDaoImpl;
import dbc.DatabaseConnection;

public class RemarkDaoProxy implements IRemarkDao{
    private DatabaseConnection dbc = null;
    private IRemarkDao dao = null;
    public RemarkDaoProxy() throws Exception{
        this.dbc = new DatabaseConnection();
        this.dao = new RemarkDaoImpl(this.dbc.getConnection());
    }
    public ArrayList<Remark> findRemark(int count) throws Exception {
        ArrayList<Remark> remarks = new ArrayList<Remark>();
        try{
            remarks = this.dao.findRemark(count);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }
        return remarks;
    }
    @Override
    public boolean insertRemark(Remark remark) throws Exception {
        boolean flag = false;
        try{
                flag = this.dao.insertRemark(remark);
        }catch(Exception e){
                throw e;
        }finally{
               this.dbc.close();
          }
        return flag;
    }
    @Override
    public boolean insertRemark(String username, String buildname,String layer, String windowname, String dishname, String content,int dishscore) throws Exception {
        boolean flag = false;
        try{
                flag = this.dao.insertRemark(username,buildname,layer,windowname,dishname,content,dishscore);
        }catch(Exception e){
                throw e;
        }finally{
               this.dbc.close();
          }
        return flag;
    }
    @Override
    public ArrayList<Remark> findRemarkbyDishName(String buildname)throws Exception {
        ArrayList<Remark> remarks = new ArrayList<Remark>();
        try{
            remarks = this.dao.findRemarkbyDishName(buildname);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }
        return remarks;
    }
    @Override
    public ArrayList<Remark> findRemarkbyBLWD(String buildname, String layer,String windowname, String dishname) throws Exception {
        ArrayList<Remark> remarks = new ArrayList<Remark>();
        try{
            remarks = this.dao.findRemarkbyBLWD(buildname, layer, windowname, dishname);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }
        return remarks;
    }
    @Override
    public ArrayList<Remark> findRemarkbyBLW(String buildname, String layer,String windowname) throws Exception {
        ArrayList<Remark> remarks = new ArrayList<Remark>();
        try{
            remarks = this.dao.findRemarkbyBLW(buildname,layer,windowname);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }
        return remarks;
    }

}
