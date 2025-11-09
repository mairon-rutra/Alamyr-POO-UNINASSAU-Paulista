import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class leitor_tabuada {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o número da tabuada que deseja ler: ");
        int num = sc.nextInt();

        String pasta = "../Alamyr-POO-UNINASSAU-Paulista/tabuadas";
        String nomeArquivo = pasta + File.separator + "tabuada_" + num + ".txt";
        File arquivo = new File(nomeArquivo);

        if (!arquivo.exists()) {
            System.out.println("❌ O arquivo " + nomeArquivo + " não existe!");
        } else {
            System.out.println("📄 Conteúdo do arquivo " + nomeArquivo + ":");
            try (Scanner leitor = new Scanner(arquivo)) {
                while (leitor.hasNextLine()) {
                    System.out.println(leitor.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println("❌ Erro ao abrir o arquivo: " + e.getMessage());
            }
        }

        sc.close();
    }
}