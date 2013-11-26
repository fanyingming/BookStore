package com.imu.csbookstore.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.imu.csbookstore.javabean.Book;
import com.imu.csbookstore.util.DBPoolUtil;
import com.imu.csbookstore.util.ImgWriteUtil;
import com.imu.csbookstore.util.UploadUtil;

public class BookDao {
	public boolean addBook(Book book) throws Exception {
		/*
		 * ͼ��ע�ᣬ�����ݿ������һ��ͼ��
		 * 
		 * @param Bookʵ���࣬����Ҫ��ӵ��û���Ϣ
		 * 
		 * @return ���ͼ��ID�����ݿ��е�ͼ�����ظ��������ʧ�ܣ�����false,��ӳɹ�����true
		 */
		System.out.println("ѡ������ID��" + book.getBook_classId());
		Connection conn = DBPoolUtil.getConnection();
		if (book.getBook_locationName() != null
				&& !book.getBook_locationName().equals("")) {
			String sql = "insert into tb_book (book_name,book_author,book_press,book_describtion,book_price,book_locationName,book_number,book_classID) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBook_name());
			pstmt.setString(2, book.getBook_author());
			pstmt.setString(3, book.getBook_press());
			pstmt.setString(4, book.getBook_describtion());
			pstmt.setDouble(5, book.getBook_price());
			pstmt.setString(6, book.getBook_locationName());

			pstmt.setInt(7, book.getBook_number());
			pstmt.setInt(8, book.getBook_classId());
			pstmt.executeUpdate();
		} else {
			String sql = "insert into tb_book (book_name,book_author,book_press,book_describtion,book_price,book_number,book_classID) values(?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBook_name());
			pstmt.setString(2, book.getBook_author());
			pstmt.setString(3, book.getBook_press());
			pstmt.setString(4, book.getBook_describtion());
			pstmt.setDouble(5, book.getBook_price());
			pstmt.setInt(6, book.getBook_number());
			pstmt.setInt(7, book.getBook_classId());

			pstmt.executeUpdate();
		}
		DBPoolUtil.closeConnection(conn);
		return true;
	}

	public List searchBookByClass(int class_id) throws Exception {
		/*
		 * ��ͼ��������ͼ��
		 * 
		 * @param class_idͼ�������
		 * 
		 * @return ���ز��ҵ���ͼ������
		 */
		List books = new ArrayList();
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_book where book_classID=" + class_id
				+ " order by book_saleNum desc";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// pstmt.setInt(1, class_id);
		ResultSet result = pstmt.executeQuery(sql);

		while (result.next()) {
			Book u = new Book();
			u.setBook_id(result.getInt("book_id"));
			u.setBook_name(result.getString("book_name"));
			u.setBook_author(result.getString("book_author"));
			u.setBook_press(result.getString("book_press"));
			u.setBook_describtion(result.getString("book_describtion"));
			u.setBook_price(result.getDouble("book_price"));
			u.setBook_locationName(result.getString("book_locationName"));
			u.setBook_number(result.getInt("book_number"));
			u.setBook_classId(result.getInt("book_classID"));
			u.setBook_saleNum(result.getInt("book_saleNum"));
			books.add(u);
		}
		DBPoolUtil.closeConnection(conn);
		return books;
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
		Connection conn = DBPoolUtil.getConnection();
		String sql = "delete from tb_book where book_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, book_id);
		pstmt.executeUpdate();
		DBPoolUtil.closeConnection(conn);
		// ɾ��ͼƬ�ļ�
		File file = new File(imgPath);
		// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��
		if (file.isFile() && file.exists()) {
			file.delete();
		}
		return true;
	}

	public boolean modifyBook(Book book) throws Exception {
		/*
		 * �޸�ͼ������
		 * 
		 * @param Book����ͼ����Ϣ
		 * 
		 * @return �޸ĳɹ�����true,�޸�ʧ�ܷ���false
		 */
		//
		// System.out.println("ѡ������ID��"+book.getBook_classId());
		Connection conn = DBPoolUtil.getConnection();
		if (book.getBook_locationName() != null
				&& !book.getBook_locationName().equals("")) {
			String sql = "update tb_book set book_name=?,book_author=?,book_press=?,"
					+ "book_describtion=?,book_price=?,book_locationName=?,book_number=?,"
					+ "book_classID=? where book_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBook_name());
			pstmt.setString(2, book.getBook_author());
			pstmt.setString(3, book.getBook_press());
			pstmt.setString(4, book.getBook_describtion());
			pstmt.setDouble(5, book.getBook_price());
			pstmt.setString(6, book.getBook_locationName());
			pstmt.setInt(7, book.getBook_number());
			pstmt.setInt(8, book.getBook_classId());
			// pstmt.setInt(9, book.getBook_saleNum());
			pstmt.setInt(9, book.getBook_id());
			pstmt.executeUpdate();
		} else {
			String sql = "update tb_book set book_name=?,book_author=?,book_press=?,"
					+ "book_describtion=?,book_price=?,book_number=?,"
					+ "book_classID=? where book_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBook_name());
			pstmt.setString(2, book.getBook_author());
			pstmt.setString(3, book.getBook_press());
			pstmt.setString(4, book.getBook_describtion());
			pstmt.setDouble(5, book.getBook_price());

			pstmt.setInt(6, book.getBook_number());
			pstmt.setInt(7, book.getBook_classId());
			// pstmt.setInt(8, book.getBook_saleNum());
			pstmt.setInt(8, book.getBook_id());
			pstmt.executeUpdate();
		}
		DBPoolUtil.closeConnection(conn);
		return true;
	}

	public List ListAllBooks() throws Exception {
		List book = new ArrayList();
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_book";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);

		while (result.next()) {
			Book u = new Book();
			u.setBook_id(result.getInt("book_id"));
			u.setBook_name(result.getString("book_name"));
			u.setBook_author(result.getString("book_author"));
			u.setBook_press(result.getString("book_press"));
			u.setBook_describtion(result.getString("book_describtion"));
			u.setBook_price(result.getDouble("book_price"));
			u.setBook_locationName(result.getString("book_locationName"));
			u.setBook_number(result.getInt("book_number"));
			u.setBook_classId(result.getInt("book_classID"));
			u.setBook_saleNum(result.getInt("book_saleNum"));
			book.add(u);
		}
		DBPoolUtil.closeConnection(conn);
		return book;

	}

	public Book getBookByBookId(int book_id) throws Exception {
		Book u = new Book();
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_book where book_id=" + book_id;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);
		if (result.next()) {
			u.setBook_id(book_id);
			u.setBook_name(result.getString("book_name"));
			u.setBook_author(result.getString("book_author"));
			u.setBook_press(result.getString("book_press"));
			u.setBook_describtion(result.getString("book_describtion"));
			u.setBook_price(result.getDouble("book_price"));
			u.setBook_locationName(result.getString("book_locationName"));
			u.setBook_number(result.getInt("book_number"));
			u.setBook_classId(result.getInt("book_classID"));
			u.setBook_saleNum(result.getInt("book_saleNum"));
		}
		DBPoolUtil.closeConnection(conn);
		return u;

	}

	public int getBookTotalNumByClassId(int class_id) throws Exception {
		int book_num = 0;
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_book where book_classID=" + class_id;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);
		if (result.next()) {
			result.last();
			book_num = result.getRow();
		} else {
			book_num = 0;
		}
		DBPoolUtil.closeConnection(conn);
		return book_num;
	}

	public int getBookTotalNum() throws Exception {
		int book_num = 0;
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_book ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);
		if (result.next()) {
			result.last();
			book_num = result.getRow();
		} else {
			book_num = 0;
		}
		DBPoolUtil.closeConnection(conn);
		return book_num;
	}

	public String getBookNameByBookId(int book_id) throws Exception {
		String str = "";
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_book where book_id='" + book_id + "'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);
		if (result.next()) {
			str = result.getString("book_name");
		}
		DBPoolUtil.closeConnection(conn);
		return str;
	}

	public List listAllBooksOrderBySaleNum(int n) throws Exception {
		List book = new ArrayList();
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_book order by book_saleNum desc limit "
				+ n;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);

		while (result.next()) {
			Book u = new Book();
			u.setBook_id(result.getInt("book_id"));
			u.setBook_name(result.getString("book_name"));
			u.setBook_author(result.getString("book_author"));
			u.setBook_press(result.getString("book_press"));
			u.setBook_describtion(result.getString("book_describtion"));
			u.setBook_price(result.getDouble("book_price"));
			u.setBook_locationName(result.getString("book_locationName"));
			u.setBook_number(result.getInt("book_number"));
			u.setBook_classId(result.getInt("book_classID"));
			u.setBook_saleNum(result.getInt("book_saleNum"));
			book.add(u);
		}
		DBPoolUtil.closeConnection(conn);
		return book;
	}

	public List listAllBooksOrderBySaleNum(int begin, int offset)
			throws Exception {

		List book = new ArrayList();
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_book order by book_saleNum desc limit "
				+ begin + "," + offset;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);

		while (result.next()) {
			Book u = new Book();
			u.setBook_id(result.getInt("book_id"));
			u.setBook_name(result.getString("book_name"));
			u.setBook_author(result.getString("book_author"));
			u.setBook_press(result.getString("book_press"));
			u.setBook_describtion(result.getString("book_describtion"));
			u.setBook_price(result.getDouble("book_price"));
			u.setBook_locationName(result.getString("book_locationName"));
			u.setBook_number(result.getInt("book_number"));
			u.setBook_classId(result.getInt("book_classID"));
			u.setBook_saleNum(result.getInt("book_saleNum"));
			book.add(u);
		}
		DBPoolUtil.closeConnection(conn);
		return book;
	}

	public List listAllBooksOrderByBookId(int n) throws Exception {
		List book = new ArrayList();
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_book order by book_id desc limit " + n;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);

		while (result.next()) {
			Book u = new Book();
			u.setBook_id(result.getInt("book_id"));
			u.setBook_name(result.getString("book_name"));
			u.setBook_author(result.getString("book_author"));
			u.setBook_press(result.getString("book_press"));
			u.setBook_describtion(result.getString("book_describtion"));
			u.setBook_price(result.getDouble("book_price"));
			u.setBook_locationName(result.getString("book_locationName"));
			u.setBook_number(result.getInt("book_number"));
			u.setBook_classId(result.getInt("book_classID"));
			u.setBook_saleNum(result.getInt("book_saleNum"));
			book.add(u);
		}
		DBPoolUtil.closeConnection(conn);
		return book;
	}

	public List listAllBooksOrderByBookId(int begin, int offset)
			throws Exception {
		List book = new ArrayList();
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_book order by book_id desc limit "
				+ begin + "," + offset;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);

		while (result.next()) {
			Book u = new Book();
			u.setBook_id(result.getInt("book_id"));
			u.setBook_name(result.getString("book_name"));
			u.setBook_author(result.getString("book_author"));
			u.setBook_press(result.getString("book_press"));
			u.setBook_describtion(result.getString("book_describtion"));
			u.setBook_price(result.getDouble("book_price"));
			u.setBook_locationName(result.getString("book_locationName"));
			u.setBook_number(result.getInt("book_number"));
			u.setBook_classId(result.getInt("book_classID"));
			u.setBook_saleNum(result.getInt("book_saleNum"));
			book.add(u);
		}
		DBPoolUtil.closeConnection(conn);
		return book;
	}

	public List listNBooksFromClassOrderBySaleNum(int class_id, int n)
			throws Exception {
		List books = new ArrayList();
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_book where book_classID=" + class_id
				+ " order by book_saleNum desc limit " + n;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// pstmt.setInt(1, class_id);
		// pstmt.setInt(2, n);
		ResultSet result = pstmt.executeQuery(sql);

		while (result.next()) {
			Book u = new Book();
			u.setBook_id(result.getInt("book_id"));
			u.setBook_name(result.getString("book_name"));
			u.setBook_author(result.getString("book_author"));
			u.setBook_press(result.getString("book_press"));
			u.setBook_describtion(result.getString("book_describtion"));
			u.setBook_price(result.getDouble("book_price"));
			u.setBook_locationName(result.getString("book_locationName"));
			u.setBook_number(result.getInt("book_number"));
			u.setBook_classId(result.getInt("book_classID"));
			u.setBook_saleNum(result.getInt("book_saleNum"));
			books.add(u);
		}
		DBPoolUtil.closeConnection(conn);
		return books;
	}

	public List listBooksByClassOrderBySaleNum(int class_id, int begin,
			int offset) throws Exception{
			List book = new ArrayList();
			Connection conn = DBPoolUtil.getConnection();
			Statement stmt;
			String sql = "select * from tb_book where book_ClassID="+class_id+" order by book_saleNum desc limit "
					+ begin + "," + offset;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet result = pstmt.executeQuery(sql);

			while (result.next()) {
				Book u = new Book();
				u.setBook_id(result.getInt("book_id"));
				u.setBook_name(result.getString("book_name"));
				u.setBook_author(result.getString("book_author"));
				u.setBook_press(result.getString("book_press"));
				u.setBook_describtion(result.getString("book_describtion"));
				u.setBook_price(result.getDouble("book_price"));
				u.setBook_locationName(result.getString("book_locationName"));
				u.setBook_number(result.getInt("book_number"));
				u.setBook_classId(result.getInt("book_classID"));
				u.setBook_saleNum(result.getInt("book_saleNum"));
				book.add(u);
			}
			DBPoolUtil.closeConnection(conn);
			return book;
	}

	public int getSecrchBookNum(String keyWords)throws Exception {
		int book_num = 0;
		Connection conn = DBPoolUtil.getConnection();
		Statement stmt;
		String sql = "select * from tb_book where book_name like '%"+keyWords+"%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery(sql);
		if (result.next()) {
			result.last();
			book_num = result.getRow();
		} else {
			book_num = 0;
		}
		DBPoolUtil.closeConnection(conn);
		return book_num;
	}

	public List getSecrchBookByBookName(String keyWords, int begin, int offset) throws Exception{

			List book = new ArrayList();
			Connection conn = DBPoolUtil.getConnection();
			Statement stmt;
			String sql = "select * from tb_book where book_name like '%"+keyWords+"%' order by book_saleNum desc limit "
					+ begin + "," + offset;

			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet result = pstmt.executeQuery(sql);
			System.out.println("begin:"+begin);
			System.out.println("offset:"+offset);
			System.out.println("keyWords:"+keyWords);
			int i=0;
			while (result.next()) {
				i++;
				Book u = new Book();
				u.setBook_id(result.getInt("book_id"));
				u.setBook_name(result.getString("book_name"));
				u.setBook_author(result.getString("book_author"));
				u.setBook_press(result.getString("book_press"));
				u.setBook_describtion(result.getString("book_describtion"));
				u.setBook_price(result.getDouble("book_price"));
				u.setBook_locationName(result.getString("book_locationName"));
				u.setBook_number(result.getInt("book_number"));
				u.setBook_classId(result.getInt("book_classID"));
				u.setBook_saleNum(result.getInt("book_saleNum"));
				book.add(u);
			}
			System.out.println("num="+i);
			DBPoolUtil.closeConnection(conn);
			return book;
	}

}
