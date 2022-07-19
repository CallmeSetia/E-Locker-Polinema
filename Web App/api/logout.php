<?php
include "../util/koneksi.php";

$response["message"] = "";

if (isset($_GET['idMhs'])) {
    $idMhs = $_GET['idMhs'];

    $sqlSetDefaultOtp = "UPDATE `tbl_otp` SET `otp_masuk` = '', `otp_keluar` = '', `time_valid` = '', `id_mhs` = '0'  WHERE `tbl_otp`.`id_mhs` = '$idMhs'";
    $sqlSetDefaultGeo = "UPDATE `tbl_geolocation` SET `id_mhs` = '0' , `lat` = 0, `lng` = 0 WHERE`tbl_geolocation`.`id_mhs` = '$idMhs'";
    $sqlSetDefaultLocker = "UPDATE `tbl_locker` SET `status` = '0', `id_mhs` = 0 WHERE `tbl_locker`.`id_mhs` = '$idMhs'";

    $res1 = $koneksi->query($sqlSetDefaultOtp);
    $res2 = $koneksi->query($sqlSetDefaultGeo);
    $res3 = $koneksi->query($sqlSetDefaultLocker);

    $response["message"] = "1";

    echo json_encode($response);
}
else {

    $response["message"] = "0";
    echo json_encode($response);
}

?>


