<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/5/2019
  Time: 12:29 PM
  To change this template use File | Settings | File Templates.
--%>

<style>
    .au-btn--green {
        background-color: rgba(241, 125, 151, 0.09) !important;
    }

    .au-btn--blue {
        background-color: #e2eda4 !important;
    }
</style>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <script src="./resources/newLayout/js/all.js"></script>
    <script src="./resources/newLayout/js/all.min.js"></script>
    <link href="./resources/newLayout/css/all.css" rel="stylesheet" media="all">
    <link href="./resources/newLayout/css/all.min.css" rel="stylesheet" media="all">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Hotel</title>

    <!-- Custom fonts for this template-->
    <link href="./resources/newLayout/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="./resources/newLayout/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link href="./resources/newLayout/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="./resources/newLayout/vendor/fontawesome-free/css/sb-admin.css" rel="stylesheet">
    <link href="./resources/newLayout/vendor/fontawesome-free/css/sb-admin.min.css" rel="stylesheet">
    <link href="./resources/newLayout/vendor/fontawesome-free/css/_cards.scss" rel="stylesheet">
    <link href="./resources/newLayout/vendor/fontawesome-free/css/_footer.scss" rel="stylesheet">
    <link href="./resources/newLayout/vendor/fontawesome-free/css/_global.scss" rel="stylesheet">
    <link href="./resources/newLayout/vendor/fontawesome-free/css/_login.scss" rel="stylesheet">
    <link href="./resources/newLayout/vendor/fontawesome-free/css/_mixins.scss" rel="stylesheet">
    <link href="./resources/newLayout/vendor/fontawesome-free/css/_navbar.scss" rel="stylesheet">
    <link href="./resources/newLayout/vendor/fontawesome-free/css/_utilities.scss" rel="stylesheet">
    <link href="./resources/newLayout/vendor/fontawesome-free/css/_variables.scss" rel="stylesheet">
    <link href="./resources/newLayout/vendor/fontawesome-free/css/sb-admin.scss" rel="stylesheet">
</head>

<body id="page-top" style="background-color: rgba(0,0,0,.03);">

<jsp:include page="../static/header.jsp"></jsp:include>

<div id="wrapper">

    <jsp:include page="../static/menu.jsp"></jsp:include>

    <div id="content-wrapper">

        <div class="container-fluid">


            <div class="card mb-3" style="border: none">
                <div class="card-header">Reservation &nbsp;<small>List</small>
                    <div class="btn-group fa-pull-right">
                        <a href="newReservation">
                            <button class="btn btn-success"><strong>+</strong> Add</button>
                        </a>
                    </div>
                </div>
                <div class="card-body">
                    <div style="margin-bottom: 20px;">Reservation</div>
                    <form method="post">
                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-2">
                                    <select name="advRoomTypeCmbId"
                                            id="advRoomTypeCmbId"
                                            class="form-control">
                                        <option value="0">--Filter By Room Type--</option>
                                        <c:forEach items="${roomTypeList}" var="rtl">
                                            <option value="${rtl.id_room_type}">${rtl.room_type}</option>
                                        </c:forEach>
                                    </select>
                                </div >
                                <div class="col-md-2" >
                                    <input type="text" id="advBeginCheckInDateId"
                                           name="advBeginCheckInDateId" placeholder="Check In"
                                           class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <input type="text"  id="advBeginCheckOutDateId"
                                           name="advBeginCheckOutDateId" placeholder="Check Out"
                                           class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <select name="advPaymentStatusCmbId"
                                            id="advPaymentStatusCmbId"
                                            class="form-control">
                                        <option value="0">--Filter By Payment
                                            Status--
                                        </option>
                                        <c:forEach items="${paymentStatusList}" var="psl">
                                            <option value="${psl.id_payment_status}">${psl.payment_status}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <select name="advBookingStatusCmbId"
                                            id="advBookingStatusCmbId"
                                            class="form-control">
                                        <option value="0">--Filter By Booking
                                            Status--
                                        </option>
                                        <c:forEach items="${bookingStatusList}" var="bsl">
                                            <option value="${bsl.id_booking_status}">${bsl.booking_status}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-1">
                                    <select name="advGuestCmbId"
                                            id="advGuestCmbId"
                                            class="form-control">
                                        <option value="0">Select Guest</option>
                                        <c:forEach items="${guestList}" var="gl">
                                            <option value="${gl.id_guest}">${gl.name}${gl.surname}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-1">
                                    <button type="button" class="btn au-btn--blue"
                                            id="advSearchReservationBtnId"> Get
                                    </button>
                                </div>

                            </div>
                        </div>
                    </form>
                    <div class="table-responsive">
                        <table class="table table-borderless" id="reservationTableId" style="width: 100%;"
                               cellspacing="0">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Guest</th>
                                <th>Room</th>
                                <th>Check in</th>
                                <th>Check out</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${reservationList}" var="rl">
                                <c:if test="${rl.active eq 1}">
                                <tr role="row" class="odd">
                                    <td>${rl.id_reservation}</td>
                                    <td>${rl.guest.name} ${rl.guest.surname}</td>
                                    <td>${rl.roomType.room_type}</td>
                                    <td>${rl.check_in}</td>
                                    <td>${rl.check_out}</td>
                                    <td>
                                        <div class="btn-group" style="float: right">
                                            <a class="btn btn-primary"
                                               href="editReservation/${rl.id_reservation}">
                                                <i class="fas fa-pencil-alt"></i>Edit
                                            </a>
                                            <a class="btn btn-danger"
                                               href="javascript: deleteReservation('${rl.id_reservation}','${rl.id_reservation}');">
                                                <i class="fas fa-trash"></i>Delete
                                            </a>
                                            <!--  <a class="btn au-btn--blue"
                                               href="rs?action=editReservationRoom&id=${rl.id_reservation}">
                                                <i class="fas fa-home"></i>Room
                                            </a>
                                            <a class="btn au-btn--green"
                                               href="gs?action=paymentGuest&id=${rl.id_reservation}">
                                                <i class="fas fa-credit-card"></i>Payment
                                            </a>-->
                                            <a class="btn btn-secondary"
                                               href="moreReservation/${rl.id_reservation}">
                                                <i class="fas fa-eye"></i>View
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- /.container-fluid -->



    </div>
    <!-- /.content-wrapper -->

</div>
<!-- /#wrapper -->

<footer class="footer " style="background-color: #418bd6 ">
    <jsp:include page="../static/footer.jsp"></jsp:include>
</footer>
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="ls?action=login">Logout</a>
            </div>
        </div>
    </div>
</div>


<!-- Jquery JS-->

<script src="./resources/newLayout/js/jquery-3.2.1.min.js"></script>
<script src="./resources/newLayout/js/jquery-ui.js"></script>


<!-- Bootstrap core JavaScript-->
<script src="./resources/newLayout/vendor/jquery/jquery.js"></script>
<script src="./resources/newLayout/vendor/jquery/jquery.min.js"></script>
<script src="./resources/newLayout/vendor/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="./resources/newLayout/vendor/jquery/jquery.easing.min.js"></script>
<script src="./resources/newLayout/vendor/jquery/jquery.easing.compatibility.js"></script>
<script src="./resources/newLayout/vendor/jquery/jquery.easing.js"></script>

<!-- Page level plugin JavaScript-->
<script src="./resources/newLayout/vendor/datatables/jquery.dataTables.js"></script>
<script src="./resources/newLayout/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/newLayout/vendor/datatables/dataTables.bootstrap4.js"></script>
<script src="./resources/newLayout/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Custom scripts for all pages-->

<script src="./resources/newLayout/vendor/js/sb-admin.js"></script>
<!-- Demo scripts for this page-->
<script src="./resources/newLayout/vendor/js/demo/datatables-demo.js"></script>

<script src="./resources/js/reservation.js"></script>

<script>
    $(function () {
        $('#reservationTableId').DataTable();

        $('#advBeginCheckInDateId').datepicker({
            changeMonth: true,
            changeYear: true
        });
        $('#advBeginCheckOutDateId').datepicker({
            changeMonth: true,
            changeYear: true
        });
        $('#advEndCheckInDateId').datepicker({
            changeMonth: true,
            changeYear: true
        });
        $('#advEndCheckOutDateId').datepicker({
            changeMonth: true,
            changeYear: true
        });

    });

</script>
</body>

</html>


