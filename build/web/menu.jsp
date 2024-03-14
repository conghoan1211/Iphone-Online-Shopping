<%-- 
    Document   : menu.jsp
    Created on : Sep 23, 2023, 9:03:01 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Giới thiệu</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
<!-- css -->
<link href="assests/css/main.css" rel="stylesheet" type="text/css"/>

<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

<!-- icon title -->
<link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">
<!-- boostrap -->
<link href="./assests/boostrap/bootstrap.css" rel="stylesheet">

<header class="header">
    <div class="grid">
        <nav class="header__navbar">
            <ul class="header__navbar-list">
                <li class="header__navbar-item">
                    <span class="header__navbar-title--no-pointer" style="text-decoration: none;">Kết nối</span>
                    <a href="https://www.facebook.com/profile.php?id=100034311945968" class="header__navbar-icon-link">
                        <i style="color: var(--white-color)" class="fab fa-facebook"></i>
                    </a>
                    <a href="https://instagram.com/_hoan.1102?igshid=MzMyNGUyNmU2YQ%3D%3D&utm_source=qr&fbclid=IwAR2WFKiqNalqEKpH3CT0DzU7zVCIQjV-8f0Getm54CpSyLKbMrBYdTVNMBQ" class="header__navbar-icon-link">
                        <i style="color: var(--white-color)" class="fab fa-instagram" aria-hidden="false"></i>&nbsp
                    </a>
                </li>
                <span class="header__navbar-item-left">
                    Hotline:
                    <a class="header__navbar-support" style="text-decoration: none" href="tel:19004869" title="1900 4869">1900 4869</a>
                </span>
                <span class="header__navbar-item-left">
                    Email:
                    <a class="header__navbar-support" style="text-decoration: none" href="mailto:hoanpche170404@fpt.edu.vn">support@suga.vn</a>
                </span>
            </ul>

            <ul class="header__navbar-list">
                <li class="header__navbar-item">
                    <a href="manager.html" class="header__navbar-item-link" style="text-decoration: none;"><i class="fa-regular fa-bell"></i> &nbspThông báo</a>
                </li>
                <li class="header__navbar-item">
                    <a href="#" class="header__navbar-item-link" style="text-decoration: none;"><i class="fa-regular fa-circle-question"></i> &nbspHỗ trợ </a>
                </li>

                <c:if test="${sessionScope.acc == null}" >
                    <li class="header__navbar-item header__navbar-item--strong header__navbar-item--separate">
                        <a href="register" class="header__navbar-item-link" style="text-decoration: none;">Đăng ký</a>
                    </li>
                    <li class="header__navbar-item header__navbar-item--strong">
                        <a href="login" class="header__navbar-item-link" style="text-decoration: none;">Đăng nhập</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc != null}" >
                    <li class="header__navbar-item header__navbar-user"> 
                        <img id="ngoaiFormImage2" class="header__navbar-user-img" 
                             src="https://yt3.ggpht.com/yti/ADpuP3MEBtPK2w3PD74lGPnowgesuAR_VQYgWY4u0_NPcw=s88-c-k-c0x00ffffff-no-rj" alt="">

                        <c:if test="${(AccDetail.nickname == null) || (AccDetail.nickname == '')}">
                            <span style="padding-left: 6px">${sessionScope.acc.username}</span>
                        </c:if>
                        <c:if test="${AccDetail.nickname != null}">
                            <span style="padding-left: 6px">${AccDetail.nickname}</span>
                        </c:if>
                        <ul class="header__navbar-user-menu" >
                            <li class="header__navbar-user-item">
                                <a href="profile" style="text-decoration: none;">Tài khoản của tôi</a>
                            </li>
                            <c:if test="${sessionScope.acc.isAdmin == 1}" >
                                <li class="header__navbar-user-item">
                                    <a href="manager" style="text-decoration: none;">Admin</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.acc.isAdmin != 1}" >
                                <li class="header__navbar-user-item">
                                    <a href="order?type=0" style="text-decoration: none;">Đơn mua</a>
                                </li>
                            </c:if>
                            <li class="header__navbar-user-item">
                                <a href="logout" style="text-decoration: none;">Đăng xuất</a>
                            </li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </nav>

        <!-- header with search  -->
        <div class="header-with-search">
            <a href="home" class="header__logo">
                <i class="fa-brands fa-apple header__logo-img"></i>
            </a>
            <a href="home" class="header__logo-name" style="color: var(--white-color)" >Suga </a>

            <form action="search" method="post" class="header__search">
                <input type="text" value="${textS}" name="text" class="header__search-input" placeholder="Tìm tên sản phẩm hoặc thương hiệu" title="search">

                <div class="header__search-select">
                    <span class="header__search-select-label">Lựa chọn&nbsp</span>
                    <img class="header__search-select-img" src="./assests/img/caret-down-solid.svg" alt="">

                    <ul class="header__search-option">
                        <li class="header__search-option-item ">
                            <a href="" style="color: var(--text-color); text-decoration: none;">Trong shop</a>
                        </li>
                        <li class="header__search-option-item">
                            <a href="" style="color: var(--text-color);text-decoration: none">Ngoài shop</a>
                        </li>
                    </ul>
                </div>
                <button type="submit" class="header__search-button">
                    <i class="fa-solid fa-magnifying-glass" style="color: white"></i>
                </button>
            </form>

            <!--header cart-->
            <div  class="header__cart">
                <a href="showcart" style="padding: 6px 6px 6px 0" ><i class="header__cart-icon fa-solid fa-cart-shopping"></i></a>
                <span class="header__cart-notice">${amount} </span> 
                <!-- cart is empty -->
                <div class="header__cart-list ">
                    <c:if test="${amount == 0}" >
                        <img src="assests/img/no-cart.png" alt="" class="header__cart-list-none-img">
                        <span class="header__cart-list-none-msg">Chưa có sản phẩm</span> 
                    </c:if>
                    <c:if test="${amount != 0}" >
                        <h4 class="header__cart-heading">Sẳn phẩm mới thêm</h4>
                        <ul class="header__cart-list-item">
                            <c:forEach items="${cart.items}" var="i">
                                <li class="header__cart-item">
                                    <img class="header__cart-img" src="${i.product.image}" alt="">
                                    <a href="detail?pid=${i.product.id}" class="header__cart-item-info" style="text-decoration: none;">
                                        <div class="header__cart-item-head">
                                            <h5 class="header__cart-item-name">${i.product.title}</h5>
                                            <span class="header__cart-item-price">${i.product.currentPrice}</span>
                                        </div>
                                        <div class="header__cart-item-body">
                                            <span class="header__cart-item-address">Nơi bán: ${i.product.origin} </span>
                                            <span class="header__cart-item-remove"> x${i.quantity} </span>
                                        </div>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>

                        <div class="header__cart-footer">
                            <p>${amount} thêm hàng vào giỏ</p>
                            <a href="showcart" class="btn header__cart-btn"> Xem giỏ hàng </a>
                        </div>
                    </c:if>
                </div>
            </div>

        </div>
    </div>
</header>

