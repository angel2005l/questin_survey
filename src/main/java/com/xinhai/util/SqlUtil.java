package com.xinhai.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class SqlUtil {

	public static String getdate() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		return time;
	}

	/**
	 * 获得连接
	 * 
	 * @return con
	 */
	public static Connection getConn() {
		Connection con = null;
		try {
			String sqlcon = "jdbc:sqlite://c:/Users/xinhai/BI511_JAVA_HGY_TestForEmp.db";
			Class.forName("org.sqlite.JDBC").newInstance();
			con = DriverManager.getConnection(sqlcon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭statement
	 * 
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
				ps = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭ResultSet
	 * 
	 * @param set
	 */
	public static void close(ResultSet set) {
		try {
			if (set != null) {
				set.close();
				set = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭Connection
	 * 
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
	 * 
	 * @param str
	 *            * 13+任意数 * 15+除4的任意数 * 18+除1和4的任意数 * 17+除9的任意数 * 147
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static boolean isPhone(String str) throws PatternSyntaxException {
		String reg = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		return m.matches();// 正确为true
	}

	/**
	 * string类型判断英文
	 * 
	 * @param Str
	 *            System.out.println(isEnglish("程序员之家")); //false
	 *            System.out.println(isEnglish("robert")); //true
	 * @return
	 */
	public static boolean isEnglish(String Str) {
		return Str.matches("^[a-zA-Z]*");
	}

	/**
	 * char类型string类型判断中文
	 * 
	 * @param char
	 *            System.out.println(isChinese('员')); //true
	 *            System.out.println(isChinese('s')); //false
	 * @param str
	 *            System.out.println(isChinese("程序员论坛")); //true
	 * @return
	 */
	public static boolean isChinese(String str) {
		String regEx = "[\\u4e00-\\u9fa5]+";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.find())
			return true;
		else
			return false;
	}

	public static boolean isChinese(char chinese) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(chinese);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param sql
	 *            获得查询sql语句
	 * @return list 返回查询获取的数据
	 * @throws Exception
	 */
	public static List<Map<String, Object>> select(String sql) throws Exception {
		List list = new ArrayList(); // 查询后的结果存放位置
		String status = "no", result = "error"; // 数据库查询状态与结果
		Connection conn = getConn(); // 建立数据库连接conn
		Statement stat = (Statement) conn.createStatement(); // 创建Statment对象，执行SQL语句
		ResultSet rs = null; // 创建ResultSet结果集对象
		Map<String, Object> res_data = null; // 创建map，为查询结果的存放位置，注意：该对象只有一个内存地址，不可以存储多个内存地址
		try {
			rs = stat.executeQuery(sql); // 执行sql语句，将结果存入rs中
			while (rs.next()) { // 遍历结果集
				res_data = new HashMap<String, Object>();
				ResultSetMetaData md = rs.getMetaData(); // 接口，用来获取rs对象中的列类型和属性信息的对象
				int row = md.getColumnCount(); // 返回rs中的列数
				for (int i = 1; i <= row; i++) {
					res_data.put(md.getColumnLabel(i), rs.getObject(i)); // 遍历rs结果，存入map中

				}
				list.add(res_data); // 将map存入list中
				result = "success";
				status = "yes";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		} finally {
			close(rs);
			close(stat);
			close(conn);
		}
		res_data.put("status", status);
		res_data.put("result", result);
		return list;
	}

	/**
	 * 
	 * @param sql
	 *            获得增加sql语句
	 * @return list 返回增加获取的数据
	 * @throws Exception
	 */
	public static Map<String, Object> insert(String sql) throws Exception {
		Map<String, Object> res_data = new HashMap<String, Object>();
		String status = "no", result = "error";
		Connection conn = getConn();
		Statement stat = (Statement) conn.createStatement();
		try {
			int flag = 0;
			flag = stat.executeUpdate(sql);
			if (flag != 0) {
				status = "yes";
				result = "success";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		} finally {
			close(stat);
			close(conn);
		}
		res_data.put("status", status);
		res_data.put("result", result);
		return res_data;
	}

	/**
	 * 测试sqlite功能
	 * 
	 * @param args
	 * @throws SQLException
	 */

	 public static void main(String[] args) throws SQLException {
		 SqlUtil.getConn();
//	 UserDao dao = new UserDao();
//	 List<User> selUser = dao.selUser("admin");
//	 System.err.println(selUser);
	 }

	/**
	 * 测试判断方法
	 * 
	 * @param args
	 */
	// public static void main(String[] args) {
	/*
	 * System.out.println(isPhone("13815659845")); //true
	 * System.out.println(isPhone("1381231")); //false
	 * System.out.println(isChinese('员')); //true
	 * System.out.println(isChinese('s')); //false
	 * System.out.println(isEnglish("程序员之家")); //false
	 * System.out.println(isEnglish("robert")); //true
	 * System.out.println(isChinese("asdad")); //true
	 */
}
