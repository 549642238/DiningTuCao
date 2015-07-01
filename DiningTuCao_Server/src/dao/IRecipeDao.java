package dao;

import java.util.ArrayList;

import vo.Recipe;
import vo.Window;

public interface IRecipeDao {
    public Recipe findRecipeIdbyWindowDishname(Window window,String dishname) throws Exception;
    public ArrayList<String> findDishsbyWindowid(Window window)throws Exception ;
    public ArrayList<Recipe> findRecipsbyWindowId(int windowid) throws Exception;
    public boolean insertDishname(int windowid ,String dishname) throws Exception;
    public boolean deleteDishname(int windowid ,String dishname) throws Exception;
    public boolean updateDishname(int windowid ,String olddishname,String newdishname) throws Exception;
}
