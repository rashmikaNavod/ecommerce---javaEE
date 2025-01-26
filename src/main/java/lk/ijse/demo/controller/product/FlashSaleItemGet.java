package lk.ijse.demo.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.demo.entity.SaleProduct;
import lk.ijse.demo.util.HibernateUtil;
import org.hibernate.Session;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FlashSaleItemGet", value = "/flashSaleItemGet")
public class FlashSaleItemGet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SaleProduct> saleProductList =  new ArrayList<>();

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            saleProductList = session.createQuery("from SaleProduct", SaleProduct.class).list();
            session.close();

            JsonArrayBuilder productList = Json.createArrayBuilder();
            for(SaleProduct sale_product: saleProductList){
                JsonObjectBuilder sp = Json.createObjectBuilder();
                sp.add("productId", sale_product.getProductId());
                sp.add("imageUrl", sale_product.getImageUrl());
                sp.add("title", sale_product.getTitle());
                sp.add("price", sale_product.getPrice());
                sp.add("priceDecimal", sale_product.getPriceDecimal());
                sp.add("originalPrice", sale_product.getOriginalPrice());
                sp.add("discount", sale_product.getDiscount());
                productList.add(sp);
            }

            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", HttpServletResponse.SC_OK);
            response.add("alert", "success");
            response.add("alertMessage", "provide product");
            response.add("data", productList);
            resp.setContentType("application/json");
            resp.getWriter().write(response.build().toString());


        }catch (Exception e){
            e.printStackTrace();
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.add("message", "not provide all Customer");
            response.add("data", "");
            resp.setContentType("application/json");
            resp.getWriter().write(response.build().toString());

        }

    }
}
