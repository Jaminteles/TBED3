package filesystem;

import java.util.*;

/**
 * Classe abstrata que representa um nó no sistema de arquivos.
 * Pode ser um arquivo ou um diretório.
 * 
 * @author TBED3
 * @version 1.0
 */
public abstract class Node {
    /** Nome do nó */
    protected String name;
    
    /** Diretório pai deste nó */
    protected DirectoryNode parent;

    /**
     * Construtor do nó.
     * 
     * @param name o nome do nó
     */
    public Node(String name) {
        this.name = name;
        this.parent = null;
    }

    /**
     * Obtém o nome do nó.
     * 
     * @return o nome do nó
     */
    public String getName() { return name; }
    
    /**
     * Define um novo nome para o nó.
     * 
     * @param newName o novo nome
     */
    public void setName(String newName) { this.name = newName; }

    /**
     * Obtém o diretório pai deste nó.
     * 
     * @return o diretório pai ou null se for o raiz
     */
    public DirectoryNode getParent() { return parent; }
    
    /**
     * Define o diretório pai deste nó.
     * 
     * @param parent o diretório pai
     */
    public void setParent(DirectoryNode parent) { this.parent = parent; }

    /**
     * Obtém o caminho absoluto completo do nó.
     * 
     * @return o caminho absoluto, começando com "/"
     */
    public String getAbsolutePath() {
        if (parent == null) return "/" + name;
        return parent.getAbsolutePath() + "/" + name;
    }

    /**
     * Verifica se este nó é um diretório.
     * 
     * @return true se for um diretório, false caso contrário
     */
    public abstract boolean isDirectory();
}