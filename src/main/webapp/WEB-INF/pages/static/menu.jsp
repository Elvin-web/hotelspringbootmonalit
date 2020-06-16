<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/7/2019
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
    .svg-inline--fa {
        margin-right: 10px !important;
    }

    .fa-fw fa-tachometer-alt {
        color: #777777 !important;
    }
    .dropdown-menu {
        background-color: rgba(119, 119, 119, 0.75);
    }

</style>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <!-- Sidebar -->
        <ul class="sidebar navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="javascript:window.location.reload();">
                    <i class="fas fa-tachometer-alt"></i>
                    <span>Dashboard</span>
                </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="getReservationList" id="bookingId">
                    <i class="fas fa-list-alt"></i>
                    <span>Booking</span>
                </a>
            </li>

            <li class="nav-item active">
                <a class="nav-link" href="getGuestList" id="guestId1"><i class="fas fa-users"></i>
                    <span>Guest</span>
                </a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-cubes"></i>
                    <span>Hotel Configuration</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item" href="getRoomTypeList">Room Types</a>
                    <a class="dropdown-item" href="getRoomList">Rooms</a>
                    <a class="dropdown-item" href="getPriceList">Price Manager</a>
                    <a class="dropdown-item" href="getServicesList">Paid Services</a>
                    <a class="dropdown-item" href="getFloorList">Floors</a>
                    <a class="dropdown-item" href="getAmenitiesList" id="amenitiesBtnId">Amenities</a>
                    <a class="dropdown-item" href="getHousekeepingStatusList">Housekeeping Status</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-users"></i>
                    <span>HR Management</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item" href="getStaffList">Employees</a>
                    <a class="dropdown-item" href="getPositionList">Designations</a>
                </div>
            </li>
          <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown1" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-cogs"></i>
                    <span>Administrative</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item" href="getHotelList">Settings</a>
                    <a class="dropdown-item" href="getCountryList">Country</a>
                    <a class="dropdown-item" href="getCityList">City</a>
                    <a class="dropdown-item" href="getTaxList">Tax Manager</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown2" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-tags"></i>
                    <span>Expenses</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item" href="getExpensesList">Expenses</a>
                    <a class="dropdown-item" href="getExpensesTypeList">Expenses Category</a>
                </div>
            </li>
        </ul>

