package lk.ijse.demo.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.demo.entity.User;
import lk.ijse.demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserDeactivateServlet", value = "/user-deactivate")
public class UserDeactivateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.createQuery("update User u set u.status = :status where u.userId = :id")
                    .setParameter("id", id)
                    .setParameter("status", "Deactivate")
                    .executeUpdate();
            tx.commit();

            List<User> userList = new ArrayList<>();
            userList = session.createQuery("from User", User.class).list();
            session.close();

            req.setAttribute("alert", "success");
            req.setAttribute("alertMessage", "User deactivate successfully");
            req.setAttribute("list", userList);
            req.setAttribute("list_type","User");
            req.getRequestDispatcher("admin_panel.jsp").forward(req, resp);

        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute("alert", "error");
            req.setAttribute("alertMessage", "Server error");
            req.getRequestDispatcher("admin_panel.jsp").forward(req, resp);
        }

    }
}
