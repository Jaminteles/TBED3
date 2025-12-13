package filesystem;

import java.util.*;

/**
 * Gerenciador do sistema de arquivos.
 * Implementa operações básicas como criar, deletar, renomear e listar
 * arquivos e diretórios.
 * 
 * @author TBED3
 * @version 1.0
 */
public class FileSystem {

    /** Nó raiz do sistema de arquivos */
    private DirectoryNode root;

    /**
     * Construtor do sistema de arquivos.
     * Inicializa com um diretório raiz vazio.
     */
    public FileSystem() {
        root = new DirectoryNode("root");
    }

    /**
     * Obtém o diretório raiz do sistema de arquivos.
     * 
     * @return o nó raiz
     */
    public DirectoryNode getRoot() { return root; }

    /**
     * Navega até um diretório específico usando seu caminho.
     * 
     * @param path o caminho do diretório (ex: "/root/pasta1/pasta2")
     * @return o DirectoryNode encontrado, ou null se o caminho for inválido
     */
    public DirectoryNode navigateTo(String path) {
        String[] parts = path.split("/");
        DirectoryNode current = root;

        for (String p : parts) {
            if (p.isEmpty() || p.equals("root")) continue;

            Optional<Node> found = current.getChildren().stream()
                    .filter(n -> n.getName().equals(p) && n.isDirectory())
                    .findFirst();

            if (found.isEmpty()) return null;

            current = (DirectoryNode) found.get();
        }
        return current;
    }

    /**
     * Cria um novo diretório dentro de um diretório pai.
     * 
     * @param path o caminho do diretório pai
     * @param name o nome do novo diretório
     */
    public void createDirectory(String path, String name) {
        DirectoryNode parent = navigateTo(path);
        if (parent != null) {
            parent.addChild(new DirectoryNode(name));
        }
    }

    /**
     * Cria um novo arquivo dentro de um diretório.
     * 
     * @param path o caminho do diretório pai
     * @param name o nome do novo arquivo
     * @param size o tamanho do arquivo em bytes
     */
    public void createFile(String path, String name, int size) {
        DirectoryNode parent = navigateTo(path);
        if (parent != null) {
            parent.addChild(new FileNode(name, size));
        }
    }

    /**
     * Renomeia um arquivo ou diretório.
     * 
     * @param path o caminho completo do nó a ser renomeado
     * @param newName o novo nome
     * @return true se renomeado com sucesso, false caso contrário
     */
    public boolean rename(String path, String newName) {
        Node target = findNode(path);
        if (target == null) return false;
        target.setName(newName);
        return true;
    }

    /**
     * Remove um arquivo ou diretório do sistema de arquivos.
     * 
     * @param path o caminho completo do nó a ser removido
     * @return true se removido com sucesso, false caso contrário
     */
    public boolean delete(String path) {
        Node node = findNode(path);
        if (node == null || node.getParent() == null) return false;

        node.getParent().removeChild(node);
        return true;
    }

    /**
     * Lista o conteúdo de um diretório.
     * Exibe todos os arquivos e subdiretórios contidos.
     * 
     * @param path o caminho do diretório a ser listado
     */
    public void listDirectory(String path) {
        DirectoryNode dir = navigateTo(path);
        if (dir != null) {
            for (Node c : dir.getChildren()) {
                System.out.println((c.isDirectory() ? "[DIR] " : "[FILE] ") + c.getName());
            }
        }
    }

    /**
     * Encontra um nó específico pelo seu caminho completo.
     * 
     * @param path o caminho completo do nó
     * @return o nó encontrado, ou null se não existir
     */
    public Node findNode(String path) {
        if (path.equals("/root")) return root;

        String[] parts = path.split("/");
        DirectoryNode current = root;

        for (int i = 1; i < parts.length - 1; i++) {
            String part = parts[i];

            if (part.equals("root") || part.isEmpty()) continue; // <-- ADICIONE ISTO

                Optional<Node> found = current.getChildren().stream()
                .filter(n -> n.getName().equals(part) && n.isDirectory())
                .findFirst();

        if (found.isEmpty()) return null;
        current = (DirectoryNode) found.get();
}


        String target = parts[parts.length - 1];
        return current.getChildren().stream()
                .filter(n -> n.getName().equals(target))
                .findFirst().orElse(null);
    }

    /**
     * Busca por arquivos ou diretórios com um nome específico.
     * Realiza uma busca recursiva em toda a árvore do sistema de arquivos.
     * 
     * @param name o nome do nó a ser procurado
     */
    public void searchByName(String name) {
        searchRecursive(root, name);
    }

    /**
     * Realiza a busca recursiva de um nó por nome.
     * 
     * @param node o nó atual sendo verificado
     * @param name o nome do nó a ser procurado
     */
    private void searchRecursive(Node node, String name) {
        if (node.getName().equals(name))
            System.out.println(node.getAbsolutePath());

        if (node.isDirectory()) {
            for (Node child : ((DirectoryNode) node).getChildren()) {
                searchRecursive(child, name);
            }
        }
    }

    /**
     * Exibe a árvore completa do sistema de arquivos.
     * Mostra a estrutura hierárquica de diretórios e arquivos.
     */
    public void printTree() {
        printRecursive(root, 0);
    }

    /**
     * Realiza a impressão recursiva da árvore de diretórios.
     * 
     * @param node o nó atual sendo impresso
     * @param depth a profundidade do nó (para indentação)
     */
    private void printRecursive(Node node, int depth) {
        System.out.println("  ".repeat(depth) + (node.isDirectory() ? "[DIR] " : "[FILE] ") + node.getName());
        if (node.isDirectory()) {
            for (Node child : ((DirectoryNode) node).getChildren()) {
                printRecursive(child, depth + 1);
            }
        }
    }
}