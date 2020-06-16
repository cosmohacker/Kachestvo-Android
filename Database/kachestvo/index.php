<?php

error_reporting(-1);
ini_set('display_errors', 'On');

require_once 'db_functions.php';
require 'libs/Slim/Slim.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();

$app->get('/comments', function() {
    $response = array();
    $db = new db_functions();

    $result = $db->getAllComments();

    $response["error"] = false;
    $response["comments"] = array();

    while ($comments = $result->fetch_assoc()) {
        $tmp = array();
        $tmp["Id"] = $comments["Id"];
        $tmp["Name"] = $comments["Name"];
        $tmp["Comment"] = $comments["Comment"];
        $tmp["Created_At"] = $comments["Created_At"];
        array_push($response["comments"], $tmp);
    }
    echoRespnse(200, $response);
});

$app->post('/comments/insert',function(){

$response = array();
$db = new db_functions();

if ( isset($_POST['Name']) && isset($_POST['Comment'])) {

	$Name = $_POST['Name'];
    $Comment = $_POST['Comment'];
      $kachestvo = $db->storeComment($Name, $Comment);
      if ($kachestvo) {
          $response["error"] = FALSE;
          $response["kachestvo"]["Name"] = $kachestvo["Name"];
          $response["kachestvo"]["Comment"] = $kachestvo["Comment"];
              echoRespnse(200, $response);
      } else {
          $response["error"] = TRUE;
          $response["error_msg"] = "Unknown error occurred in registration!";
          echoRespnse(200, $response);
  }
} else {
  $response["error"] = TRUE;
  $response["error_msg"] = "Required parameters is missing!";
  echoRespnse(200, $response);
}
});

$app->post('/presentation/next',function(){

    $response = array();
    $db = new db_functions();
    
    if (isset($_POST['Next'])) {
    
        $Next = $_POST['Next'];
          $kachestvo = $db->nextSlide($Next);
          if ($kachestvo) {
              $response["error"] = FALSE;
              $response["kachestvo"]["Next"] = $kachestvo["Next"];
                  echoRespnse(200, $response);
          } else {
              $response["error"] = TRUE;
              $response["error_msg"] = "Unknown error occurred in registration!";
              echoRespnse(200, $response);
      }
    } else {
      $response["error"] = TRUE;
      $response["error_msg"] = "Required parameters is missing!";
      echoRespnse(200, $response);
    }
    });

    $app->post('/presentation/previous',function(){

        $response = array();
        $db = new db_functions();
        
        if (isset($_POST['Previous'])) {
        
            $Previous = $_POST['Previous'];
              $kachestvo = $db->previousSlide($Previous);
              if ($kachestvo) {
                  $response["error"] = FALSE;
                  $response["kachestvo"]["Previous"] = $kachestvo["Previous"];
                      echoRespnse(200, $response);
              } else {
                  $response["error"] = TRUE;
                  $response["error_msg"] = "Unknown error occurred in registration!";
                  echoRespnse(200, $response);
          }
        } else {
          $response["error"] = TRUE;
          $response["error_msg"] = "Required parameters is missing!";
          echoRespnse(200, $response);
        }
        });

        $app->post('/email/send',function(){

            $response = array();
            $db = new db_functions();
            
            if (isset($_POST['Name']) && isset($_POST['Email']) && isset($_POST['Subject'])) {
            
                $Name = $_POST['Name'];
                $Email = $_POST['Email'];
                $Subject = $_POST['Subject'];
                  $kachestvo = $db->sendMail($Name, $Email, $Subject);
                  if ($kachestvo) {
                      $response["error"] = FALSE;
                      $response["kachestvo"]["Name"] = $kachestvo["Name"];
                      $response["kachestvo"]["Email"] = $kachestvo["Email"];
                      $response["kachestvo"]["Subject"] = $kachestvo["Subject"];
                          echoRespnse(200, $response);
                  } else {
                      $response["error"] = TRUE;
                      $response["error_msg"] = "Unknown error occurred in registration!";
                      echoRespnse(200, $response);
              }
            } else {
              $response["error"] = TRUE;
              $response["error_msg"] = "Required parameters is missing!";
              echoRespnse(200, $response);
            }
            });

/**
 * Echoing json response to client
 * @param String $status_code Http response code
 * @param Int $response Json response
 */
function echoRespnse($status_code, $response) {
    $app = \Slim\Slim::getInstance();
    // Http response code
    $app->status($status_code);

    // setting response content type to json
    $app->contentType('application/json');

    echo json_encode($response);
}

$app->run();

?>