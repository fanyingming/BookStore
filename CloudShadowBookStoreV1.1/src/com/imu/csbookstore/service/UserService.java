package com.imu.csbookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.imu.csbookstore.dao.UserDao;
import com.imu.csbookstore.javabean.User;

public class UserService {
	UserDao dao = new UserDao();

	public boolean addUser(User u) throws Exception {
		/*
		 * �û�ע�ᣬ�����ݿ������һ���û�
		 * 
		 * @param Userʵ���࣬����Ҫ��ӵ��û���Ϣ
		 * 
		 * @return ����û��������ݿ��е��û����ظ�����ע��ʧ�ܣ�����false,��ӳɹ�����true
		 */
		return dao.addUser(u);

	}

	public boolean isExist(User u) throws Exception {
		/*
		 * ����û��Ƿ����
		 * 
		 * @param Userʵ���࣬�����û���¼��Ϣ
		 * 
		 * @return ����û����ڷ���true�������ڷ���false
		 */
		return dao.isExist(u);
	}

	public boolean deleteUserByUid(int userName) throws Exception {
		/*
		 * ͨ��UIDɾ��һ���û�
		 * 
		 * @param userName�û������ݿ����˺�����
		 * 
		 * @return ɾ���ɹ�����true,ʧ�ܷ���false
		 */
		return dao.deleteUserByUid(userName);
	}

	public boolean modifyUser(User u) throws Exception {
		/*
		 * �޸��û���Ϣ
		 * 
		 * @param User�����û���Ϣ
		 * 
		 * @return �޸ĳɹ�����true,�޸�ʧ�ܷ���false
		 */
		return dao.modifyUser(u);
	}

	public List listAllUsers() throws Exception {
		return dao.listAllUsers();
	}

	public User getUserByUserId(int user_id) throws Exception {
		return dao.getUserByUserId(user_id);
	}

	public int getUserIdByUserName(String user_name) throws SQLException {
		return dao.getUserIdByUserName(user_name);
	}

	public String getUserNameByUserId(int user_id) throws Exception {
		return dao.getUserNameByUserId(user_id);
	}
	public int getUserTotalNum() throws Exception {
		return dao.getUserTotalNum();
	}
	public List listAllUserOrderByUserId(int begin, int offset) throws Exception{
		return dao.listAllUserOrderByUserId(begin, offset);
	}
}
