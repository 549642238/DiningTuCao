package dao.proxy;

import java.util.ArrayList;

import vo.Recipe;
import vo.Window;
import dao.IRecipeDao;
import dao.impl.RecipeDaoImpl;
import dbc.DatabaseConnection;

public class RecipeDaoProxy implements IRecipeDao {
    private DatabaseConnection dbc = null;
    private IRecipeDao dao = null;
    public RecipeDaoProxy()throws Exception{
        this.dbc = new DatabaseConnection();
        this.dao = new RecipeDaoImpl(this.dbc.getConnection());
    }
    public ArrayList<String> findDishsbyWindowid(Window window)throws Exception {
        ArrayList<String> dishs = new ArrayList<String>();
        try{
            dishs = this.dao.findDishsbyWindowid(window);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }
        return dishs;
    }
    public Recipe findRecipeIdbyWindowDishname(Window window,String dishname)throws Exception {
        Recipe recipe = new Recipe();
        try{
            recipe = this.dao.findRecipeIdbyWindowDishname(window,dishname);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }
        return recipe;
    }
    @Override
    public ArrayList<Recipe> findRecipsbyWindowId(int windowid)throws Exception {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        try{
            recipes = this.dao.findRecipsbyWindowId(windowid);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }
        return recipes;
    }
    @Override
    public boolean insertDishname(int windowid, String dishname)throws Exception {
        boolean flage = false;
        try{
            this.dao.insertDishname(windowid, dishname);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }//try
        return flage;
    }
    @Override
    public boolean deleteDishname(int windowid, String dishname)throws Exception {
        boolean flage = false;
        try{
            this.dao.deleteDishname(windowid, dishname);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }//try
        return flage;
    }
    @Override
    public boolean updateDishname(int windowid, String olddishname, String newdishname) throws Exception {
        boolean flage = false;
        try{
            this.dao.updateDishname(windowid, olddishname, newdishname);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }//try
        return flage;
    }//updataeDishname


}
