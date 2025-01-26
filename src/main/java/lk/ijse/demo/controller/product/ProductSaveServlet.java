package lk.ijse.demo.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lk.ijse.demo.entity.Category;
import lk.ijse.demo.entity.Product;
import lk.ijse.demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet(name = "ProductSaveServlet", value = "/product-save")
public class ProductSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String category = req.getParameter("category");
        int qty = Integer.parseInt(req.getParameter("qty"));
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        Part imagePart = req.getPart("image");

        InputStream imageInputStream =  imagePart.getInputStream();
        BufferedImage image = ImageIO.read(imageInputStream);

        //Check image resolution
        if(image == null || image.getWidth() >= 800 || image.getHeight() >= 800) {
            req.setAttribute("alert", "error");
            req.setAttribute("alertMessage", "Image is too large");
            req.getRequestDispatcher("admin_panel.jsp").forward(req, resp);
            return;
        }

        //Create Image Save Path
        String fileName = imagePart.getSubmittedFileName();
        String uploadPath = "uploads/" + fileName;
        File uploadFile = new File(getServletContext().getRealPath("/") + uploadPath);
        uploadFile.getParentFile().mkdirs();
        imagePart.write(uploadFile.getAbsolutePath());

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            Category category1 = (Category) session.createQuery("from Category c where c.categoryName=:name")
            .setParameter("name", category).uniqueResult();

            Product p = new Product();
            p.setImage(uploadPath);
            p.setPrice(price);
            p.setCategory(category1);
            p.setProductName(name);
            p.setQuantity(qty);
            p.setDescription(description);
            session.save(p);
            tx.commit();

            List<Product> productList = new ArrayList<>();
            productList = session.createQuery("from Product", Product.class).list();
            session.close();

            req.setAttribute("alert", "success");
            req.setAttribute("alertMessage", "Product saved");
            req.setAttribute("list", productList);
            req.setAttribute("list_type","Product");
            req.getRequestDispatcher("admin_panel.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("alert", "error");
            req.setAttribute("alertMessage", "Server error");
            req.getRequestDispatcher("admin_panel.jsp").forward(req, resp);
        }

    }
}
