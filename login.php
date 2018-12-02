<?php


require_once("config.php");


if(isset($_POST))
{
  $varFN=$_REQUEST["selectFn"];

  if ($varFN =="login")
  {
    $username = $_REQUEST["username"];
    $pass = $_REQUEST["password"];

    if($username == "admin")
    {
          $adminLogin = "SELECT * FROM ADMIN WHERE ID = :login AND PSS = :pass";
          $stmt = $dbPDO->prepare($adminLogin);
          $stmt->execute(array(':login'=>$username,':pass'=>$pass));

          $count = $stmt->rowCount();
          if($count != 0)
          {
            $level = array('level'=>1);
          }
          else
          {
            $level = array('level'=>-1);
          }
    }

    else
    {
      $userLogin = "SELECT * FROM USERS WHERE USERNAME = :login AND PSS = :pass";
      $stmt = $dbPDO->prepare($userLogin);
      $stmt ->execute(array(':login'=>$username,':pass'=>$pass));

      $count = $stmt->rowCount();
      if($count != 0)
      {
        $level = array('level'=>2);
      }
      else
      {
        $level = array('level'=>-1);
      }
    }

    echo json_encode($level);
  }

  if($varFN == "register")
  {
    $username = $_REQUEST["username"];
    $pass = $_REQUEST["password"];
    $gender = $_REQUEST["gender"];
    $mobile = $_REQUEST["mobile"];

    $checkUser = "SELECT * FROM USERS WHERE USERNAME = :login";
    $stmt = $dbPDO->prepare($checkUser);
    $stmt ->execute(array(':login'=>$username));

    $count = $stmt->rowCount();

    if($count == 0)
    {
      $addUser = "INSERT INTO USERS (USERNAME, PSS, GENDER, PHONENO) VALUES(:login, :pass, :gen, :mn)";
      $stmt = $dbPDO->prepare($addUser);
      $stmt -> execute(array(':login'=>$username,':pass'=>$pass,':gen'=>$gender,':mn'=>$mobile));


      $success = array('success'=>1);

    }
    else
    {
      $success = array('success'=>-1);
    }


    echo json_encode($success);
  }



}

/*  $adminLogin = "SELECT * FROM ADMIN";
  $stmt = $dbPDO->prepare($adminLogin);
  $stmt->execute();

  $count = $stmt->rowCount();
  if($count != 0)
  {
    $level = 1;
  }
  else
  {
    $level = -1;
  }

  echo json_encode($level);
*/

 ?>
