<?php
ob_start();
session_start();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Dashboard - NiceAdmin Bootstrap Template</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="../../assets/img/favicon.png" rel="icon">
    <link href="../../assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="../../assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="../../assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="../../assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="../../assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="../../assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="../../assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="../../assets/css/style.css" rel="stylesheet">

    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVzGNR5y3p-FdmoTngYTSQVvEZZlRKRlk&callback=  initMap">
    </script>
    <!--    <script src="gmap.min.js"></script>-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <!-- =======================================================
    * Template Name: NiceAdmin - v2.2.2
    * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
    <style>
        #map {
            width: 100%;
            height: 400px;
        }
    </style>
</head>

<body onload="initialize()">


<?php include_once "ui-menu_penjaga.php" ?>s
<main id="main" class="main">

    <div class="pagetitle">
        <h1>Dashboard</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                <li class="breadcrumb-item active">Dashboard</li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <section class="section dashboard">
        <div class="row">
            <div class="col-12 m-1">
                <h5 class="pagetitle">Status dan Kontrol Locker</h5>
            </div>

            <div class=" col-sm-6 ">
                <div class="card bg-success-light text-center m-2 ">
                    <div class="card-body">
                        <h5 class="card-title">Locker 1 </h5>
                        <p class="card-text">Status : <b>Bisa Digunakan</b></p>
                        <a href="../../api/board/CommandLocker.php?controlLocker1=1" target="_blank" class="btn btn-success">Buka Locker</a>
                        <a href="../../api/board/CommandLocker.php?controlLocker1=0" target="_blank" class="btn btn-danger">Tutup Locker</a>
                    </div>
                </div>
            </div>
            <div class=" col-sm-6 ">
                <div class="card bg-success-light text-center m-2 ">
                    <div class="card-body">
                        <h5 class="card-title">Locker 2 </h5>
                        <p class="card-text">Status : <b>Bisa Digunakan</b></p>
                        <a href="../../api/board/CommandLocker.php?controlLocker2=1" target="_blank" class="btn btn-success">Buka Locker</a>
                        <a href="../../api/board/CommandLocker.php?controlLocker2=0" target="_blank" class="btn btn-danger">Tutup Locker</a>
                    </div>
                </div>
            </div>
            <div class=" col-sm-6 ">
                <div class="card bg-success-light text-center m-2 ">
                    <div class="card-body">
                        <h5 class="card-title">Locker 3 </h5>
                        <p class="card-text">Status : <b>Bisa Digunakan</b></p>
                        <a href="../../api/board/CommandLocker.php?controlLocker3=1" target="_blank" class="btn btn-success">Buka Locker</a>
                        <a href="../../api/board/CommandLocker.php?controlLocker3=0" target="_blank" class="btn btn-danger">Tutup Locker</a>
                    </div>
                </div>
            </div>
            <div class=" col-sm-6 ">
                <div class="card bg-success-light text-center m-2 ">
                    <div class="card-body">
                        <h5 class="card-title">Locker 4 </h5>
                        <p class="card-text">Status : <b>Tidak Bisa Digunakan</b></p>
                        <a href="../../api/board/CommandLocker.php?controlLocker4=1" target="_blank" class="btn btn-success">Buka Locker</a>
                        <a href="../../api/board/CommandLocker.php?controlLocker4=0" target="_blank" class="btn btn-danger">Tutup Locker</a>
                    </div>
                </div>
            </div>

        </div>
    </section>

</main><!-- End #main -->

<!-- ======= Footer ======= -->
<footer id="footer" class="footer">
    <div class="copyright">
        &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved
    </div>
    <!--    <div class="credits">-->
    <!--        &lt;!&ndash; All the links in the footer should remain intact. &ndash;&gt;-->
    <!--        &lt;!&ndash; You can delete the links only if you purchased the pro version. &ndash;&gt;-->
    <!--        &lt;!&ndash; Licensing information: https://bootstrapmade.com/license/ &ndash;&gt;-->
    <!--        &lt;!&ndash; Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ &ndash;&gt;-->
    <!--        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>-->
    <!--    </div>-->
</footer><!-- End Footer -->

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="../../assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="../../assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../../assets/vendor/chart.js/chart.min.js"></script>
<script src="../../assets/vendor/echarts/echarts.min.js"></script>
<script src="../../assets/vendor/quill/quill.min.js"></script>
<script src="../../assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="../../assets/vendor/tinymce/tinymce.min.js"></script>
<script src="../../assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="../../assets/js/main.js"></script>

</body>

</html>
