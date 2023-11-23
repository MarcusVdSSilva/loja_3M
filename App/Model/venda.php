<?php 
    require_once("conexao.php");
    require_once("produto.php.php");
    class usuario extends conexao{
        private $id;
        private $valor;
        private $data;
        private $produto;
        private $tabela = "usuario";

        //Getters e Setters
        public function getId(){
            return $this->id;
        }
        public function setId($id){
            $this->id = $id;
        }

        public function getValor(){
            return $this->valor;
        }
        public function setValor($valor){
            $this->valor = $valor;
        }

        public function getData(){
            return $this->data;
        }
        public function setData($data){
            $this->data = $data;
        }

        public function getProduto(){
            return $this->produto;
        }
        public function setProduto($produto){
            $this->produto = $produto;
        }

        //Metodos

        //Consulta no banco
		public function listar(){
			$sql = "SELECT * FROM $this->tabela ORDER BY `data` ASC";
			$result = $this->conn->query($sql);
			
			if($result == true){
				return $result;
                
			}else{
				die("Falha na consulta!");
			}

            $this->conn->close();
		}

        //Cadastrar
        public function cadastrar($valor,$data,$id_usuario){
            $sql= "insert into $this->tabela(valor,data,id_usuario) values(?,?,?)";
            $stmt = $this->conn->prepare($sql);
            $stmt->bind_param('sss', $valor,$data,$id_usuario);
            $stmt->execute();
            if($stmt==true){
                /*ALTERAR LINHA DE BAIXO */
                header("Location: ../View/LOCALCOLOCARAQUI.php?sucess");
                return $stmt;
            }else{
                /*ALTERAR LINHA DE BAIXO */
                header("Location: ../View/LOCALCOLOCARAQUI.php?error");
                die("Falha no Cadastro!");
            }

            $stmt->close();
			$this->conn->close();	
        }

    }
?>