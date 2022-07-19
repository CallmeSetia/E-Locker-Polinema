<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "smartlocker";

header('Content-Type: application/json; charset=utf-8');

$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if (isset($_GET['username'] ) && isset($_GET['password'] ) ) {

    $nim = $_GET['username'];
    $password = $_GET['password'];

    $sql = "SELECT * FROM `tbl_mahasiswa` WHERE  `nim_mhs` = '$nim'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0 && $nim == $password)  {
        $response = array();
        $response["message"]="Login Berhasil";
        $response["data"]=array();
        while($row = $result->fetch_assoc()) {
            array_push($response['data'], $row);
        }
        echo json_encode($response);
    }
    else {
        $response["message"]= "Username atau Password Salah";
        echo json_encode($response);
    }
}
else {
    $response["message"]= "Tidak ada data";
    echo json_encode($response);
}

?>