<?php
ob_start();
session_start();

include '../../util/koneksi.php';

$dataLokasi = array();

$sqlGetDataLokasi = "SELECT * FROM `tbl_geolocation` ";
$res = $koneksi->query($sqlGetDataLokasi);

while ($row = $res->fetch_assoc()) {
    array_push($dataLokasi, $row);
}

print_r($dataLokasi);
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
        <!-- Google Maps JS API -->

        <div class="row">
            <div id="map_canvas" style="width: 100%; height:900px;"></div>

        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>

        <script  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVzGNR5y3p-FdmoTngYTSQVvEZZlRKRlk&callback=initMapinitializeMap"></script>
        <!-- GMaps Library -->
        <script src="gmaps.js"></script>
        <script>

            function initialize() {
                var latlng = new google.maps.LatLng(-7.9300970, 112.6658720);
                var myOptions = {
                    zoom: 18,
                    center: latlng,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

                const circle = new google.maps.Circle({
                    strokeColor: "#FF0000",
                    strokeOpacity: 0.8,
                    strokeWeight: 2,
                    fillColor: "#FF0000",
                    fillOpacity: 0.35,
                    map,
                    center:{ lat: -7.9300970, lng: 112.6658720},
                    radius: 22,
                });

                var shape = {
                    coord: [1, 1, 1, 45, 43, 45, 43 , 1],
                    type: 'poly'
                };
                var markers = [
                    {id: "mhs1", lat: <?= $dataLokasi[0]['lat']?>, lng:   <?= $dataLokasi[0]['lng']?>   },
                    {id: "mhs2", lat: <?= $dataLokasi[1]['lat']?>, lng:   <?= $dataLokasi[1]['lng']?> },
                    {id: "mhs3",  lat: <?= $dataLokasi[2]['lat']?>, lng:   <?= $dataLokasi[2]['lng']?> },
                    {id: "mhs4",  lat: <?= $dataLokasi[3]['lat']?>, lng:   <?= $dataLokasi[3]['lng']?> },
                ];

                var myMarkers = new Array();
                for (index in markers) {
                    myMarkers[markers[index]['id']] = addMarker(map, markers[index]);
                    // myMarker.append(addMarker(map, markers[index]));
                    console.log( myMarkers);
                }

                function addMarker(map, data) {
                    //create the markers
                    var marker = new google.maps.Marker({
                        position: new google.maps.LatLng(data.lat, data.lng),
                        map: map,
                        title: data.id,
                    });

                    //create the info windows
                    var content = document.createElement("DIV");
                    var title = document.createElement("DIV");
                    title.innerHTML = data.id;
                    content.appendChild(title);

                    var infowindow = new google.maps.InfoWindow({
                        content: content
                    });

                    // Open the infowindow on marker click
                    google.maps.event.addListener(marker, "click", function() {
                        infowindow.open(map, marker);
                    });
                    return marker;
                }
                // setInterval(function () {
                //         // myMarkers['mhs1'].setPosition(new google.maps.LatLng(7.415,-121.374));
                //         var a = parseFloat(getRandomInRange(0, 100, 3));
                //         var b = parseFloat( getRandomInRange(-123 , -121, 3));
                //         console.log(a);
                //         console.log(b);
                //
                //         myMarkers['mhs1'].setPosition(new google.maps.LatLng(a,b));
                //         var a = parseFloat(getRandomInRange(0, 100, 3));
                //         var b = parseFloat( getRandomInRange(-123 , -121, 3));
                //         myMarkers['mhs2'].setPosition(new google.maps.LatLng(a,b));
                //
                //         var a = parseFloat(getRandomInRange(0, 100, 3));
                //         var b = parseFloat( getRandomInRange(-123 , -121, 3));
                //         myMarkers['mhs3'].setPosition(new google.maps.LatLng(a,b));
                //
                //         var a = parseFloat(getRandomInRange(0, 100, 3));
                //         var b = parseFloat( getRandomInRange(-123 , -121, 3));
                //         myMarkers['mhs4'].setPosition(new google.maps.LatLng(a,b));
                //     }
                //     , 5000);
            }

            function getRandomInRange(from, to, fixed) {
                return (Math.random() * (to - from) + from).toFixed(fixed) * 1;
                // .toFixed() returns string, so ' * 1' is a trick to convert to number
            }
        </script>



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
