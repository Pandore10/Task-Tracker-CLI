import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RepositoryManager {
    //Função da classe:
    //Salvar e carrega tasks em JSON.
    //Atualizar o lastId para evitar ids duplicados.

    public static int getNumTasks(String filename) {
        File file = new File(filename);

        if (!file.exists()) return -1;

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                if (scanner.next().contains("\"numTasks\"")) {
                    int numTasks = Integer.parseInt(scanner.next().replace(",", ""));
                    scanner.close();
                    return numTasks;
                }

                scanner.close();
            }  
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao procurar chave 'numTasks': " + e);
        }

        return -1;
    }
}
