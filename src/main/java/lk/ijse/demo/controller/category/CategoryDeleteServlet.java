package lk.ijse.demo.controller.category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.demo.entity.Category;
import lk.ijse.demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoryDeleteServlet", value = "/category-delete")
public class CategoryDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(session.get(Category.class, id));
            tx.commit();

            List<Category> categoryList =  new ArrayList<>();
            categoryList = session.createQuery("from Category", Category.class).list();
            session.close();

            req.setAttribute("alert", "success");
            req.setAttribute("alertMessage", "Category deleted successfully");
            req.setAttribute("list", categoryList);
            req.setAttribute("list_type","Category");
            req.getRequestDispatcher("admin_panel.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("alert", "error");
            req.setAttribute("alertMessage", "Server error");
            req.getRequestDispatcher("admin_panel.jsp").forward(req, resp);
        }

    }
}
