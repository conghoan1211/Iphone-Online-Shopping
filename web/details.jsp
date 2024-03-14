<%-- 
    Document   : details
    Created on : Sep 23, 2023, 3:01:11 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"> 

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Suga</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <!-- css -->
        <link rel="stylesheet" href="./assests/css/base.css">
        <link rel="stylesheet" href="./assests/css/main.css">
        <link rel="stylesheet" href="./assests/css/details.css">
        <link href="assests/css/feedback.css" rel="stylesheet" type="text/css"/>
        <!-- font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

        <!-- icon title -->
        <link rel="icon" type="image/x-icon" href="./assests/img/cat-icon-title.ico">
        <!-- boostrap -->
        <link href="./assests/boostrap/bootstrap.css" rel="stylesheet">

        <style>
            .home-filter__btn:hover {
                color: azure;
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
                <div class="container__details">
                    <div class="grid">
                        <div class="app__details ">
                            <div class="app__details-header row">
                                <div class="app__details-header-img col-md-5 col-sm-5 col-lg-5 col-xs-5">
                                    <div class="app__details-header-img__first ">
                                        <img id="large-image" src="" alt="">
                                        <div class="home-product-item__status">
                                            <!-- <i class="fas fa-check"></i> -->
                                            <span class="status__freeship">FREESHIP</span>
                                            <span class="status__xtra">X-TRA</span>
                                        </div>
                                    </div>
                                    <div class="flex">
                                        <div class="detail-picture">
                                            <div class="detail-picture__contain">
                                                <img class="detail-picture__img"
                                                     src="${detail.image}"
                                                alt="">
                                        </div>
                                    </div>
                                    <div class="detail-picture">
                                        <div class="detail-picture__contain">
                                            <img class="detail-picture__img"
                                                 src="${pd.detail_img1}"
                                                 alt="">
                                        </div>
                                    </div>
                                    <div class="detail-picture">
                                        <div class="detail-picture__contain">
                                            <img class="detail-picture__img"
                                                 src="${pd.detail_img2}"
                                                 alt="">
                                        </div>
                                    </div>
                                    <div class="detail-picture">
                                        <div class="detail-picture__contain">
                                            <img class="detail-picture__img"
                                                 src="${pd.detail_img3}"
                                                 alt="">
                                        </div>
                                    </div>
                                    <div class="detail-picture">
                                        <div class="detail-picture__contain">
                                            <img class="detail-picture__img"
                                                 src="https://down-vn.img.susercontent.com/file/7bb6d3ae9b86f3d7a7b1ca0e48ec311a_tn"
                                                 alt="">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="app__details-header-describe col-md-7 col-sm-7 col-lg-7 ">
                                <form name="form" action="detail" method="post" class="header__describe">
                                    <input type="hidden" name="pid" value="${detail.id}" />
                                    <div class="header__describe-title">
                                        ${detail.title}
                                    </div>
                                    <div class="header__describe-brand flex">
                                        <div class="header__describe-brand__name flex">
                                            <span class="describe__name">
                                                Thương hiệu:
                                            </span>
                                            <span class="describe__data">
                                                Apple
                                            </span>
                                        </div>
                                        <div class="header__describe-brand__address flex">
                                            <span class="describe__name">
                                                Nơi bán:
                                            </span>
                                            <span class="describe__data">
                                                ${detail.origin}
                                            </span>
                                        </div>
                                        <div class="header__describe-brand__sold flex "
                                             style="border-right: 1px solid var(--white-color);">
                                            <span class="describe__name">
                                                Đã bán:
                                            </span>
                                            <span class="describe__data amount__sold">
                                                ${detail.amountOfSold}
                                            </span>
                                        </div>
                                    </div>
                                    <div class="header__describe-price flex">
                                        <div class="header__describe-price__old">
                                            ${detail.oldPrice}đ
                                        </div>
                                        <div class="header__describe-price__current">
                                            ${detail.currentPrice}đ
                                        </div>
                                    </div>
                                    <div class="header__describe-about flex">
                                        <div class="header__describe-about__title">Mô tả sản phẩm </div>
                                        <div class="header__describe-about__detail">
                                            ${detail.describe}
                                        </div>
                                    </div>

                                    <div class="header__describe-amount flex">
                                        <span class="header__describe-about__title">Màu sắc </span>
                                        <div class="header__describe-about__color">
                                            <c:forEach items="${listColor}" var="color" >
                                                <input required type="button" style="margin-left: 15px" class="color-button" data-color="${color.color_id}" value="${color.color_name}"/>
                                            </c:forEach>
                                        </div>
                                        <input type="hidden" name="selectedColor" id="selectedColor" value="">
                                    </div>

                                    <div class="header__describe-amount flex">
                                        <span class="header__describe-about__title">Số lượng</span>
                                        <input class="describe__choose" name="number" id="number" type="number" min="0" required >
                                        <span class="header__describe-amount__number">${detail.quantity_in_stock} sản phẩm có sẵn</span>
                                    </div>
                                    <div class="header__describe-option">
                                        <button type="button" onclick="addToCart('${detail.id}')"  class="btn__option btn__option-cart"><i class="fa-solid fa-cart-plus"></i>&nbsp; Thêm vào giỏ hàng</button>
                                        <button type="button" onclick="buyNow('${detail.id}')"  class="btn__option btn__option-buy" > Mua ngay</button>
                                    </div>
                                </form>
                            </div>
                        </div

                    </div>
                    <div style="padding-top: 20px;">
                        <p class="detail-product-title">CÁC SẢN PHẨM LIÊN QUAN</p>
                    </div>
                    <div class=" app__details-related">
                        <div class="detail__product-list">
                            <c:forEach items="${listRelated}" var="c">
                                <div class="grid__column-2-6">
                                    <a href="detail?pid=${c.id}" class="detail__product-item">
                                        <div class="home-product-item__img"
                                             style="background-image: url(${c.image})"> </div>
                                        <h4 class="detail-product-item__name text__overflow">${c.title}</h4>
                                        <div class="detail-product-item__coupons">
                                            <div class="detail-product-item__coupons-price">Giảm ₫10k</div>
                                            <div class="detail-product-item__coupons-sale">Flash Sale</div>
                                        </div>
                                        <div class="detail-product-item__price">
                                            <span class="home-product-item__price-current text__overflow">${c.currentPrice}</span>
                                            <span class="home-product-item__sold"> Đã bán ${c.amountOfSold}</span>
                                        </div>
                                        <div class="home-product-item_favorite">
                                            <i class="fas fa-check"></i>
                                            <span>${c.status}</span>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

            <!--feedback user-->
            <div class="app__feedback">
                <div class="grid feedback">
                    <div class="feedback-title">ĐÁNH GIÁ SẢN PHẨM</div>
                    <div class="feedback-overiew flex">
                        <div class="feedback-rating">
                            <span class="feedback-rating__average">4.9</span>
                            <span class="feedback-rating__score">trên 5</span>
                            <div class="feedback-rating__star">
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                            </div>
                        </div>
                        <div class="feedback-filter flex">
                            <div class="feedback-filter__btn">
                                <a href="detail?pid=${detail.id}&rate=0" class="btn__light feedback-filter__btn-submit  ${tag == 0? "feedback-filter__btn-submit--active":""}">Tất Cả</a>
                            </div>
                            <div class="feedback-filter__btn">
                                <a href="detail?pid=${detail.id}&rate=5" class="btn__light feedback-filter__btn-submit  ${tag == 0? "feedback-filter__btn-submit--active":""}">5 Sao (3,3k)</a>
                            </div>
                            <div class="feedback-filter__btn">
                                <a href="detail?pid=${detail.id}&rate=4" class="btn__light feedback-filter__btn-submit  ${tag == 0? "feedback-filter__btn-submit--active":""}">4 Sao (377)</a>
                            </div>
                            <div class="feedback-filter__btn">
                                <a href="detail?pid=${detail.id}&rate=3" class="btn__light feedback-filter__btn-submit"  ${tag == 0? "feedback-filter__btn-submit--active":""}>3 Sao (118)</a>
                            </div>
                            <div class="feedback-filter__btn">
                                <a href="detail?pid=${detail.id}&rate=2" class="btn__light feedback-filter__btn-submit"  ${tag == 0? "feedback-filter__btn-submit--active":""}>2 Sao (46)</a>
                            </div>
                            <div class="feedback-filter__btn">
                                <a href="detail?pid=${detail.id}&rate=1" class="btn__light feedback-filter__btn-submit"  ${tag == 0? "feedback-filter__btn-submit--active":""}>1 Sao (89)</a>
                            </div>
                            <div class="feedback-filter__btn">
                                <a href="detail?pid=${detail.id}&rate=-1" class="btn__light feedback-filter__btn-submit"  ${tag == 0? "feedback-filter__btn-submit--active":""}>Có Hình Ảnh / Video(1,2k)</a>
                            </div>
                        </div>
                    </div>

                    <c:forEach items="${feedB}" var="f" >
                        <div class="feedback-main">
                            <div class="suga-avatar feedback-avt">
                                <c:if test="${f.display == 1}" >
                                    <img class="suga-avatar__img"
                                         src="https://yt3.ggpht.com/yti/ADpuP3MEBtPK2w3PD74lGPnowgesuAR_VQYgWY4u0_NPcw=s88-c-k-c0x00ffffff-no-rj"
                                         alt="">
                                </c:if>
                                <c:if test="${f.display != 1}" >
                                    <img class="suga-avatar__img" 
                                         src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/1200px-User-avatar.svg.png" 
                                         alt="" >
                                </c:if>
                            </div>
                            <div class="feedback-content">
                                <div class="feedback-content__author">
                                    <c:if test="${f.display == 1}" >
                                        <span class="feedback-content__author-name">${f.username}</span>
                                    </c:if>
                                    <c:if test="${f.display == 0}" >
                                        <span class="feedback-content__author-name">anonymous user</span>
                                    </c:if>
                                    <div class="feedback-content__author-rating">
                                        <c:forEach begin="1" end="${f.rate}" >
                                            <i class="fas fa-star"></i>
                                        </c:forEach>  
                                        <c:forEach begin="1" end="${5 - f.rate}" >
                                            <i style="color: #d6d6d6" class="fas fa-star"></i>
                                        </c:forEach>  
                                    </div>
                                    <div class="feedback-content__author-time">
                                        <span>${f.feedDate} | Phân loại hàng: ${f.cate}-${f.colorname} </span>
                                    </div>
                                </div>
                                <div class="feedback-content__author-comment">
                                    <span class="feedback-content-label" style="color: rgba(0, 0, 0, 0.4);"> 
                                        Đúng với mô tả: &nbsp;
                                        <span style="color: var(--text-color);"> ${f.describe}</span>
                                    </span>
                                    <span class="feedback-content-label" style="padding: 4px 0; color: rgba(0, 0, 0, 0.4)">
                                        Tính năng nổi bật: &nbsp; 
                                        <span style="color: var(--text-color);"> ${f.feeture}</span>
                                    </span>
                                    <span class="feedback-content-label" style="color: rgba(0, 0, 0, 0.4); "> 
                                        Chất lượng sản phẩm: &nbsp;
                                        <span style="color: var(--text-color);">${f.standard}</span> 
                                    </span>
                                </div>
                                <div class="feedback-content__author-main"> ${f.content}</div>
                                <div class="flex">
                                    <div class="feedback-content-pic">
                                        <div class="feedback-content__image"
                                             style="background-image: url(https://down-bs-vn.img.susercontent.com/vn-11134103-7r98o-ln787jvdjpi0a6_tn.webp);">
                                        </div>
                                    </div>
                                    <div class="feedback-content-pic">
                                        <div class="feedback-content__image"
                                             style="background-image: url(https://down-bs-vn.img.susercontent.com/vn-11134103-7r98o-ln787jvdjpi0a6_tn.webp);">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                   
                </div>
            </div>
                            
        </div>
        <!--app footer-->
        <jsp:include page="footer.jsp"></jsp:include>
    </div>

    <script src="assests/js/Jquery.js"></script>
    <script src="assests/js/bootstrap.min.js"></script>
    <script src="assests/js/main.js"></script>
    <script type="text/javascript">
                                            function addToCart(id) {
                                                var number = document.getElementById("number").value;
                                                var selectedColor = document.getElementById("selectedColor").value;

                                                if (number > 0 && selectedColor !== "") {
                                                    var form = document.form;
                                                    form.action = "detail";
                                                    form.method = "post";

                                                    var input = document.createElement("input");
                                                    input.type = "hidden";
                                                    input.name = "pid";
                                                    input.value = id;
                                                    form.appendChild(input);

                                                    input = document.createElement("input");
                                                    input.type = "hidden";
                                                    input.name = "quantity";
                                                    input.value = number;
                                                    form.appendChild(input);

                                                    form.submit();
                                                } else {
                                                    if (number <= 0) {
                                                        alert("Vui lòng nhập số lượng hợp lệ.");
                                                    } else if (selectedColor === "") {
                                                        alert("Vui lòng chọn ít nhất một màu sắc.");
                                                    }
                                                }
                                            }

                                            function buyNow(id) {
                                                var number = document.form.number.value; // Sử dụng document.formName.inputName để lấy giá trị
                                                window.location.href = "checkout?pid=" + id + "&quantity=" + number;
                                            }

                                            $(document).ready(function () {
                                                $(".color-button").click(function () {
                                                    // Loại bỏ hiệu ứng chọn màu cho tất cả các nút
                                                    $(".color-button").removeClass("selected");

                                                    // Thêm hiệu ứng chọn màu cho nút được click
                                                    $(this).addClass("selected");

                                                    // Lấy giá trị màu từ data-color của nút và cập nhật input ẩn
                                                    var selectedColor = $(this).data("color");
                                                    $("#selectedColor").val(selectedColor);
                                                });
                                            }
                                            );

                                            const smallImages = document.querySelectorAll('.detail-picture__img');
                                            const largeImage = document.getElementById('large-image');
                                            const largeImageContainer = document.querySelector('.large-image-container');


                                            smallImages.forEach((smallImage, index) => {
                                                smallImage.addEventListener('mouseover', () => {
                                                    largeImage.src = smallImage.src;
                                                    largeImageContainer.style.display = 'block';
                                                });

                                                smallImage.addEventListener('mouseout', () => {
                                                    largeImageContainer.style.display = 'none';
                                                });
                                            });


                                            // Thiết lập ảnh mặc định
                                            largeImage.src = smallImages[0].src;

    </script>
</body>
</html>