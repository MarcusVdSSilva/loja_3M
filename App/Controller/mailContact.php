<?php 
     if(!empty($_REQUEST["nome"]) && !empty($_REQUEST["email"]) && !empty($_REQUEST["cel"]) && !empty($_REQUEST["msg"])){
        $nome = $_REQUEST["nome"];
        $telefone = $_REQUEST["cel"];
        $email = $_REQUEST["email"];
        $msg = $_REQUEST["msg"];
        
        $to = "formulariodecontato@memtechnologicalsolutions.uni5.net";
        $subject = "Formulario de Contato do Site M&M S.T.";
        $body = "
            <html>
            <meta charset='utf-8'>
            <table align='center' cellpadding='0' cellspacing='0' width='600' style='border-spacing: 2px 5px;'>
            <tr>
                <td style='padding: 10px O 10px 0; font-family: sans-serif;'>
                    <h2 style='color: $00c'>Contato via formulário - M&M Soluções Tecnologicas</h2> <br>
                    <h4 style='color: 4999'>Dados Pessoais:</h4><br>
                    <p>Retemente: " . $nome . "</p>
                    <p>Telefone: ". $telefone ."</p>
                    <p>E-mail: ". $email ."</p>
                </td>
            </tr>
                <tr><td><hr></td></tr>
            <tr>
                <td style='padding: 10px O 10px 0; font-family: sans-serif; font-size: 16px;'>
                    <h4 style='color: 4999'>Mensagem:</h4>
                    ". $msg ."
                </td>
            </tr>
            </table>
            </html>
            
        ";
        
        $headers = array(
            'Content-type'=> 'text/html; charset=utf-8',
            'From' => 'contato@memtechnologicalsolutions.uni5.net',
            'Reply-To' => "$mail",
            'X-Mailer' => 'PHP/' . phpversion(),
            'Cc' => 'contato@memtechnologicalsolutions.uni5.net',
            'Bcc' => 'contato@memtechnologicalsolutions.uni5.net'
        );

        $enviarEmail = mail($to, $subject, $body, $headers);
    }
?>