<?php
require_once "../util/koneksi.php";
function generateKey($keyLength) {
    // Set a blank variable to store the key in
    $key = "";
    for ($x = 1; $x <= $keyLength; $x++) {
        // Set each digit
        $key .= rand(0, 9);
    }
    return $key;
}
header("Content-Type: application/json");

$response = array();
$response["qrCode"]= "OKE";
$response["otp"]=array();

date_default_timezone_set("Asia/Jakarta");
$date_valid = date("Y-m-d H:i:s",strtotime(date("Y-m-d H:i:s")." +1440 minutes"));
$dt   = new DateTime($date_valid);




$sql = "SELECT * FROM `tbl_locker`  WHERE status = 0 ";
$res = $koneksi->query($sql);
if ($res -> num_rows > 0) {
    $data = $res->fetch_assoc();
    $lockerAktif = (int) $data['id_locker'] ;

    $data["locker"] = (string) $lockerAktif;
}
else {
    $data["locker"] = "0";

}
$data["validUntil"] =  $dt->getTimestamp();

$millis =   new DateTime( date("Y-m-d H:i:s",strtotime(date("Y-m-d H:i:s")." 0 minutes")));
$data['time_now'] = $millis->getTimestamp();

$data["masuk"] = generateKey(4);
$data["keluar"] = generateKey(4);

array_push($response['otp'], $data);
echo json_encode($response);


?>


