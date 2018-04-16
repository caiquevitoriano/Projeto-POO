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
import modelo.Produto;

/**
 * Classe de implementação do Dao Produto
 *
 * @author caiq-
 */
public class ProdutoDaoImpl implements ProdutoDao {

    private final File file;

    /**
     * Metodo de persistencia
     *
     * @author caiq-
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public ProdutoDaoImpl() throws IOException, ClassNotFoundException {

        file = new File("produtos.bin");

        if (!file.exists()) {
            file.createNewFile();
        }

    }

    /**
     * Método utilizado para salvar as informações de um Produto.
     *
     * @param p
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public boolean salvar(Produto p) throws IOException, ClassNotFoundException {
        if (buscar(p.getCodigo()) == null) {
            List<Produto> produtos = listar();

            if (produtos.add(p)) {
                atualizarArquivos(produtos);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Método utilizado para buscar as informações de um Produto.
     *
     * @param codigo
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public Produto buscar(String codigo) throws IOException, ClassNotFoundException {

        List<Produto> produtos;
        produtos = listar();

        for (Produto p : produtos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }

        return null;
    }

    /**
     * Método utilizado para listar as informações dos Produto.
     *
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public List<Produto> listar() throws IOException, ClassNotFoundException {
        if (file.length() > 0) {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

            return (List<Produto>) in.readObject();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Método para deletar um Produto.
     *
     * @param p
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public boolean deletar(Produto p) throws IOException, ClassNotFoundException {
        List<Produto> produtos = listar();

        if (produtos.remove(p)) {
            atualizarArquivos(produtos);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para atulizar um Produto.
     *
     * @param p
     * @return
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public boolean atualizar(Produto p) throws IOException, ClassNotFoundException {
        List<Produto> produtos = listar();

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo().equals(p.getCodigo())) {
                produtos.set(i, p);
                atualizarArquivos(produtos);
                return true;
            }
        }

        return false;
    }

    /**
     * Método para atulizar os Produtos.
     *
     */
    private void atualizarArquivos(List<Produto> produtos) throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

        out.writeObject(produtos);
        out.close();
    }

}
