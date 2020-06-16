<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/26/2019
  Time: 2:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <script src="../../resources/newLayout/js/all.js"></script>
    <script src="../../resources/newLayout/js/all.min.js"></script>
    <link href="../../resources/newLayout/css/all.css" rel="stylesheet" media="all">
    <link href="../../resources/newLayout/css/all.min.css" rel="stylesheet" media="all">
    <link href="../../resources/newLayout/css/jquery-ui.css" rel="stylesheet" media="all">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Hotel</title>

    <!-- Custom fonts for this template-->
    <link href="../../resources/newLayout/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="../../resources/newLayout/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link href="../../resources/newLayout/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../../resources/newLayout/vendor/fontawesome-free/css/sb-admin.css" rel="stylesheet">
    <link href="../../resources/newLayout/vendor/fontawesome-free/css/sb-admin.min.css" rel="stylesheet">
    <link href="../../resources/newLayout/vendor/fontawesome-free/css/_cards.scss" rel="stylesheet">
    <link href="../../resources/newLayout/vendor/fontawesome-free/css/_footer.scss" rel="stylesheet">
    <link href="../../resources/newLayout/vendor/fontawesome-free/css/_global.scss" rel="stylesheet">
    <link href="../../resources/newLayout/vendor/fontawesome-free/css/_login.scss" rel="stylesheet">
    <link href="../../resources/newLayout/vendor/fontawesome-free/css/_mixins.scss" rel="stylesheet">
    <link href="../../resources/newLayout/vendor/fontawesome-free/css/_navbar.scss" rel="stylesheet">
    <link href="../../resources/newLayout/vendor/fontawesome-free/css/_utilities.scss" rel="stylesheet">
    <link href="../../resources/newLayout/vendor/fontawesome-free/css/_variables.scss" rel="stylesheet">
    <link href="../../resources/newLayout/vendor/fontawesome-free/css/sb-admin.scss" rel="stylesheet">
</head>

<body id="page-top" style="background-color: rgba(0,0,0,.03);">

<jsp:include page="../static/header.jsp"></jsp:include>

<div id="wrapper">

    <jsp:include page="../static/menu.jsp"></jsp:include>

    <div id="content-wrapper">

        <div class="container-fluid">


            <!-- DataTables Example -->

            <!-- MAIN CONTENT-->
            <div class="main-content" id="main">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card" style="border: none">
                                    <div class="card-header">
                                        <strong>Invoice</strong> <br>
                                    </div>
                                    <div class="card-body card-block">
                                        <form action="updatePayment" method="post"
                                              enctype="multipart/form-data"
                                              class="form-horizontal">
                                            <table style="width:100%; border:0">
                                                <tbody>
                                                <tr>
                                                    <td>
                                                        <table style="width:100%; border-bottom:1px solid #CCCCCC; padding-bottom:20px;">
                                                            <tbody>
                                                            <tr>
                                                                <td align="right">
                                                                    <b>Invoice number : ${payment.id_payment}</b><br>
                                                                    <b>Added date:</b>${payment.added_date}<br>
                                                                    <b>Payment method:</b>${payment.payType.pay_type}
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table style="width:100%; border-bottom:1px solid #CCCCCC; padding-bottom:30px;">
                                                            <tbody>
                                                            <tr>
                                                                <td align="left" width="45%">
                                                                    <b>Payment to</b><br>
                                                                    <address>${payment.reservation.hotel.name}<br>
                                                                        ${payment.reservation.hotel.address}<br>
                                                                        ${payment.reservation.hotel.phone}<br>
                                                                        ${payment.reservation.hotel.email}
                                                                    </address>
                                                                </td>
                                                                <td width="10%"></td>
                                                                <td align="right" width="45%" colspan="1">
                                                                    <b>Bill to</b><br>
                                                                    <address>${payment.reservation.guest.name} ${payment.reservation.guest.surname}<br>
                                                                        ${payment.reservation.guest.phone}<br>
                                                                        ${payment.reservation.guest.email}
                                                                    </address>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left" style="padding-top: 10px;">
                                                                    Invoice Entries
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <table style="width:200%; border:1px solid #CCCCCC;">
                                                                        <tbody>
                                                                        <tr>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:10%; align:left">
                                                                                <b>#</b>
                                                                            </td>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:75%; align:left">
                                                                                <b>Detail</b>
                                                                            </td>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:15%; align:left">
                                                                                <b>Amount</b>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:10%; align:left">
                                                                                <b>${payment.id_payment}</b>
                                                                            </td>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:75%; align:left">
                                                                                Payment
                                                                            </td>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:15%; align:left">
                                                                                ${payment.amount}
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:10%; align:left">
                                                                            </td>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:75%; align:left">
                                                                                Total Amount
                                                                            </td>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:15%; align:left">
                                                                                <b>${payment.amount}</b>
                                                                            </td>
                                                                        </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </td>
                                                            </tr>

                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table style="width:100%; border-bottom:1px solid #CCCCCC; padding-bottom:20px;">
                                                            <tbody>
                                                            <tr>
                                                                <a href="/hotelMonalit/getReservationList">
                                                                    <button type="button" class="btn btn-primary btn-sm"
                                                                            id="paymentSubmitIdU" onclick="window.print()">
                                                                        <i class="fa  fa-print"></i> Print
                                                                    </button>
                                                                </a>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </form>
                                    </div>
                                    <div class="card-footer" align="right">
                                        <a href="/hotelMonalit/getReservationList">
                                            <button type="button" class="btn btn-secondary">
                                                 Close
                                            </button>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- END MAIN CONTENT-->
        </div>
        <!-- /.container-fluid -->


    </div>
    <!-- /.content-wrapper -->

</div>
<!-- /#wrapper -->
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
                <a class="btn btn-primary" href="./newLayout/vendor/html/login.html">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- Jquery JS-->

<script src="../../resources/newLayout/js/jquery-3.2.1.min.js"></script>
<script src="../../resources/newLayout/js/jquery-ui.js"></script>


<!-- Bootstrap core JavaScript-->
<script src="../../resources/newLayout/vendor/jquery/jquery.js"></script>
<script src="../../resources/newLayout/vendor/jquery/jquery.min.js"></script>
<script src="../../resources/newLayout/vendor/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../../resources/newLayout/vendor/jquery/jquery.easing.min.js"></script>
<script src="../../resources/newLayout/vendor/jquery/jquery.easing.compatibility.js"></script>
<script src="../../resources/newLayout/vendor/jquery/jquery.easing.js"></script>

<!-- Page level plugin JavaScript-->
<script src="../../resources/newLayout/vendor/datatables/jquery.dataTables.js"></script>
<script src="../../resources/newLayout/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="../../resources/newLayout/vendor/datatables/dataTables.bootstrap4.js"></script>
<script src="../../resources/newLayout/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Custom scripts for all pages-->

<script src="../../resources/newLayout/vendor/js/sb-admin.js"></script>
<!-- Demo scripts for this page-->
<script src="../../resources/newLayout/vendor/js/demo/datatables-demo.js"></script>

<script src="../../resources/js/payment.js"></script>
</body>

</html>
<!-- end document-->
