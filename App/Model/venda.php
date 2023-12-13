<?php 
    require_once("conexao.php");
    require_once("produto.php");
    class venda extends conexao{
        private $id;
        private $valor;
        private $data;
        private $produto;
        private $tabela = "venda";

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
			$sql = "SELECT 
            v.id as id_venda,
            v.valor,
            v.data,
            u.nome as nome_usuario,
            p.nome as nome_produto
            FROM 
                venda v
            JOIN 
                usuario u ON v.id_usuario = u.id
            JOIN 
                venda_produto vp ON v.id = vp.id_venda
            JOIN 
                produto p ON vp.id_produto = p.id
            ORDER BY 
                v.data DESC
            ";
        
            $result = $this->conn->query($sql);
			
			if($result == true){
				return $result;
                
			}else{
				die("Falha na consulta!");
			}

            $this->conn->close();
        }

        //Consulta no banco vendas do cliente
		public function listarVendaCliente($idCliente){
			$sql = "SELECT 
            v.id as id_venda,
            v.valor,
            v.data,
            u.nome as nome_usuario,
            p.nome as nome_produto
        FROM 
            venda v
        JOIN 
            usuario u ON v.id_usuario = u.id
        JOIN 
            venda_produto vp ON v.id = vp.id_venda
        JOIN 
            produto p ON vp.id_produto = p.id
        WHERE 
            v.id_usuario = $idCliente
        ORDER BY 
            v.data DESC;
        ";
			$result = $this->conn->query($sql);
			
			if($result == true){
				return $result;
                
			}else{
				die("Falha na consulta!");
			}

            $this->conn->close();
		}

        //Cadastrar
        public function cadastrar($valor,$id_usuario, $produtos){
            $sql= "insert into $this->tabela(valor,id_usuario) values(?,?)";
            $stmt = $this->conn->prepare($sql);
            $stmt->bind_param('ss', $valor,$id_usuario);
            $stmt->execute();
            if($stmt==true){
                $id = $this->conn->insert_id;
                require_once("venda_produto.php");

                //SALVANDO NO BANCO
                
                foreach($produtos as $item) {
                    $venda_produto = new Venda_produto();
                    $venda_produto->cadastrar($id, $item);
                }
                
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