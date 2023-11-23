<?php 
    require_once("conexao.php");
    class produto extends conexao{
        private $id;
        private $nome;
        private $marca;
        private $preco;
        private $foto;
        private $tabela = "produto";

        //Getters e Setters
        public function getId(){
            return $this->id;
        }
        public function setId($id){
            $this->id = $id;
        }

        public function getNome(){
            return $this->nome;
        }
        public function setNome($nome){
            $this->nome = $nome;
        }

        public function getMarca(){
            return $this->marca;
        }
        public function setMarca($marca){
            $this->marca = $marca;
        }

        public function getPreco(){
            return $this->preco;
        }
        public function setPreco($preco){
            $this->preco = $preco;
        }

        public function getFoto(){
            return $this->foto;
        }
        public function setFoto($foto){
            $this->preco = $foto;
        }

        //Metodos

        //Consulta geral
		public function listar(){
			$sql = "SELECT * FROM $this->tabela ORDER BY nome ASC";
			$result = $this->conn->query($sql);
			
			if($result == true){
				return $result;
                
			}else{
				die("Falha na consulta!");
			}

            $this->conn->close();
		}
        
        //Consulta por id
		public function consultaId($id){
			$sql = "SELECT * FROM $this->tabela where id=$id";
			$result = $this->conn->query($sql);
			
			if($result == true){
				return $result;
                
			}else{
				die("Falha na consulta!");
			}

            $this->conn->close();
		}

        //Cadastrar
        public function cadastrar($nome,$marca,$preco,$foto){
            $sql= "insert into $this->tabela(nome,marca,preco,foto) values(?,?,?,?)";
            $stmt = $this->conn->prepare($sql);
            $stmt->bind_param('ssss', $nome,$marca,$preco,$foto);
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

        //Editar
		public function editar($nome,$marca,$preco,$foto,$id){
			$sql = "UPDATE $this->tabela SET nome = ? , marca = ?, preco = ?, foto = ?
			WHERE id = ?";
			$stmt = $this->conn->prepare($sql);
			$stmt->bind_param('ssssi',$nome,$marca,$preco,$foto,$id);
			$stmt->execute();
			
			if($stmt == true){
                /*ALTERAR LINHA DE BAIXO */
                header("Location: ../View/LOCALCOLOCARAQUI.php?sucess");
			}else{
                /*ALTERAR LINHA DE BAIXO */
                header("Location: ../View/LOCALCOLOCARAQUI.php?error");
				die("Falha no Processo!");
			}
			$stmt->close();
			$this->conn->close();	
		}

    }
?>