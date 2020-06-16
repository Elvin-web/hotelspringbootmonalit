<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/8/2019
  Time: 11:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--var timepickers=$('.timepicker').wickedpicker();
console.log(timepickers.wickedpicker('time',1));-->

<!DOCTYPE html>
<html lang="en">

<head>
    <script src="./resources/newLayout/js/all.js"></script>
    <script src="./resources/newLayout/js/all.min.js"></script>
    <link href="./resources/newLayout/css/all.css" rel="stylesheet" media="all">
    <link href="./resources/newLayout/css/all.min.css" rel="stylesheet" media="all">
    <link href="./resources/newLayout/css/jquery-ui.css" rel="stylesheet" media="all">


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

            <!-- MAIN CONTENT-->
            <div class="main-content" id="main">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card" style="border: none">
                                    <div class="card-header">
                                        <strong>Employee Form</strong> new
                                    </div>
                                    <div class="card-body card-block">
                                        <form action="addStaff" method="post"
                                              enctype="multipart/form-data" class="form-horizontal">
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input"
                                                           class=" form-control-label"><strong>Name</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input"
                                                           name="name" placeholder="Name" class="form-control">
                                                    <small class="form-text text-muted">Please enter name</small>
                                                </div>
                                            </div>

                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input1"
                                                           class=" form-control-label"><strong>Surname</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input1"
                                                           name="surname" placeholder="Surname" class="form-control">
                                                    <small class="form-text text-muted">Please enter surname</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input3"
                                                           class=" form-control-label"><strong>Address</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input3"
                                                           name="address" placeholder="Address"
                                                           class="form-control">
                                                    <small class="form-text text-muted">Please enter address</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input4"
                                                           class=" form-control-label"><strong>Birthday</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input4"
                                                           name="dob" placeholder="Birthday"
                                                           class="form-control">
                                                    <small class="form-text text-muted">Please enter birthday</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input11"
                                                           class=" form-control-label"><strong>ID</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input11"
                                                           name="ID1" placeholder="ID"
                                                           class="form-control">
                                                    <small class="form-text text-muted">Please enter ID</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input5"
                                                           class=" form-control-label"><strong>Gender</strong></label> &nbsp;
                                                    <div class="form-check-inline form-check">
                                                        <input type="radio" id="male" name="gender" value="male">
                                                        <label for="male">Male</label><br>
                                                        &nbsp;
                                                        <input type="radio" id="female" name="gender" value="female">
                                                        <label for="female">Female</label><br>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input5"
                                                           class=" form-control-label"><strong>Phone</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input5"
                                                           name="phone" placeholder="Phone"
                                                           class="form-control">
                                                    <small class="form-text text-muted">Please enter phone</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input2"
                                                           class=" form-control-label"><strong>Email</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input2"
                                                           name="email" placeholder="Email"
                                                           class="form-control">
                                                    <small class="form-text text-muted">Please enter email</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input9"
                                                           class=" form-control-label"><strong>Username</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input9"
                                                           name="username" placeholder="Username"
                                                           class="form-control">
                                                    <small class="form-text text-muted">Please enter username</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input10"
                                                           class=" form-control-label"><strong>Password</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input10"
                                                           name="password" placeholder="Password"
                                                           class="form-control">
                                                    <small class="form-text text-muted">Please enter password</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input6"
                                                           class=" form-control-label"><strong>Job
                                                        start</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input6"
                                                           name="jobStart" placeholder="Job start"
                                                           class="form-control">
                                                    <small class="form-text text-muted">Please enter job
                                                        start</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input7"
                                                           class=" form-control-label"><strong>Job
                                                        end</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input7"
                                                           name="jobEnd" placeholder="Job end"
                                                           class="form-control">
                                                    <small class="form-text text-muted">Please enter job end</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input8"
                                                           class=" form-control-label"><strong>Salary</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input8"
                                                           name="salary" placeholder="Salary"
                                                           class="form-control">
                                                    <small class="form-text text-muted">Please enter salary</small>
                                                </div>
                                            </div>

                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="select"
                                                           class=" form-control-label"><strong>Position</strong></label>
                                                    <select name="position" style="width: 400px;" id="select"
                                                            class="form-control">
                                                        <option value="0">Please select Position</option>
                                                        <c:forEach items="${positionList}" var="pl">
                                                            <option value="${pl.id_position}">${pl.position_value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="file-input"
                                                           class=" form-control-label"><strong>Image</strong></label>
                                                    <input type="file" style="width: 400px;" id="file-input"
                                                           name="fileName1" class="form-control">
                                                    <small class="form-text text-muted">Please enter image</small>
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-primary btn-sm"> Save
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
<script src="./resources/newLayout/vendor/jquery/jquery.js"></script>
<script src="./resources/newLayout/vendor/js/bootstrap.bundle.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="./resources/newLayout/vendor/js/sb-admin.min.js"></script>
<script src="./resources/newLayout/vendor/js/sb-admin.js"></script>
<!-- Jquery JS-->
<script src="./resources/newLayout/js/jquery-3.2.1.min.js"></script>
<script src="./resources/newLayout/js/jquery-ui.js"></script>
<script src="./resources/js/staff.js"></script>
<script type="text/javascript">
    $(function () {
        $('#text-input4').datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
</script>

</body>

</html>



