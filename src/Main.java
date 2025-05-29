import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Centro centro1 = new Centro("Centro de Saúde Lisboa", "123456789", "Rua das Flores, 123", "218765432", "contato@centrolisboa.pt");
        Centro centro2 = new Centro();

        Paciente paciente1 = new Paciente("123456789", "Joana Silva", 'F', LocalDate.of(1990, 5, 12));
        Paciente paciente2 = new Paciente("987654321", "Carlos Mendes", 'M', LocalDate.of(1985, 3, 22));
        Paciente paciente3 = new Paciente("456123789", "Rita Carvalho", 'F', LocalDate.of(2001, 12, 5));
        Paciente paciente4 = new Paciente("321789654", "Alex Rocha", 'O', LocalDate.of(1998, 7, 30));

        // Criar exames
        Exame exame1_1 = new ExameRM(LocalDate.of(2024, 4, 10), paciente1, "José Mateus", true);
        Exame exame1_2 = new ExameRX(LocalDate.of(2024, 4, 15), paciente1, "Maria Alves", "Tórax");
        Exame exame1_3 = new ExameTAC(LocalDate.of(2024, 4, 20), paciente1, "José Mateus", 45.5);

        Exame exame2_1 = new ExameRM(LocalDate.of(2024, 4, 10), paciente2, "José Mateus", false);
        Exame exame2_2 = new ExameRX(LocalDate.of(2024, 4, 10), paciente2, "Maria Alves", "Joelho");
        Exame exame2_3 = new ExameTAC(LocalDate.of(2024, 4, 18), paciente2, "Maria Alves", 38.0);

        Exame exame3_1 = new ExameRM(LocalDate.of(2024, 3, 18), paciente3, "José Mateus", true);
        Exame exame3_2 = new ExameRX(LocalDate.of(2024, 3, 20), paciente3, "Maria Alves", "Coluna");
        Exame exame3_3 = new ExameTAC(LocalDate.of(2024, 3, 25), paciente3, "José Mateus", 50.0);

        Exame exame4_1 = new ExameRM(LocalDate.of(2024, 6, 1), paciente4, "José Mateus", false);
        Exame exame4_2 = new ExameRX(LocalDate.of(2024, 6, 2), paciente4, "Maria Alves", "Cabeça");
        Exame exame4_3 = new ExameTAC(LocalDate.of(2024, 6, 5), paciente4, "Maria Alves", 47.25);

        // Guardar todos os exames numa lista
        List<Exame> exames = new ArrayList<>();
        exames.add(exame1_1);
        exames.add(exame1_2);
        exames.add(exame1_3);
        exames.add(exame2_1);
        exames.add(exame2_2);
        exames.add(exame2_3);
        exames.add(exame3_1);
        exames.add(exame3_2);
        exames.add(exame3_3);
        exames.add(exame4_1);
        exames.add(exame4_2);
        exames.add(exame4_3);

        // Data para filtrar
        LocalDate dataFiltrar = LocalDate.of(2024, 4, 10);

        System.out.println("-----------------------------------------------------------");
        System.out.println("Exames realizados no dia " + dataFiltrar + ", ordenados por técnico:");
        System.out.println("-----------------------------------------------------------");
        exames.stream()
                .filter(e -> e.getDataRealizacao().equals(dataFiltrar))
                .sorted(Comparator.comparing(Exame::getTecnicoResponsavel))
                .forEach(e -> System.out.printf("Código: %d | Técnico: %-12s | Paciente: %-15s | Data: %s | Custo: €%.2f%n",
                        e.getCodigo(),
                        e.getTecnicoResponsavel(),
                        e.getPaciente().getNome(),
                        e.getDataRealizacao(),
                        e.calcularCusto()));
        System.out.println("-----------------------------------------------------------\n");

        // Lista dos pacientes para facilitar busca
        List<Paciente> pacientes = List.of(paciente1, paciente2, paciente3, paciente4);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduz o número do utente do paciente: ");
        String codigoPaciente = scanner.nextLine();

        Paciente pacienteEncontrado = pacientes.stream()
                .filter(p -> p.getNumeroUtente().equals(codigoPaciente))
                .findFirst()
                .orElse(null);

        if (pacienteEncontrado == null) {
            System.out.println("Paciente não encontrado com o código: " + codigoPaciente);
        } else {
            System.out.println("\nExames do paciente: " + pacienteEncontrado.getNome());
            System.out.println("-----------------------------------------------------------");
            System.out.printf("%-6s | %-12s | %-10s | %-8s | %-6s%n", "Código", "Técnico", "Data", "Tipo", "Custo");
            System.out.println("-----------------------------------------------------------");

            exames.stream()
                    .filter(e -> e.getPaciente().getNumeroUtente().equals(codigoPaciente))
                    .forEach(e -> {
                        String tipoExame;
                        if (e instanceof ExameRM) {
                            tipoExame = "RM";
                        } else if (e instanceof ExameRX) {
                            tipoExame = "RX";
                        } else if (e instanceof ExameTAC) {
                            tipoExame = "TAC";
                        } else {
                            tipoExame = "Outro";
                        }

                        System.out.printf("%-6d | %-12s | %-10s | %-8s | €%6.2f%n",
                                e.getCodigo(),
                                e.getTecnicoResponsavel(),
                                e.getDataRealizacao(),
                                tipoExame,
                                e.calcularCusto());
                    });

            System.out.println("-----------------------------------------------------------");
            double custoTotal = exames.stream()
                    .filter(e -> e.getPaciente().getNumeroUtente().equals(codigoPaciente))
                    .mapToDouble(Exame::calcularCusto)
                    .sum();

            System.out.printf("Custo total dos exames do paciente %s: €%.2f%n", pacienteEncontrado.getNome(), custoTotal);
            System.out.println("-----------------------------------------------------------");
        }
    }
}
