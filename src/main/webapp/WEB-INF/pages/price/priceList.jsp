<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 1/12/2020
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Main CSS-->
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


            <!-- DataTables Example -->

            <div class="card mb-3" style="border: none">
              <!--  <div class="card-header">Price &nbsp;<small>List</small>
                    <div class="btn-group fa-pull-right">
                        <a href="newPrice">
                            <button class="btn btn-success"><strong>+</strong> Add</button>
                        </a>
                    </div>
                </div>-->
                <div class="card-body">
                    <div style="margin-bottom: 20px;">Price</div>
                    <div class="table-responsive">
                        <table class="table table-borderless" id="dataTable" style="width: 100%;" cellspacing="0">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Mon</th>
                                <th>Tue</th>
                                <th>Wed</th>
                                <th>Thu</th>
                                <th>Fri</th>
                                <th>Sat</th>
                                <th>Sun</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${roomTypeList}" var="pl">
                                <tr role="row" class="odd">
                                    <td>${pl.id_room_type}</td>
                                    <td>${pl.room_type}</td>
                                    <td>${pl.price.base_price}</td>
                                    <td>${pl.price.base_price}</td>
                                    <td>${pl.price.base_price}</td>
                                    <td>${pl.price.base_price}</td>
                                    <td>${pl.price.base_price}</td>
                                    <td>${pl.price.base_price}</td>
                                    <td>${pl.price.base_price}</td>
                                    <td>
                                      <!--  <div class="btn-group" style="float: right">
                                            <a class="btn btn-primary"
                                               href="prs?action=editPrice&id=${pl.id_room_type}">
                                                <i class="fas fa-pencil-alt"></i>Edit
                                            </a>
                                            <a class="btn btn-danger"
                                               href="javascript: deletePrice('${pl.id_room_type}','${pl.id_room_type}');">
                                                <i class="fas fa-trash"></i>Delete
                                            </a>
                                        </div>-->
                                    </td>
                                </tr>
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

<script src="./resources/js/price.js"></script>

</body>

</html>
