package br.com.fiap.fiapstore.teste;

import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.factory.DaoFactory;
import br.com.fiap.fiapstore.model.Produto;

import java.time.LocalDate;
import java.util.List;

public class ProdutoDaoTeste {
    public static void main(String[] args) {

        ProdutoDao dao = DaoFactory.getProdutoDAO();

        //Cadastrar um produto
        Produto produto = new Produto(
                0,
                "Mouse wireless",
                77.49,
                LocalDate.of(2024, 10, 21),
                63);
//        try {
//            dao.cadastrar(produto);
//            System.out.println("Produto cadastrado.");
//        } catch (DBException e) {
//            e.printStackTrace();
//        }

        //Buscar um produto pelo código e atualizar
//        produto = dao.buscar(1);
//        produto.setNome("Monitor LED 24P");
//        produto.setValor(891.99);
//        try {
//            dao.atualizar(produto);
//            System.out.println("Produto atualizado.");
//        } catch (DBException e) {
//            e.printStackTrace();
//        }

        //Listar os produtos
//        List<Produto> lista = dao.listar();
//        for (Produto item : lista) {
//            System.out.println(
//                    item.getNome() + " " +
//                            item.getQuantidade() + " " +
//                            item.getValor());
//        }

        //Remover um produto
//        try {
//            dao.remover(1);
//            System.out.println("Produto removido.");
//        } catch (DBException e) {
//            e.printStackTrace();
//        }
   }
}
