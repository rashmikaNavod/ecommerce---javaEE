<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.demo.entity.Category" %>
<%@ page import="lk.ijse.demo.entity.Product" %>
<%@ page import="lk.ijse.demo.util.HibernateUtil" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="lk.ijse.demo.controller.category.CategoryViewServlet" %>
<%@ page import="lk.ijse.demo.entity.User" %>
<%@ page import="lk.ijse.demo.entity.SaleProduct" %><%--
  Created by IntelliJ IDEA.
  User: Rashmika Navo
  Date: 1/16/2025
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">--%>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/model.css">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v2.1.6/css/unicons.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  </head>

  <body>

  <%
    String alert = (String) request.getAttribute("alert");
    String alertMessage = (String) request.getAttribute("alertMessage");

    if(alert != null && alertMessage != null){
  %>
  <script>
    Swal.fire({
      icon: '<%= alert %>',
      // title: 'Product Saved!',
      text: '<%= alertMessage %>',
    });
  </script>
  <%
    }
  %>

  <nav>
    <div class="container d-flex align-items-center justify-content-between">
      <img src="assests/easyBird.png" alt="" class="nav-logo">
      <a href="">LOG OUT</a>
    </div>
  </nav>

  <main>
    <div class="container">
      <div class="row">

        <div class="col-3">
          <div class="left">

            <a class="menu-item" data-bs-toggle="collapse" href="#manageProduct" role="button" aria-expanded="false" aria-controls="manageProduct">
              <span><i class="uil uil-store"></i></span>
              <h3>Products</h3>
            </a>
            <div class="collapse menu-item-content" id="manageProduct">
              <div>
                <a data-bs-toggle="modal" data-bs-target="#saveProductModal" href="">Add Product</a>
                <a href="product-view">View All Products</a>
              </div>
            </div>

            <a class="menu-item" data-bs-toggle="collapse" href="#manageCategory" role="button" aria-expanded="false" aria-controls="manageCategory">
              <span><i class="uil uil-file-plus-alt"></i></span>
              <h3>Categories</h3>
            </a>
            <div class="collapse menu-item-content" id="manageCategory">
              <div>
                <a data-bs-toggle="modal" data-bs-target="#saveCategoryModal" href="">Add Category</a>
                <a href="category-view">View All Categories</a>
              </div>
            </div>

            <a class="menu-item" data-bs-toggle="collapse" href="#manageUser" role="button" aria-expanded="false" aria-controls="manageUser">
              <span><i class="uil uil-user"></i></span>
              <h3>Users</h3>
            </a>
            <div class="collapse menu-item-content" id="manageUser">
              <div>
                <a href="user-view">View All Users</a>
              </div>
            </div>

            <a class="menu-item" data-bs-toggle="collapse" href="#manageOder" role="button" aria-expanded="false" aria-controls="manageOder">
              <span><i class="uil uil-shop"></i></span>
              <h3>Orders</h3>
            </a>
            <div class="collapse menu-item-content" id="manageOder">
              <div>
                <a href="">View All Orders</a>
              </div>
            </div>

          </div>
        </div>

        <div class="col-9">
          <div class="right">

            <%
              String type = (String) request.getAttribute("list_type");
              if(type != null){
              if("Category".equals(type)){
            %>

            <table class="table table-striped">
              <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Category Name</th>
                <th scope="col">Action</th>
              </tr>
              </thead>
              <tbody>
                <%
                  List<Category> dataList = (List<Category>)request.getAttribute("list");
                  if(dataList != null){
                  for(Category category : dataList){
                %>
                <tr>
                  <td><%=category.getCategoryId()%></td>
                  <td><%=category.getCategoryName()%></td>
                  <td>
                    <a class="tbl-btn delete-btn" href="category-delete?id=<%=category.getCategoryId()%>">Delete</a>
                    <a class="tbl-btn update-btn" href="">Update</a>
                  </td>
                </tr>
                <%
                  }
                  }
                %>
              </tbody>
            </table>

            <%
              } else if ("Product".equals(type)) {
            %>

            <table class="table table-striped">
              <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
<%--                <th scope="col">Description</th>--%>
                <th scope="col">Price</th>
                <th scope="col">Qty</th>
                <th scope="col">Category</th>
                <th scope="col">Image</th>
                <th scope="col">Action</th>
              </tr>
              </thead>
              <tbody>
              <%
                List<Product> dataList = (List<Product>)request.getAttribute("list");
                if(dataList != null){
                for(Product product : dataList){
              %>
              <tr>
                <td><%=product.getProductId()%></td>
                <td><%=product.getProductName()%></td>
<%--                <td><%=product.getDescription()%></td>--%>
                <td><%=product.getPrice()%></td>
                <td><%=product.getQuantity()%></td>
                <td><%=product.getCategory().getCategoryName()%></td>
                <td><img src="<%=product.getImage()%>" alt="" style="width: 50px"></td>
                <td>
                  <a class="tbl-btn delete-btn" href="product-delete?id=<%=product.getProductId()%>">Delete</a>
                  <a class="tbl-btn update-btn" href="">Update</a>
                  <a class="tbl-btn disable-btn" href="flashSaleItemSave?id=<%=product.getProductId()%>">Sale</a>
                </td>
              </tr>
              <%
                }
                }
              %>
              </tbody>
            </table>

            <%
              } else if ("User".equals(type)) {
            %>

            <table class="table table-striped">
              <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">PhoneNumber</th>
                <th scope="col">Status</th>
              </tr>
              </thead>
              <tbody>
              <%
                List<User> dataList = (List<User>)request.getAttribute("list");
                if(dataList != null){
                  for(User user : dataList){
              %>
              <tr>
                <td><%=user.getUserId()%></td>
                <td><%=user.getName()%></td>
                <td><%=user.getEmail()%></td>
                <td><%=user.getPhoneNumber()%></td>
                <%
                  if("Active".equals(user.getStatus())){
                %>
                <td>
                  <a class="tbl-btn active-btn" href="user-deactivate?id=<%=user.getUserId()%>">Active</a>
                </td>
                <%
                  }else {
                %>
                <td>
                  <a class="tbl-btn disable-btn" href="user-active?id=<%=user.getUserId()%>">Deactivate</a>
                </td>
                <%
                  }
                %>
              </tr>
              <%
                }
                }
              %>
              </tbody>
            </table>

            <%
              }
              }
            %>

          </div>
        </div>

      </div>
    </div>
  </main>

  <!-- Modal Save product -->
  <div class="modal fade save-product" id="saveProductModal" tabindex="-1" aria-labelledby="saveProductModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header justify-content-between">
          <h1 class="modal-title fs-5 fw-medium" id="saveProductModalLabel">Save Product</h1>
          <span data-bs-dismiss="modal" aria-label="Close"><i class="uil uil-times-circle icon" ></i></span>
        </div>
        <div class="modal-body">
          <form action="product-save" method="post" enctype="multipart/form-data">

            <div class="form-group">
              <label for="name">Product Name:</label>
              <input type="text" id="name" name="name" required>
            </div>

            <div class="form-group">
              <label for="category">Category:</label>
              <select id="category" name="category" required>

                <%
                  List<Category> categoryList =  new ArrayList<>();
                  Session session1 = HibernateUtil.getSessionFactory().openSession();
                  categoryList = session1.createQuery("from Category", Category.class).list();
                  session1.close();
                  for(Category category : categoryList){
                %>
                <option><%=category.getCategoryName()%></option>
                <%
                  }
                %>
              </select>
            </div>

            <div class="form-group">
              <label for="qty">Quantity:</label>
              <input type="number" id="qty" name="qty" required>
            </div>

            <div class="form-group">
              <label for="price">Price:</label>
              <input type="number" id="price" name="price" step="0.01" required>
            </div>

            <div class="form-group">
              <label for="description">Description:</label>
              <textarea id="description" name="description" rows="4" required></textarea>
            </div>

            <div class="form-group">
              <label for="image">Product Image:</label>
              <input type="file" id="image" name="image" accept="image/*" required>
            </div>

            <div class="form-actions">
              <button type="submit">Submit</button>
            </div>

          </form>
        </div>
      </div>
    </div>
  </div>

  <!-- Modal Save Category -->
  <div class="modal fade" id="saveCategoryModal" tabindex="-1" aria-labelledby="saveCategoryModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header justify-content-between">
          <h1 class="modal-title fs-5 fw-medium" id="saveCategoryModalLabel">Save Category</h1>
          <span data-bs-dismiss="modal" aria-label="Close"><i class="uil uil-times-circle icon" ></i></span>
        </div>
        <div class="modal-body">

          <form action="category-save" method="post">
            <div class="form-group">
              <label for="category_name">Category Name:</label>
              <input type="text" id="category_name" name="name" required>
            </div>
            <div class="form-actions">
              <button type="submit">Submit</button>
            </div>
          </form>

        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  </body>
</html>
