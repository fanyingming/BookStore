package com.imu.csbookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imu.csbookstore.javabean.Book;
import com.imu.csbookstore.javabean.ShoppingCart;
import com.imu.csbookstore.javabean.User;
import com.imu.csbookstore.service.BookService;
import com.imu.csbookstore.service.DeliveryService;
import com.imu.csbookstore.service.IndentService;
import com.imu.csbookstore.service.ShoppingCartService;
import com.imu.csbookstore.service.UserService;
import com.imu.csbookstore.util.SessionChecker;

public class ShoppingCartServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShoppingCartServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		ShoppingCartService service = new ShoppingCartService(request);
		try {
			if (type.equals("add")) {
				int book_number=Integer.parseInt(request.getParameter("book_number"));
				int book_id = Integer.parseInt(request.getParameter("book_id"));
				service.addToCart(book_id,book_number);
				response.sendRedirect("ShoppingCartServlet?type=list");
			} else if (type.equals("list")) {
				List list = new ArrayList();
				list = service.ListCart();
				double totalPrice = service.getTotalPrice();
				request.setAttribute("totalPrice", totalPrice);
				request.setAttribute("list", list);
				request.getRequestDispatcher("shoppingCart.jsp").forward(
						request, response);
			} else if (type.equals("modify")) {
				service.modify();
				response.sendRedirect("ShoppingCartServlet?type=list");

			} else if (type.equals("delete")) {

				int book_id = Integer.parseInt(request.getParameter("book_id"));
				service.deleteByBookId(book_id);
				response.sendRedirect("ShoppingCartServlet?type=list");

			} else if (type.equals("modified")) {

			} else if (type.equals("beforePay")) {

				SessionChecker sessionChecker = new SessionChecker(request);
				if (!sessionChecker.checkUser()) {

					response.sendRedirect("login.jsp");
				} else {
					service.modify();
					List list = new ArrayList();
					list = service.ListCart();
					if (list == null || list.size() == 0) {// ����û�û�й����κζ����͵�֧����
						response.sendRedirect("ShoppingCartServlet?type=list");
					} else {
						// �û��ڹ��ﳵ�޸Ĺ����������ȷ��֧����Ӧ�����޸���Ʒ����Ȼ�������湤��
						
						double totalPrice = service.getTotalPrice();
						DeliveryService deliverService = new DeliveryService();
						//
						int user_id = sessionChecker.getUser_id();
						List deliveryList = new ArrayList();
						deliveryList = deliverService
								.searchDeliveryByUser(user_id);
						request.setAttribute("list", list);
						request.setAttribute("totalPrice", totalPrice);
						request.setAttribute("deliveryList", deliveryList);
						request.getRequestDispatcher("pay.jsp").forward(
								request, response);
					}
				}
			} else if (type.equals("pay")) {
				// ����֧���������裺
				// ����һ��indent��Ȼ���סindent_id�������indentDetails.

				service.addIndent();
				HttpSession session = request.getSession();
				// ��չ��ﳵ
				List list = null;
				session.setAttribute("cart", list);
				request.getRequestDispatcher("main.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
