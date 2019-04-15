<?php


$con = new mysqli("localhost", "student.famnit", "pwd", "test");
if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }


$sql = "SELECT `Radio`,`Confidence` FROM `EAH` WHERE `ID`= (SELECT MAX(ID) FROM EAH)";


$r = mysqli_query($con,$sql);

$result = array();

while($row = mysqli_fetch_array($r)){
    array_push($result,array(
        'Radio'=>$row['Radio'],
        'Confidence'=>$row['Confidence'],

    ));
}

echo json_encode(array('result'=>$result));

mysqli_close($con);
