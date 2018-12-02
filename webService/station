<?php

require_once("config.php");


if(isset($_POST))
{
  $varFN=$_REQUEST["selectFn"];

  if ($varFN =="addStation")
  {
    $stationArea = $_REQUEST["stationArea"];
    $stationAddress = $_REQUEST["stationAddress"];

    $addStation = "INSERT INTO STATION (SAREA, ADDRESS) VALUES(:area, :address)";
    $stmt = $dbPDO->prepare($addStation);
    $stmt -> execute(array(':area'=>$stationArea,':address'=>$stationAddress));

    $success = array('success'=>1);

    echo json_encode($success);
  }


  else if($varFN == "getAllStation")
  {


    $getStation = "SELECT * FROM STATION ";
    $stmt = $dbPDO->prepare($getStation);
    $stmt ->execute();

    $recordSetObj = $stmt->fetchAll(PDO::FETCH_OBJ);

    echo json_encode($recordSetObj);

  }

  else if($varFN == "deleteStation")
  {
    $stationId = $_REQUEST["stationId"];


    $checkStation = "SELECT * FROM SOS WHERE SID = :stationId";
    $stmt = $dbPDO->prepare($checkStation);
    $stmt ->execute(array(':stationId'=>$stationId));

    $count = $stmt->rowCount();

    if($count == 0)
    {
      $deleteStation = "DELETE  FROM STATION WHERE SID='$stationId'";
      $stmt = $dbPDO->prepare($deleteStation);
      $stmt -> execute(array(':stationId'=>$stationId));


      $success = array('success'=>1);

    }
    else
    {
      $success = array('success'=>-1);
    }


    echo json_encode($success);
  }




}


 ?>
