<?php

include_once "../../util/koneksi.php";


if (isset($_GET['lockerState1']) &&  isset($_GET['lockerState2'])  && isset($_GET['lockerState3']) && isset($_GET['lockerState4']) ) {

    $lockerState = array($_GET['lockerState1'], $_GET['lockerState2'], $_GET['lockerState3'], $_GET['lockerState4']);
    echo $lockerState[1];
    for ($i = 1; $i < count($lockerState) + 1; $i++) {
        $status = $lockerState[$i - 1];
        $sql =  "UPDATE `tbl_locker` SET `status`='$status' WHERE `id_locker`='$i'";
        $res = $koneksi->query($sql);
    }

}

else if (isset($_GET['get_state'])) {
    
}

?>