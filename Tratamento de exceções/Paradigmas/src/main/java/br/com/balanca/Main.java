package br.com.balanca;

import br.com.balanca.exceptions.*;
import br.com.balanca.models.Produto;
import br.com.balanca.factory.BalancaFactory;
import br.com.balanca.interfaces.IBalanca;
import br.com.balanca.enums.TipoBalanca;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ExportacaoFalhouException {
        try {
            // Criando produtos
            Produto produto1 = new Produto(276, "QUEIJO GRUYERE KG", "9", 21.99);
            Produto produto2 = new Produto(288, "QUEIJO PROVOLETE KG", "9", 12.29);

            List<Produto> produtos = new ArrayList<>();
            produtos.add(produto1);
            produtos.add(produto2);

            // Selecionando balanças e exportando produtos
            IBalanca balancaFilizola = BalancaFactory.getBalanca(TipoBalanca.FILIZOLA_SMART);
            balancaFilizola.exportar(produtos, "C:\\Users\\VenicioLira\\Desktop\\Nova pasta (2)\\teste");

            IBalanca balancaToledo = BalancaFactory.getBalanca(TipoBalanca.TOLEDO_MGV6);
            balancaToledo.exportar(produtos, "C:\\Users\\VenicioLira\\Desktop\\Nova pasta (2)\\teste");

            IBalanca balancaUrano = BalancaFactory.getBalanca(TipoBalanca.URANO_INTEGRA);
            balancaUrano.exportar(produtos, "C:\\Users\\VenicioLira\\Desktop\\Nova pasta (2)\\teste");

            System.out.println("Arquivos gerados com sucesso!");

        } catch (CodigoProdutoInvalidoException | PrecoProdutoInvalidoException | TipoProdutoInvalidoException e) {
            System.err.println("Erro na configuração do produto: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
}
