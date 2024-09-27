package model;

public class Item {
    private int codigo;
    private String descricao;
    private double valor;
    private double impostoCalculado;
    private double total;

    public Item(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.impostoCalculado = 0.0;
        this.total = 0.0;
    }

    public double getValor() {
        return valor;
    }

    public void setImpostoCalculado(double impostoCalculado) {
        this.impostoCalculado = impostoCalculado;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return String.format("Item{codigo=%d, descricao='%s', valor=%.2f, impostoCalculado=%.2f, total=%.2f}",
                codigo, descricao, valor, impostoCalculado, total);
    }
}
