<?php

require_once("config.php");

if(isset($_POST))
{
  $varFN = $_REQUEST["selectFn"];
  if($varFN == "getAllHistory")
  {


    $getHistory = "SELECT * FROM SOS";
    $stmt = $dbPDO->prepare($getHistory);
    $stmt ->execute();

    $recordSetObj = $stmt->fetchAll(PDO::FETCH_OBJ);

    echo json_encode($recordSetObj);

  }

  else if($varFN == "updateTaken")
  {
    $sosID = $_REQUEST["sosId"];
    $status = "TAKEN";
    $updateTaken = "UPDATE SOS SET SOSSTATUS = '$status' WHERE SOSID = '$sosID'";
    $stmt = $dbPDO->prepare($updateTaken);
    $stmt ->execute();

    $success = array('success'=>1);

    echo json_encode($success);
  }

  else if($varFN == "updateWait")
  {
    $sosID = $_REQUEST["sosId"];
    $status = "WAIT";
    $updateTaken = "UPDATE SOS SET SOSSTATUS = '$status' WHERE SOSID = '$sosID'";
    $stmt = $dbPDO->prepare($updateTaken);
    $stmt ->execute();

    $success = array('success'=>1);

    echo json_encode($success);
  }

    else if($varFN == "getUserHistory")
  {

    $username = $_REQUEST["user"];
    $getHistory = "SELECT * FROM SOS WHERE USERNAME = :user";
    $stmt = $dbPDO->prepare($getHistory);
    $stmt ->execute(array(':user'=>$username));

    $recordSetObj = $stmt->fetchAll(PDO::FETCH_OBJ);

    echo json_encode($recordSetObj);

  }


}

?>
