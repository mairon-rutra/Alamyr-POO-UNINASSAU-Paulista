import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class buscarContatinho extends JFrame {
    private JTextField campoNome;
    private JTextArea resultado;

    public buscarContatinho() {
        setTitle("Buscar Contatinho");
        setSize(600, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelSuperior = new JPanel();
        painelSuperior.add(new JLabel("Nome do Contatinho:"));
        campoNome = new JTextField(20);
        painelSuperior.add(campoNome);
        JButton botaoBuscar = new JButton("Buscar");
        painelSuperior.add(botaoBuscar);

        resultado = new JTextArea();
        resultado.setEditable(false);

        add(painelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        botaoBuscar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            Contatinho c = buscarContatinhoPorNome(nome, "./contatinhos.txt");
            if (c != null) {
                resultado.setText(
                        "Nome: " + c.getNome() + "\n" +
                                "Email: " + c.getEmail() + "\n" +
                                "Telefone: " + c.getTelefone() + "\n" +
                                "Categoria: " + c.getCategoria()
                );
            } else {
                resultado.setText(null);
            }
        });
    }

    private Contatinho buscarContatinhoPorNome(String nome, String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            JOptionPane.showMessageDialog(this, "Arquivo " + nomeArquivo + " não encontrado!");
            return null;
        }

        try (Scanner leitor = new Scanner(arquivo)) {
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] partes = linha.split("#");
                if (partes.length == 4 && partes[0].equalsIgnoreCase(nome)) {
                    // Criamos um novo Contatinho (classe do outro arquivo)
                    return new Contatinho(partes[0], partes[1], partes[2], partes[3]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new buscarContatinho().setVisible(true));
    }
}
