<?php

class db_functions {

    private $conn;

    // constructor
    function __construct() {
        require_once 'db_connect.php';
        // connecting to database
        $db = new db_connect();
        $this->conn = $db->connect();
    }

    // destructor
    function __destruct() {

    }

    /**
     * Storing comment
     */
    public function storeComment($Name, $Comment) {

        $stmt = $this->conn->prepare("INSERT INTO comments(Name, Comment) VALUES (?, ?)");
        $stmt->bind_param("ss", $Name, $Comment);
        $result = $stmt->execute();
        $stmt->close();

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM comments WHERE Comment = ?");
            $stmt->bind_param("s", $Comment);
            $stmt->execute();
            $comment = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $comment;
        } else {
            return false;
        }
    }

     /**
     * Next
     */
    public function nextSlide($Next) {

        $stmt = $this->conn->prepare("INSERT INTO next(Next) VALUES (?)");
        $stmt->bind_param("s", $Next);
        $result = $stmt->execute();
        $stmt->close();

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM next WHERE Next = ?");
            $stmt->bind_param("s", $Next);
            $stmt->execute();
            $next = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $next;
        } else {
            return false;
        }
    }

    public function previousSlide($Previous) {

        $stmt = $this->conn->prepare("INSERT INTO previous(Previous) VALUES (?)");
        $stmt->bind_param("s", $Previous);
        $result = $stmt->execute();
        $stmt->close();

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM previous WHERE Previous = ?");
            $stmt->bind_param("s", $Previous);
            $stmt->execute();
            $previous = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $previous;
        } else {
            return false;
        }
    }

    /**
     * Email
     */
    public function sendMail($Name, $Email, $Subject) {

        $stmt = $this->conn->prepare("INSERT INTO emails(Name, Email, Subject) VALUES (?, ?, ?)");
        $stmt->bind_param("sss", $Name, $Email, $Subject);
        $result = $stmt->execute();
        $stmt->close();

        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM emails WHERE Subject = ?");
            $stmt->bind_param("s", $Subject);
            $stmt->execute();
            $email = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $email;
        } else {
            return false;
        }
    }

    public function getAllComments(){
        $stmt = $this->conn->prepare("SELECT * FROM comments");
        $stmt->execute();
        #$comment = $stmt->get_result()->fetch_assoc();
        $tasks = $stmt->get_result();
        $stmt->close();
        return $tasks;
      }
}

?>