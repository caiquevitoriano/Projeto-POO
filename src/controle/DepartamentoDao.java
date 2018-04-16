/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.util.List;
import modelo.Departamento;

/** Interface Dao de departamento
 *
 * @author caiq-
 */
public interface DepartamentoDao {
    
    public boolean salvar(Departamento d) throws IOException, ClassNotFoundException;

    public Departamento buscar(String numero) throws IOException, ClassNotFoundException;

    public List<Departamento> listar() throws IOException, ClassNotFoundException;

    public boolean deletar(Departamento d) throws IOException, ClassNotFoundException;

    public boolean atualizar(Departamento d) throws IOException, ClassNotFoundException;
    
}
