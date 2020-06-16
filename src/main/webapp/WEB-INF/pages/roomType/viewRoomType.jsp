<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/28/2019
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <script src="../resources/newLayout/js/all.js"></script>
    <script src="../resources/newLayout/js/all.min.js"></script>
    <link href="../resources/newLayout/css/all.css" rel="stylesheet" media="all">
    <link href="../resources/newLayout/css/all.min.css" rel="stylesheet" media="all">
    <link href="../resources/newLayout/css/jquery-ui.css" rel="stylesheet" media="all">


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Hotel</title>
    <!-- Custom fonts for this template-->
    <link href="../resources/newLayout/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="../resources/newLayout/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link href="../resources/newLayout/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../resources/newLayout/vendor/fontawesome-free/css/sb-admin.css" rel="stylesheet">
    <link href="../resources/newLayout/vendor/fontawesome-free/css/sb-admin.min.css" rel="stylesheet">
    <link href="../resources/newLayout/vendor/fontawesome-free/css/_cards.scss" rel="stylesheet">
    <link href="../resources/newLayout/vendor/fontawesome-free/css/_footer.scss" rel="stylesheet">
    <link href="../resources/newLayout/vendor/fontawesome-free/css/_global.scss" rel="stylesheet">
    <link href="../resources/newLayout/vendor/fontawesome-free/css/_login.scss" rel="stylesheet">
    <link href="../resources/newLayout/vendor/fontawesome-free/css/_mixins.scss" rel="stylesheet">
    <link href="../resources/newLayout/vendor/fontawesome-free/css/_navbar.scss" rel="stylesheet">
    <link href="../resources/newLayout/vendor/fontawesome-free/css/_utilities.scss" rel="stylesheet">
    <link href="../resources/newLayout/vendor/fontawesome-free/css/_variables.scss" rel="stylesheet">
    <link href="../resources/newLayout/vendor/fontawesome-free/css/sb-admin.scss" rel="stylesheet">
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
                                        <strong>View Room type</strong>
                                    </div>
                                    <div class="card-body card-block">
                                        <form action="getRoomTypeList" method="post"
                                              enctype="multipart/form-data" class="form-horizontal">
                                            <div class="row">
                                                <div class="table-responsive col-md-6">
                                                    <table class="table" style="width: 100%; border-block:none" >
                                                        <tbody>
                                                        <tr>
                                                            <td style="border: none">
                                                                Title  : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${roomType1.room_type}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Slug   :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${roomType1.slug}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Short code   : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${roomType1.short_code}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Description   :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${roomType1.description}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Base Occupancy  : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${roomType1.roomStructure.base_occupancy}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Higher Occupancy  : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${roomType1.roomStructure.higher_occupancy}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Extra Bed  : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${roomType1.roomStructure.extra_bed}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Kids Occupancy  : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${roomType1.roomStructure.kids_occupancy}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Base Price  : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${roomType1.price.base_price}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Extra Bed Price  : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${roomType1.price.extra_bed_price}
                                                            </td>
                                                        </tr>


                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="table-responsive col-md-6">
                                                    <table class="table" style="width: 100%; border-block:none" >
                                                        <tbody>
                                                        <tr>
                                                            <td style="border: none"> <a class="item-gallery-sidebar wrap-pic-w" href="/hotelMonalit/${filePath2}"
                                                                                         data-lightbox="gallery-footer" >
                                                                <img src="/hotelMonalit/${filePath2}" alt="GALLERY" style="width: 90%;height: 110%;border-radius: 8px">
                                                            </a>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>


                                            <a href="getRoomTypeList">
                                                <button type="button" class="btn btn-primary btn-sm">
                                                    Close
                                                </button>
                                            </a>
                                        </form>
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
<!-- Bootstrap core JavaScript-->
<script src="../resources/newLayout/vendor/jquery/jquery.js"></script>
<script src="../resources/newLayout/vendor/js/bootstrap.bundle.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="../resources/newLayout/vendor/js/sb-admin.min.js"></script>
<script src="../resources/newLayout/vendor/js/sb-admin.js"></script>
<!-- Jquery JS-->
<script src="../resources/newLayout/js/jquery-3.2.1.min.js"></script>
<script src="../resources/newLayout/js/jquery-ui.js"></script>
<script src="../resources/js/roomType.js"></script>
</body>

</html>
<!-- end document-->




