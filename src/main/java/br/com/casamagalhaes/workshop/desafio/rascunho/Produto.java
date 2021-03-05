package br.com.casamagalhaes.workshop.desafio.rascunho;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


/* um produto é composto por um item e a quantidade deles que o cliente irá adquirir */

@Entity
public class Produto {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Você não definiu um item válido!")
    private long itemId;

    @NotEmpty(message = "Quantidade não pode ser vazia")
    @Min(value = 1, message = "Você só pode adicionar um item ao pedido se for levar ao menos uma unidade!")
    private int quantidade;

    /*     GETTERS AND SETTERS */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    
}
