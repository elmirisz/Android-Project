<?php


$con = new mysqli("localhost", "student.famnit", "pwd", "test");
if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }
$result = array();//instancirali da snimimo



$sql = "SELECT Name,Radio FROM `EAH` WHERE `ID`= (SELECT MAX(ID) FROM EAH)";


$r = mysqli_query($con,$sql);

while($row = mysqli_fetch_array($r)){
    array_push($result,array(
        'Name'=>$row['Name'],
        'Radio'=>$row['Radio'],

    ));
}
$name = $result[0]['Name'];
$radio = $result[0]['Radio'];





$sql = "SELECT COUNT(ID)AS ALLROWS FROM `EAH` WHERE Name='$name'";


$r = mysqli_query($con,$sql);//ovo je vec rezultat



while($row = mysqli_fetch_array($r)){
    array_push($result,array(
        'ALLROWS'=>$row['ALLROWS'],

    ));
}


 $sql="SELECT COUNT(ID)AS FAKE FROM `EAH` WHERE Name='$name' AND Radio='FAKE'";
 $r = mysqli_query($con,$sql);//ovo je vec rezultat
 while($row = mysqli_fetch_array($r)){
     array_push($result,array(
         'FAKE'=>$row['FAKE']


     ));}

$percentage = round(((int)$result[2]['FAKE'] / (int)$result[1]['ALLROWS'] * 100),0);

$sql="SELECT SUM(`Confidence`) AS SUM FROM EAH WHERE `Radio`='FAKE' AND Name='$name'";
$r = mysqli_query($con,$sql);//ovo je vec rezultat
while($row = mysqli_fetch_array($r)){
    array_push($result,array(
        'SUM'=>$row['SUM']
));}

$confidencePercentage= round(((int)$result[3]['SUM'] / (int)$result[2]['FAKE'] ),0);

$result = array();
array_push($result,array(
    'People'=>$percentage,
    'Confidence'=>$confidencePercentage
));

echo json_encode(array('result'=>$result));

mysqli_close($con);

// (SELECT COUNT(ID) FROM `EAH` WHERE Name='image2' AND Radio='FAKE')
// //returns number of images which are set to be FAKE
//
// SELECT COUNT(ID) FROM `EAH` WHERE Name='image2'
// //sveukupan broj slika
//
//
//s
//
//
//
//
// Select * FROM CAST((SELECT CAST(SELECT COUNT(ID)AS FAKE FROM `EAH` WHERE Name='image2' AND Radio='FAKE' AS int)*100 AS INT)/ CAST(SELECT COUNT(ID) AS REAL1 FROM `EAH` WHERE Name='image2' AS int)
//
// SELECT CAST(FAKE as int) FROM (SELECT COUNT(ID)AS FAKE FROM `EAH` WHERE Name='image2' AND Radio='FAKE' as int)
//
