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
import org.hibernate.Transaction;

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

//            JsonArrayBuilder productList = Json.createArrayBuilder();
//            for(SaleProduct sale_product: productList){
//                JsonObjectBuilder sp = Json.createObjectBuilder();
//                sp.add("productId", sale_product.getProductId());
//                sp.add("imageUrl", sale_product.getImageUrl());
//                sp.add("title", sale_product.getTitle());
//                sp.add("price", sale_product.getPrice());
//                sp.add("priceDecimal", sale_product.getPriceDecimal());
//                sp.add("originalPrice", sale_product.getOriginalPrice());
//                sp.add("discount", sale_product.getDiscount());
//                productList.add(sp);
//            }

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
