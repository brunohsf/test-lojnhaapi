package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {

    public static ProdutoPojo criarProdutoComumComOValorIgualA(double valor){

        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("PRODUTO TESTE");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("Azul");

        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("COMPONENTE TESTE");
        componente.setComponenteQuantidade(1);

        List<ComponentePojo> componentes = new ArrayList<>();
        componentes.add(componente);

        produto.setComponentes(componentes);

        return produto;

    }


}
