package br.com.casamagalhaes.workshop.desafio.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import br.com.casamagalhaes.workshop.desafio.enums.StatusPedido;

@Entity
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pedido")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;
    
  //  @NotEmpty(message = "Nome não pode ser vazio!")
  //  @Size(min = 5, max = 100, message = "Nome deve ter de 5 a 100 caracteres!")
    private String nomeCliente;
    
   // @NotEmpty(message = "Endereço não pode ser vazio!")
    private String endereco;
    
  //  @NotEmpty(message = "Telefone não pode ser vazio!")
    @Size(min = 6, max = 14, message = "Telefone deve possuir de 6 a 14 NÚMEROS!")
    private String telefone;
  
    private double valorTotalProdutos;
    
    private double taxa;
    
    private double valorTotal;
    
    //iria fazer apenas assinalando os ids dos itens, separando a quantidade que não é um atributo inerente do item, mas a lógica extenderia mais um poouco o tempo 
    @ElementCollection
    @OneToMany
    private List<Item> itensPedido;

    /*     GETTERS AND SETTERS    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getValorTotalProdutos() {
        return valorTotalProdutos;
    }

    public void setValorTotalProdutos(double valorTotalProdutos) {
        this.valorTotalProdutos = valorTotalProdutos;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Item> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<Item> itensPedido) {
        this.itensPedido = itensPedido;
    }
    
}
