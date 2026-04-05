package vendas.controller;

import vendas.dao.CategoriaDAO;
import vendas.model.Categoria;

public class CategoriaController {

    CategoriaDAO categoriaDAO = new CategoriaDAO();

    // 💾 SALVAR
    public boolean salvar(Categoria categoria) {

        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            System.out.println("Nome da categoria é obrigatório!");
            return false;
        }

        return categoriaDAO.salvar(categoria);
    }

    // 🔄 ALTERAR
    public boolean alterar(Categoria categoria) {

        if (categoria.getId() <= 0) {
            System.out.println("ID inválido!");
            return false;
        }

        return categoriaDAO.alterar(categoria);
    }

    // ❌ EXCLUIR
    public boolean excluir(int id) {

        if (id <= 0) {
            System.out.println("ID inválido!");
            return false;
        }

        return categoriaDAO.excluir(id);
    }

    // 🔍 PESQUISAR (por ID)
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