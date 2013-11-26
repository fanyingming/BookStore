package com.imu.csbookstore.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.imu.csbookstore.javabean.*;
import com.imu.csbookstore.util.SessionChecker;
import com.imu.csbookstore.util.ToolUtil;

public class ShoppingCartService {
	HttpServletRequest request;

	public ShoppingCartService(HttpServletRequest request) {
		this.request = request;
	}

	public void addToCart(int book_id,int book_number) {
		HttpSession session = request.getSession();
		List list = (List) session.getAttribute("cart");
		if (list == null) {
			list = new ArrayList();
			ShoppingCart cart = new ShoppingCart();
			cart.setBook_id(book_id);
			cart.setBook_number(book_number);
			cart.setDate(ToolUtil.getNowDate());
			// System.out.println("-----------"+cart.getDate());
			list.add(cart);
		} else {
			// ���ղؼ���������û���ظ����һ����Ʒ��
			int i;
			for (i = 0; i < list.size(); i++) {
				ShoppingCart cart = (ShoppingCart) list.get(i);
				if (cart.getBook_id() == book_id) {
					cart.setBook_number((cart.getBook_number() + book_number));
					list.set(i, cart);
					break;
				}
			}
			if (i == list.size()) {
				ShoppingCart cart = new ShoppingCart();
				cart.setBook_id(book_id);
				cart.setBook_number(book_number);
				cart.setDate(ToolUtil.getNowDate());
				list.add(cart);
			}
		}

		session.setAttribute("cart", list);

	}

	public List ListCart() throws Exception {
		HttpSession session = request.getSession();
		List list = (List) session.getAttribute("cart");
		if (list == null)
			return list;
		List newList = new ArrayList();
		BookService bookService = new BookService();
		for (int i = 0; i < list.size(); i++) {
			ShoppingCart cart = (ShoppingCart) list.get(i);
			ShoppingCart newCart = new ShoppingCart();
			newCart.setBook_id(cart.getBook_id());
			newCart.setBook_number(cart.getBook_number());
			newCart.setDate(cart.getDate());
			Book book = bookService.getBookByBookId(cart.getBook_id());

			newCart.setBook_locationName(book.getBook_locationName());
			newCart.setBook_name(book.getBook_name());
			newCart.setBook_price(book.getBook_price());
			newList.add(newCart);
		}
		session.setAttribute("cart", newList);
		return newList;
	}

	public void deleteByBookId(int book_id) {
		HttpSession session = request.getSession();
		List list = (List) session.getAttribute("cart");
		for (int i = 0; i < list.size(); i++) {
			ShoppingCart cart = (ShoppingCart) list.get(i);
			if (cart.getBook_id() == book_id) {
				list.remove(i);
				session.setAttribute("cart", list);
				break;
			}
		}
	}

	public void modify() {

		HttpSession session = request.getSession();
		List list = (List) session.getAttribute("cart");
		for (int i = 0; i < list.size(); i++) {
			ShoppingCart cart = (ShoppingCart) list.get(i);
			// ���ÿ����Ʒ�ĸ�����ͨ��"book_number"+ͼ����ȡ������
			if (request.getParameter("book_number" + cart.getBook_id()) != null) {
				int book_number = Integer.parseInt(request
						.getParameter("book_number" + cart.getBook_id()));
				cart.setBook_number(book_number);
				list.set(i, cart);
			}
		}
		session.setAttribute("cart", list);
	}

	public double getTotalPrice() {

		double totalPrice = 0;
		HttpSession session = request.getSession();
		List list = (List) session.getAttribute("cart");
		if (list == null)
			return 0;
		for (int i = 0; i < list.size(); i++) {
			ShoppingCart cart = (ShoppingCart) list.get(i);
			totalPrice += cart.getBook_price() * cart.getBook_number();
			// System.out.println("Book_price:"+cart.getBook_price());
			/*
			 * if(request.getParameter("book_number"+cart.getBook_id())!=null){
			 * int
			 * book_number=Integer.parseInt(request.getParameter("book_number"
			 * +cart.getBook_id())); totalPrice +=
			 * book_number*cart.getBook_price(); }else{ return -1; }
			 */
		}
		return totalPrice;

	}

	public void addIndent() throws Exception {
		IndentService indentService = new IndentService();
		Indent indent = new Indent();
		if (request.getParameter("delivery_id") == null)
			return;
		int delivery_id = Integer.parseInt(request.getParameter("delivery_id"));
		SessionChecker sessionChecker = new SessionChecker(request);
		int user_id = sessionChecker.getUser_id();
		double indent_price = Double.parseDouble(request
				.getParameter("indent_price"));
		indent.setDelivery_id(delivery_id);
		indent.setUser_id(user_id);
		indent.setIndent_price(indent_price);
		indentService.addIndent(indent);

		int indent_id = indentService.getLatestIndentIdByUserId(user_id);
		HttpSession session = request.getSession();
		List list = (List) session.getAttribute("cart");
		for (int i = 0; i < list.size(); i++) {
			ShoppingCart cart = (ShoppingCart) list.get(i);
			IndentDetails indentDetails = new IndentDetails();
			indentDetails.setBook_id(cart.getBook_id());
			indentDetails.setIndent_id(indent_id);
			indentDetails.setNumber(cart.getBook_number());
			indentDetails.setPrice(cart.getBook_price());
			IndentDetailsService indentDetailsService = new IndentDetailsService();
			indentDetailsService.addIndent(indentDetails);
			// ����궩���󣬻���Ҫ�޸�ÿ����Ŀ���������
			int book_id = cart.getBook_id();
			int book_number = cart.getBook_number();
			BookService bookService = new BookService();
			Book book = bookService.getBookByBookId(book_id);
			book.setBook_number(book.getBook_number() - book_number);
			book.setBook_saleNum(book.getBook_saleNum() + book_number);
			bookService.modifyBook(book);
		}
	}
}
