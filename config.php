<?php
$hostAddr = "127.0.0.1";
$dbName = "petRescue";
$dbUser = "root";
$dbPwd = "root";
$dbPort = 3306;

$dbPDO = new PDO("mysql:host=$hostAddr;dbname=$dbName",$dbUser,$dbPwd);


/*$connection=mysqli_connect("127.0.0.1", "root", "root") or die("Connection Fail");
mysqli_select_db($connection,"petRescue") or die("Cannot connect to database");*/

?>
