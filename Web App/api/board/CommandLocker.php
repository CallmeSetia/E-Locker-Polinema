<?php

include_once "../../util/koneksi.php";

$locker = array(0, 0, 0 ,0);

if (isset($_GET['controlLocker1'])) {
    $control = (int) $_GET['controlLocker1'];
    $sql = "UPDATE `tbl_locker` SET `control` = '$control' WHERE `tbl_locker`.`id_locker` = 1; ";
    $res = $koneksi->query($sql);

}

else if (isset($_GET['controlLocker2'])) {
    $control = (int) $_GET['controlLocker2'];
    $sql = "UPDATE `tbl_locker` SET `control` = '$control' WHERE `tbl_locker`.`id_locker` = 2; ";
    $res = $koneksi->query($sql);

}

else if (isset($_GET['controlLocker3'])) {
    $control = (int) $_GET['controlLocker3'];
    $sql = "UPDATE `tbl_locker` SET `control` = '$control' WHERE `tbl_locker`.`id_locker` = 3; ";
    $res = $koneksi->query($sql);

}

else if (isset($_GET['controlLocker4'])) {
    $control = (int) $_GET['controlLocker4'];
    $sql = "UPDATE `tbl_locker` SET `control` = '$control' WHERE `tbl_locker`.`id_locker` = 4 ";
    $res = $koneksi->query($sql);

}

else {

    for ($i = 1; $i < count($locker) + 1; $i++) {
        $sql = "SELECT * FROM `tbl_locker` WHERE  `id_locker` = '$i'";
        $res = $koneksi->query($sql);
        $data = $res->fetch_assoc();
        echo $data['control'];
        echo "#";
    }
}




//echo "0#0#0#0#";
?>