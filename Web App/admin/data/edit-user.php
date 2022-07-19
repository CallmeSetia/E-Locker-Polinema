<?php
require_once "../../util/koneksi.php";
require_once "../../util/fungsi.php";

session_start();
$ID_WARGA_NOW = 0;

$FLAG_KEC = 0;

$entry_flag = -1;

$id_mhs = $_GET['id_mhs'];
$sqlMhs = "SELECT * FROM `tbl_mahasiswa` WHERE `id_mhs` = '$id_mhs'";
$res = $koneksi->query($sqlMhs);
$dataMahasiswa = $res->fetch_assoc();


if (isset($_GET['update_mhs'])) {
    echo "1";
    $id_mhs = $_GET['id_mhs'];
    $nim = $_GET['nim_mhs'];
    $nama = strtoupper($_GET['nama_mhs']);
    $alamat = strtoupper($_GET['alamat_mhs']);
    $noHp = $_GET['nohp_mhs'];
    $tgl_lahir = $_GET['tgl_lahir'];
    $email_mhs = $_GET['email_mhs'];
    $prodi_mhs = strtoupper($_GET['prodi_mhs']);
    $nohp = $_GET['nohp_mhs'];

    $inspeksi = $_SESSION['username'];

//    $inspeksi = $_SESSION['username'];

    $sqlInsert = "UPDATE `tbl_mahasiswa` SET `nim_mhs`='$nim',
                           `nama_mhs`='$nama',`email_mhs`='$alamat',`alamat_mhs`='$alamat',
                           `prodi_mhs`='$prodi_mhs',`nohp_mhs`='$nohp',
                           `tgl_lahir_mhs`='$tgl_lahir' WHERE `id_mhs`='$id_mhs'";
    $res = $koneksi->query($sqlInsert);

    if (!$res) $entry_flag = 0;
    else $entry_flag = 1;

    header("location: list-user.php");
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>EDIT MAHASISWA</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="../../assets/img/favicon.png" rel="icon">
    <link href="../../assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
          rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="../../assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="../../assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="../../assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="../../assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="../../assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="../../assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/i18n/defaults-*.min.js"></script>

    <!-- Template Main CSS File -->
    <link href="../../assets/css/style.css" rel="stylesheet">

    <!-- =======================================================
    * Template Name: NiceAdmin - v2.2.2
    * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
</head>

<body>
<header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">

        <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->


    <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">


            <li class="nav-item dropdown pe-3">

                <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">

                    <span class=" d-md-block  ps-2">  <?php echo $_SESSION['username']; ?></span>
                </a><!-- End Profile Iamge Icon -->
            </li><!-- End Profile Nav -->
        </ul>
    </nav><!-- End Icons Navigation -->

</header><!-- End Header -->
<?php include_once "ui-menu_penjaga.php" ?>
<main id="main" class="main">

    <div class="pagetitle">
        <h1>Dashboard</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                <li class="breadcrumb-item active">User</li>
                <li class="breadcrumb-item active">Register</li>
                <li class="breadcrumb-item active"
                </li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <section class="section dashboard">
        <div class="row">
            <div class="col-lg-12">
                <h4 class="pagetitle">Tambah Pelanggan <b></b></h4>
                <form class="row g-3 needs-validation" action="" method="get">
                    <div class="card">
                        <div class="card-body">

                            <div class="row g-3 p-5">
                                <div class="col-12">
                                    <div class="row mb-3">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">NIM Mahasiswa</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="inputText" value="<?= $dataMahasiswa['nim_mhs']; ?>"   name="nim_mhs">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Nama Mahasiswa</label>
                                        <div class="col-sm-10">
                                            <input type="text"   name="nama_mhs" value="<?= $dataMahasiswa['nama_mhs']; ?>" class="form-control" id="inputText">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Email Mahasiswa</label>
                                        <div class="col-sm-10">
                                            <input type="email"  name="email_mhs" value="<?= $dataMahasiswa['email_mhs']; ?>"   class="form-control" id="inputText">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Alamat Mahasiswa</label>
                                        <div class="col-sm-10">
                                            <textarea name="alamat_mhs"  id="" cols="100" rows="10"><?= $dataMahasiswa['alamat_mhs']; ?></textarea>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Prodi Mahasiswa</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="prodi_mhs" class="form-control" value="<?= $dataMahasiswa['prodi_mhs']; ?>"   id="inputText">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">No Hp Mahasiswa</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="nohp_mhs" class="form-control"  value="<?= $dataMahasiswa['nohp_mhs']; ?>"   id="inputText">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Tgl Lahir Mahasiswa</label>
                                        <div class="col-sm-10">
                                            <input type="date" name="tgl_lahir"  value="<?= $dataMahasiswa['tgl_lahir_mhs']; ?>" class="form-control">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-2"></div>
                                        <div class="col-sm-10">
                                            <input type="hidden" name="update_mhs" value="1" class="form-control">
                                            <input type="hidden" name="id_mhs" value="<?= $id_mhs ?>" class="form-control">
                                            <button type="submit" class="btn btn-primary">Update Mahasiswa</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
            </form>

        </div>
        </div>
    </section>

</main><!-- End #main -->
<a class="back-to-top d-flex align-items-center justify-content-center" href="#"><i
            class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="../../assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="../../assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../../assets/vendor/chart.js/chart.min.js"></script>
<script src="../../assets/vendor/echarts/echarts.min.js"></script>
<script src="../../assets/vendor/quill/quill.min.js"></script>
<script src="../../assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="../../assets/vendor/tinymce/tinymce.min.js"></script>
<script src="../../assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="../../assets/js/main.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
    var select_box_element = document.querySelector('#select_box');

    dselect(select_box_element, {
        search: true
    });
</script>
</body>
</html>
