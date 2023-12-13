<?php 
    require_once("conexao.php");
    require_once("venda.php");
    class venda_produto extends conexao{
        private $id;
        private $id_produto;
        private $id_venda;
        private $tabela = "venda_produto";

        //Getters e Setters
        public function getId(){
            return $this->id;
        }
        public function setId($id){
            $this->id = $id;
        }

        public function getId_produto(){
            return $this->id_produto;
        }
        public function setId_produto($id_produto){
            $this->id_produto = $id_produto;
        }

        public function getId_venda(){
            return $this->id_venda;
        }
        public function setId_venda($id_venda){
            $this->id_venda = $id_venda;
        }


        //Metodos
        //Cadastrar
        public function cadastrar($id_venda, $produto){
            $sql= "insert into $this->tabela(id_venda,id_produto,quantidade) values(?,?,?)";
            $stmt = $this->conn->prepare($sql);
            $stmt->bind_param('ssi', $id_venda,$produto->id, $produto->quantidade);
            $stmt->execute();
            if($stmt==true){
                
            }else{
                /*ALTERAR LINHA DE BAIXO */
                header("Location: ../View/LOCALCOLOCARAQUI.php?error");
                die("Falha no Cadastro!");
            }

            $stmt->close();
			$this->conn->close();
            
        }
		public function listar($idVenda){
			$sql = 
            "SELECT ".
            "  PRO.ID, ".
            "  PRO.NOME, ".
            "  PRO.MARCA, ".
            "  PRO.PRECO, ".
            "  PRO.FOTO64, ".
            "  VDP.QUANTIDADE ".

            "FROM $this->tabela VDP ". 

            "INNER JOIN PRODUTOS PRO ".
            "ON VDP.ID_PRODUTO = PRO.ID ". 

            "INNER JOIN VENDA VND ".
            "ON VDP.ID_VENDA = VND.ID ".
            "WHERE VND.ID =$idVenda ";
            
			$result = $this->conn->query($sql);
			
			if($result == true){
				return $result;
                
			}else{
				die("Falha na consulta venda produto!");
			}

            $this->conn->close();
		}
        

    }
?>