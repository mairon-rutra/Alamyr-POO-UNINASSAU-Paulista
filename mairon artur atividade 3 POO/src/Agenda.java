import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Agenda {
    private List<Contatinho> lista = new ArrayList<>();

    public void addContatinho(Contatinho c) {
        lista.add(c);
    }

    public void ordenarLista() {
        Collections.sort(lista, Comparator.comparing(Contatinho::getNome));
    }

    public void salvarLista(String nomeArquivo) {
        ordenarLista();
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            for (Contatinho c : lista) {
                writer.write(c.toString() + "\n");
            }
            System.out.println("✅ Lista salva com sucesso em: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda();

        System.out.println("=== CADASTRO DE CONTATINHOS ===");

        char continuar;
        do {
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("E-mail: ");
            String email = sc.nextLine();

            System.out.print("Telefone: ");
            String telefone = sc.nextLine();

            System.out.print("Categoria: ");
            String categoria = sc.nextLine();

            Contatinho c = new Contatinho(nome, email, telefone, categoria);
            agenda.addContatinho(c);

            System.out.print("Deseja adicionar outro contatinho? (s/n): ");
            continuar = sc.nextLine().toLowerCase().charAt(0);
            System.out.println();

        } while (continuar == 's');

        agenda.salvarLista("contatinhos.txt");
        sc.close();
    }
}

class Contatinho {
    private String nome;
    private String email;
    private String telefone;
    private String categoria;

    public Contatinho(String nome, String email, String telefone, String categoria) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.categoria = categoria;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getCategoria() { return categoria; }

    @Override
    public String toString() {
        return nome + "#" + email + "#" + telefone + "#" + categoria;
    }
}
