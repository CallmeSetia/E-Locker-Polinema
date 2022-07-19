<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "smartlocker";

$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$row = 1;
if (($handle = fopen("member_lib_export.csv", "r")) !== FALSE) {
    while (($data = fgetcsv($handle, 1000, ";")) !== FALSE) {
        $num = count($data);
        echo "<p> $num fields in line $row: <br /></p>\n";
        $row++;
        if ($data[3] == "Mahasiswa" && $data[10] == "A ( Aktif )") {

            echo "Nim : ".$data[0] ."<br>";
            echo "Nama Mahasiswa : ".$data[1]."<br>";
            echo "Status Mahasiswa : ".$data[3]."<br>";
            echo "Email Mahasiswa : ".$data[4]."<br>";
            echo "Alamat Mahasiswa : ".$data[5]."<br>";
            echo "Kode Pos Mahasiswa : ".$data[6]."<br>";
            echo "Prodi Mahasiswa : ".$data[7]."<br>";
            echo "Foto Mahasiswa? :".$data[9]."<br>";
            echo "Aktif Mahasiswa? :".$data[10]."<br>";
            echo "No HP Mahasiswa? :".$data[11]."<br>";
            echo "Tanggal Lahir Mahasiswa? :".$data[16]."<br>";

//            $nim = (string) $data[0];

//            $nama = (string) $data[1];
//            $email = (string) $data[4];
//            $alamat = (string) $data[5]." ".(string) $data[6] ;
//            $prodi = (string)$data[7];
//            $foto = (string)$data[9];
//            $hp = (string)$data[11];
//            $tgl = (string)$data[16];
//
//            $sql = "INSERT INTO `tbl_mahasiswa`(`id_mhs`, `nim_mhs`, `nama_mhs`, `email_mhs`, `alamat_mhs`, `prodi_mhs`, `foto_mhs`, `nohp_mhs`, `tgl_lahir_mhs`)
//                            VALUES (NULL,'$nim','$nama','$email','$alamat','$prodi','$foto','$hp','$tgl')";
//            if ($conn->query($sql) === TRUE) {
//                echo "New record created successfully";
//            } else {
//                echo "Error: " . $sql . "<br>" . $conn->error;
//            }
        }


//        for ($c=0; $c < $num; $c++) {
//            echo $data[$c] . "<br />\n";
//        }
    }
    fclose($handle);
}
?>