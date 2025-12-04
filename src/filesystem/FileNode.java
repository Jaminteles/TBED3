package filesystem;

/**
 * Representa um arquivo no sistema de arquivos.
 * Um arquivo é um nó folha que contém dados com um tamanho específico.
 * 
 * @author TBED3
 * @version 1.0
 */
public class FileNode extends Node {

    /** Tamanho do arquivo em bytes */
    private int size;

    /**
     * Construtor do arquivo.
     * 
     * @param name o nome do arquivo
     * @param size o tamanho do arquivo em bytes
     */
    public FileNode(String name, int size) {
        super(name);
        this.size = size;
    }

    /**
     * Obtém o tamanho do arquivo.
     * 
     * @return o tamanho em bytes
     */
    public int getSize() { return size; }

    /**
     * Verifica se este nó é um diretório.
     * Um arquivo nunca é um diretório.
     * 
     * @return sempre retorna false
     */
    @Override
    public boolean isDirectory() { return false; }
}