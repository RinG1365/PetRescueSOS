<?php
$hostAddr = "127.0.0.1";
$dbName = "petRescue";
$dbUser = "root";
$dbPwd = "root";
$dbPort = 3306;

$dbPDO = new PDO("mysql:host=$hostAddr;dbname=$dbName",$dbUser,$dbPwd);

?>

