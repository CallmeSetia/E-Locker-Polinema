<?php

require_once "../../util/koneksi.php";
require_once "../../util/fungsi.php";

session_start();

if (isset($_GET['id_pelanggan'])) {
    $id_pel = $_GET['id_pelanggan'];
    $sqlInsert = "DELETE FROM `tbl_pelanggan` WHERE `id_pelanggan` = '$id_pel'";
    $res = $koneksi->query($sqlInsert);

    if ($res) header("location: list-user.php?delete_pel=1");
    else header("location: list-user.php?delete_pel=0");
}


?>