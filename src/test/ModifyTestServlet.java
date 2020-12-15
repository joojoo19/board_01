package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import auth.service.User;
import jdbc.ConnectionProvider;

/**
 * Servlet implementation class ModifyTestServlet
 */
@WebServlet("/testarticlemodify")
public class ModifyTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User authUser = (User) request.getSession().getAttribute("authUser");
		String noVal = request.getParameter("no");
		System.out.println(noVal);
		ArticleDao dao = new ArticleDao();
		ArticleContentDao cDao = new ArticleContentDao();
		try (Connection con = ConnectionProvider.getConnection()) {
			int ac = cDao.update(con, 49, "테스트 수정");
		    int n = dao.update(con, 49, "테스트 수정");
			
			System.out.println(ac + n);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
