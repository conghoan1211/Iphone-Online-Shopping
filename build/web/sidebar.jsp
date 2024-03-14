<%-- 
    Document   : sidebar
    Created on : Dec 27, 2023, 11:00:25 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container__heading">
    <div class="container__heading">
        <div class="header__manager">
            <div class="header__manager-left">
                <a href="/suga/home" class="header__manager-item">
                    <i class="fa-brands fa-apple"></i>
                    <span class="header__manager-item__name">Suga</span>
                </a>
                <span class="header__manager-title">Quản lý Admin</span>
            </div>

            <div class="header__manager-right">
                <div class="header__manager-user flex">
                    <img class="header__manager-user-img"
                         src="https://yt3.ggpht.com/yti/ADpuP3MEBtPK2w3PD74lGPnowgesuAR_VQYgWY4u0_NPcw=s88-c-k-c0x00ffffff-no-rj" alt="">
                    <c:if test="${(sessionScope.AccDetail.nickname == null) || (sessionScope.AccDetail.nickname == '')}">
                        <span class="header__manager-user-name"> ${sessionScope.acc.username}</span>
                    </c:if>
                    <c:if test="${sessionScope.AccDetail.nickname != null}">
                        <span class="header__manager-user-name">${sessionScope.AccDetail.nickname}</span>
                    </c:if>
                </div>
                <ul class="header__manager-user-menu">
                    <li class="header__manager-user-item">
                        <a href="/suga/home" style="padding-top: 10px;">Trang chủ</a>
                    </li>
                    <li class="header__manager-user-item">
                        <a href="/suga/profile">Tài khoản của tôi</a>
                    </li>
                    <li class="header__manager-user-item">
                        <a href="/suga/logout">Đăng xuất</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="sidebar">
    <ul class="sidebar-menu">
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/3fa3bdb20eb201ae3f157ee8d11a39d5" alt="Quản Lý Sản Phẩm">
            <a href="/suga/manager" class="sidebar-item__title" >Quản Lý Sản Phẩm</a>
        </li>
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/f82f8ccb649afcdf4f07f1dd9c41bcb0" alt="Quản Lý Đơn Hàng">
            <a href="/suga/manager/order?type=0" class="sidebar-item__title">Quản Lý Đơn Hàng</a>
        </li>
        <li class="sidebar-item">
            <i class="fa-regular fa-user sidebar-item__icon"></i>
            <a href="/suga/add.account" class="sidebar-item__title">Quản Lý Tài Khoản</a>
        </li>
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/09759afab8ae066ca5e1630bc19133a1" alt="Quản Lý Đơn Hàng">
            <a href="/suga/chart" class="sidebar-item__title">Dữ Liệu</a>
        </li>
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/f9e8756bcf783fe1783bf59577fdb263" alt="Quản Lý Đơn Hàng">
            <a href="" class="sidebar-item__title">Tài Chính</a>
        </li>
        <li class="sidebar-item">
            <img class="sidebar-item__icon" src="https://cf.shopee.vn/file/9f2ae273250a1a723d7d8892c9584c6d" alt="Quản Lý Đơn Hàng">
            <a href="" class="sidebar-item__title">Chăm Sóc Khách Hàng</a>
        </li>
    </ul>
</div>