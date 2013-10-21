<?php 

//connection to the database
$conn = pg_connect("host=localhost
port=5432
dbname=commments.db
user=postgres
password='postgres'");

if(isset($_POST["myHttpData"])) { //if there is data to import in the database
	 $data=$_POST["myHttpData"];
	 $fp = fopen('testSqlite.txt', 'w');
	fwrite($fp, $data);
	fclose($fp);
	/*
	$aImporter = json_decode($_POST['lenomduget']);

	foreach($aImporter as $valeur) {
		echo $valeur;
		
	}*/
	
}
else {
	$retour = pg_query($conn,"SELECT * FROM Comments");

	$resultats=array('comments' => array()); 
	
	While($ligne = pg_fetch_assoc($retour)) { 
		foreach($ligne as $cle => $valeur){ 
			$tableau[$cle] = $valeur; 
		}
		$resultats['comments'][]=$tableau; 
	}
	
	$resultatsJSON = json_encode($resultats);

	print($resultatsJSON); pg_close($conn); 
}
?>


