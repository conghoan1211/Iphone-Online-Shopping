<%-- 
    Document   : carte
    Created on : Oct 20, 2023, 11:51:12 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Giỏ Hàng</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- boostrap -->
        <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css.cart/cart.css">
        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">

    </head>

    <body>
        <div class="cart">
            <header class="header__cart">
                <div class="grid">
                    <nav class="header__navbar">
                        <ul class="header__navbar-list">
                            <li class="header__navbar-item">
                                <span class="header__navbar-title--no-pointer">Kết nối </span>
                                <a href="" class="header__navbar-icon-link">
                                    <i class="fab fa-facebook" style="color: var(--white-color);"></i>
                                </a>

                                <a href="" class="header__navbar-icon-link">
                                    <i class="fab fa-instagram" style="color: var(--white-color);"
                                       aria-hidden="false"></i>&nbsp
                                </a>
                            </li>

                            <li class="header__navbar-item">
                                Hotline:
                                <a class="header__navbar-support" style="text-decoration: none;" href="tel:19004869"
                                   title="1900 4869">1900 4869</a>
                            </li>
                            <li class="header__navbar-item">
                                Email:
                                <a class="header__navbar-support" style="text-decoration: none;"
                                   href="mailto:hoanpche170404@fpt.edu.vn">support@suga.vn</a>
                            </li>
                        </ul>

                        <ul class="header__navbar-list">
                            <li class="header__navbar-item">
                                <a href="manager" class="header__navbar-item-link" style="text-decoration: none;"><i
                                        class="fa-regular fa-bell"></i> &nbspThông báo</a>
                            </li>

                            <li class="header__navbar-item">
                                <a href="" class="header__navbar-item-link" style="text-decoration: none;"><i
                                        class="fa-regular fa-circle-question"></i> &nbspHỗ trợ </a>
                            </li>

                            <!-- <li class="header__navbar-item header__navbar-item--strong header__navbar-item--separate">
                            <a href="" class="header__navbar-item-link" style="text-decoration: none;">Đăng ký</a>
                        </li>
                        <li class="header__navbar-item header__navbar-item--strong">
                            <a href="" class="header__navbar-item-link" style="text-decoration: none;">Đăng nhập</a>
                        </li> -->

                            <li class="header__navbar-item header__navbar-user">
                                <c:if test="${sessionScope.acc == null}" >
                                <li class="header__navbar-item header__navbar-item--strong header__navbar-item--separate">
                                    <a href="register.jsp" class="header__navbar-item-link" style="text-decoration: none;">Đăng ký</a>
                                </li>
                                <li class="header__navbar-item header__navbar-item--strong">
                                    <a href="login.jsp" class="header__navbar-item-link" style="text-decoration: none;">Đăng nhập</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.acc != null}" >
                                <li class="header__navbar-item header__navbar-user"> 
                                    <img class="header__navbar-user-img" 
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
                                                <a href="manager" style="text-decoration: none;">Quản lý sản phẩm</a>
                                            </li>
                                            <li class="header__navbar-user-item">
                                                <a href="add.account" style="text-decoration: none;">Quản lý tài khoản</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${sessionScope.acc.isAdmin != 1}" >
                                            <li class="header__navbar-user-item">
                                                <a href="" style="text-decoration: none;">Đơn mua</a>
                                            </li>
                                        </c:if>
                                        <li class="header__navbar-user-item">
                                            <a href="logout" style="text-decoration: none;">Đăng xuất</a>
                                        </li>
                                    </ul>
                                </li>
                            </c:if>
                            </li>
                        </ul>
                    </nav>
                </div>
            </header>

            <!-- cart with search -->
            <div class="cart-with-search">
                <div class="grid cart-search">
                    <div class="cart__header-logo">
                        <a href="home" class="header-logo">
                            <i class="fa-brands fa-apple"></i>
                            <div class="header-logo__name">Suga </div>
                        </a>
                        <a href="showcart" style="color: var(--primary-color)" class="cart__header-title">
                            Giỏ Hàng
                        </a>
                    </div>
                    <form action="search" method="post" class="cart__header-search">
                        <input class="cart__header-search-input" type="text" name="text"
                               placeholder="NGÀY 12/11 SIÊU SALE ĐẾN 50%">
                        <button type="submit" class="cart__header-search-btn">
                            <i class="fa-solid fa-magnifying-glass" style="color: white;"></i>
                        </button>
                    </form>
                </div>
            </div>

            <!-- cart container -->
            <div class="container" style="padding: 0;">
                <!-- main cart -->
                <div class="cart-box">
                    <div class="cart-menu-list">
                        <div class="cart-menu-item__product">Sản Phẩm</div>
                        <div class="cart-menu-item__price">Đơn Giá</div>
                        <div class="cart-menu-item__amount">Số Lượng</div>
                        <div class="cart-menu-item__total">Số Tiền</div>
                        <div class="cart-menu-item__action">Thao Tác</div>
                    </div>
                </div>

                <!-- cart product -->
                <c:set var="counter" value="1" />
                <c:forEach items="${cart.items}" var="i">
                    <div class="cart-box cart-product">
                        <section class="cart-product-item">
                            <div class="cart-product-order">${counter}</div>
                            <div class="cart-product-infor">
                                <div class="cart-product-infor__img">
                                    <img src="${i.product.image}"
                                         alt="">
                                </div>
                                <div class="cart-product-infor__right" style="padding: 5px 20px 0 10px ;">
                                    <div class="cart-product-infor__describe text__overflow">${i.product.title}</div>
                                    <img class="cart-product-infor__freeship"
                                         src="https://down-vn.img.susercontent.com/file/vn-50009109-abef578ba9ba32dc8c078d16f9f00943"
                                         alt="">
                                </div>
                                <div class="cart-product-cate">
                                    <span> Phân Loại Hàng: </span>
                                    <div>${i.product.cate.cname}, ${i.color.color_name}</div>
                                </div>
                            </div>
                            <div class="cart-product-price">
                                <div class="cart-product-price__old">${i.product.oldPrice}</div>
                                <div class="cart-product-price__current">${i.product.currentPrice}</div>
                            </div>
                            <div class="cart-product-amount">
                                <button type="button" class="cart-product-amount__btn">
                                    <a href="process?number=-1&pid=${i.product.id}" style="padding: 4px"><i class="fa-solid fa-minus"></i></a>
                                </button>
                                <input type="text" class="cart-product-amount__num" value="${i.quantity}" />
                                <button type="button" class="cart-product-amount__btn">
                                    <a href="process?number=1&pid=${i.product.id}"style="padding: 4px"><i class="fa-solid fa-plus"></i></a>
                                </button>
                            </div>
                            <div class="cart-product-total">${(i.product.currentPrice*i.quantity)}</div>
                            <form action="process" method="post" class="cart-product-action">
                                <input type="hidden" name="id" value="${i.product.id}" />
                                <!--<input type="hidden" name="colorid" value="${i.color.color_id}" />-->

                                <input class="cart-product-action-del" type="submit" value="Xóa" />
                            </form>
                        </section>
                    </div>
                    <c:set var="counter" value="${counter + 1}" />
                </c:forEach>
            </div>

            <!-- cart check out -->
            <div class="cart-box cart-checkout">
                <div class="_hoan1">
                    <div class="_caoh1">
                        <span></span>
                        <input style="padding-right: 20px;" type="checkbox" name="">
                    </div>
                    <div class="_caoh2">
                        <span class="_caoh2-xu">
                            <img style="height: 24px; padding-right: 6px;"
                                 src="https://down-vn.img.susercontent.com/file/a0ef4bd8e16e481b4253bd0eb563f784" alt="">
                            Suga Xu
                        </span>
                        <span class="_caoh2-use_xu">Dùng 200 Suga Xu</span>
                        <span class="_caoh2-numb_xu">-₫200</span>
                    </div>
                </div>
                <div class="_hoan2">
                    <span class="_naoh1">Tổng thanh toán (1 Sản Phẩm): </span>
                    <div class="_naoh2">
                        <span class="_naoh3">${cart.totalMoney}</span>
                        <a href="showcheckout" class="btn__save btn-checkout" >Mua Hàng</a>
                    </div>

                </div>
            </div>
        </div>
    </body>
    <script src="assests/js/main.js"></script>

</html>
