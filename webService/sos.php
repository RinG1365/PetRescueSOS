<?php

require_once("config.php");

if(isset($_POST))
{
  $varFN = $_REQUEST["selectFn"];

  if($varFN == "addSOS")
  {
    $sosDate = $_REQUEST["sosDate"];
    $sosTime =  $_REQUEST["sosTime"];
    $stationID =  $_REQUEST["stationID"];
    $sender =  $_REQUEST["sender"];
    $pType =  $_REQUEST["pType"];
    $pQuan =  $_REQUEST["pQuan"];
    $rescuerID =  $_REQUEST["rescuerID"];
    $status =  $_REQUEST["status"];

    $addSos = "INSERT INTO SOS (SOSDATE, SOSTIME, SOSSTATUS, PTYPE, PQUAN, USERNAME, RID, SID) VALUES
              (:sdate, :stime, :sstatus, :ptype, :pquan, :username, :rid, :sid)";
    $stmt = $dbPDO->prepare($addSos);
    $stmt->execute(array(':sdate'=>$sosDate, ':stime'=>$sosTime, ':sstatus'=>$status, ':ptype'=>$pType, ':pquan'=>$pQuan,
            ':username'=>$sender, ':rid'=>$rescuerID,':sid'=>$stationID ));

    $success = array('success'=>1);

    echo json_encode($success);
  }


}

?>
