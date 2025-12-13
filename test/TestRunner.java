import filesystem.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * TestRunner.java
 * Classe para executar os testes do sistema de arquivos.
 * <p>
 * Esta classe testa as principais funcionalidades do sistema da classe {@link FileSystem},
 * como:
 * </p>
 * <ul>
 *   <li>Criar diretórios e arquivos</li>
 *   <li>Criação de arquivos</li>
 *   <li>Renomear e deletar</li>
 *   <li>Buscar por nome</li>
 * </ul>
 * 
 * <p> Os teste são executado sem uso de frameworks externos, apenas com métodos simples 
 * (asserts manuais).
 * </p>
 * 
 * @author TBED3
 * @version 1.0
 */

public class TestRunner {

    /** Contador de testes que passaram */
    private static int passed = 0;
    /** Contador de testes que falharam */
    private static int failed = 0;

    /**
     * Método principal para executar os testes.
     * 
     * @param args argumentos da linha de comando (não usados)
     */
    public static void main(String[] args) {
        testCreateDirectoryAndFile();
        testRenameAndDelete();
        testSearchAndTree();

        System.out.println("\n====== RESUMO ======");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }
    /** 
     * Incrementa o contador de testes que passaram.
     */
    private static void ok() { passed++; }
    /** 
     * Incrementa o contador de testes que falharam e imprime a mensagem.
     * 
     * @param msg a mensagem de falha
     */
    private static void fail(String msg){ 
        failed++; 
        System.out.println("FALHA: " + msg); 
    }

    /** 
     * Verifica se a condição é verdadeira.
     * 
     * @param cond a condição a ser verificada
     * @param msg a mensagem de falha se a condição for falsa
     */

    private static void assertTrue(boolean cond, String msg) {
        if (cond) ok(); 
        else fail(msg);
    }
    /** 
     * Verifica se os dois objetos são iguais.
     * 
     * @param exp o valor esperado
     * @param act o valor atual
     * @param msg a mensagem de falha se os valores não forem iguais
     */

    private static void assertEquals(Object exp, Object act, String msg) {
        if (exp == null ? act == null : exp.equals(act))
            ok();
        else
            fail(msg + " (esperado=" + exp + ", atual=" + act + ")");
    }
    /** 
     * Testa a criação de diretórios e arquivos.
     * <p>
     * Verifica se:
     * </p>
     * <ul>
     *  <li>Diretórios são criados corretamente</li>
     *  <li>O arquivo existe após a criação</li>
     *  <li>O nome do arquivo está correto</li>
     * </ul>
     */

    private static void testCreateDirectoryAndFile() {
        System.out.println("\n--- testCreateDirectoryAndFile ---");
        FileSystem fs = new FileSystem();

        fs.createDirectory("/root", "docs");
        DirectoryNode d = fs.navigateTo("/root/docs");
        assertTrue(d != null, "/root/docs deve existir");

        fs.createFile("/root/docs", "arquivo.txt", 100);
        Node n = fs.findNode("/root/docs/arquivo.txt");
        assertTrue(n != null, "arquivo criado deve existir");
        assertEquals("arquivo.txt", n.getName(), "nome correto do arquivo");
    }
    /** 
     * Testa renomear e deletar arquivos e diretórios.
     * <p>
     * Verifica se:
     * </p>
     * <ul>
     *  <li>O arquivo é renomeado corretamente</li>
     *  <li>O arquivo é deletado corretamente</li>
     * </ul>
     */

    private static void testRenameAndDelete() {
        System.out.println("\n--- testRenameAndDelete ---");
        FileSystem fs = new FileSystem();

        fs.createDirectory("/root", "pasta");
        fs.createFile("/root/pasta", "a.txt", 10);

        boolean rename = fs.rename("/root/pasta/a.txt", "b.txt");
        assertTrue(rename, "rename deve retornar true");

        boolean deleted = fs.delete("/root/pasta/b.txt");
        assertTrue(deleted, "arquivo deve ser deletado");
    }
    /** 
     * Testa a busca por nome e a exibição da árvore de diretórios.
     * <p>
     * Verifica se:
     * </p>
     * <ul>
     *  <li>A busca retorna o caminho correto</li>
     * </ul>
     */

    private static void testSearchAndTree() {
        System.out.println("\n--- testSearchAndTree ---");
        FileSystem fs = new FileSystem();

        fs.createDirectory("/root", "d1");
        fs.createDirectory("/root/d1", "d2");
        fs.createFile("/root/d1/d2", "f.txt", 10);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));

        fs.searchByName("f.txt");

        System.out.flush();
        System.setOut(old);

        String result = out.toString();
        assertTrue(result.contains("/root/d1/d2/f.txt"), "search deve mostrar caminho do arquivo");
    }
}
