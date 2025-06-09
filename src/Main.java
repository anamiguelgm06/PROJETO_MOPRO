import Exceptions.Excecao;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Excecao {

        Scanner scanner = new Scanner(System.in);
        Centro centro1 = new Centro("Centro de Saúde Lisboa", 123456789, "Rua das Flores, 123", 218765432, "contato@centrolisboa.pt");

        ArrayList<Paciente> pacientes = new ArrayList<>();
        ArrayList<Exame> exames = new ArrayList<>();

        // Carregamento automático de dados iniciais (2 técnicos, 4 pacientes, 2 exames cada)
        carregarDadosIniciais(pacientes, exames);

        int opcao;

        do {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Adicionar Paciente");
            System.out.println("2. Adicionar Exame");
            System.out.println("3. Listar Exames por Data");
            System.out.println("4. Ver Exames de um Paciente");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.print("Número de Utente: ");
                    String numeroUtente = scanner.nextLine();

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Sexo (M/F/O): ");
                    char sexo = scanner.nextLine().toUpperCase().charAt(0);

                    System.out.print("Data de nascimento (AAAA-MM-DD): ");
                    String[] partes = scanner.nextLine().split("-");
                    int ano = Integer.parseInt(partes[0]);
                    int mes = Integer.parseInt(partes[1]);
                    int dia = Integer.parseInt(partes[2]);

                    boolean existe = false;
                    for (Paciente p : pacientes) {
                        if (p.getNumeroUtente().equals(numeroUtente)) {
                            existe = true;
                            break;
                        }
                    }

                    if (existe) {
                        System.out.println("Erro: Número de utente já existe!");
                    } else {
                        Paciente novo = new Paciente(numeroUtente, nome, sexo, new Data(ano, mes, dia));
                        pacientes.add(novo);
                        System.out.println("Paciente adicionado com sucesso.");
                    }

                }

                case 2 -> {
                    if (pacientes.isEmpty()) {
                        System.out.println("Não há pacientes. Adicione primeiro.");
                        break;
                    }

                    System.out.print("Número de utente do paciente: ");
                    String utente = scanner.nextLine();

                    Paciente paciente = null;

                    for (Paciente p : pacientes) {
                        if (p.getNumeroUtente().equals(utente)) {
                            paciente = p;
                            break;  // Para o loop quando encontrar o paciente
                        }
                    }

                    if (paciente == null) {
                        System.out.println("Paciente não encontrado.");
                        break;
                    }

                    System.out.print("Data do exame (AAAA-MM-DD): ");
                    String[] partes = scanner.nextLine().split("-");
                    Data data = new Data(
                            Integer.parseInt(partes[0]),
                            Integer.parseInt(partes[1]),
                            Integer.parseInt(partes[2])
                    );

                    System.out.print("Técnico responsável: ");
                    String tecnico = scanner.nextLine();

                    System.out.print("Tipo de exame (RM/RX/TAC): ");
                    String tipo = scanner.nextLine().toUpperCase();

                    switch (tipo) {
                        case "RM" -> {
                            System.out.print("Usa contraste? (true/false): ");
                            boolean contraste = Boolean.parseBoolean(scanner.nextLine());
                            exames.add(new ExameRM(data, paciente, tecnico, contraste));
                        }
                        case "RX" -> {
                            System.out.print("Zona do corpo: ");
                            String zona = scanner.nextLine();
                            exames.add(new ExameRX(data, paciente, tecnico, zona));
                        }
                        case "TAC" -> {
                            System.out.print("Custo unitário: ");
                            double custo = Double.parseDouble(scanner.nextLine());
                            exames.add(new ExameTAC(data, paciente, tecnico, custo));
                        }
                        default -> System.out.println("Tipo de exame inválido.");
                    }

                    System.out.println("Exame adicionado com sucesso.");
                }

                case 3 -> {
                    System.out.print("Data a filtrar (AAAA-MM-DD): ");
                    String[] partes = scanner.nextLine().split("-");
                    Data dataFiltrar = new Data(
                            Integer.parseInt(partes[0]),
                            Integer.parseInt(partes[1]),
                            Integer.parseInt(partes[2])
                    );

                    System.out.println("Exames realizados em " + dataFiltrar + ":");

                    // Criar uma lista temporária para guardar os exames filtrados pela data
                    List<Exame> examesFiltrados = new ArrayList<>();

                    // Filtrar exames pela data desejada
                    for (Exame e : exames) {
                        if (e.getDataRealizacao().equals(dataFiltrar)) {
                            examesFiltrados.add(e);
                        }
                    }

                    // Ordenar a lista filtrada pelo nome do técnico responsável
                    examesFiltrados.sort(Comparator.comparing(Exame::getTecnicoResponsavel));

                    // Imprimir os exames já filtrados e ordenados
                    for (Exame e : examesFiltrados) {
                        System.out.printf("Código: %d | Técnico: %-12s | Paciente: %-15s | Data: %s | Custo: €%.2f%n",
                                e.getCodigo(),
                                e.getTecnicoResponsavel(),
                                e.getPaciente().getNome(),
                                e.getDataRealizacao(),
                                e.calcularCusto());
                    }
                }

                case 4 -> {
                    System.out.print("Número de utente: ");
                    String codigoPaciente = scanner.nextLine();

                    Paciente pacienteEncontrado = null;
                    for (Paciente p : pacientes) {
                        if (p.getNumeroUtente().equals(codigoPaciente)) {
                            pacienteEncontrado = p;
                            break; // Sai do loop assim que encontra o paciente
                        }
                    }


                    if (pacienteEncontrado == null) {
                        System.out.println("Paciente não encontrado.");
                        break;
                    }

                    System.out.println("Exames do paciente: " + pacienteEncontrado.getNome());
                    System.out.printf("%-6s | %-12s | %-10s | %-8s | %-6s%n", "Código", "Técnico", "Data", "Tipo", "Custo");

                    double total = 0;

                    for (Exame e : exames) {
                        if (e.getPaciente().getNumeroUtente().equals(codigoPaciente)) {
                            String tipoExame = switch (e) {
                                case ExameRM exameRM -> "RM";
                                case ExameRX exameRX -> "RX";
                                case ExameTAC exameTAC -> "TAC";
                                default -> "Outro";
                            };
                            double custo = e.calcularCusto();
                            total += custo;

                            System.out.printf("%-6d | %-12s | %-10s | %-8s | €%6.2f%n",
                                    e.getCodigo(),
                                    e.getTecnicoResponsavel(),
                                    e.getDataRealizacao(),
                                    tipoExame,
                                    custo);
                        }
                    }

                    System.out.printf("Custo total dos exames: €%.2f%n", total);
                }

                case 0 -> System.out.println("A sair...");

                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // Método que preenche automaticamente os dados iniciais
    private static void carregarDadosIniciais(List<Paciente> pacientes, List<Exame> exames) {
        Paciente p1 = new Paciente("001", "Joana Silva", 'F', new Data(1990, 5, 12));
        Paciente p2 = new Paciente("002", "Carlos Mendes", 'M', new Data(1985, 3, 22));
        Paciente p3 = new Paciente("003", "Rita Carvalho", 'F', new Data(2001, 12, 5));
        Paciente p4 = new Paciente("004", "Alex Rocha", 'O', new Data(1998, 7, 30));

        pacientes.addAll(List.of(p1, p2, p3, p4));

        exames.add(new ExameRM(new Data(2024, 6, 1), p1, "José Mateus", true));
        exames.add(new ExameRX(new Data(2024, 6, 2), p1, "Maria Alves", "Tórax"));

        exames.add(new ExameTAC(new Data(2024, 6, 1), p2, "José Mateus", 40.0));
        exames.add(new ExameRX(new Data(2024, 6, 2), p2, "Maria Alves", "Joelho"));

        exames.add(new ExameRX(new Data(2024, 6, 3), p3, "Maria Alves", "Coluna"));
        exames.add(new ExameRM(new Data(2024, 6, 4), p3, "José Mateus", false));

        exames.add(new ExameTAC(new Data(2024, 6, 5), p4, "José Mateus", 45.5));
        exames.add(new ExameRX(new Data(2024, 6, 6), p4, "Maria Alves", "Cabeça"));
    }
}
