package lk.ijse.demo.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.demo.entity.Product;
import lk.ijse.demo.entity.SaleProduct;
import lk.ijse.demo.util.HibernateUtil;
import org.hibernate.Session;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductViewServlet", value = "/product-view")
public class ProductViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> productList = new ArrayList<>();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            productList = session.createQuery("from Product", Product.class).list();
            session.close();

            JsonArrayBuilder allProduct = Json.createArrayBuilder();
            for(Product product : productList){
                JsonObjectBuilder p = Json.createObjectBuilder();
                p.add("productId", product.getProductId());
                p.add("productName", product.getProductName());
                p.add("description", product.getDescription());
                p.add("image", product.getImage());
                p.add("price", product.getPrice());
                p.add("quantity", product.getQuantity());
                allProduct.add(p);
            }

            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", HttpServletResponse.SC_OK);
            response.add("alert", "success");
            response.add("alertMessage", "provide product");
            response.add("data", allProduct);
            resp.setContentType("application/json");
            resp.getWriter().write(response.build().toString());

            req.setAttribute("list", productList);
            req.setAttribute("list_type","Product");
            req.getRequestDispatcher("admin_panel.jsp").forward(req, resp);

        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute("alert", "error");
            req.setAttribute("alertMessage", "Server error");
            req.getRequestDispatcher("admin_panel.jsp").forward(req, resp);
        }

    }

}
