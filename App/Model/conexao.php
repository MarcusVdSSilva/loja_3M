<?php 
    abstract Class conexao{
        private $servidor = 'localhost';
        private $user = 'root';
        private $pass = '';
        private $banco = 'loja_3M';
        public $conn;

        public function __construct(){
            $this->conexao();
        }

        private function conexao(){
            $this->conn = new mysqli($this->servidor, $this->user, $this->pass, $this->banco);
        }
    }
?>