<?php
class db_connect {
    private $conn;

    public function connect() {
    require_once 'db_config.php';
        $this->conn = new mysqli(DB_hostname, DB_user, DB_password, DB_databaseName);

        if (mysqli_connect_errno()) {
                    echo "Failed to connect to MySQL: " . mysqli_connect_error();
                }

        return $this->conn;
    }
}

?>
