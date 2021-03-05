package br.com.casamagalhaes.workshop.desafio.rascunho;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casamagalhaes.workshop.desafio.models.Item;
//import br.com.casamagalhaes.workshop.desafio.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;
    
    public Produto findById(Long id) {        
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    
    public List<Produto> findAll() {
        return repository.findAll();
    }
    public Produto save(Produto item) {
        return repository.saveAndFlush(item);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
}
