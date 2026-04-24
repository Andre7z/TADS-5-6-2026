package avaliacao1.controller;

import avaliacao1.dao.CategoriaDAO;
import avaliacao1.model.Categoria;

public class CategoriaController {
    CategoriaDAO categoriaDAO = new CategoriaDAO();

    public boolean salvar(Categoria categoria) {
        return categoriaDAO.salvar(categoria);
    }

    public boolean alterar(Categoria categoria) {
        return categoriaDAO.alterar(categoria);
    }

    public boolean excluir(int id) {

        return categoriaDAO.excluir(id);
    }
}