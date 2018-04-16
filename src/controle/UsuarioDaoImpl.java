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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 * Classe de implementação do Dao Usuario
 *
 * @author caiq-
 */
public final class UsuarioDaoImpl implements UsuarioDao {

    private final File file;

    /**
     * Metodo de persistencia
     *
     * @author caiq-
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public UsuarioDaoImpl() throws IOException, ClassNotFoundException {

        file = new File("usuarios.bin");

        if (!file.exists()) {
            file.createNewFile();
        }

        try {
            this.salvar(new Usuario("admin", "admin"));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método utilizado para salvar as informações de um Usuario.
     *
     * @param u
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public boolean salvar(Usuario u) throws IOException, ClassNotFoundException {

        if (buscar(u.getNome()) == null) {
            List<Usuario> usuarios = listar();

            if (usuarios.add(u)) {
                atualizarArquivos(usuarios);
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
     * @param nome
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public Usuario buscar(String nome) throws IOException, ClassNotFoundException {

        List<Usuario> usuarios;
        usuarios = listar();

        for (Usuario u : usuarios) {
            if (u.getNome().equals(nome)) {
                return u;
            }
        }

        return null;
    }

    /**
     * Método utilizado para listar as informações dos Usuarios.
     *
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public List<Usuario> listar() throws IOException, ClassNotFoundException {

        if (file.length() > 0) {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

            return (List<Usuario>) in.readObject();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Método para deletar um Usuario.
     *
     * @param u
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public boolean deletar(Usuario u) throws IOException, ClassNotFoundException {
        List<Usuario> usuarios = listar();

        if (usuarios.remove(u)) {
            atualizarArquivos(usuarios);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para atulizar um Usuario.
     *
     * @param u
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public boolean atualizar(Usuario u) throws IOException, ClassNotFoundException {

        List<Usuario> usuarios = listar();

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNome().equals(u.getNome())) {
                usuarios.set(i, u);
                atualizarArquivos(usuarios);
                return true;
            }
        }

        return false;
    }

    /**
     * Método para fazer login de um Usuario.
     *
     * @param nome
     * @param senha
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public Usuario userLogin(String nome, String senha) throws IOException, ClassNotFoundException {

        List<Usuario> usuarios = listar();

        for (Usuario u : usuarios) {
            if (u.getNome().equals(nome) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;

    }

    /**
     * Método para atulizar os Usuarios.
     *
     */
    private void atualizarArquivos(List<Usuario> usuarios) throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

        out.writeObject(usuarios);
        out.close();
    }

}
