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
import modelo.Departamento;

/**
 *
 * @author caiq-
 */
public class DepartamentoDaoImpl implements DepartamentoDao {

    private final File file;

    public DepartamentoDaoImpl() throws IOException {

        file = new File("departamentos.bin");

        if (!file.exists()) {
            file.createNewFile();
        }
    }

    @Override
    public boolean salvar(Departamento d) throws IOException, ClassNotFoundException {

        if (buscar(d.getNumero()) == null) {
            List<Departamento> departamentos = listar();

            if (departamentos.add(d)) {
                atualizarArquivos(departamentos);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    @Override
    public Departamento buscar(String numero) throws IOException, ClassNotFoundException {
        List<Departamento> departamentos;
        departamentos = listar();

        for (Departamento d : departamentos) {
            if (d.getNumero().equals(numero)) {
                return d;
            }
        }

        return null;
    }

    @Override
    public List<Departamento> listar() throws IOException, ClassNotFoundException {
        if (file.length() > 0) {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

            return (List<Departamento>) in.readObject();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean deletar(Departamento d) throws IOException, ClassNotFoundException {
        List<Departamento> departamentos = listar();

        if (departamentos.remove(d)) {
            atualizarArquivos(departamentos);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean atualizar(Departamento d) throws IOException, ClassNotFoundException {
        List<Departamento> departamentos = listar();

        for (int i = 0; i < departamentos.size(); i++) {
            if (departamentos.get(i).getNumero().equals(d.getNumero())) {
                departamentos.set(i, d);
                atualizarArquivos(departamentos);
                return true;
            }
        }

        return false;
    }

    private void atualizarArquivos(List<Departamento> departamentos) throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

        out.writeObject(departamentos);
        out.close();
    }

}
