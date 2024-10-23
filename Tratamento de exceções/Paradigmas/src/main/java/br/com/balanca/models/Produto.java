package br.com.balanca.models;

import br.com.balanca.exceptions.CodigoProdutoInvalidoException;
import br.com.balanca.exceptions.PrecoProdutoInvalidoException;
import br.com.balanca.exceptions.TipoProdutoInvalidoException;

public class Produto {
    private int codigo;
    private String descricao;
    private String tipo;
    private double valor;

    public Produto() {}

    public Produto(int codigo, String descricao, String tipo, double valor) throws CodigoProdutoInvalidoException, TipoProdutoInvalidoException, PrecoProdutoInvalidoException {
        setCodigo(codigo);
        setDescricao(descricao);
        setTipo(tipo);
        setValor(valor);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) throws CodigoProdutoInvalidoException {
        if (codigo <= 0) {
            throw new CodigoProdutoInvalidoException("O código do produto deve ser maior que zero.");
        }
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) throws TipoProdutoInvalidoException {
        if (tipo == null || tipo.isEmpty()) {
            throw new TipoProdutoInvalidoException("O tipo do produto não pode ser vazio ou nulo.");
        }
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) throws PrecoProdutoInvalidoException {
        if (valor <= 0) {
            throw new PrecoProdutoInvalidoException("O valor do produto deve ser maior que zero.");
        }
        this.valor = valor;
    }
}
