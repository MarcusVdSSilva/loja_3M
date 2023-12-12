<?php 
    abstract Class conexao{
        private $servidor = 'mysql.memsolucoestecnologicas.com.br';
        private $user = 'memsolucoestec';
        private $pass = 'Loja3M';
        private $banco = 'memsolucoestec';
        public $conn;

        public function __construct(){
            $this->conexao();
        }

        private function conexao(){
            $this->conn = new mysqli($this->servidor, $this->user, $this->pass, $this->banco);
        }
    }
?>