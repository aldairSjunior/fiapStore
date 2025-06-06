package br.com.fiap.fiapstore.model;

import java.time.LocalDate;

public class Produto {

    private int codigo;
    private String nome;
    private double valor;
    private LocalDate dataFabricacao;
    private int quantidade;
    private Categoria categoria;

    public Produto(){

    }

    public Produto(int codigo, String nome, double valor,
                   LocalDate dataFabricacao, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.dataFabricacao = dataFabricacao;
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
