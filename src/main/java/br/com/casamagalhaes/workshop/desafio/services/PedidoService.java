package br.com.casamagalhaes.workshop.desafio.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.casamagalhaes.workshop.desafio.enums.StatusPedido;
import br.com.casamagalhaes.workshop.desafio.models.Item;
import br.com.casamagalhaes.workshop.desafio.models.Pedido;
import br.com.casamagalhaes.workshop.desafio.repository.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository repository;
    
    public List<Pedido> findAll(){
        return repository.findAll();
    }
    
    ///////// calcula o valor total dos itens e do pedido por completo e salva o pedido ////////
    public Pedido save(Pedido pedido) {
        double valorTotalProdutos = 0;
        for (Item item : pedido.getItensPedido())
        {
            valorTotalProdutos += item.getPreco()*item.getQuantidade();
        }
        pedido.setValorTotalProdutos(valorTotalProdutos);
        pedido.setValorTotal(valorTotalProdutos+pedido.getTaxa());
        return repository.save(pedido);
    }
    
    public void delete(Long id){
        if(!repository.existsById(id))
            throw new EntityNotFoundException("Pedido id: " + id + "nao existe");
        repository.deleteById(id);
    }
    
    public Pedido saveAndFlush(Pedido pedido){
        return repository.saveAndFlush(pedido);
    }

    public Pedido updatePedido(Long id, Pedido pedido) {
        if (repository.existsById(id)) {
            if (id.equals(pedido.getId()))
                return repository.saveAndFlush(pedido);
            else
                throw new UnsupportedOperationException("Id informado diferente do pedido!");
        } else
        throw new EntityNotFoundException("Pedido id: " + pedido.getId());
    }
    /////////// recebe um id de um pedido e um novo status, altera e devolve o pedido com novo status ///////
    public Pedido updateStatusEntrega(Long id, StatusPedido novoStatus) {
        if (repository.existsById(id)) {
            Pedido target = repository.getOne(id);
            StatusPedido antigoStatus = target.getStatusPedido();
            ///////// Se o status novo é CANCELADO, o antigo nao pode ser EM_ROTA, ENTREGUE ou CANCELADO ////////
            if (((novoStatus == StatusPedido.CANCELADO) && 
            ((antigoStatus == StatusPedido.EM_ROTA)||(antigoStatus == StatusPedido.ENTREGUE)||(antigoStatus==StatusPedido.CANCELADO))))
            { 
                throw new UnsupportedOperationException("Status não pode ser alterado para CANCELADO no momento...");
            }
            //////// Se o estado novo é em rota, e o anterior nao seja pronto, que venha uma exceção ///////
            else if((novoStatus == StatusPedido.EM_ROTA) && !(antigoStatus == StatusPedido.PRONTO))
            {
                throw new UnsupportedOperationException("Status não pode ser alterado para EM_ROTA sem estar status PRONTO...");
            }
            //////// Se o estado novo é ENTREGUE, e o anterior nao seja EM_ROTA, que venha uma exceção ///////
            else if((novoStatus == StatusPedido.ENTREGUE) && !(antigoStatus == StatusPedido.EM_ROTA)){
                throw new UnsupportedOperationException("Status não pode ser alterado para ENTREGUE sem estar status EM_ROTA...");
            }
            //////// Se nenhuma regra de negocio for transgredida, realize a alteração ////////
            else
            {
                target.setStatusPedido(novoStatus);
                return repository.saveAndFlush(target);
            }
            
        } 
        /////// Se não existe pedido com o ID informado, retorne uma exceção
        else{
            throw new EntityNotFoundException("Pedido id: " + id +" não encontrado");
        }
    }
    
    public Page<Pedido> listarTodosPaginado(Integer numeroPagina, Integer tamanhoPagina){
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return repository.findAll(pageable);
    }
    
    
}
