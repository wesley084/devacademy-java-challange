package br.com.casamagalhaes.workshop.desafio.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casamagalhaes.workshop.desafio.models.Item;
import br.com.casamagalhaes.workshop.desafio.repository.ItemRepository;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository repository;

    public Item findById(Long id) {        
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

	public List<Item> findAll() {
		return repository.findAll();
	}
    public Item save(Item item) {
        return repository.save(item);
    }
    public List<Item> saveAll(List<Item>itens){
        return repository.saveAll(itens);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
