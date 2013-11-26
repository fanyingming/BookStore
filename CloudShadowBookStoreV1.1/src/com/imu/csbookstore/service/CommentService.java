package com.imu.csbookstore.service;

import java.util.List;

import com.imu.csbookstore.dao.CommentDao;
import com.imu.csbookstore.javabean.Comment;

public class CommentService {
	CommentDao dao = new CommentDao();

	public void addComment(Comment comment) throws Exception {
		/*
		 * ����һ������
		 * 
		 * @param comment������Ҫ�������
		 * 
		 * @void
		 */
		dao.addComment(comment);
	}

	public List listCommentByBookID(int book_id) throws Exception {
		/*
		 * ����ͼ��id�г�������������
		 * 
		 * @param book_id
		 * 
		 * @return List �����Ȿ���������������
		 */
		return dao.listCommentByBookID(book_id);
	}

	public boolean deleteCommentByCommentID(int commentID) throws Exception {
		/*
		 * ͨ������ID��������ɾ��һ������
		 * 
		 * @param commentID����ID
		 * 
		 * @return ɾ���ɹ�����true,ʧ�ܷ���false
		 */
		return dao.deleteCommentByCommentID(commentID);
	}

	public boolean modifyCommentState(int state) throws Exception {
		/*
		 * �޸�����
		 * 
		 * @param state 1�����ܹ���ʾ
		 * 
		 * @return �޸ĳɹ�����true,�޸�ʧ�ܷ���false
		 */
		CommentDao dao = new CommentDao();
		dao.modifyCommentState(state);
		return true;
	}


	public List listAllComment() throws Exception {
		return dao.listAllComment();
	}

	public List listCommentByUserID(int user_id) throws Exception {
		return dao.listCommentByUserID(user_id);
	}
	public int getCommentTotalNum() throws Exception {
		return dao.getCommentTotalNum();
	}
	public List listAllCommentOrderByUserId(int begin, int offset) throws Exception{
		return dao.listAllCommentOrderByUserId(begin, offset);
	}
	public List listAllCommentOrderByCommentIdAndUserId(int user_id,int begin, int offset) throws Exception{
		return dao.listAllCommentOrderByCommentIdAndUserId(user_id,begin, offset);
	}
	public int getCommentTotalNumByUserId(int user_id) throws Exception {
		return dao.getCommentTotalNumByUserId(user_id);
	}
}
