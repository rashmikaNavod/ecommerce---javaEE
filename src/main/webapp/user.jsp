<%@ page import="lk.ijse.demo.entity.SaleProduct" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rashmika
  Date: 1/26/2025
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/index&user.css">
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

        <div class="search-bar">
            <input type="search" placeholder="Search  easyBird">
            <i class="uil uil-search"></i>
        </div>

        <div class="d-flex align-items-center gap-1">
            <a id="id-user" class="nav-btn" href="" ></a>
            <a id="log-out" class="nav-btn"></a>
            <span  id="navigate-cart"><i style="color: white" class="uil uil-shopping-cart icon shop"></i></span>
        </div>

    </div>
</nav>

<main>

    <%--  offer banner--%>
    <div id="carouselExampleAutoplaying" class="carousel slide container" data-bs-ride="carousel" style="max-width: 930px">
        <div class="carousel-inner">
            <a href="" class="carousel-item active">
                <img src="assests/offer/active.jpeg" class="d-block w-100" alt="...">
            </a>
            <a href="" class="carousel-item">
                <img src="assests/offer/1.jpeg" class="d-block w-100" alt="...">
            </a>
            <a href="" class="carousel-item">
                <img src="assests/offer/2.jpeg" class="d-block w-100" alt="...">
            </a>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <%--  flash sale--%>
    <div class="flash-sale container">
        <h2>Flash Sale</h2>
        <div id="slide-container" class="slider-container">

        </div>
    </div>

<%--        just for you--%>
    <div class="just-for-you container" >
        <h2>Just For You</h2>
        <div class="just-for-you-slider">

        </div>
    </div>

    <a href="admin_panel.jsp" target="_blank">LogIn Admin Panel</a>

</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="js/script.js"></script>
</body>
</html>
