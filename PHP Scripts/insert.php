

<?php
//ovdje sam kontektovao bazu podataka
$con = new mysqli("localhost", "student.famnit", "pwd", "test");
if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }


//ovdje cu snimit varijable koje dobijem
$response = array(); //za Json


//ovdje cu slati za bazu
if (isset($_GET['Name']) && isset($_GET['Radio']) && isset($_GET['Confidence'])) {
  //variable koje ce nas app slati u bazu
echo "Entered before GET";
 $Name = $_GET['Name'];
 $Radio = $_GET['Radio'];
 $Confidence = (int)$_GET['Confidence'];
 echo $Name;
 echo $Radio;
 echo $Confidence;
 echo "Came after GET";
 $sql="INSERT INTO `EAH` (`ID`, `Name`, `Radio`, `Confidence`) VALUES (NULL, '$Name', '$Radio', '$Confidence');";
 echo "Came after sql";
 $result = mysqli_query($con,$sql);

 if ($result) {
       // successfully inserted into database
       $response["success"] = 1;
       $response["message"] = "Product successfully created.";

       // echoing JSON response
       echo json_encode($response);

   } else {
       // failed to insert row
       $response["success"] = 0;
       $response["message"] = "Oops! An error occurred.";

       // echoing JSON response
       echo json_encode($response);
   }
} else {
   // required field is missing
   $response["success"] = 0;
   $response["message"] = "Required field(s) is missing";

   // echoing JSON response
   echo json_encode($response);
}



//6th part on web page i followed
//now goes android


mysqli_close($con);
//$mysqli.close();
?>

http://www.studenti.famnit.upr.si/~89161011/OLD/insert.php?Name='image2'&Radio='FAKE'&Confidence=73?Name=image3&Radio=FAKE&Confidence=48
