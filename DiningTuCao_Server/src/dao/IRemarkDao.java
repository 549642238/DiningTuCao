package dao;
import java.util.ArrayList;

import vo.*;
public interface IRemarkDao {
    public ArrayList<Remark> findRemark(int count) throws Exception;
    public ArrayList<Remark> findRemarkbyDishName(String buildname) throws Exception;
    public ArrayList<Remark> findRemarkbyBLWD(String buildname,String layer,String windowname,String dishname) throws Exception;
    public ArrayList<Remark> findRemarkbyBLW(String buildname,String layer,String windowname) throws Exception;
    public boolean insertRemark(Remark remark)throws Exception;
    public boolean insertRemark(String username,String buildname,String layer,String windowname,String dishname,String content,int dishscore) throws Exception;
}
