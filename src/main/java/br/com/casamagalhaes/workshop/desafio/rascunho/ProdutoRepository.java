package br.com.casamagalhaes.workshop.desafio.rascunho;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import br.com.casamagalhaes.workshop.desafio.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}