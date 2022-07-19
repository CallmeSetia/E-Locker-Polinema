<?php
require_once '../util/koneksi.php';

if (isset($_GET['idMhs']) && isset($_GET['otp'])) {
    $otp = $_GET['otp'];
    $idMhs = $_GET['idMhs'];
    // cek otp masuk ??
    $sqlMasuk = "SELECT * FROM `tbl_otp` WHERE `otp_masuk` = '$otp' AND `id_mhs` = '$idMhs'";
    $res = $koneksi->query($sqlMasuk);
    if ($res ->num_rows > 0 ) {
        // OTP MASUK
        $data = $res->fetch_assoc();
        $loker = $data['id_otp'];

        $sqlRelayBuka = "UPDATE `tbl_locker` SET `control`='1' WHERE `id_locker` = '$loker'";
        $res = $koneksi->query($sqlRelayBuka);
    }
    else {
        $sqlKeluar = "SELECT * FROM `tbl_otp` WHERE `otp_keluar` = '$otp' AND `id_mhs` = '$idMhs'";
        $res = $koneksi->query($sqlKeluar);
        if ($res->num_rows > 0) {
            $data = $res->fetch_assoc();
            $loker = $data['id_otp'];

            $millis =   new DateTime( date("Y-m-d H:i:s",strtotime(date("Y-m-d H:i:s")." 5 minutes")));
            $waktu5minit = $millis->getTimestamp();


            $sqlUpdateWaktu = "UPDATE `tbl_otp` SET `time_valid` = '$waktu5minit' WHERE `otp_keluar` = '$otp' AND `id_mhs` = '$idMhs'";
            $result = $koneksi->query($sqlUpdateWaktu);

            $sqlRelayBuka = "UPDATE `tbl_locker` SET `control`='1' WHERE `id_locker` = '$loker'";
            $res = $koneksi->query($sqlRelayBuka);

        }
    }
}

$response = array();
$response["locker"]= "1";
$response["status"]= "OKE";
echo json_encode($response);
