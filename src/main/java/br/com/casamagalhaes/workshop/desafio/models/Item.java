package br.com.casamagalhaes.workshop.desafio.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*  O item é pensado para ser a representação do produto fisico
mas não adicionando caracteristicas além das necessárias para o projeto atual.
   
    Não serão consideradas limitaçoes de estoque, pois não temos um estoque.
*/

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_item")
    private Long id;

    //@NotEmpty(message = "Descrição obrigatória!")
   // @Size(min = 4, max = 50, message = "Descrição deve ter de 4 a 50 caracteres!")
    private String descricao;

 //   @NotEmpty(message = "Preço é obrigatório!")
    private Double preco;

	//a ideia era nao colocar a quantidade aqui em item , mas acabou que fiquei sem tempo pra afzer oq queria e estou reformulando nisso mesmo
	//@NotEmpty(message = "Quantidade obrigatória!")
	private int quantidade;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Pedido pedido;

    /*     GETTERS AND SETTERS */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
    
}