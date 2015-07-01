package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Recipe;
import vo.Window;
import dao.IRecipeDao;

public class RecipeDaoImpl implements IRecipeDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    public RecipeDaoImpl(Connection conn){
        this.conn = conn;
    }
    public ArrayList<String> findDishsbyWindowid(Window window) throws Exception {//搜索特定窗口下的所有菜系名称
        ArrayList<String> dishs = new ArrayList<String>();
        String sql = "select distinct dishname from recipe where windowid=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, window.getWindowId());
        ResultSet rs = this.pstmt.executeQuery();
       
        while(rs.next()){
            dishs.add(rs.getString(1));
        }
        return dishs;
    }
    @Override
    public Recipe findRecipeIdbyWindowDishname(Window window,String dishname) throws Exception {
        Recipe recipe = new Recipe();
        
        String sql = "select distinct recipeid from recipe where windowid=? and dishname=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, window.getWindowId());
        this.pstmt.setString(2, dishname);
        ResultSet rs = this.pstmt.executeQuery();
       if(rs.next()){
           recipe.setRecipeId(rs.getInt(1));
           //*********************************************
       }//if
        return recipe;
    }
    @Override
    public ArrayList<Recipe> findRecipsbyWindowId(int windowid) throws Exception {//窗口的所有菜系名称和菜的平均分
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        String sql = "select recipeid,windowid,dishname,averagedishscore from recipe where windowid=? order by averagedishscore desc";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, windowid);
        ResultSet rs = this.pstmt.executeQuery();
       
        while(rs.next()){
            Recipe temprecipe = new Recipe();
            temprecipe.setRecipeId(rs.getInt(1));
            temprecipe.setWindowId(rs.getInt(2));
            temprecipe.setDishname(rs.getString(3));
            temprecipe.setAverageDishScore(rs.getDouble(4));
            recipes.add(temprecipe);
        }
        return recipes;
    }
  //食堂窗口工作人员可以管理自己窗口下的菜系
    public boolean insertDishname(int windowid ,String dishname) throws Exception{//增加新菜系
        boolean flage = false;
        String sql = "insert into recipe(windowid,dishname) values(?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, windowid);
        this.pstmt.setString(2,dishname);
        if(this.pstmt.executeUpdate()>0){
            flage = true;
        }
        return flage;
    }

    public boolean deleteDishname(int windowid ,String dishname) throws Exception{//删除菜系
        boolean flage = false;
        String sql = "delete from recipe where windowid=? and dishname=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, windowid);
        this.pstmt.setString(2,dishname);
        if(this.pstmt.executeUpdate()>0){
            flage = true;
        }
        return flage;
    }
    public boolean updateDishname(int windowid ,String olddishname,String newdishname) throws Exception{//更新菜名
        boolean flage = false;
        String sql = "update recipe set dishname=? where windowid=? and dishname=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(2, windowid);
        this.pstmt.setString(3,olddishname);
        this.pstmt.setString(1,newdishname);
        if(this.pstmt.executeUpdate()>0){
            flage = true;
        }
        return flage;
    }

    
}
