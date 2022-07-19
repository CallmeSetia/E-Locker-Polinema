<?php
require_once "../util/koneksi.php";

//if (isset($_GET['idMhs']) && isset($_GET['OTPMasuk']) && isset($_GET['OTPKeluar']) ) {
//
//}

header("Content-Type: application/json");
date_default_timezone_set("Asia/Jakarta");
$response = array();
$response["message"] = "";
$date_valid = date("Y-m-d H:i:s",strtotime(date("Y-m-d H:i:s")." 0 minutes"));
$dt = new DateTime($date_valid);
if (isset($_GET['idMhs']) && isset($_GET['OTPMasuk'])  && isset($_GET['OTPKeluar'])  && isset($_GET['TimeValid']) && isset($_GET['Locker'])) { //
    $id_mhs = $_GET['idMhs'];
    $otp_masuk = (string) $_GET['OTPMasuk'];
    $otp_keluar = $_GET['OTPKeluar'];
    $time_valid = (int) $_GET['TimeValid'];
    $locker =  (int) $_GET['Locker'];

//    echo $dt;


    if ((int) $dt->getTimestamp() < $time_valid) {
        $response["message"]= "OKE";
        $sql = "UPDATE `tbl_otp` SET `otp_masuk`='$otp_masuk',`otp_keluar`='$otp_keluar',
                     `id_mhs`='$id_mhs',`time_valid`='$time_valid'
                    WHERE `id_otp`='$locker'";

        $res = $koneksi->query($sql);

        $sql1 = "UPDATE `tbl_geolocation` SET `id_mhs`='$id_mhs' WHERE `id_geo`='$locker'";
        $ress = $koneksi->query($sql1);

        echo json_encode($response);
    }
}


//$data["token"] = rand(215192, 51233) * rand(124, 1255);


//array_push($response['data'], $data);
//echo json_encode($response);

// message
// data
// -- otp_masuk
// -- otp_keluar
// -- time_otp