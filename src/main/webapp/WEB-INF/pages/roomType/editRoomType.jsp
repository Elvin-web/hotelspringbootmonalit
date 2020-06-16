<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/17/2019
  Time: 4:25 PM
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
                                        <strong>Room type Form</strong> edit
                                    </div>
                                    <div class="card-body card-block">
                                        <form action="updateRoomType" method="post"
                                              enctype="multipart/form-data" class="form-horizontal">
                                            <input type="hidden" name="id" value="${roomType1.id_room_type}">
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-inputU"
                                                           class=" form-control-label"><strong>Title</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-inputU" value="${roomType1.room_type}"
                                                           name="name" placeholder="Title" class="form-control">
                                                    <small class="form-text text-muted">Please enter title</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input1U"
                                                           class=" form-control-label"><strong>Slug</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input1U" value="${roomType1.slug}"
                                                           name="slug" placeholder="Slug" class="form-control">
                                                    <small class="form-text text-muted">Please enter slug</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input2U"
                                                           class=" form-control-label"><strong>Short code</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input2U" value="${roomType1.short_code}"
                                                           name="shortCode" placeholder="Short code" class="form-control">
                                                    <small class="form-text text-muted">Please enter short code</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input3U"
                                                           class=" form-control-label"><strong>Base occupancy</strong></label>
                                                    <input type="number" style="width: 400px;" id="text-input3U" value="${roomType1.roomStructure.base_occupancy}"
                                                           name="baseOccupancy" placeholder="Base occupancy" class="form-control">
                                                    <small class="form-text text-muted">Please enter base occupancy</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input4U"
                                                           class=" form-control-label"><strong>Higher occupancy</strong></label>
                                                    <input type="number" style="width: 400px;" id="text-input4U" value="${roomType1.roomStructure.higher_occupancy}"
                                                           name="higherOccupancy" placeholder="Higher occupancy" class="form-control">
                                                    <small class="form-text text-muted">Please enter higher occupancy</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input5U"
                                                           class=" form-control-label"><strong>Kids occupancy</strong></label>
                                                    <input type="number" style="width: 400px;" id="text-input5U" value="${roomType1.roomStructure.kids_occupancy}"
                                                           name="kidsOccupancy" placeholder="Kids occupancy" class="form-control">
                                                    <small class="form-text text-muted">Please enter kids occupancy</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input6U"
                                                           class=" form-control-label"><strong>Base price</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input6U" value="${roomType1.price.base_price}"
                                                           name="basePice" placeholder="Base price" class="form-control">
                                                    <small class="form-text text-muted">Please enter base price</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input8U"
                                                           class=" form-control-label"><strong>Extra bed price</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input8U" value="${roomType1.price.extra_bed_price}"
                                                           name="extraBedPrice" placeholder="Extra bed price" class="form-control">
                                                    <small class="form-text text-muted">Please enter extra bed price</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="textarea-inputU"
                                                           class=" form-control-label"><strong>Description</strong></label>
                                                    <textarea name="description" style="width: 400px;" id="textarea-inputU" rows="9"
                                                              placeholder="Content..." class="form-control">${roomType1.description}</textarea>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="file-input"
                                                           class=" form-control-label"><strong>Image</strong></label>
                                                    <input type="file" style="width: 400px;" id="file-input"
                                                           name="fileName1" placeholder="Name" class="form-control-file"  value="${roomType1.image}">
                                                    <a class="item-gallery-sidebar wrap-pic-w" href="/hotelMonalit/${filePath2}" data-lightbox="gallery-footer">
                                                        <img src="/hotelMonalit/${filePath2}" alt="GALLERY">
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label class=" form-control-label"><strong>Extra bed</strong></label>
                                                    <div class="form-check">
                                                        <div class="checkbox">
                                                            <label for="checkbox1U" class="form-check-label ">
                                                                <input type="checkbox" id="checkbox1U"  name="extraBed" <c:if test="${roomType1.roomStructure.extra_bed==1}">checked </c:if> name="checkbox1" value="1" class="form-check-input">
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-primary btn-sm">
                                                Update
                                            </button>
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











