package br.com.casamagalhaes.workshop.desafio.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.casamagalhaes.workshop.desafio.enums.StatusPedido;
import br.com.casamagalhaes.workshop.desafio.models.Item;
import br.com.casamagalhaes.workshop.desafio.models.Pedido;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PedidoControllerTest {
    @Value("${server.port}")
    private int port;

    private RequestSpecification requisicao;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    private void preperarRequisicao(){
       requisicao = new RequestSpecBuilder()
          .setBasePath("/api/v1/pedidos")
          .setPort(port)
          .setAccept(ContentType.JSON)
          .setContentType(ContentType.JSON)
          .log(LogDetail.ALL)
          .build();
    }

    @Test
     public void deveReceberOk(){
         given()
          .spec(requisicao)
        .expect()   
          .statusCode(HttpStatus.SC_OK)  
        .when()  
          .get();
     }

     @Test
     public void deveriaCriarUmProduto() throws JsonProcessingException {
        Pedido pedidoCadastrado =
        given()
          .spec(requisicao)
          .body(mapper.writeValueAsString(dadoUmPedido()))
        .when()  
          .post()
        .then()
          .statusCode(HttpStatus.SC_CREATED)
        .extract()
          .as(Pedido.class);    
        
        assertNotNull(pedidoCadastrado, "Pedido não foi cadastrado");    
        assertNotNull(pedidoCadastrado.getId(), "Id do pedido não gerado");       
     }

     @Test
     public void deveDeletarUmPedido() throws JsonProcessingException {

       Pedido pedidoCadastrado =
        given()
          .spec(requisicao)
          .body(mapper.writeValueAsString(dadoUmPedido()))
        .when()  
          .post()
        .then()
          .statusCode(HttpStatus.SC_CREATED)
        .extract()
          .as(Pedido.class);    
        
        assertNotNull(pedidoCadastrado, "pedido não foi cadastrado");    
        assertNotNull(pedidoCadastrado.getId(), "id do pedido não gerado");
        
        given()
          .spec(requisicao)
        .when()
          .delete("/delete/{id}", pedidoCadastrado.getId())
        .then() 
          .statusCode(HttpStatus.SC_NO_CONTENT);

        given()
          .spec(requisicao)
          .body(mapper.writeValueAsString(pedidoCadastrado))
        .when()
          .get("/delete/{id}", pedidoCadastrado.getId())
        .then() 
          .statusCode(HttpStatus.SC_NOT_FOUND);

     }

     private Pedido dadoUmPedido(){
        Pedido pedido = new Pedido();
        List<Item> itens = new ArrayList<>();
        double valorTotalProdutos = 0;
        for(int j=1; j<=5; j++){
            Item i = new Item();
            Long pseudoId = Long.valueOf (j);
            i.setId(pseudoId);
            i.setDescricao("descricao " + j);
            i.setPreco(0.0 + j*2);
            i.setQuantidade(j);
            valorTotalProdutos += j * 2 * j;
            itens.add(i);
        }
        pedido.setEndereco("Rua da Bala");
        pedido.setItensPedido(itens);
        pedido.setNomeCliente("Fulano de Tal");
        pedido.setStatusPedido(StatusPedido.PENDENTE);
        pedido.setTaxa(10);
        pedido.setTelefone("8825882599");
        pedido.setValorTotalProdutos(valorTotalProdutos);
        pedido.setValorTotal(valorTotalProdutos + pedido.getTaxa());
        return pedido;
     }

}
