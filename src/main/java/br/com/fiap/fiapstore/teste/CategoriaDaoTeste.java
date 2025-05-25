package br.com.fiap.fiapstore.teste;

import br.com.fiap.fiapstore.dao.CategoriaDao;
import br.com.fiap.fiapstore.factory.DaoFactory;
import br.com.fiap.fiapstore.model.Categoria;

import java.util.List;

public class CategoriaDaoTeste {

    public static void main(String[] args) {
        CategoriaDao dao = DaoFactory.getCategoriaDAO();

        List<Categoria> lista = dao.listar();
        for (Categoria categoria : lista) {
            System.out.println(categoria.getCodigo() + " " + categoria.getNome());
        }
    }
}