package dao;

import java.util.ArrayList;

import vo.Window;

public interface IWindowDao {
    /**
     * @return 
     * @throws Exception
     */
    public ArrayList<String> findBuildName() throws Exception;
    /**
     * @param buildname
     * @return
     * @throws Exception
     */
    public ArrayList<String> findLayers(String buildname) throws Exception;
    /**
     * @param buildname
     * @param layer
     * @return
     * @throws Exception
     */
    public ArrayList<String> findWindowsbyBuildandLayer(String buildname,String layer) throws Exception;
    public Window findWindowbyBuildLayerandWindowname(String build,String layer,String windowname)throws Exception;
    public Window findWindowbyWindowId(int windowid)throws Exception;
    public ArrayList<Window> tuiJianWindows(int count) throws Exception;
    public boolean updateWindowName(int windowid,String windowname) throws Exception;
}
