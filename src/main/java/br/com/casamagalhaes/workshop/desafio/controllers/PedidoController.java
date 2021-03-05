package br.com.casamagalhaes.workshop.desafio.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.casamagalhaes.workshop.desafio.enums.StatusPedido;
import br.com.casamagalhaes.workshop.desafio.models.Item;
import br.com.casamagalhaes.workshop.desafio.models.Pedido;
import br.com.casamagalhaes.workshop.desafio.services.ItemService;
import br.com.casamagalhaes.workshop.desafio.services.PedidoService;

@RestController
@RequestMapping(path = "/api/v1/pedidos")
 // nao aceita // consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService = new PedidoService();
    @Autowired
    private ItemService itService = new ItemService();
    
    @GetMapping({"/",""})
    public List<Pedido> listarTodos(){
        return pedidoService.findAll();
    }
    ////// salvar, bandido que faz a gente perder todo o tempo ////////
    @PostMapping("/post")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Pedido save(@Valid @RequestBody Pedido pedido){
        itService.saveAll(pedido.getItensPedido());
        return pedidoService.save(pedido);
    } 
    ////// update, recebe um pedido, identifica o id e substitui ///////// 
    @PutMapping("/put/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Pedido updatePedido(@RequestBody Pedido pedido, @PathVariable Long id){
        return pedidoService.updatePedido(id, pedido);
    }
    ////// delete, recebendo id pela URI
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id){
        pedidoService.delete(id);
    }
    /////////// atualização status de entrega do pedido //////////
    @PutMapping("/{id}/status")
    @ResponseStatus(code = HttpStatus.OK)
    public Pedido updateStatusEntrega(@RequestBody StatusPedido novoStatus, @PathVariable Long id){
        return pedidoService.updateStatusEntrega(id, novoStatus);
    }

    /////// begin ////////// Exceções ///////////// 
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void notFound(){}
    
    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public void notSupported(){}
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            
        });
        return errors;
    } 
    /////// end ////////// Exceções ///////////// 
    
    //// begin /////////// teste salvar avulso ///////// desconsiderar (retirar NotEmpty, ou excepta) ///////////////
    @PostMapping({"/TestarSalvar"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public Pedido salvarteste(){
        
        Item it = new Item();
        it.setDescricao("bolacha");
        it.setPreco(5.0);
        it.setQuantidade(2);
        itService.save(it);
        
        Pedido pedido = new Pedido();
        double valorTotalProdutos = 0.0;
        pedido.setNomeCliente("nomeCliente");
        pedido.setTelefone("87878798");
        pedido.setEndereco("Rua da ladeira");
        pedido.setItensPedido(itService.findAll());
        pedido.setTaxa(10);
        pedido.setStatusPedido(StatusPedido.PENDENTE);
        for (Item i : pedido.getItensPedido()) {
            valorTotalProdutos += (i.getPreco()*it.getQuantidade());
        }
        pedido.setValorTotalProdutos(valorTotalProdutos);
        pedido.setValorTotal(valorTotalProdutos+pedido.getTaxa());
        
        return pedidoService.save(pedido);
    }
    ///// end ////////// teste salvar avulso ///////// desconsiderar ///////////////
    
    
    //// begin //////// não funcionando por algum motivo //////// guardar pra dps //////////////
    /*    
    @RequestMapping(method = RequestMethod.POST, path = "/save")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void save(@RequestBody Pedido pedido){
        pedidoService.save(pedido);
    }
    
    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Pedido salvar(@Valid @RequestBody Pedido pedido){
        pedido.setEndereco("casa");
        return pedidoService.save(pedido);
    } */
    //// end //////// não esta funcionando por algum motivo //////////////////////
}
