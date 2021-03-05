package br.com.casamagalhaes.workshop.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.casamagalhaes.workshop.desafio.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}