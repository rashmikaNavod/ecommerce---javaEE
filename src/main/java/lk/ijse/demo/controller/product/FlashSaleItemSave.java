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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FlashSaleItemSave", value = "/flashSaleItemSave")
public class FlashSaleItemSave extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Product product = session.get(Product.class, id);

            String valueStr = String.valueOf(product.getPrice());
            String[] parts = valueStr.split("\\.");
            int decimalPart = Integer.parseInt(parts[1]);

            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setProductId(product.getProductId());
            saleProduct.setImageUrl(product.getImage());
            saleProduct.setTitle(product.getProductName());
            saleProduct.setPrice((int) (product.getPrice()-(product.getPrice()*0.4)));
            saleProduct.setPriceDecimal(decimalPart);
            saleProduct.setOriginalPrice(product.getPrice());
            saleProduct.setDiscount(40);

            session.save(saleProduct);
            tx.commit();

            req.setAttribute("alert", "success");
            req.setAttribute("alertMessage", "Product successfully added to sale");
//            req.setAttribute("saleProductList", saleProductList);
            req.getRequestDispatcher("admin_panel.jsp").forward(req, resp);

        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute("alert", "error");
            req.setAttribute("alertMessage", "Server error");
            req.getRequestDispatcher("admin_panel.jsp").forward(req, resp);
        }
    }

}
