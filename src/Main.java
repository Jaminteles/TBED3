import filesystem.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FileSystem fs = new FileSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\nComando: ");
            String cmd = sc.nextLine();

            try {
                String[] parts = cmd.split(" ");
                switch (parts[0]) {

                    case "mkdir":
                        fs.createDirectory(parts[1], parts[2]);
                        break;

                    case "touch":
                        fs.createFile(parts[1], parts[2], Integer.parseInt(parts[3]));
                        break;

                    case "ls":
                        fs.listDirectory(parts[1]);
                        break;

                    case "rm":
                        fs.delete(parts[1]);
                        break;

                    case "mv":
                        fs.rename(parts[1], parts[2]);
                        break;

                    case "find":
                        fs.searchByName(parts[1]);
                        break;

                    case "tree":
                        fs.printTree();
                        break;

                    case "exit":
                        return;

                    default:
                        System.out.println("Comando inválido.");
                }
            } catch (Exception e) {
                System.out.println("Erro no comando.");
            }
        }
    }
}
