package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static int port = 9802;
    public static void main(String [] args){
        OnlineUser.users = new ArrayList<String>();
        OnlineUser.threads = new ArrayList<DealThread>();
        
        //System.out.println("服务器启动成功");
        System.out.println("server start succeed");
        try {
            showVersion();
            showLog();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }//try
        
        try{
            ServerSocket ss = new ServerSocket(port);
            while (true){
                    Socket cs = ss.accept(); 
                    DealThread t = new DealThread(cs);
                    
                    t.setName(cs.getInetAddress().toString()+":"+cs.getPort());//新用户请求连接，记录线程信息
                    t.start();
                    OnlineUser.threads.add(t);
                    //System.out.println("新的用户连接");
                    System.out.println("new user connected");
            }//while
          
        }catch(IOException e){//监控 ss 和 cs 
            //System.out.println("服务器未正常退出");
            System.out.println("server logout with error");
            e.printStackTrace();
        }finally{
          //  System.out.println("服务器关闭");
            System.out.println("server stoped");
        }//try
      }//main
    

    public  static void showVersion() throws Exception{//打印版本信息
        System.out.println("---------------------------------------------------");
        File file = new File("resources/version");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempstring = new String();
        while( (tempstring = reader.readLine() ) != null){
            System.out.println(tempstring);
        }//while
        System.out.println("---------------------------------------------------");
    }//showVersion
    
    public static void showLog() throws Exception{//打印版本信息
        System.out.println("---------------------------------------------------");
        File file = new File("resources/log");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempstring = new String();
        while( (tempstring = reader.readLine() ) != null){
            System.out.println(tempstring);
        }//while
        System.out.println("---------------------------------------------------");
    }//showVersion
    
}
