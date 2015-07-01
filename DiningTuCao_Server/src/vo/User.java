package vo;
import java.util.Date;
public class User {
    private String id;
    private String name;
    private String passwd;
    private String grade;
    private String birth;//数据库中对应datetime
    private String sex;
    private String email;
    private String phone;
    private Date registertime ;//对应数据空中是datetime
   
    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPasswd(String passwd){
        this.passwd = passwd;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public void setGrade(String grade){
        this.grade = grade;
    }
    public void setBirth(String birth){
        this.birth = birth;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setRegisterTime(Date time){
        this.registertime = time;
    }   
    
    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getPasswd(){
        return this.passwd;
    }
    public String getSex(){
        return this.sex;
    }
    public String getGrade(){
        return this.grade;
    }
    public String getBirth(){
        return this.birth;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhone(){
        return this.phone;
    }
    public Date getRegisterTime(){
        return this.registertime;
    }
}