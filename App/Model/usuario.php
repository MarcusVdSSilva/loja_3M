<?php 
    require_once("conexao.php");
    class usuario extends conexao{
        private $id;
        private $perfil;
        private $status;
        private $nome;
        private $cpf_cnpj;
        private $email;
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

        public function getTelefone(){
            return $this->telefone;
        }
        public function setTelefone($telefone){
            $this->telefone = $telefone;
        }

        //Metodos

        //Consulta no banco de todos usuarios
		public function listar(){
			$sql = "SELECT * FROM $this->tabela ORDER BY nome,perfil ASC";
			$result = $this->conn->query($sql);
			
			if($result == true){
				return $result;
                
			}else{
				die("Falha na consulta!");
			}

            $this->conn->close();
		}

        //Consulta no banco de todos usuarios
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

        //Consulta por nome
		public function consultaNome($nome, $perfil){
            if($perfil==""){
                $sql = "SELECT * FROM $this->tabela where nome like '%$nome%' ORDER BY nome,perfil ASC";
            }else{
                $sql = "SELECT * FROM $this->tabela where perfil='$perfil' and nome like '%$nome%' ORDER BY nome,perfil ASC";
            }
			
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
                $this->logar($email,$senha);
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
                header("Location: ../../View/usuario.php?sucess");
                return $stmt;
            }else{
                /*ALTERAR LINHA DE BAIXO */
                header("Location: ../../View/usuario.php?error");
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
		public function editar($perfil,$status,$nome,$cpf_cnpj,$email,$telefone,$id){
			$sql = "UPDATE $this->tabela SET perfil = ? , `status` = ? , nome = ? , cpf_cnpj = ? , email = ? , telefone = ?
			WHERE id = ?";
			$stmt = $this->conn->prepare($sql);
			$stmt->bind_param('ssssssi',$perfil,$status,$nome,$cpf_cnpj,$email,$telefone,$id);
			$stmt->execute();
			
			if($stmt == true){
                /*ALTERAR LINHA DE BAIXO */
                header("Location: ../../View/usuario.php?sucess");
			}else{
                /*ALTERAR LINHA DE BAIXO */
                header("Location: ../../View/usuario.php?error");
				die("Falha no Processo!");
			}
			$stmt->close();
			$this->conn->close();	
		}

        //Logar
        public function logar($email,$senha){
            $sql= "select * from $this->tabela where email = ? ";
            $stmt = $this->conn->prepare($sql);
            $stmt->bind_param('s', $email);
            $stmt->execute();
            $stmt->store_result();
			
			if($stmt->num_rows == 1){
                $stmt->bind_result($id,$perfil,$status,$nome,$cpf_cnpj,$email,$telefone,$senhaHash);
                $stmt->fetch();

                if(!password_verify($senha, $senhaHash)){
                    header('Location: ../../View/login.php?error=acessonegado');
                    return;
                }
    
                if($status=="A" && $perfil!=="0"){
                    session_start();
                    $_SESSION['logado'] = true;
                    $_SESSION['id'] = $id;
                    $_SESSION['perfil'] = $perfil;
                    $_SESSION['status'] = $status;
                
                    header("Location: ../../View/home.php");
                }else{
                    header('Location: ../../View/login.php?error=acessonegado');
                }
				
			}else{
                echo "ERROR";
                //header('Location: login.php?erro=login');
			}
			$stmt->close();
			$this->conn->close();
            
        }

        //Logar Cliente
        public function logarcliente($email,$senha){
            $sql= "select * from $this->tabela where email = ? ";
            $stmt = $this->conn->prepare($sql);
            $stmt->bind_param('s', $email);
            $stmt->execute();
            $stmt->store_result();
			
			if($stmt->num_rows == 1){
                $stmt->bind_result($id,$perfil,$status,$nome,$cpf_cnpj,$email,$telefone,$senhaHash);
                $stmt->fetch();

                if($status=="A" && $perfil=="0"){
                    $dadosCliente = array(
                    'id' => $id,
                    'perfil' => $perfil,
                    'status' => $status,
                    'nome' => $nome,
                    'cpf_cnpj' => $cpf_cnpj,
                    'email' => $email,
                    'telefone' => $telefone,
                    'senha' => $senhaHash
                    );

                    if(password_verify($senha, $senhaHash)){
                        echo json_encode($dadosCliente);
                    }
                    else{
                        echo("NULL");
                    }
                    
                }else{
                    echo("NULL");
                }
				
			}else{
                echo "ERROR";
                //header('Location: login.php?erro=login');
			}
			$stmt->close();
			$this->conn->close();
            
        }


    }
?>