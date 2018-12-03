package com.yhz.webmagic.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CsdnBlogDao {
	 private Connection conn = null;
	    private Statement stmt = null;

	    public CsdnBlogDao() {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/xfyun?user=root&password=root&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true";
	            conn = DriverManager.getConnection(url);
	            stmt = conn.createStatement();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	    }

	    public int add(CsdnBlog csdnBlog) {
	        try {
	            String sql = "INSERT INTO `xfyun`.`csdnblog` (`id`, `title`, `date`, `tags`, `category`, `view`, `comments`, `copyright`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, csdnBlog.getId());
	            ps.setString(2, csdnBlog.getTitle());
	            ps.setString(3, csdnBlog.getDate());
	            ps.setString(4, csdnBlog.getTags());
	            ps.setString(5, csdnBlog.getCategory());
	            ps.setInt(6, csdnBlog.getView());
	            ps.setInt(7, csdnBlog.getComments());
	            ps.setInt(8, csdnBlog.getCopyright());
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally{
	        	try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
	        return -1;
	    }
}
