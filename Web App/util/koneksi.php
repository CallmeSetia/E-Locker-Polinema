<?php

$DB_HOST = "localhost";
$DB_USER = "root";
$DB_PASSWORD = "";
$DB_DATABASE = "smartlocker";

$koneksi = new mysqli($DB_HOST, $DB_USER,$DB_PASSWORD,$DB_DATABASE);

// Check connection
if ($koneksi -> connect_errno) {
    echo "Failed to connect to MySQL: " . $koneksi -> connect_error;
}


?> 