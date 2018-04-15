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
 *
 * @author caiq-
 */
public class FuncionarioDaoImpl implements FuncionarioDao {

    private final File file;

    public FuncionarioDaoImpl() throws IOException, ClassNotFoundException {

        file = new File("funcionarios.bin");

        if (!file.exists()) {
            file.createNewFile();
        }

    }

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

    @Override
    public List<Funcionario> listar() throws IOException, ClassNotFoundException {

        if (file.length() > 0) {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

            return (List<Funcionario>) in.readObject();
        } else {
            return new ArrayList<>();
        }

    }

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

    private void atualizarArquivos(List<Funcionario> funcionarios) throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

        out.writeObject(funcionarios);
        out.close();
    }

}
