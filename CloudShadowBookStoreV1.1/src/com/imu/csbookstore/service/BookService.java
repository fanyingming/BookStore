package com.imu.csbookstore.service;

import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.imu.csbookstore.dao.BookDao;
import com.imu.csbookstore.javabean.Book;

public class BookService {
	BookDao dao = new BookDao();

	public boolean addBook(Book book) throws Exception {
		/*
		 * ͼ��ע�ᣬ�����ݿ������һ��ͼ��
		 * 
		 * @param Bookʵ���࣬����Ҫ��ӵ��û���Ϣ
		 * 
		 * @return ���ͼ��ID�����ݿ��е�ͼ�����ظ��������ʧ�ܣ�����false,��ӳɹ�����true
		 */
		return dao.addBook(book);
	}

	public List searchBookByClass(int class_id) throws Exception {
		/*
		 * ��ͼ��������ͼ��
		 * 
		 * @param class_idͼ�������
		 * 
		 * @return ���ز��ҵ���ͼ������
		 */
		return dao.searchBookByClass(class_id);
	}

	public boolean deleteBookByBookId(int book_id, String imgPath)
			throws Exception {
		/*
		 * ͨ��BIDɾ��һ��ͼ��
		 * 
		 * @param bidͼ�������ݿ��е�ID
		 * 
		 * @return ɾ���ɹ�����true,ʧ�ܷ���false
		 */
		return dao.deleteBookByBookId(book_id, imgPath);
	}

	public boolean modifyBook(Book book) throws Exception {
		/*
		 * �޸�ͼ������
		 * 
		 * @param Book����ͼ����Ϣ
		 * 
		 * @return �޸ĳɹ�����true,�޸�ʧ�ܷ���false
		 */
		return dao.modifyBook(book);
	}

	public List ListAllBooks() throws Exception {
		return dao.ListAllBooks();

	}

	public Book getBookByBookId(int book_id) throws Exception {
		return dao.getBookByBookId(book_id);
	}

	public int getBookTotalNumByClassId(int class_id) throws Exception {
		return dao.getBookTotalNumByClassId(class_id);
	}

	public int getBookTotalNum() throws Exception {
		return dao.getBookTotalNum();
	}

	public String getBookNameByBookId(int book_id) throws Exception {
		return dao.getBookNameByBookId(book_id);
	}

	public List listAllBooksOrderBySaleNum(int n) throws Exception {
		return dao.listAllBooksOrderBySaleNum(n);
	}

	public List listAllBooksOrderBySaleNum(int begin, int offset)
			throws Exception {
		return dao.listAllBooksOrderBySaleNum(begin, offset);
	}

	public List listAllBooksOrderByBookId(int n) throws Exception {
		return dao.listAllBooksOrderByBookId(n);
	}

	public List listAllBooksOrderByBookId(int begin, int offset)
			throws Exception {
		return dao.listAllBooksOrderByBookId(begin, offset);
	}

	// �г�ĳ������������õ�ǰn����
	public List listNBooksFromClassOrderBySaleNum(int class_id, int n)
			throws Exception {
		return dao.listNBooksFromClassOrderBySaleNum(class_id, n);
	}
	public List listBooksByClassOrderBySaleNum(int class_id,int begin, int offset)throws Exception {
		return dao.listBooksByClassOrderBySaleNum(class_id,begin,offset);
	}
	
	public int getSecrchBookNum(String keyWords)throws Exception{
		return dao.getSecrchBookNum(keyWords);
	}
	
	public List getSecrchBookByBookName(String keyWords,int begin,int offset)throws Exception{
		return dao.getSecrchBookByBookName(keyWords,begin,offset);
	}
}
