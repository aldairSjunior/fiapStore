package br.com.fiap.fiapstore.controller;

import br.com.fiap.fiapstore.dao.CategoriaDao;
import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.factory.DaoFactory;
import br.com.fiap.fiapstore.model.Categoria;
import br.com.fiap.fiapstore.model.Produto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/produtos")
public class ProdutoServlet extends HttpServlet {

    private ProdutoDao dao;
    private CategoriaDao categoriaDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getProdutoDAO();
        categoriaDao = DaoFactory.getCategoriaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao){
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "editar":
                editar(req, resp);
                break;
            case "excluir":
                excluir(req, resp);
        }

    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int codigo = Integer.parseInt(req.getParameter("codigoExcluir"));

        try {
            dao.remover(codigo);
            req.setAttribute("mensagem", "Produto removido com sucesso!");
        }catch (DBException e){
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao remover o produto");
        }

        listar(req, resp);

    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        double valor = Double.valueOf(req.getParameter("valor"));
        int quantidade = Integer.valueOf(req.getParameter("quantidade"));
        LocalDate fabricacao = LocalDate.parse(req.getParameter("fabricacao"));
        int codigoCategoria = Integer.parseInt(req.getParameter("categoria"));

        Categoria categoria = new Categoria();
        categoria.setCodigo(codigoCategoria);

        Produto produto = new Produto(
                0,
                nome,
                valor,
                fabricacao,
                quantidade
        );
        produto.setCategoria(categoria);

        try {
            dao.cadastrar(produto);
            req.setAttribute("mensagem", "Produto cadastrado com sucesso");
        } catch (DBException e){
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar o produto");
        }
//        req.getRequestDispatcher("cadastro-produto.jsp").forward(req, resp);
        abrirFormCadastro(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(req.getParameter("codigo"));
            String nome = req.getParameter("nome");
            int quantidade = Integer.parseInt(req.getParameter("quantidade"));
            double preco = Double.parseDouble(req.getParameter("valor"));
            LocalDate fabricacao = LocalDate.parse(req.getParameter("fabricacao"));
            int codigoCategoria = Integer.parseInt(req.getParameter("categoria"));

            Categoria categoria = new Categoria();
            categoria.setCodigo(codigoCategoria);

            Produto produto = new Produto(
                    codigo, nome, preco, fabricacao, quantidade);
            produto.setCategoria(categoria);

            dao.atualizar(produto);

            req.setAttribute("mensagem", "Produto atualizado!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }
        listar(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao){
            case "listar":
                listar(req, resp);
                break;

            case "abrir-form-edicao":
                abrirForm(req, resp);
                break;

            case "abrir-form-cadastro":
                abrirFormCadastro(req, resp);
                break;
        }



    }

    private void abrirFormCadastro(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        carregarOpcoesCategoria(req);
        req.getRequestDispatcher("cadastro-produto.jsp").forward(req,resp);
    }

    private void carregarOpcoesCategoria(HttpServletRequest req){
        List<Categoria> lista = categoriaDao.listar();
        req.setAttribute("categorias", lista);
    }

    private void abrirForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("codigo"));
        Produto produto = dao.buscar(id);
        req.setAttribute("produto", produto);
        carregarOpcoesCategoria(req);
        req.getRequestDispatcher("editar-produto.jsp")
                .forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Produto> lista = dao.listar();
        req.setAttribute("produtos", lista);
        req.getRequestDispatcher("lista-produto.jsp").forward(req, resp);
    }
}
