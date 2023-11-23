<?php 
    require_once("conexao.php");
    class usuario extends conexao{
        private $id;
        private $perfil;
        private $status;
        private $nome;
        private $cpf_cnpj;
        private $email;
        private $senha;
        private $telefone;
        private $tabela = "usuario";

        //Getters e Setters
        public function getId(){
            return $this->id;
        }
        public function setId($id){
            $this->id = $id;
        }

        public function getPerfil(){
            return $this->perfil;
        }
        public function setPerfil($perfil){
            $this->perfil = $perfil;
        }

        public function getStatus(){
            return $this->status;
        }
        public function setStatus($status){
            $this->status = $status;
        }

        public function getNome(){
            return $this->nome;
        }
        public function setNome($nome){
            $this->nome = $nome;
        }

        public function getCpf_cnpj(){
            return $this->cpf_cnpj;
        }
        public function setCpf_cnpj($cpf_cnpj){
            $this->cpf_cnpj = $cpf_cnpj;
        }

        public function getEmail(){
            return $this->email;
        }
        public function setEmail($email){
            $this->email = $email;
        }

        public function getSenha(){
            return $this->senha;
        }
        public function setSenha($senha){
            $this->senha = $senha;
        }

        public function getTelefone(){
            return $this->telefone;
        }
        public function setTelefone($telefone){
            $this->telefone = $telefone;
        }

        //Metodos

        //Consulta no banco
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

        //Cadastrar Lado Do Cliente
        public function cadastrarCliente($nome,$cpf_cnpj,$email,$senha,$telefone){
            $sql= "insert into $this->tabela(nome,cpf_cnpj,email,senha,telefone) values(?,?,?,?,?)";
            $stmt = $this->conn->prepare($sql);
            $stmt->bind_param('sssss', $nome,$cpf_cnpj,$email,$senha,$telefone);
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
        
        //Cadastrar Lado Do Administrador
        public function cadastrar($perfil,$nome,$cpf_cnpj,$email,$senha,$telefone){
            $sql= "insert into $this->tabela(perfil,nome,cpf_cnpj,email,senha,telefone) values(?,?,?,?,?,?)";
            $stmt = $this->conn->prepare($sql);
            $stmt->bind_param('ssssss', $perfil,$nome,$cpf_cnpj,$email,$senha,$telefone);
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

        //Editar Lado Do Cliente
		public function editarCliente($nome,$cpf_cnpj,$email,$senha,$telefone,$id){
			$sql = "UPDATE $this->tabela SET nome = ? , cpf_cnpj = ? , email = ? , senha = ? , telefone = ? 
			WHERE id = ?";
			$stmt = $this->conn->prepare($sql);
			$stmt->bind_param('sssssi',$nome,$cpf_cnpj,$email,$senha,$telefone,$id);
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
        
        //Editar Lado Do Administrador
		public function editar($perfil,$status,$nome,$cpf_cnpj,$email,$senha,$telefone,$id){
			$sql = "UPDATE $this->tabela SET perfil = ? , `status` = ? , nome = ? , cpf_cnpj = ? , email = ? , senha = ? , telefone = ? 
			WHERE id = ?";
			$stmt = $this->conn->prepare($sql);
			$stmt->bind_param('sssssssi',$perfil,$status,$nome,$cpf_cnpj,$email,$senha,$telefone,$id);
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