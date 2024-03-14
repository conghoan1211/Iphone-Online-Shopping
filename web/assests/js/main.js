/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function formatPrice(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

document.addEventListener("DOMContentLoaded", function () {
    const priceElements = document.querySelectorAll(".table__product-price, .home-product-item__price-current, .home-product-item__price-old,\n\
                        .header__describe-price__old, .header__describe-price__current, .cart-product-total,\n\
                        .cart-product-price__old, .cart-product-price__current, ._naoh3, .header__cart-item-price,\n\
                        .checkouttotal, .checkout-total-money, .chart-box__price");
    priceElements.forEach(function (element) {
        const price = parseInt(element.textContent.replace(".", ""));
        element.textContent = "₫" + formatPrice(price);
    });
});

function formatAmountSold(amountSold) {
    if (amountSold >= 1000) {
        const formattedAmount = (amountSold / 1000).toLocaleString('en-US', {minimumFractionDigits: 1, maximumFractionDigits: 1}).replace(".", ",");
        return formattedAmount + "k";
    } else {
        return amountSold.toString();
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const amountSoldElements = document.querySelectorAll(".home-product-item__sold, .amount__sold");
    amountSoldElements.forEach(function (element) {
        const amountSold = parseInt(element.textContent.replace("Đã bán ", ""));
        element.textContent = "Đã bán " + formatAmountSold(amountSold);
    });
});

function formatAmountSold(amountSold) {
    return amountSold.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

document.addEventListener("DOMContentLoaded", function () {
    const amountSoldElements = document.querySelectorAll(".chart-box__order, .format-money");
    amountSoldElements.forEach(function (element) {
        const amountSold = parseInt(element.textContent.replace("Đã bán ", ""));
        element.textContent = formatAmountSold(amountSold);
    });
});
            