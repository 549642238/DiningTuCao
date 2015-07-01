package vo;

import java.sql.Timestamp;

public class Remark {
    private Timestamp timeid;
    private String username;
    private String windowname;
    private String dishname;
    private String buildname;
    private String layer;
    private String content;
    private int dishscore;
    private int zancount;
    private int caicount;
    public Timestamp getTimeId(){
        return this.timeid;
    }
    public String getUserName(){
        return this.username;
    }
    public String getWindowName(){
        return this.windowname;
    }
    public String getDishName(){
        return this.dishname;
    }
    public String getBuildName(){
        return this.buildname;
    }
    public String getLayer(){
        return this.layer;
    }
    public int getDishScore(){
        return this.dishscore;
    }
    public String getContent(){
        return this.content;
    }
    public int getZanCount(){
        return this.zancount;
    }
    public int getCaiCount(){
        return this.caicount;
    }
    
    public void setUserName(String id){
        this.username = id;  
    }
    public  void setTimeId(Timestamp time){
        this.timeid = time;
    }
    public void setWindowName(String window){
        this.windowname = window;
    }
    public void setDishName(String dish){
        this.dishname = dish;
    }
    public void setBuildName(String build){
        this.buildname = build;
    }
    public void setLayer(String layer){
        this.layer = layer;
    }
    public void setDishScore(int score){
        this.dishscore = score;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setZanCount(int count){
        this.zancount = count;
    }
    public void setCaiCount(int count){
        this.caicount = count;
    }
}
