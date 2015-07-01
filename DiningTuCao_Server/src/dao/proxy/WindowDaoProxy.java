package dao.proxy;
import java.util.ArrayList;

import vo.Window;
import dbc.*;
import dao.*;
import dao.impl.WindowDaoImpl;
public class WindowDaoProxy implements IWindowDao{
    private DatabaseConnection dbc = null;
    private IWindowDao dao = null;
    public WindowDaoProxy() throws Exception{
        this.dbc = new DatabaseConnection();
        this.dao = new WindowDaoImpl(this.dbc.getConnection());
    }
    @Override
    public ArrayList<String> findBuildName() throws Exception {
        ArrayList<String> buildsname = new ArrayList<String>();
        try{
            buildsname = this.dao.findBuildName();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }
        return buildsname;
    }

    public ArrayList<String> findLayers(String buildname) throws Exception {
        ArrayList<String> layers = new ArrayList<String>();
        try{
            layers = this.dao.findLayers(buildname);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }
        return layers;
    }

    public ArrayList<String> findWindowsbyBuildandLayer(String buildname, String layer)throws Exception{
        ArrayList<String> windows = new ArrayList<String>();
        try{
            windows = this.dao.findWindowsbyBuildandLayer(buildname,layer);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
                this.dbc.close();
            }
        return windows;
    }
    @Override
    public Window findWindowbyBuildLayerandWindowname(String build,String layer, String windowname) throws Exception {
        Window window = new Window();
        try{
            window = this.dao.findWindowbyBuildLayerandWindowname(build, layer, windowname);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }
        return window;
    }
    @Override
    public ArrayList<Window> tuiJianWindows(int count) throws Exception {
        ArrayList<Window> windows = new ArrayList<Window>();
        try{
            windows = this.dao.tuiJianWindows(count);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
                this.dbc.close();
            }
        return windows;
    }
    @Override
    public Window findWindowbyWindowId(int windowid) throws Exception {
        Window window = new Window();
        try{
            window = this.dao.findWindowbyWindowId(windowid);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
                this.dbc.close();
            }
        return window;
    }
    @Override
    public boolean updateWindowName(int windowid, String windowname)throws Exception {
       boolean flage = false;
       try{
           flage = this.dao.updateWindowName(windowid, windowname);
       }catch(Exception e ){
           e.printStackTrace();
       }finally{
           this.dbc.close();
       }
        return flage;
    }

}
