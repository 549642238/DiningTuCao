package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.ArrayList;

import factory.DaoFactory;
import vo.*;

public class DealThread extends Thread {
    public Socket cc;
    BufferedReader in;
    PrintWriter out;
    User user = null;

    DealThread(Socket cc) {
        this.cc = cc;
        try {
            this.cc.setSoTimeout(300000);//2分钟该超时时间为了 处理保持与客户端的连接，避免客户端的不正常退回导致的 用户再登陆时报已经在线的错误
        } catch (SocketException e1) {
           e1.printStackTrace();
        }//try
        
        try {
            this.in = new BufferedReader( new InputStreamReader(cc.getInputStream()) );
            
            this.out = new PrintWriter(cc.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }//try
    }

    public void run() {
        try {
            while (true) {
                System.out.println("online users:"+OnlineUser.users);
                String stream = in.readLine();
                
               if(user!=null)
                    System.out.println("Stream:" + stream + "-----" + user.getId()); 
             //  System.out.println("Stream:" + stream + "-----"); 
                switch (stream) {
                case ("login"):// 发现登陆消息类型
                    if(login(in.readLine(),in.readLine()).equals("alreadyonline")){
                        
                    }//if
                    break;
                case ("logout")://用户退出 后线程生命周期结束
                    logout(user.getId());return;//return 表示退出run
                case ("register"):
                    register(in.readLine(),in.readLine(),in.readLine());break;
                case ("modify"):
                    User user = new User();
                    user.setId(in.readLine());
                    user.setPasswd(in.readLine());
                    user.setName(in.readLine());
                    user.setSex(in.readLine());
                    user.setEmail(in.readLine());
                    user.setPhone(in.readLine());
                    user.setGrade(in.readLine());
                    user.setBirth(in.readLine());
                    modifyUser(user);break;
                case ("dimsearch")://利用窗口搜索或者利用 一定菜名搜索返回有关菜的品论
                    dimSearch(in.readLine(),in.readLine(),in.readLine(),in.readLine());break;
                case ("remark")://最多发送30条信息
                    sendRecommend(30);break;
                case ("build"):
                    sendBuilds();break;
                case ("layer"):
                    sendLayers(in.readLine());break;
                case ("window"):
                    sendWindows(in.readLine(),in.readLine());break;
                case ("tucao"):
                    TuCao(in.readLine(),in.readLine(),in.readLine(),in.readLine(),in.readLine(),in.readLine(),Integer.parseInt(in.readLine()));
                    break;
                case ("recipe"):
                    sendRecipe(in.readLine(),in.readLine(),in.readLine());break;
                case("searchremarkbydish")://只利用菜名搜索
                    remarkSearchbydishname(in.readLine());break;
                case ("cai")://remarkid,userid
                    String remarkid= in.readLine();
               // System.out.println("***"+remarkid+"****");
                    setCai(Timestamp.valueOf(remarkid),in.readLine());break;
                case ("zan"):
                    setZan(Timestamp.valueOf(in.readLine()),in.readLine());break;
                    //setZan(Timestamp.valueOf(in.readLine()),in.readLine());break;
                case ("tuijian")://窗口推荐最多数目为15
                    sendTuiJian(15);break;
                case ("windowinformation")://每周推荐的window下菜名和菜分数
                    sendWindow_Recipes(Integer.parseInt(in.readLine()));
                }//switch

            }//while

        }catch (IOException e) {//IO 错误说明是客户端不正常断开
           // e.printStackTrace();
            if(user!=null)
                OnlineUser.users.remove(user.getId());//在线用户列表 删除该用户
            try {
                this.in.close();
                this.out.close();
                this.cc.close();
            }catch (Exception e1) {
                //e1.printStackTrace();
            }finally{
                //System.out.println("退出线程："+Thread.currentThread().getName());
                System.out.println("Exitting Thread："+Thread.currentThread().getName());
            }//try
        }catch(Exception e){//服务器端错误
               e.printStackTrace();
        }//try
    }//run
    
    private String login(String id, String passwd) throws IOException,Exception{
        out.println("login");
//        for (String userid: OnlineUser.users){//检查是否用户已经在线
//            if(userid.equals(id)){
//               // System.out.println("重复登陆");
//                System.out.println("repite login");
//                out.println("alreadyonline");
//                return "alreadyonline";
//            }//if
//        }//for
        if (DaoFactory.getUserInstance().findByUserId(id) == null) {//检查是否有该用户
            out.println("nouser");// 1
            return "nouser";
        } else {
            if ((this.user = DaoFactory.getUserInstance().findByUserIdandPasswd(id, passwd)) == null) {//核实用户身份
              out.println("passwderror");// 2
              return "passwderror";
            } else {
                OnlineUser.users.add(id);//加入该用户到在线用户列表
                out.println("loginsucceed");//3
                sendProfile(user);
                return "loginsucceed";
                //sendRecommend();
                }
           }
     }
    
    private void logout(String id) throws IOException{//用户正常退出 相关处理
        OnlineUser.users.remove(id);//在线用户列表 删除该用户
        //out.println("logout");
        this.in.close();
        this.out.close();
        this.cc.close();
        System.out.println("logout succeed. Exitting Thread："+Thread.currentThread().getName());
        //out.println("logoutsucceed");
     }
    private void register(String id,String passwd,String name)throws IOException,Exception{
        User user  = new User();
        user.setId(id);
        user.setPasswd(passwd);
        user.setName(name);
        user.setGrade(in.readLine());
        user.setSex(in.readLine());            
        user.setEmail(in.readLine());
        user.setPhone(in.readLine());
        user.setBirth(in.readLine());
        if(DaoFactory.getUserInstance().findByUserId(id) != null){
            out.println("register");
            out.println("alreadyused");
            System.out.println("register :alreadyused");
        }else{
           
            if(DaoFactory.getUserInstance().doCreate(user)){
                out.println("register");
                out.println("succeed");
            }else{
                out.println("register");
                out.println("failure");
            }
            
        }
    }
    private void sendProfile(User user)throws IOException{
        out.println(user.getId());
        out.println(user.getPasswd());
        out.println(user.getName());
        out.println(user.getGrade());
        out.println(user.getBirth());
        out.println(user.getSex());
        out.println(user.getEmail());
        out.println(user.getPhone());
        out.println(user.getRegisterTime());
    }
    private void modifyUser(User user)throws Exception{
        try {
            if(DaoFactory.getUserInstance().modifyUser(user)){
                out.println("modify");
                out.println("succeed");
                out.println("login");
                out.println("loginsucceed");
                sendProfile(user);
            }else{
                out.println("modify");
                out.println("failure");
            }//if
        } catch (Exception e) {
            e.printStackTrace();
        }//try
    }
    private void sendRecommend(int count)throws Exception{
            out.println("remark");
        try{
            ArrayList<Remark> remarks = new ArrayList<Remark>();
            remarks = DaoFactory.getRemarkInstance().findRemark(count);//发送所有的评论
            for(Remark remark: remarks){
                out.println(remark.getBuildName());//1
                out.println(remark.getLayer());//2
                out.println(remark.getWindowName());//3
                out.println(remark.getDishName());//4
                out.println(remark.getUserName());//5
                out.println(remark.getContent());//6
                out.println(remark.getDishScore());//7
                out.println(remark.getZanCount());//8
                out.println(remark.getCaiCount());//9
                out.println(remark.getTimeId());//10

                //设置踩和赞的状态
                Zan zan = null;
               try{
                   String userid = DaoFactory.getUserInstance().findByUserName(remark.getUserName()).getId();
                   zan = DaoFactory.getZanInstance().getZan(remark.getTimeId(),userid);
                   if(zan==null){
                           out.println("nozan");
                   }else{
                       if(zan.getCaiState()==1){//踩有两种状态，0表示无踩，1表示有踩。此时表示无踩，则设置踩
                              out.println("yeszan");
                       }else{//如果用户对该评论已经踩过了 设置踩状态为 无踩
                               out.println("nozan");
                       }//if
                   }//if
               }catch(Exception e){
                   e.printStackTrace();
               }//try
               //判断踩状态
               Zan cai = null;
               try{
                   String userid = DaoFactory.getUserInstance().findByUserName(remark.getUserName()).getId();
                   cai = DaoFactory.getZanInstance().getZan(remark.getTimeId(),userid);
                   if(cai==null){
                           out.println("nocai");
                   }else{
                       if(cai.getCaiState()==1){//踩有两种状态，0表示无踩，1表示有踩。此时表示无踩，则设置踩
                              out.println("yescai");
                       }else{//如果用户对该评论已经踩过了 设置踩状态为 无踩
                               out.println("nocai");
                       }//if
                   }//if
               }catch(Exception e){
                   e.printStackTrace();
               }//try
                
                }
            out.println("end");
        }catch(Exception e){
            e.printStackTrace();
        }//try
    }//sendRecommend
    
    public void sendBuilds()throws Exception{
        out.println("build");
        try{
            ArrayList<String> builds = new ArrayList<String>();
            builds = DaoFactory.getWindowInstance().findBuildName();
            for(String build: builds){
                    out.println(build);
            }//for
            out.println("end");
        }catch(Exception e){
            e.printStackTrace();
        }//try
        
    }//sendBuilds
    public void sendLayers(String build)throws Exception{
       
        out.println("layer");
        try{
            ArrayList<String> layers = new ArrayList<String>();
            layers = DaoFactory.getWindowInstance().findLayers(build);
            for(String layer:layers){
                    out.println(layer);
            }//for
            out.println("end");
        }catch(Exception e){
            e.printStackTrace();
        }//try
        
    }
    private void sendWindows(String build,String layer)throws Exception{
        out.println("window");
        try{
            ArrayList<String> windows = new ArrayList<String>();
            windows = DaoFactory.getWindowInstance().findWindowsbyBuildandLayer(build,layer);
            for(String window: windows){
                    out.println(window);
            }//for
            out.println("end");
        }catch(Exception e){
            e.printStackTrace();
        }//try
    }
    private void sendRecipe(String build,String layer,String windowname)throws Exception{
        out.println("recipe");
        try{
            ArrayList<String> recipes = new ArrayList<String>();
            Window window = DaoFactory.getWindowInstance().findWindowbyBuildLayerandWindowname(build, layer, windowname);
            recipes = DaoFactory.getRecipeInstance().findDishsbyWindowid(window);
            for(String recipe: recipes){
                    out.println(recipe);
            }//for
            out.println("end");
        }catch(Exception e){
            e.printStackTrace();
        }//try
    }
   private void TuCao(String username, String buildname,String layer, String windowname, String dishname, String content,int dishscore)throws Exception{
       out.println("tucao");
       try{
          if(DaoFactory.getRemarkInstance().insertRemark(username, buildname, layer, windowname, dishname, content, dishscore)){
              out.println("succeed");
          }else{
              out.println("failure");
          }//if
              
       }catch(Exception e){
           e.printStackTrace();
       }//try
   }
   private void remarkSearchbydishname(String dishname)throws Exception{
       out.println("searchremarkbydish");
       try{
           ArrayList<Remark> remarks = new ArrayList<Remark>();
           remarks = DaoFactory.getRemarkInstance().findRemarkbyDishName(dishname);
           for(Remark remark: remarks){
               out.println(remark.getBuildName());//1
               out.println(remark.getLayer());//2
               out.println(remark.getWindowName());//3
               out.println(remark.getDishName());//4
               out.println(remark.getUserName());//5
               out.println(remark.getContent());//6
               out.println(remark.getDishScore());//7
               out.println(remark.getZanCount());//8
               out.println(remark.getCaiCount());//9
               out.println(remark.getTimeId());//10
               }
           out.println("end");
       }catch(Exception e){
           e.printStackTrace();
       }//try
   }
   

   private void dishRemarkSearch(String buildname,String layer,String windowname, String dishname)throws Exception{
       out.println("dishremarksearch");
      // System.out.println("in dishreamrk search");
       try{
           int  count = 0;
           ArrayList<Remark> remarks = new ArrayList<Remark>();
           remarks = DaoFactory.getRemarkInstance().findRemarkbyBLWD(buildname, layer, windowname, dishname);
           for(Remark remark: remarks){
               //System.out.println("********"+ count++);
               out.println(remark.getBuildName());//1
               out.println(remark.getLayer());//2
               out.println(remark.getWindowName());//3
               out.println(remark.getDishName());//4
               out.println(remark.getUserName());//5
               out.println(remark.getContent());//6
               out.println(remark.getDishScore());//7
               out.println(remark.getZanCount());//8
               out.println(remark.getCaiCount());//9
               out.println(remark.getTimeId());//10               System.out.println("reamrk:"+remark.getDishName());
               }
           out.println("end");
       }catch(Exception e){
           e.printStackTrace();
       }//try
   }//dishRemarkSearch
   
   private void windowscoreSearch(String buildname,String layer,String windowname)throws Exception{
       out.println("windowscoresearch");
       try{
           //System.out.println("in  windowscoresearch");
           Window window = new Window();
           window = DaoFactory.getWindowInstance().findWindowbyBuildLayerandWindowname(buildname, layer, windowname);
           out.println(window.getWindowScore());//发送窗口 分数
//           ArrayList<String> recipes = new ArrayList<String>();
//           recipes = DaoFactory.getRecipeInstance().findDishsbyWindowid(window);
//           for(String recipe: recipes){
//               out.println(recipe);
//           }//for
//           out.println("end");
           
       }catch(Exception e){
           e.printStackTrace();
       }//try
   }//windowscoreSearch
   
   private void dimSearch(String buildname,String layer,String windowname, String dishname)throws Exception{//两种方式搜索评论
       //System.out.println(buildname+layer+windowname+"**"+dishname+"**");
       try{
           if(dishname.equals("")){
               windowscoreSearch(buildname,layer,windowname);//按照特定窗口搜索时，只发送窗口的分数
           }else{
               dishRemarkSearch(buildname,layer,windowname,dishname);//精准到特定窗口和菜谱名称搜索评论
           }//if
           
       }catch(Exception e){
           e.printStackTrace();
       }//try
       
   }//dimSearch
   private void setCai(Timestamp remarkid,String userid)throws Exception{
       Zan zan = null;
       //System.out.println(remarkid+"***"+userid);
       out.println("cai");
      try{
          zan = DaoFactory.getZanInstance().getCai(remarkid, userid);
          if(zan==null){
              if( DaoFactory.getZanInstance().insertCai(remarkid, userid, 1)){
                  out.println("succeed");
              }else{
                  out.println("failure");
              }//if
          }else{
              if(zan.getCaiState()==0){//踩有两种状态，0表示无踩，1表示有踩。此时表示无踩，则设置踩
                 if( DaoFactory.getZanInstance().setCaiState(remarkid, userid, 1)){
                     out.println("succeed");
                 }else{
                     out.println("failure");
                 }//if
                 
              }else{//如果用户对该评论已经踩过了 设置踩状态为 无踩
                  if( DaoFactory.getZanInstance().setCaiState(remarkid, userid, 0)){
                      out.println("repeat");
                  }else{
                      out.println("failure");
                  }//if
              }//if
          }//if
      }catch(Exception e){
          e.printStackTrace();
      }//try
   }//setCai
   private void setZan(Timestamp remarkid,String userid)throws Exception{
       //System.out.println(remarkid+"***"+userid);
       out.println("zan");
       Zan zan = null;
      try{
          zan = DaoFactory.getZanInstance().getZan(remarkid, userid);
          if(zan==null){//检查该用户是否已经有过赞的记录
                  if(DaoFactory.getZanInstance().insertZan(remarkid, userid,1)){//点赞
                      out.println("succeed");
                  }else{
                      out.println("failure");
                  }//if
          }else{
              if(zan.getZanState()==0){//赞有两种状态，0表示无赞，1表示有赞。此时表示无赞，则设置赞
                  if( DaoFactory.getZanInstance().setZanState(remarkid, userid, 1)){
                      out.println("succeed");
                  }else{
                      out.println("failure");
                  }//if
              }else{//如果用户对该评论已经赞过了 设置赞状态为 无赞
                  if( DaoFactory.getZanInstance().setZanState(remarkid, userid, 0) ){
                      out.println("repeat");
                  }else{
                      out.println("failure");
                  }//if
              }//if
          }//if
      }catch(Exception e){
          e.printStackTrace();
      }//try
   }//setZan
   
   private void sendTuiJian(int count)throws Exception{//按照窗口分数高低推荐窗口 最多推荐数目是count
       out.println("tuijian");
       try{
           ArrayList<Window> windows = new ArrayList<Window>(count);
           windows = DaoFactory.getWindowInstance().tuiJianWindows(count);//最多推荐15个
           for(Window window: windows){
                   out.println(window.getBuildName());
                   out.println(window.getLayer());
                   out.println(window.getWindowName());
                   out.println(window.getWindowScore());
                   out.println(window.getWindowId());
           }//for
           out.println("end");
       }catch(Exception e){
           e.printStackTrace();
       }//try
   }
   private void sendWindow_Recipes(int windowid)throws Exception{
      //System.out.println("windowid:" + windowid);
       out.println("windowinformation");
       try {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        recipes = DaoFactory.getRecipeInstance().findRecipsbyWindowId(windowid);
        for(Recipe recipe: recipes){
            //System.out.println("dish:" + recipe.getDishName());//**********
            out.println(recipe.getDishName());
            out.println(recipe.getAverageDishScore());
        }//for
        
        out.println("end");
    } catch (Exception e) {
        e.printStackTrace();
    }//try
   }
}
