<?php

include_once "../../util/koneksi.php";


if (isset($_GET['lockerControl']) &&  isset($_GET['num']) ) {
    $cntrol = $_GET['lockerControl'];
    $i =$_GET['num'];
    $sql =  "UPDATE `tbl_locker` SET `control`='$cntrol' WHERE `id_locker`='$i'";
    $res = $koneksi->query($sql);

//    echo $lockerState[1];
//    for ($i = 1; $i < count($lockerState) + 1; $i++) {
//        $status = $lockerState[$i - 1];
//
//    }

}

//else if (isset($_GET['get_state'])) {
//
//}

?>