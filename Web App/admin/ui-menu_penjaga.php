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
<!-- ======= Sidebar ======= -->

<!-- ======= Sidebar ======= -->
<aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

        <li class="nav-item">
            <a class="nav-link " href="index.php">
                <i class="bi bi-grid"></i>
                <span>Dashboard</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="data/list-user.php">
                <i class="bi bi-people"></i>
                <span>User Mahasiswa</span>
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="data/map-user.php">
                <i class="bi bi-map"></i>
                <span>Data Lokasi Mahsiswa</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="data/control-locker.php">
                <i class="bi bi-tablet"></i>
                <span>Kontrol Locker</span>
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="">
                <i class="bi bi-input-cursor"></i>
                <span>Token Mahasiswa</span>
            </a>
        </li>



        <li class="nav-item">
            <a class="nav-link collapsed" href="logout.php">
                <i class="bi bi-power"></i>
                <span>Logout</span>
            </a>
        </li><!-- End Login Page Nav -->

    </ul>

</aside><!-- End Sidebar-->
