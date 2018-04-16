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
 * Classe de implementação do Dao Departamnto
 *
 * @author caiq-
 */
public class DepartamentoDaoImpl implements DepartamentoDao {

    private final File file;

    /**
     * Metodo de persistencia
     *
     * @author caiq-
     * @throws java.io.IOException
     */
    public DepartamentoDaoImpl() throws IOException {

        file = new File("departamentos.bin");

        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Método utilizado para salvar as informações de um Departamento.
     *
     * @param d
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
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

    /**
     * Método utilizado para buscar as informações de um Usuario.
     *
     * @param numero
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
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

    /**
     * Método utilizado para listar as informações dos Departamento.
     *
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public List<Departamento> listar() throws IOException, ClassNotFoundException {
        if (file.length() > 0) {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

            return (List<Departamento>) in.readObject();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Método para deletar um Usuario.
     *
     * @param d
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
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

    /**
     * Método para atulizar um Departamento.
     *
     * @param d
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
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

    /**
     * Método para atulizar os Departamentos.
     *
     */
    private void atualizarArquivos(List<Departamento> departamentos) throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

        out.writeObject(departamentos);
        out.close();
    }

}
