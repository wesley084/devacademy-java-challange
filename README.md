# dev_academy_challenge
Não deu muuuito certo, mas foram dois dias de muita luta então não deixaria de postar. 
Acabei tendo a ideia de sofisticar mais no inicio, fazer mais puxado pra integração de um estoque e separar a quantidade de ser um atributo inerente do item. 
Depois de um tempo as definições do endpoints estavam um tanto diferentes e surgiu um erro de banco, depois de muito sofrer resolvi voltar a ideia basica do desafio. 
Sem começar do zero fui reaproveitando oq estava criado mas isso foi um problema, pra salvar persistia um erro e depois de 2h percebi ser erro na injeção do postman. 
No fim das contas algumas coisas funcionam mas não tudo como deveria, a etapa de testes foi a mais sacrificada, não há tempo. 

Enfim, 

*SALVAR http://localhost:8099/api/v1/pedidos/post Passar os dados no corpo da pagina

* Ao cadastrar pode se usar tanto a notação a seguir quanto SEM os VALORES TOTAIS   
* NÃO  se deve adicionar o ID do item nem do pedido durante o cadastro  
 {
         "statusPedido": "PENDENTE",
         "nomeCliente": "nomeCliente",
         "endereco": "Rua da ladeira",
         "telefone": "87878798",
         "valorTotalProdutos": 10.0,
         "taxa": 10.0,
         "valorTotal": 20.0,
         "itensPedido": [
             {
                 "descricao": "bolacha",
                 "preco": 5.0,
                 "quantidade": 2
             }
         ]
     }

* DELETAR http://localhost:8099/api/v1/pedidos/delete/{ID}
* UPDATE STATUS PEDIDO  http://localhost:8099/api/v1/pedidos/{ID}/status
* GET PEDIDOS  http://localhost:8099/api/v1/pedidos
