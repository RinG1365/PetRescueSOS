<?php

require_once("config.php");

if(isset($_POST))
{
  $varFN=$_REQUEST["selectFn"];

  if ($varFN =="addRescuer")
  {
    $r_name = $_REQUEST["r_name"];
    $gender = $_REQUEST["gender"];
    $phone = $_REQUEST["PhoneNo"];

    $addRescuer = "INSERT INTO RESCUER (RNAME,GENDER,PHONENO) VALUES(:rname, :gender, :Phoneno)";
    $stmt = $dbPDO->prepare($addRescuer);
    $stmt -> execute(array(':rname'=>$r_name,':gender'=>$gender,':Phoneno'=>$phone));

    $success = array('success'=>1);

    echo json_encode($success);
  }


  else if($varFN == "getAllRescuer")
  {


    $getRescuer = "SELECT * FROM RESCUER ";
    $stmt = $dbPDO->prepare($getRescuer);
    $stmt ->execute();

    $recordSetObj = $stmt->fetchAll(PDO::FETCH_OBJ);

    echo json_encode($recordSetObj);

  }

  else if($varFN == "deleteRescuer")
  {
    $rescuerId = $_REQUEST["rid"];


    $checkRescuer = "SELECT * FROM SOS WHERE RID = :rid";
    $stmt = $dbPDO->prepare($checkRescuer);
    $stmt ->execute(array(':rid'=>$rescuerId));

    $count = $stmt->rowCount();

    if($count == 0)
    {
      $deleteRescuer = "DELETE FROM RESCUER WHERE RID= :rid ";
      $stmt = $dbPDO->prepare($deleteRescuer);
      $stmt -> execute(array(':rid'=>$rescuerId));


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
