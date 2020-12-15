package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import article.model.Writer;
import jdbc.ConnectionProvider;

/**
 * Servlet implementation class ArticleSelectTestServlet
 */
@WebServlet("/testArticleSelected")
public class ArticleSelectTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleSelectTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArticleDao dao = new ArticleDao();
		ArticleContentDao cDao = new ArticleContentDao();
		try (Connection con = ConnectionProvider.getConnection()) {
			ArticleContent ac = cDao.selectedById(con, 54);
			Article n = dao.selectById(con, 54);
			String str3 = ac.getContent();
			String str = n.getTitle();
			String str2 = n.getWriter().getName();
			
			System.out.println(str + str2 + str3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
