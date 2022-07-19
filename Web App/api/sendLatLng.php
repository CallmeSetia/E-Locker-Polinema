<?php

//$response["message"] = "1";
//echo json_encode($response);

require_once '../util/koneksi.php';

if (isset($_GET['idMhs']) && isset($_GET['lat']) && isset($_GET['lng'])  ) {
    $idMhs = $_GET['idMhs'];
    $lat = $_GET['lat'];
    $lng  = $_GET['lng'];

    $sqlUpdate = "UPDATE `tbl_geolocation` SET `lat` = '$lat', `lng` = '$lng' WHERE `tbl_geolocation`.`id_mhs` = '$idMhs' ";
    $res = $koneksi->query($sqlUpdate);

}