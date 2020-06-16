<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/31/2019
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
    <script type="text/javascript">
        window.onload = function () {

            var dps = [[]];
            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                exportEnabled: true,
                theme: /*"light1",  "light1",*/ "light2",/* "dark1","dark2"*/
                title: {
                    text: "Revenue:" +
                        new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getUTCFullYear() + '/' + (new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getMonth() + 1) + '/' + new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getDate()
                        + "-" + new Date().getUTCFullYear() + "/" + (new Date().getUTCMonth() + 1) + "/" + new Date().getUTCDate()
                },
                axisX: {
                    //valueFormatString: "YYYY/MM/DD"
                   // valueFormatString: "####",

                     labelFormatter: function (e) {
                        // return CanvasJS.formatDate( e.value, "DD MMM");
                         return CanvasJS.formatDate( e.value, "YYYY/MM/DD");
                     }
                },

               /* axisY: {
                    valueFormatString: "#,##0.##,."
                },*/
              /*  axisX:{
                    interval: 1,
                    intervalType: "day",
                    viewportMinimum: new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7).getUTCFullYear(),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getMonth() + 1 ,new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getDate()),
                    // viewportMinimum:
                   // viewportMaximum:
                    viewportMaximum:new Date(new Date().getFullYear(),new Date().getUTCMonth() + 1,new Date().getUTCDate())
                },*/
                data: [{
                    // type: "spline",
                    type: "splineArea",
                    color: "rgba(54,158,173,.7)",
                    markerSize: 5,
                    yValueFormatString: "AZN #,##0.##",
                    xValueFormatString: "YYYY/MM/DD",
                    //  xValueFormatString: "####",
                    // xValueFormatString: "DD MMM",
                    dataPoints: dps[0]
                    /*dataPoints: [

                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getDate()), y: 5647000},
                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 6)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 6)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 6)).getDate()), y: 2798000},
                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 5)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 5)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 5)).getDate()), y: 3386000},
                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 4)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 4)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 4)).getDate()), y: 6704000},
                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 3)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 3)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 3)).getDate()), y: 6026000},
                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 2)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 2)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 2)).getDate()), y: 2394000},
                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 1)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 1)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 1)).getDate()), y: 1872000},
                        {x: new Date(new Date().getUTCFullYear(),(new Date().getUTCMonth() + 1),new Date().getUTCDate()), y: 2140000}

                    ]*/
                }]
            });

            var xValue;
            var yValue;
           // var tarix;
            <c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
            <c:forEach items="${dataPoints}" var="dataPoint">
            xValue = parseInt("${dataPoint.x}");
            yValue = parseFloat("${dataPoint.y}");
            dps[parseInt("${loop.index}")].push({
                x: xValue,
                y: yValue
            });
            </c:forEach>
            </c:forEach>

            chart.render();

        }
    </script>

    <!--   <script type="text/javascript">
        window.onload = function () {

            var dps = [[]];
            var chart = new CanvasJS.Chart("chartContainer", {
                theme: "light2", //"light1", "dark1", "dark2"
                animationEnabled: true,
                title: {
                    text: "Revenue:" +
                        new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getUTCFullYear() + '/' + (new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getMonth() + 1) + '/' + new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getDate()
                        + "-" + new Date().getUTCFullYear() + "/" + (new Date().getUTCMonth() + 1) + "/" + new Date().getUTCDate()
                },
                axisX: {
                    valueFormatString: "YYYY/MM/DD"
                },
                axisY: {

                    includeZero: true,

                    maximum: 2000
                },
                data: [{
                    type: "column",
                    xValueType: "dateTime",
                    xValueFormatString: "YYYY/MM/DD",
                   // yValueFormatString: "#,##0mn tonnes",
                    yValueFormatString: "AZN #,##0.##",
                    dataPoints: dps[0]
                }]
            });

            var xValue;
            var yValue;

            <c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
            <c:forEach items="${dataPoints}" var="dataPoint">
            xValue = parseInt("${dataPoint.x}");
            yValue = parseFloat("${dataPoint.y}");
            label = "${dataPoint.label}";
            indexLabel = "${dataPoint.indexLabel}";
            dps[parseInt("${loop.index}")].push({
                x: xValue,
                y: yValue
            });
            </c:forEach>
            </c:forEach>

            chart.render();

        }
    </script>-->
    <!-- <script>
         window.onload = function() {

             var dataPoints = [];

             var options =  {
                 animationEnabled: true,
                 theme: "light2",
                 title: {
                     text: "Daily Sales Data"
                 },
                 axisX: {
                     valueFormatString: "DD MMM YYYY"
                 },
                 axisY: {
                     title: "USD",
                     titleFontSize: 24,
                     includeZero: false
                 },
                 data: [{
                     type: "spline",
                     yValueFormatString: "$#,###.##",
                     dataPoints: dataPoints
                 }]
             };

             function addData(data) {
                 for (var i = 0; i < data.length; i++) {
                     dataPoints.push({
                         x: new Date(data[i].date),
                         y: data[i].units
                     });
                 }
                 $("#chartContainer").CanvasJSChart(options);

             }
             $.getJSON("https://canvasjs.com/data/gallery/jquery/daily-sales-data.json", addData);

         }
     </script>-->


</head>

<body id="page-top">

<jsp:include page="./static/header.jsp" flush="true"></jsp:include>

<div id="wrapper">
    <jsp:include page="./static/menu.jsp" flush="true"></jsp:include>

    <div id="content-wrapper">

        <div class="container-fluid">

            <!-- Breadcrumbs-->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="index.jsp">Dashboard</a>
                </li>
                <li class="breadcrumb-item active">Overview</li>
            </ol>

            <!-- Icon Cards-->
            <div class="row">
                <div class="col-xl-3 col-sm-6 mb-3">
                    <div class="card text-white bg-primary o-hidden h-100">
                        <div class="card-body">
                            <div class="card-body-icon">
                                <i class="fas fa-fw fa-comments"></i>
                            </div>
                            <div class="mr-5">26 New Messages!</div>
                        </div>
                        <a class="card-footer text-white clearfix small z-1" href="#">
                            <span class="float-left">View Details</span>
                            <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
                        </a>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6 mb-3">
                    <div class="card text-white bg-warning o-hidden h-100">
                        <div class="card-body">
                            <div class="card-body-icon">
                                <i class="fas fa-fw fa-list"></i>
                            </div>
                            <div class="mr-5">11 New Tasks!</div>
                        </div>
                        <a class="card-footer text-white clearfix small z-1" href="#">
                            <span class="float-left">View Details</span>
                            <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
                        </a>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6 mb-3">
                    <div class="card text-white bg-success o-hidden h-100">
                        <div class="card-body">
                            <div class="card-body-icon">
                                <i class="fas fa-fw fa-shopping-cart"></i>
                            </div>
                            <div class="mr-5">123 New Orders!</div>
                        </div>
                        <a class="card-footer text-white clearfix small z-1" href="#">
                            <span class="float-left">View Details</span>
                            <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
                        </a>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6 mb-3">
                    <div class="card text-white bg-danger o-hidden h-100">
                        <div class="card-body">
                            <div class="card-body-icon">
                                <i class="fas fa-fw fa-life-ring"></i>
                            </div>
                            <div class="mr-5">13 New Tickets!</div>
                        </div>
                        <a class="card-footer text-white clearfix small z-1" href="#">
                            <span class="float-left">View Details</span>
                            <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
                        </a>
                    </div>
                </div>
            </div>

            <!-- Area Chart Example-->
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-chart-area"></i>
                    Area Chart Example
                </div>
                <div class="card-body">
                    <!--   <canvas id="chartContainer" width="100%" height="30"></canvas>-->
                    <div id="chartContainer" style="height: 370px; width: 100%;"></div>
                    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
                </div>
                <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
            </div>

            <!-- DataTables Example -->
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-table"></i>
                    Data Table Example
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Position</th>
                                <th>Office</th>
                                <th>Age</th>
                                <th>Start date</th>
                                <th>Salary</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>Position</th>
                                <th>Office</th>
                                <th>Age</th>
                                <th>Start date</th>
                                <th>Salary</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <tr>
                                <td>Tiger Nixon</td>
                                <td>System Architect</td>
                                <td>Edinburgh</td>
                                <td>61</td>
                                <td>2011/04/25</td>
                                <td>$320,800</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
            </div>

        </div>
        <!-- /.container-fluid -->

        <!-- Sticky Footer -->
        <footer class="sticky-footer">
            <jsp:include page="./static/footer.jsp"></jsp:include>
        </footer>

    </div>
    <!-- /.content-wrapper -->

</div>
<!-- /#wrapper -->

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
                <a class="btn btn-primary" href="./resources/newLayout/vendor/html/login.html">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="./resources/newLayout/vendor/jquery/jquery.min.js"></script>
<script src="./resources/newLayout/vendor/jquery/jquery.js"></script>
<script src="./resources/newLayout/vendor/jquery/jquery.slim.js"></script>
<script src="./resources/newLayout/vendor/jquery/jquery.slim.min.js"></script>
<script src="./resources/newLayout/vendor/jquery/jquery.min.map"></script>
<script src="./resources/newLayout/vendor/jquery/jquery.slim.min.map"></script>
<script src="./resources/newLayout/vendor/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="./resources/newLayout/vendor/jquery/jquery.easing.min.js"></script>
<script src="./resources/newLayout/vendor/jquery/jquery.easing.compatibility.js"></script>
<script src="./resources/newLayout/vendor/jquery/jquery.easing.js"></script>

<!-- Page level plugin JavaScript-->
<script src="./resources/newLayout/vendor/js/Chart.js"></script>
<script src="./resources/newLayout/vendor/js/Chart.min.js"></script>
<script src="./resources/newLayout/vendor/js/Chart.bundle.js"></script>
<script src="./resources/newLayout/vendor/js/Chart.bundle.min.js"></script>
<script src="./resources/newLayout/vendor/datatables/jquery.dataTables.js"></script>
<script src="./resources/newLayout/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="./resources/newLayout/vendor/datatables/dataTables.bootstrap4.js"></script>
<script src="./resources/newLayout/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="./resources/newLayout/vendor/js/sb-admin.min.js"></script>
<script src="./resources/newLayout/vendor/js/sb-admin.js"></script>
<!-- Demo scripts for this page-->
<script src="./resources/newLayout/vendor/js/demo/datatables-demo.js"></script>
<script src="./resources/newLayout/vendor/js/demo/chart-area-demo.js"></script>
<script src="./resources/newLayout/vendor/js/demo/chart-bar-demo.js"></script>
<script src="./resources/newLayout/vendor/js/demo/chart-pie-demo.js"></script>
</body>

</html>

