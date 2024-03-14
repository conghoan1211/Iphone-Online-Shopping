<%-- 
    Document   : home
    Created on : Sep 18, 2023, 9:10:22 AM
    Author     : admin
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Suga</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- css -->
        <link rel="stylesheet" href="assests/css/base.css">
        <link rel="stylesheet" href="assests/css/main.css">
        <link rel="stylesheet" href="assests/css/filter.css"/>
        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="assests/img/cat-icon-title.ico">
        <!-- boostrap -->
        <link href="assests/boostrap/bootstrap.css" rel="stylesheet">

        <style>
            .home-filter__btn.btn:hover {
                color: var(--white-color);
                background-color: var(--primary-color);
            }
        </style>
    </head>

    <body>
        <div class="app">
            <!--app menu-->
            <jsp:include page="menu.jsp"></jsp:include> 

                <div class="container__menu">
                    <div class="header__menu">
                        <ul class="header__menu-list">
                            <li class="header__memu-item">
                                <img class="header__memu-item-img" src="assests/img/home-icon.svg" alt="">
                                <a style="text-decoration: none" href="home">HOME</a> 
                            </li>
                            <li class="header__memu-item">
                                <a style="text-decoration: none" href="hot.html">ASSESSORY</a>
                            </li>
                            <li class="header__memu-item">
                                <a style="text-decoration: none" href="#">BRAND</a>
                                <img class="header__menu-select-icon" src="./assests/img/caret-down-solid.svg" alt="">
                            </li>
                            <li class="header__memu-item">
                                <a style="text-decoration: none" href="#">SALE</a>
                            </li>
                            <li class="header__memu-item">
                                <a style="text-decoration: none" href="intro.jsp">INTRODUCE</a>
                            </li>
                            <li class="header__memu-item">
                                <a style="text-decoration: none" href="#">CONTACT</a>
                            </li>
                        </ul>
                    </div>
                </div> <!--end header-menu -->

                <!--container details-->
                <div class="app__container">
                    <div class="grid2">
                        <div class="grid__row app__content row">
                            <div class="grid__column-2 col-md-2 col-sm-2 col-lg-2 col-xs-2">
                                <nav class="category">
                                    <h3 class="categoty__heading">
                                        <i class="fa-solid fa-list categoty__heading-icon" style="color: #000000;"></i>
                                        Danh mục
                                    </h3>
                                    <ul class="category-list">
                                    <c:forEach items="${listC}" var="o">
                                        <li class="category-item text-overflow ${tag == o.cid ?"category-item--active ":""}" title="${o.cname}">
                                            <a href="category?cid=${o.cid}" class="category-item__link" style="text-decoration: none">${o.cname}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </nav>

                            <form action="category" class="category">
                                <h3 class="categoty__heading" style="font-size: 1.66rem;">
                                    <i class="fa-solid fa-filter categoty__heading-icon"></i>
                                    Bộ lọc tìm kiếm
                                </h3>
                                <div class="category-list filter" style="padding-left: 20px;">
                                    <span class="filter-title">Nơi Bán</span>
                                    <div class="filter-item">
                                        <input type="checkbox" name="origin" value="TP.Hồ Chí Minh" class="filter-input">
                                        <span class="filter-name">TP. Hồ Chí Minh</span>
                                    </div>
                                    <div class="filter-item">
                                        <input type="checkbox" name="origin" value="Hà Nội" class="filter-input">
                                        <span class="filter-name"> Hà Nội</span>
                                    </div>
                                    <div class="filter-item">
                                        <input type="checkbox" name="origin" value="Thái Bình" class="filter-input">
                                        <span class="filter-name"> Thái Bình</span>
                                    </div>
                                    <div class="filter-item">
                                        <input type="checkbox" name="origin" value="Hưng Yên" class="filter-input">
                                        <span class="filter-name"> Hưng Yên</span>
                                    </div>
                                    <div class="filter-item">
                                        <input type="checkbox" name="other" value="other" class="filter-input">
                                        <span class="filter-name">... Khác </span>
                                    </div>
                                </div>

                                <div class=" filter category-list ">
                                    <span class="filter-title">Theo Danh Mục</span>
                                    <div class="filter-item">
                                        <input type="checkbox" name="category" value="phone" class="filter-input">
                                        <span class="filter-name"> Điện thoại</span>
                                    </div>
                                    <div class="filter-item">
                                        <input type="checkbox" name="category" value="assessory" class="filter-input">
                                        <span class="filter-name"> Linh Kiện</span>
                                    </div>
                                </div> 
                                <div class="category-list filter" >
                                    <span class="filter-title"> Khoảng Giá</span>
                                    <div class="filter-item filter-box-price">
                                        <input class="filter-input-number" type="number" name="pricefrom" placeholder="₫ TỪ">
                                        <i class="fa-solid fa-minus" style="padding-right: 8px; color: #bdbdbd;"></i>
                                        <input class="filter-input-number" type="number" name="priceto"  placeholder="₫ ĐẾN">
                                    </div>
                                    <button type="submit" class=" filter__btn btn__save" style="height: 34px"> Áp Dụng</button>
                                </div>
                            </form>
                        </div>

                        <div class="grid__column-10 col-md-10 col-sm-10 col-lg-10 col-xs-10">

                            <!-- home filter sort -->
                            <div class="home-filter__sort">
                                <span class="home-filter__title">Sắp xếp theo</span>
                                <c:forEach items="${listSorted}" var="o">
                                    <a href="home?sortID=${o.sortID}" class="home-filter__btn btn">${o.sortName}</a>
                                </c:forEach> 

                                <div class="select-input">
                                    <span class="select-input__label">Giá</span>
                                    <i class="select-input__icon fas fa-angle-down"></i>
                                    <ul class="select-input__list">
                                        <li class="select-input__item">
                                            <a href="home?sortID=4" class="select-input__link" style="text-decoration: none">Giá: Tăng dần</a>
                                        </li>
                                        <li class="select-input__item">
                                            <a href="home?sortID=5" class="select-input__link" style="text-decoration: none">Giá: Giảm dần</a>
                                        </li>
                                    </ul>
                                </div>

                                <!--home filter pagination--> 
                                <div class="home-filter__paging">
                                    <c:if test="${tag > 1}">
                                        <a href="home?index=${tag-1}" class="home-filter__paging-btn">
                                            <i class="page-icon fas fa-angle-left"> </i>
                                            <span>Prev</span>
                                        </a>
                                    </c:if>
                                    <div class="select-input" style="width: 77px;">
                                        <span class="select-input__label">${tag}</span>
                                        <i class="select-input__icon fas fa-angle-down" style="font-size: 1.1rem;"></i>
                                        <ul class="select-input__list">
                                            <c:forEach begin="1" end="${endP}" var="i">
                                                <li class="select-input__item">
                                                    <a class="select-input__link ${tag == i? "link__active":""}"  href="home?index=${i}">Page ${i}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <c:if test="${tag < endP}">
                                        <a href="home?index=${tag+1}" class="home-filter__paging-btn" style="justify-content: right">
                                            <span>Next</span>
                                            <i class="page-icon fas fa-angle-right"></i>
                                        </a>
                                    </c:if>

                                </div>
                            </div>

                            <!-- products -->
                            <div class="home-product">
                                <div class="grid__row ">
                                    <c:forEach items="${listP}" var="o" >
                                        <div class="grid__column-2-4 col-md-2-4 col-sm-2-4 col-lg-2-4 col-xs-2-4">
                                            <a href="detail?pid=${o.id}" class="home-product-item " style="text-decoration: none;">
                                                <div class="home-product-item__img"
                                                     style="background-image: url(${o.image})">
                                                </div>
                                                <h4 class="home-product-item__name text__overflow">${o.title}</h4>

                                                <div class="home-product-item__price">
                                                    <span class="home-product-item__price-old text__overflow">${o.oldPrice}</span>
                                                    <span
                                                        class="home-product-item__price-current text__overflow">${o.currentPrice}</span>
                                                </div>
                                                <div class="home-product-item__action">
                                                    <span class="home-product-item__like">
                                                        <i class="fas fa-heart"></i>
                                                    </span>
                                                    <div class="home-product-item__rating">
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                    </div>
                                                    <span class="home-product-item__sold"> Đã bán ${o.amountOfSold}</span>
                                                </div>
                                                <div class="home-product-item_favorite">  
                                                    <i class="fas fa-check"></i>
                                                    <span>${o.status}</span>
                                                </div>
                                                <div class="home-product-item__origin">${o.origin} </div>
                                            </a>
                                        </div>  
                                    </c:forEach>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!--app footer-->                            
            <jsp:include page="footer.jsp"></jsp:include>
        </div>

        <script src="assests/js/Jquery.js"></script>
        <script src="assests/js/bootstrap.min.js"></script>
        <script src="assests/js/main.js"></script>
    </body>

</html>