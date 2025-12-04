package filesystem;

import java.util.*;

/**
 * Representa um diretório no sistema de arquivos.
 * Um diretório é um nó que pode conter outros nós (arquivos e subdiretórios).
 * 
 * @author TBED3
 * @version 1.0
 */
public class DirectoryNode extends Node {

    /** Lista de nós filhos (arquivos e subdiretórios) */
    private List<Node> children;

    /**
     * Construtor do diretório.
     * 
     * @param name o nome do diretório
     */
    public DirectoryNode(String name) {
        super(name);
        children = new ArrayList<>();
    }

    /**
     * Obtém a lista de nós filhos deste diretório.
     * 
     * @return lista contendo todos os filhos (arquivos e subdiretórios)
     */
    public List<Node> getChildren() { return children; }

    /**
     * Verifica se este nó é um diretório.
     * Um diretório sempre é um diretório.
     * 
     * @return sempre retorna true
     */
    @Override
    public boolean isDirectory() { return true; }

    /**
     * Adiciona um nó filho a este diretório.
     * Define o pai do nó adicionado como sendo este diretório.
     * 
     * @param node o nó a ser adicionado
     */
    public void addChild(Node node) {
        node.setParent(this);
        children.add(node);
    }

    /**
     * Remove um nó filho deste diretório.
     * 
     * @param node o nó a ser removido
     */
    public void removeChild(Node node) {
        children.remove(node);
    }
}
