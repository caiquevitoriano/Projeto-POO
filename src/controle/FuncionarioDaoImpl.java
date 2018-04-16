/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import modelo.Funcionario;

/**
 * Classe de implementação do Dao Funcionario
 *
 * @author caiq-
 */
public class FuncionarioDaoImpl implements FuncionarioDao {

    private final File file;

    /**
     * Metodo de persistencia
     *
     * @author caiq-
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public FuncionarioDaoImpl() throws IOException, ClassNotFoundException {

        file = new File("funcionarios.bin");

        if (!file.exists()) {
            file.createNewFile();
        }

    }

    /**
     * Método utilizado para salvar as informações de um Funcionario.
     *
     * @param f
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public boolean salvar(Funcionario f) throws IOException, ClassNotFoundException {

        if (buscar(f.getCpf()) == null) {
            List<Funcionario> funcionarios = listar();

            if (funcionarios.add(f)) {
                atualizarArquivos(funcionarios);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Método utilizado para buscar as informações de um Funcionario.
     *
     * @param cpf
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public Funcionario buscar(String cpf) throws IOException, ClassNotFoundException {

        List<Funcionario> funcionarios;
        funcionarios = listar();

        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }

        return null;
    }

    /**
     * Método utilizado para listar as informações dos Funcionario.
     *
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public List<Funcionario> listar() throws IOException, ClassNotFoundException {

        if (file.length() > 0) {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

            return (List<Funcionario>) in.readObject();
        } else {
            return new ArrayList<>();
        }

    }

    /**
     * Método para deletar um Usuario.
     *
     * @param f
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public boolean deletar(Funcionario f) throws IOException, ClassNotFoundException {
        List<Funcionario> funcionarios = listar();

        if (funcionarios.remove(f)) {
            atualizarArquivos(funcionarios);
            return true;
        } else {
            return false;
        }
    }
/**
     * Método para atulizar um Funcionario.
     *
     * @param f     
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public boolean atualizar(Funcionario f) throws IOException, ClassNotFoundException {
        List<Funcionario> funcionarios = listar();

        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getCpf().equals(f.getCpf())) {
                funcionarios.set(i, f);
                atualizarArquivos(funcionarios);
                return true;
            }
        }

        return false;
    }
 /**
     * Método para atulizar os Funcionarios.
     *
     */
    private void atualizarArquivos(List<Funcionario> funcionarios) throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

        out.writeObject(funcionarios);
        out.close();
    }

}
