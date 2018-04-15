/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.util.List;
import modelo.Funcionario;

/**
 *
 * @author caiq-
 */
public interface FuncionarioDao {

    public boolean salvar(Funcionario f) throws IOException, ClassNotFoundException;

    public Funcionario buscar(String cpf) throws IOException, ClassNotFoundException;

    public List<Funcionario> listar() throws IOException, ClassNotFoundException;

    public boolean deletar(Funcionario f) throws IOException, ClassNotFoundException;

    public boolean atualizar(Funcionario f) throws IOException, ClassNotFoundException;

}
