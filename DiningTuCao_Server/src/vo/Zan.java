package vo;

import java.sql.Timestamp;

public class Zan {
    private int zanid;
    private Timestamp reamrkid;
    private String userid;
    private int zanstate;
    private int caistate;
    public void setZanId(int id){
        this.zanid = id;
    }
    public void setReamrkId(Timestamp id){
        this.reamrkid = id;
    }
    public void setUserId(String userid){
        this.userid = userid;
    }
    public void setZanState(int zanstate){
        this.zanstate = zanstate;
    }
    public void setCaiState(int caistate){
        this.caistate = caistate;
    }
    public int getZanId(){
        return this.zanid;
    }
    public Timestamp getRemarkId(){
        return this.reamrkid;
    }
    public String getUserId(){
        return this.userid;
    }
    public int getZanState(){
        return this.zanstate;
    }
    public int getCaiState(){
        return this.caistate;
    }
}
