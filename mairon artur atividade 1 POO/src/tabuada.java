import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class tabuada {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número para gerar a tabuada: ");
        int num = sc.nextInt();

        String pasta = "tabuadas";
        File dir = new File(pasta);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String nomeArquivo = pasta + File.separator + "tabuada_" + num + ".txt";

        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            for (int i = 1; i <= 10; i++) {
                String linha = num + " x " + i + " = " + (num * i) + "\n";
                writer.write(linha);
            }
            System.out.println("✅ Tabuada salva com sucesso em: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("❌ Erro ao escrever o arquivo: " + e.getMessage());
        }

        sc.close();
    }
}
