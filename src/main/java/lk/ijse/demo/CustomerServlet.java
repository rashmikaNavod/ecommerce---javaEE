package lk.ijse.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lk.ijse.demo.entity.User;
import lk.ijse.demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

@WebServlet(name = "CustomerServlet", value = "/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("customer_name");
        String address = req.getParameter("customer_address");
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            User u = new User();
            u.setName(name);
//            u.setAddress(address);
            session.save(u);
            tx.commit();
            resp.sendRedirect("index.jsp?message=success");
        }catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("index.jsp?error=failure");
        }
    }


}
