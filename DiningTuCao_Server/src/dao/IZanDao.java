package dao;

import java.sql.Timestamp;

import vo.Zan;

public interface IZanDao {
    public boolean setZanState(Timestamp remarkid, String userid,int zanstate) throws Exception;//控制userid是否赞remarkid
    public boolean setCaiState(Timestamp remarkid, String userid,int caistate) throws Exception;//控制userid是否踩remarkid
    public boolean insertZan(Timestamp remarkid,String userid,int zanstate) throws Exception;
    public boolean insertCai(Timestamp remarkid,String userid,int Caistate) throws Exception;
    public Zan getZan(Timestamp remarkid, String userid) throws Exception;
    public Zan getCai(Timestamp remarkid, String userid) throws Exception;
}
