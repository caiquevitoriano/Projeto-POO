/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.util.List;
import modelo.Usuario;

/**
 *
 * @author caiq-
 */
public interface UsuarioDao {

    public boolean salvar(Usuario u) throws IOException, ClassNotFoundException;

    public Usuario buscar(String nome) throws IOException, ClassNotFoundException;

    public List<Usuario> listar() throws IOException, ClassNotFoundException;

    public boolean deletar(Usuario u) throws IOException, ClassNotFoundException;

    public boolean atualizar(Usuario u) throws IOException, ClassNotFoundException;

    public Usuario userLogin(String nome, String senha)throws IOException, ClassNotFoundException;

}
