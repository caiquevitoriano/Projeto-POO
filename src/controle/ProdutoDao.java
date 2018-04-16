/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.util.List;
import modelo.Produto;

/**
 *
 * @author caiq-
 */
public interface ProdutoDao {
    
    public boolean salvar(Produto p) throws IOException, ClassNotFoundException;

    public Produto buscar(String codigo) throws IOException, ClassNotFoundException;

    public List<Produto> listar() throws IOException, ClassNotFoundException;

    public boolean deletar(Produto p) throws IOException, ClassNotFoundException;

    public boolean atualizar(Produto p) throws IOException, ClassNotFoundException;

    
    
}
