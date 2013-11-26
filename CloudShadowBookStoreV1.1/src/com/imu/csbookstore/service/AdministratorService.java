package com.imu.csbookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.imu.csbookstore.dao.AdministratorDao;
import com.imu.csbookstore.javabean.*;

public class AdministratorService {
	private AdministratorDao dao = new AdministratorDao();

	/*
	 * �û�ע�ᣬ�����ݿ������һ���û�
	 * 
	 * @param Userʵ���࣬����Ҫ��ӵ��û���Ϣ
	 * 
	 * @return ����û��������ݿ��е��û����ظ�����ע��ʧ�ܣ�����false,��ӳɹ�����true
	 */

	public boolean addAdministrator(Administrator u) throws Exception {
		return dao.addAdministrator(u);
	}

	public List listAllAdministrator() throws Exception {
		return dao.listAllAdministrator();
	}

	/*
	 * ����û��Ƿ����
	 * 
	 * @param Userʵ���࣬�����û���¼��Ϣ
	 * 
	 * @return ����û����ڷ���true�������ڷ���false
	 */
	public List listAllAdministratorOrderByAdministratorId(int begin, int offset)
			throws Exception {
		return dao.listAllAdministratorOrderByAdministratorId(begin, offset);
	}

	public boolean isExist(Administrator u) throws Exception {
		return dao.isExist(u);
	}

	/*
	 * ͨ��UIDɾ��һ���û�
	 * 
	 * @param userName�û������ݿ����˺�����
	 * 
	 * @return ɾ���ɹ�����true,ʧ�ܷ���false
	 */
	public boolean deleteAdministratorByAdministratorId(int administrator_id)
			throws Exception {
		return dao.deleteAdministratorByAdministratorId(administrator_id);
	}

	/*
	 * �޸��û���Ϣ
	 * 
	 * @param User�����û���Ϣ
	 * 
	 * @return �޸ĳɹ�����true,�޸�ʧ�ܷ���false
	 */
	public boolean modifyAdministrator(Administrator u) throws Exception {
		return dao.modifyAdministrator(u);
	}

	public Administrator getAdministratorById(int administrator_id)
			throws Exception {
		return dao.getAdministratorById(administrator_id);
	}

	public int getAdministratorTotalNum() throws Exception {
		return dao.getAdministratorTotalNum();
	}
}
