package lk.ijse.demo.controller.user;

import com.mysql.cj.xdevapi.JsonArray;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.demo.entity.User;
import lk.ijse.demo.util.HibernateUtil;
import org.hibernate.Session;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.IOException;

@WebServlet(name = "UserCheckServlet", value = "/user-check")
public class UserCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mobile = req.getParameter("number");
        String password = req.getParameter("password");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = (User) session.createQuery("from User u where u.phoneNumber = :mobile AND u.password = :password")
                    .setParameter("mobile", mobile)
                    .setParameter("password", password) // Ensure password is hashed
                    .uniqueResult();

            session.close();

            if(user!=null){
                JsonObjectBuilder response = Json.createObjectBuilder();
                response.add("status", HttpServletResponse.SC_OK);
                response.add("alert","success");
                response.add("alertMessage", "Login successful");
                response.add("id", user.getUserId());
                response.add("name", user.getName());
                resp.setContentType("application/json");
                resp.getWriter().write(response.build().toString());
            }else {
                JsonObjectBuilder response = Json.createObjectBuilder();
                response.add("alert","error");
                response.add("alertMessage", "Login failed");
                response.add("status", HttpServletResponse.SC_FOUND);
                resp.setContentType("application/json");
                resp.getWriter().write(response.build().toString());
            }

        } catch (Exception e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
            response.add("alert","error");
            response.add("alertMessage", "server error");
            resp.setContentType("application/json");
            resp.getWriter().write(response.build().toString());
            e.printStackTrace();
        }

    }
}
