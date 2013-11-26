package com.imu.csbookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.imu.csbookstore.javabean.Administrator;
import com.imu.csbookstore.util.DBPoolUtil;

public class AdministratorDao {
	/*
	 * �û�ע�ᣬ�����ݿ������һ���û�
	 * 
	 * @param Userʵ���࣬����Ҫ��ӵ��û���Ϣ
	 * 
	 * @return ����û��������ݿ��е��û����ظ�����ע��ʧ�ܣ�����false,��ӳɹ�����true
	 */

	public boolean addAdministrator(Administrator u) throws SQLException {
		Connection conn = DBPoolUtil.getConnection();
		String sql = "insert into tb_administrator(administrator_name,administrator_pass,administrator_mail) values(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, u.getAdministrator_name());
		pstmt.setString(2, u.getAdministrator_pass());
		pstmt.setString(3, u.getAdministrator_mail());
		pstmt.executeUpdate();
		DBPoolUtil.closeConnection(conn);
		return true;
	}

	public List listAllAdministrator() throws SQLException {
		List admins = new ArrayList();
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_administrator";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);

		while (result.next()) {
			Administrator admin = new Administrator();
			admin.setAdministrator_id(result.getInt("administrator_id"));
			admin.setAdministrator_name(result.getString("administrator_name"));
			admin.setAdministrator_pass(result.getString("administrator_pass"));
			admin.setAdministrator_mail(result.getString("administrator_mail"));
			admins.add(admin);
		}
		DBPoolUtil.closeConnection(conn);
		return admins;
	}

	/*
	 * ����û��Ƿ����
	 * 
	 * @param Userʵ���࣬�����û���¼��Ϣ
	 * 
	 * @return ����û����ڷ���true�������ڷ���false
	 */
	public boolean isExist(Administrator u) throws SQLException {
		boolean flag = false;
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_administrator where administrator_name='"
				+ u.getAdministrator_name()
				+ "' and administrator_pass='"
				+ u.getAdministrator_pass() + "'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);
		if (result.next())
			flag = true;
		DBPoolUtil.closeConnection(conn);
		return flag;
	}

	/*
	 * ͨ��UIDɾ��һ���û�
	 * 
	 * @param userName�û������ݿ����˺�����
	 * 
	 * @return ɾ���ɹ�����true,ʧ�ܷ���false
	 */
	public boolean deleteAdministratorByAdministratorId(int administrator_id)
			throws SQLException {
		Connection conn = DBPoolUtil.getConnection();
		String sql = "delete from tb_administrator where administrator_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, administrator_id);
		pstmt.executeUpdate();
		DBPoolUtil.closeConnection(conn);
		return true;
	}

	/*
	 * �޸��û���Ϣ
	 * 
	 * @param User�����û���Ϣ
	 * 
	 * @return �޸ĳɹ�����true,�޸�ʧ�ܷ���false
	 */
	public boolean modifyAdministrator(Administrator u) throws Exception {
		Connection conn = DBPoolUtil.getConnection();
		String sql = "update tb_administrator set administrator_name=?,administrator_pass=?,administrator_mail=? where administrator_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, u.getAdministrator_name());
		pstmt.setString(2, u.getAdministrator_pass());
		pstmt.setString(3, u.getAdministrator_mail());
		pstmt.setInt(4, u.getAdministrator_id());
		pstmt.executeUpdate();
		DBPoolUtil.closeConnection(conn);
		return true;
	}

	public Administrator getAdministratorById(int administrator_id)
			throws Exception {
		Administrator admin = new Administrator();
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_administrator where administrator_id="
				+ administrator_id;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);
		if (result.next()) {
			admin.setAdministrator_id(administrator_id);
			admin.setAdministrator_name(result.getString("Administrator_name"));
			admin.setAdministrator_pass(result.getString("Administrator_pass"));
			admin.setAdministrator_mail(result.getString("Administrator_mail"));
		}
		DBPoolUtil.closeConnection(conn);
		return admin;
	}

	public int getAdministratorTotalNum() throws Exception {
		int administrator_num = 0;
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_administrator ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);
		if (result.next()) {
			result.last();
			administrator_num = result.getRow();
		} else {
			administrator_num = 0;
		}
		DBPoolUtil.closeConnection(conn);
		return administrator_num;
	}

	public List listAllAdministratorOrderByAdministratorId(int begin, int offset)
			throws Exception {
		List admins = new ArrayList();
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_administrator order by administrator_id desc limit "
				+ begin + "," + offset;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);

		while (result.next()) {
			Administrator admin = new Administrator();
			admin.setAdministrator_id(result.getInt("administrator_id"));
			admin.setAdministrator_name(result.getString("administrator_name"));
			admin.setAdministrator_pass(result.getString("administrator_pass"));
			admin.setAdministrator_mail(result.getString("administrator_mail"));
			admins.add(admin);
		}
		DBPoolUtil.closeConnection(conn);
		return admins;
	}
}
