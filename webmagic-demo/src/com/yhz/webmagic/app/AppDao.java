package com.yhz.webmagic.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AppDao {
	private Connection conn = null;
	private Statement stmt = null;
	
	public AppDao() {
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
	
	public int add(App app) {
        try {
            String sql = "INSERT INTO `xfyun`.`appinfo` ( `appName`, `appSize`, `updateTime`, `downloadCount`, `score`, `commentTotalNumber`, `goodComment`, `badComment`, `middleComment`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, app.getAppName());
            ps.setString(2, app.getAppSize());
            ps.setString(3, app.getUpdateTime());
            ps.setString(4, app.getDownloadCount());
            ps.setString(5, app.getScore());
            ps.setString(6, app.getCommentTotalNumber());
            ps.setString(7, app.getGoodComment());
            ps.setString(8, app.getBadComment());
            ps.setString(9, app.getMiddleComment());
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
