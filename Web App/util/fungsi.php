<?php

require_once "koneksi.php";

$PENJAGA = 11;
$WARGA = 12;

function pass() {
    echo "";
}

function cekLoginUser($user, $pass)
{
    global $koneksi;

    $sql = "SELECT * FROM tbl_admin WHERE username = '$user' AND password = '$pass' ";
    $hasil = $koneksi->query($sql);
    if ($nums_row = $hasil->num_rows > 0) {
        return true;
    } else {
        return false;
    }
}
//
function displayLoginMassage($flag)
{

    if ($flag == -1) {
        echo ' <div class="alert alert-danger alert-dismissible fade show my-3" role="alert">
               <i class="bi bi-exclamation-octagon me-1"></i>
               Username/Password Salah!
               <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
           </div>';
    }
    else if ($flag == 0) {
        echo "";
    }
    else if ($flag == 1) {
        echo ' <div class="alert alert-success alert-dismissible fade show my-3" role="alert">
               <i class="bi bi-exclamation-octagon me-1"></i>
               Berhasil Login!
               <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
           </div>';
    }
}
//
//function changeBoardMode($MODE) {
//    global  $koneksi;
//    if ($MODE == 12) { // DAFTAR WARGA
//        $sqlBoard1 = "UPDATE `mode_board` SET `mode_board` = '12', `fingger_oke` = '0',  `data_finger` = '0' WHERE `mode_board`.`id_board` = 1";
//        $sqlBoard2 = "UPDATE `mode_board` SET `mode_board` = '12', `fingger_oke` = '0',  `data_finger` = '0' WHERE `mode_board`.`id_board` = 2";
//
//        $hasil = $koneksi->query($sqlBoard1);
//        $hasi2 = $koneksi->query($sqlBoard2);
//    }
//    else if ($MODE == 0) {
//
//            $sqlBoard1 = "UPDATE `mode_board` SET `mode_board` = '0', `fingger_oke` = '0',  `data_finger` = '0' WHERE `mode_board`.`id_board` = 1";
//            $sqlBoard2 = "UPDATE `mode_board` SET `mode_board` = '0', `fingger_oke` = '0',  `data_finger` = '0' WHERE `mode_board`.`id_board` = 2";
//
//            $hasil = $koneksi->query($sqlBoard1);
//            $hasi2 = $koneksi->query($sqlBoard2);
//
//    }
//    else if ($MODE == 11) {
//
//        $sqlBoard1 = "UPDATE `mode_board` SET `mode_board` = '11', `fingger_oke` = '0',  `data_finger` = '0' WHERE `mode_board`.`id_board` = 1";
//        $sqlBoard2 = "UPDATE `mode_board` SET `mode_board` = '11', `fingger_oke` = '0',  `data_finger` = '0' WHERE `mode_board`.`id_board` = 2";
//
//        $hasil = $koneksi->query($sqlBoard1);
//        $hasi2 = $koneksi->query($sqlBoard2);
//
//    }
//}

// ADMIN - PENJAGA

//function showTable_User($role) {
//    echo
//}


