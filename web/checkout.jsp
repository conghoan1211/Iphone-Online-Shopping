<%-- 
    Document   : checkout
    Created on : Oct 21, 2023, 1:38:22 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh Toán</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <!-- boostrap -->
    <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">
    <!-- css -->
    <link rel="stylesheet" href="./assests/css/base.css">
    <link rel="stylesheet" href="./assests/css.cart/cart.css">
    <link rel="stylesheet" href="./assests/css.cart/checkout.css">
    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

    <!-- icon title -->
    <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">
</head>

<body>
    <div class="checkout">
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
                            <a href="manager.html" class="header__navbar-item-link" style="text-decoration: none;"><i
                                    class="fa-regular fa-bell"></i> &nbspThông
                                báo</a>
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
                    <a href="showcheckout" style="color: var(--primary-color)" class="cart__header-title">
                        Thanh Toán
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

        <!-- container checkout -->
        <div class="container-checkout grid1">
            <!-- checkout form address -->
            <div class="box-checkout checkout-form" style="width: 32.334%;">
                <div class="checkout-form-title">
                    <i class="fa-solid fa-location-dot"></i>&nbsp;
                    Địa Chỉ Nhận Hàng
                </div>
                <form action="showcheckout" method="post" class="checkout-input-group">
                    <div class="checkout-input-field" style="margin-top: 0;">
                        <input required class="input-box" type="text" name="logName" value="${AccDetail.nickname}">
                        <label for="logName">Họ và tên: </label>
                    </div>
                    <div class="checkout-input-field">
                        <input required class="input-box" value="${AccDetail.email}" type="text" placeholder="...@gmail.com" name="logEmail">
                        <label for="logEmail">Email:</laDebel>
                    </div>
                    <div class="checkout-input-field">
                        <input required class="input-box" value="${AccDetail.phone}" type="number" name="logPhone">
                        <label for="logPhone">Số điện thoại: </label>
                    </div>
                    <div class="checkout-input-field">
                        <input required class="input-box" value="${AccAddr.address}" type="text" name="logAddress">
                        <label for="logAddress">Địa chỉ: </label>
                    </div>
                    <div class="checkout-input-field">
                        <input required class="input-box" value="${AccAddr.address_detail}" type="text" name="logDetailAddr">
                        <label for="logDetailAddr">Địa chỉ cụ thể: </label>
                    </div>
                    <div class="checkout-input-field">
                        <textarea required class="input-box textarea-note" type="text" name="logNote"></textarea>
                        <label for="logNote">Ghi chú: </label>
                    </div>
                    <div class="checkout-flex ">
                        <span></span>
                        <button type="submit" class="btn__save btn__checkout">Cập Nhật</button>
                    </div>
                </form>
            </div>

                <!-- checkout delivery  -->
                <form action="showcheckout" method="get" id="checkoutForm"  class="box-checkout checkout-delivery" style="width: 33.334%">
                    <div>
                        <div class="checkout-form-title" style="display: flex; justify-content: space-between" >
                            <span>
                                <i class="fa-solid fa-truck"></i>
                                Vận chuyển 
                            </span>
                          
                        </div>
                        <div class="checkout-delivery-box">
                            <input required checked type="radio" name="delivery" onchange="submitForm() class="checkout-input__radio">
                            <div class="checkout-flex">
                                <span class="checkout-delivery-name">Giao hàng tận nơi</span>
                                <span class="checkout-delivery-price">₫40.000</span>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="checkout-form-title" style="padding-top: 8px;">
                            <i class="fa-solid fa-money-check-dollar"></i>
                            Phương thức thanh toán
                        </div>
                        <div class="checkout-delivery-">
                            <div class="checkout-delivery-box">
                                <input required id="input1" type="radio" ${type == 0 ? 'checked': ''} onchange="submitForm()" class="checkout-input__radio" name="type_checkout" value="0">
                                <span for="input1" class="checkout-delivery-name">Thanh toán khi nhận hàng</span>
                            </div>
                            <div class="checkout-delivery-box">
                                <input type="radio" id="input2"  ${type == 1 ? 'checked': ''} onchange="submitForm()" class="checkout-input__radio" name="type_checkout" value="1">
                                <div class="checkout-flex">
                                    <span for="input2" class="checkout-delivery-name">Thanh toán qua ví SugaPay</span>
                                    <span class="checkout-delivery-price">Giảm thêm ₫15k</span>
                                </div>
                            </div>
                        </div>
                        <div class="checkout-message">
                            <span>${ms}</span>
                        </div>
                    </div>
                </form>

                <!-- checkout product list-->
                <form action="checkout" method="post"  class="box-checkout checkout-product" style="padding-right: 0; width: 34.334%;">
                    <span class="checkout-form-title">
                        <i class="fa-brands fa-product-hunt"></i>
                        Đơn hàng (${amount} sản phẩm)
                    </span>

                    <div class="checkout-product-list">
                        <!-- checkout product item -->
                        <c:forEach items="${cart.items}" var="i">
                            <div class="checkout-product-item">
                                <img style="height: 50px;"
                                     src="${i.product.image}" alt="">
                                <div class="checkout-flex" style="padding-top: 3px">
                                    <span class="checkout-product-title text__overflow">
                                        ${i.product.title}
                                    </span>
                                    <div>
                                        <span class="checkout-delivery-price checkouttotal">${i.product.currentPrice}</span>
                                        <span class="checkout-product-amount">${i.color.color_name}&nbsp&nbsp&nbsp  x${i.quantity}</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach> 
                    </div>

                    <!-- checkout prouduct voucher -->
                    <div class="checkout-product-voucher">
                        <div class="checkout-input-field">
                            <input  checked type="text" name="voucher" class="input-box" style="width: 250px;">
                            <label for="logDetailAddr">Nhập mã voucher </label>
                        </div>
                        <div>
                            <button class="btn__save btn__checkout">Áp dụng</button>
                        </div>
                    </div>

                    <div class="checkout-product-total">
                        <div class="checkout-flex checkout-money">
                            <span>Tổng tiền hàng</span>
                            <span class="checkouttotal">${(cart.totalMoney)}</span>
                        </div>
                        <div class="checkout-flex checkout-money">
                            <span>Phí vận chuyển</span>
                            <span>₫40.000</span>
                        </div>
                        <div class="checkout-flex checkout-money1">
                            <span>Tổng thanh toán</span>
                            <span class="checkout-total-money">${checkMoney}</span>
                        </div>
                    </div>
                    <div class="checkout-flex checkout-money">
                        <a href="showcart">
                            <i class="fa-solid fa-chevron-left"></i>
                            <span>Quay về giỏ hàng</span>
                        </a>
                        <button type="submit" onclick="checkAndSubmit(event)" class="btn__save btn__order">Đặt hàng</button>
                    </div>
                </form>
        </div>
    </div>
</body>
<script src="assests/js/main.js"></script>
<script>
      function submitForm() {
        document.getElementById('checkoutForm').submit();
    }
    function checkAndSubmit(event) {
        var input1 = document.querySelector('#input1');
        var input2 = document.querySelector('#input2');
        
        if (input1.checked || input2.checked) {
            document.getElementById('checkoutForm').submit();
        } else {
            event.preventDefault();
            alert("Vui lòng chọn phương thức thanh toán.");
        }
    }
</script>

</html>