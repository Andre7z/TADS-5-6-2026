package avaliacao1.controller;

import avaliacao1.dao.CategoriaDAO;
import avaliacao1.model.Categoria;

public class CategoriaController {

    CategoriaDAO categoriaDAO = new CategoriaDAO();

    public boolean salvar(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            System.out.println("Nome da categoria é obrigatório!");
            return false;
        }

        return categoriaDAO.salvar(categoria);
    }

    public boolean alterar(Categoria categoria) {

        if (categoria.getId() <= 0) {
            System.out.println("ID inválido!");
            return false;
        }

        return categoriaDAO.alterar(categoria);
    }

    public boolean excluir(int id) {

        if (id <= 0) {
            System.out.println("ID inválido!");
            return false;
        }

        return categoriaDAO.excluir(id);
    }

    public Categoria pesquisar(int id) {

        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }

        Categoria categoria = categoriaDAO.pesquisar(id);

        if (categoria == null) {
            System.out.println("Categoria não encontrada!");
        }

        return categoria;
    }
}