package vo;

public class Window {
    private int windowid ;
    private String windowname;
    private String layer;
    private String buildname;
    private double windowscore;
    public void setWindowId(int windowid){
        this.windowid = windowid;
    }
    public void setWindowName(String name){
        this.windowname = name;
    }
    public void setLayer(String layer){
        this.layer = layer;
    }
    public void setBuildName(String buildname){
        this.buildname = buildname;
    }
    public void setWindowScore(double windowscore){
        this.windowscore = windowscore;
    }
    public int getWindowId(){
        return this.windowid;
    }
    public String getWindowName(){
        return this.windowname;
    }
    public String getLayer(){
        return this.layer;
    }
    public String getBuildName(){
        return this.buildname;
    }
    public double getWindowScore(){
        return this.windowscore;
    }
}
