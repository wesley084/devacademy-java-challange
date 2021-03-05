package br.com.casamagalhaes.workshop.desafio.rascunho;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.casamagalhaes.workshop.desafio.enums.StatusPedido;
import br.com.casamagalhaes.workshop.desafio.models.Item;
import br.com.casamagalhaes.workshop.desafio.models.Pedido;
//import br.com.casamagalhaes.workshop.desafio.repository.ItemRepository;
import br.com.casamagalhaes.workshop.desafio.repository.PedidoRepository;

@Configuration
public class AdicionaPedidos {

/* 

    ItemService itService = new ItemService();

    CommandLineRunner initBD(ItemRepository repository){
        return args ->{
            Item it;
            for (int i=0; i<20; i++){
                it = new Item();
                it.setDescricao("descricao" + i);
                it.setPreco(10.0+i);

                repository.saveAndFlush(it);
            }
        };
    }

    CommandLineRunner initBD(PedidoRepository repository){
        return args ->{
            Pedido p;
            for (int i=0; i<20; i++){

                // preenche dados do pedido 
                p = new Pedido();
                p.setEndereco("endereco "+i);
                p.setNomeCliente("nomeCliente "+i);
                p.setStatusPedido(StatusPedido.PENDENTE);
                p.setTaxa(10.0);
                p.setTelefone("tele " +i);
                
                // pega itens e seus respectivos valores pra adicionar aos pedidos 
                List<Long> itemIds = List.of(); 
                double valorTotalProdutos = 0;
                double valorTotal = 0;
                for (Item item : itService.findAll()) {
                    if (item.getId() <= i){
                        itemIds.add(item.getId());
                        valorTotalProdutos += item.getPreco(); 
                    }
                }
                valorTotal = valorTotalProdutos + p.getTaxa();
                p.setValorTotalProdutos(valorTotalProdutos);
                p.setValorTotal(valorTotal);

                repository.saveAndFlush(p);
            }
        };
    }
    
    
     */
}
