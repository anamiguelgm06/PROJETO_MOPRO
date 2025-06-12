import Exceptions.Excecao;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.Locale;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Centro> centros = new ArrayList<>();

    public static void main(String[] args) throws Excecao {
        centros= lerDadosDeTexto();
        menuPrincipal();
        scanner.close();
    }

    private static void carregarDadosIniciais() throws Excecao {
        Centro centro = new Centro("Centro de Saúde Lisboa", 123456789, "Rua das Flores, 123", 218765432, "contato@centrolisboa.pt");
        Paciente p1 = new Paciente(1, "Joana Silva", 'F', new Data(1990, 5, 12));
        Paciente p2 = new Paciente(2, "Carlos Mendes", 'M', new Data(1985, 3, 22));
        Paciente p3 = new Paciente(3, "Rita Carvalho", 'F', new Data(2001, 12, 5));
        Paciente p4 = new Paciente(4, "Alex Rocha", 'O', new Data(1998, 7, 30));
        Tecnico t1 = new Tecnico("Cedula1", "Marco Barbosa", 1000.0f, 25, new Data(2005, 9, 22), Especialidade.RX);
        Tecnico t2 = new Tecnico("Cedula2", "Helena Barbosa", 999.9f, 24.9f, new Data(2006, 10, 12), Especialidade.RM);
        centro.adicionarPaciente(p1);
        centro.adicionarPaciente(p2);
        centro.adicionarPaciente(p3);
        centro.adicionarPaciente(p4);
        centro.adicionarTecnico(t1);
        centro.adicionarTecnico(t2);
        centro.adicionarExame(new ExameRM(new Data(2024, 6, 1), p1, t1, true));
        centro.adicionarExame(new ExameRX(new Data(2024, 6, 2), p1, t2, "Tórax"));
        centro.adicionarExame(new ExameTAC(new Data(2024, 6, 1), p2, t1, 40.0));
        centro.adicionarExame(new ExameRX(new Data(2024, 6, 2), p2, t2, "Joelho"));
        centro.adicionarExame(new ExameRX(new Data(2024, 6, 3), p3, t2, "Coluna"));
        centro.adicionarExame(new ExameRM(new Data(2024, 6, 4), p3, t1, false));
        centro.adicionarExame(new ExameTAC(new Data(2024, 6, 5), p4, t1, 45.5));
        centro.adicionarExame(new ExameRX(new Data(2024, 6, 6), p4, t2, "Cabeça"));
        centros.add(centro);

        Centro centroPorto = new Centro("Clínica do Dragão", 987654321, "Avenida dos Aliados, 456", 229876543, "geral@clinicadragao.pt");
        Paciente p5 = new Paciente(5, "Sofia Antunes", 'F', new Data(1978, 11, 15));
        Paciente p6 = new Paciente(6, "Rui Costa", 'M', new Data(1995, 1, 9));
        centroPorto.adicionarPaciente(p5);
        centroPorto.adicionarPaciente(p6);
        Tecnico t3 = new Tecnico("Cedula3", "Ana Pereira", 1100.0f, 30, new Data(1992, 8, 18), Especialidade.TAC);
        Tecnico t4 = new Tecnico("Cedula4", "Luís Martins", 1050.0f, 20, new Data(1989, 4, 3), Especialidade.RX);
        centroPorto.adicionarTecnico(t3);
        centroPorto.adicionarTecnico(t4);
        centroPorto.adicionarExame(new ExameTAC(new Data(2024, 5, 20), p5, t3, 55.0));
        centroPorto.adicionarExame(new ExameRX(new Data(2024, 5, 21), p6, t4, "Pulmões"));
        centroPorto.adicionarExame(new ExameRM(new Data(2024, 5, 22), p5, t3, false));
        centroPorto.adicionarExame(new ExameRX(new Data(2024, 5, 23), p6, t4, "Mão Direita"));
        centros.add(centroPorto);
    }

    private static String pedirInput(String pergunta, String tipoVariavel) {
        boolean respostaValida = false;
        while (!respostaValida) {
            System.out.println(pergunta);
            String resposta = "";
            switch (tipoVariavel.toLowerCase()) {
                case "string" -> {
                    resposta = scanner.nextLine();
                    return resposta;
                }
                case "string_letras" -> {
                    resposta = scanner.nextLine();
                    if (!resposta.matches("[\\p{L} ]+")) {
                        System.out.println("Erro: O texto deve conter apenas letras (incluindo acentos) e espaços, e não pode ser vazio.");
                        continue;
                    }
                    return resposta;
                }
                case "int" -> {
                    try {
                        int valor = scanner.nextInt();
                        scanner.nextLine();
                        respostaValida = true;
                        return Integer.toString(valor);
                    } catch (Exception e) {
                        System.out.println("Erro: O tipo de dado inserido não corresponde ao pedido. Por favor, insira um número inteiro sem casas decimais.");
                        scanner.nextLine();
                    }
                }
                case "data" -> {
                    String valor = scanner.nextLine();
                    if (valor.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        try {
                            LocalDate ld = LocalDate.parse(valor);
                            Data data = new Data(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
                            return valor;
                        } catch (Exception e) {
                            System.out.println("Erro: Data inválida.");
                        }
                    } else {
                        System.out.println("Erro: Formato inválido. Use AAAA-MM-DD.");
                    }
                }
                case "char" -> {
                    String valor = scanner.nextLine();
                    if (valor.length() != 1) {
                        System.out.println("Erro: Insira exatamente um caracter.");
                        continue;
                    }
                    respostaValida = true;
                    return valor;
                }
                case "double" -> {
                    try {
                        double valor = scanner.nextDouble();
                        scanner.nextLine();
                        respostaValida = true;
                        return Double.toString(valor);
                    } catch (Exception e) {
                        System.out.println("Erro: O tipo de dado inserido não corresponde ao pedido. Por favor, insira um número real.");
                        scanner.nextLine();
                    }
                }
                case "boolean" -> {
                    try {
                        boolean valor = scanner.nextBoolean();
                        scanner.nextLine();
                        respostaValida = true;
                        return Boolean.toString(valor);
                    } catch (Exception e) {
                        System.out.println("Erro: O tipo de dado inserido não corresponde ao pedido. Por favor, insira true ou false.");
                        scanner.nextLine();
                    }
                }
                default -> {
                    System.out.println("Erro: Tipo de variável " + tipoVariavel + " não existe!");
                    return "";
                }
            }
        }
        return "";
    }

    private static void menuPrincipal() throws Excecao {
        int opcao;
        do {
            System.out.println("\n------ MENU PRINCIPAL ------");
            System.out.println("1. Criar Centro");
            System.out.println("2. Gerir Centro");
            System.out.println("3. Listar Centros");
            System.out.println("4. Guardar dados num Ficheiro");
            System.out.println("0. Sair");
            opcao = Integer.parseInt(pedirInput("Opção: ", "int"));
            switch (opcao) {
                case 0 -> {return;}
                case 1 -> {
                    boolean respostaValida = false;
                    String nome = pedirInput("Nome do Centro: ", "string_letras");
                    String morada = pedirInput("Morada do Centro: ", "string");
                    String email;
                    do {
                        email = pedirInput("Email do Centro: ", "string");
                        boolean emailExistente = false;
                        for (Centro c : centros) {
                            if (c.getEmail().equals(email)) {
                                System.out.println("Erro: Já existe um centro com esse email.");
                                emailExistente = true;
                                break;
                            }
                        }
                        if (!emailExistente) {
                            respostaValida = true;
                        }
                    } while (!respostaValida);
                    int nif;
                    do {
                        nif = Integer.parseInt(pedirInput("NIF do Centro:", "int"));
                        if (String.valueOf(nif).length() != 9) {
                            System.out.println("Erro: O NIF deve conter 9 dígitos.");
                            continue;
                        }
                        boolean nifExistente = false;
                        for (Centro c : centros) {
                            if (c.getNif() == nif) {
                                System.out.println("Erro: Já existe um centro com esse NIF.");
                                nifExistente = true;
                                break;
                            }
                        }
                        if (!nifExistente) {
                            respostaValida = true;
                        }
                    } while (!respostaValida);
                    int telefone;
                    do {
                        telefone = Integer.parseInt(pedirInput("Telefone do Centro:", "int"));
                        if (String.valueOf(telefone).length() != 9) {
                            System.out.println("Erro: O numero de telefone deve conter 9 dígitos.");
                            continue;
                        }
                        boolean telefoneExistente = false;
                        for (Centro c : centros) {
                            if (c.getTelefone() == telefone) {
                                System.out.println("Erro: Já existe um centro com esse numero de telefone.");
                                telefoneExistente = true;
                                break;
                            }
                        }
                        if (!telefoneExistente) {
                            respostaValida = true;
                        }
                    } while (!respostaValida);
                    Centro centro = new Centro(nome, nif, morada, telefone, email);
                    centros.add(centro);
                    System.out.println("Centro criado com sucesso! \n" + centro);
                }
                case 2 -> {
                    Centro centroAGerir = null;
                    int nif;
                    boolean respostaValida = false;
                    do {
                        nif = Integer.parseInt(pedirInput("NIF do Centro:", "int"));
                        if (String.valueOf(nif).length() != 9) {
                            System.out.println("Erro: O NIF deve conter 9 dígitos.");
                            continue;
                        }
                        for (Centro c : centros) {
                            if (c.getNif() == nif) {
                                respostaValida = true;
                                centroAGerir = c;
                                break;
                            }
                        }
                        if (!respostaValida) {
                            System.out.println("Erro: Não existe um centro com esse NIF.");
                        }
                    } while (!respostaValida);
                    menuGerirCentro(centroAGerir);
                }
                case 3 -> {
                    if (centros.isEmpty()) {
                        System.out.println("Erro: Ainda não foram criados nenhuns centros.");
                        return;
                    }
                    for (Centro c : centros) {
                        System.out.println(c);
                    }
                }
                case 4 -> {
                    guardarDadosEmTexto();
                }
                case 5 -> {
                    lerDadosDeTexto();
                }
                default -> System.out.println("Erro: Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void menuGerirCentro(Centro c) throws Excecao {
        int opcao;
        do {
            System.out.println("\n------ GERIR CENTRO ------");
            System.out.println("1. Adicionar Paciente");
            System.out.println("2. Adicionar Técnico");
            System.out.println("3. Adicionar Exame");
            System.out.println("4. Listar Exames por Data");
            System.out.println("5. Ver Exames de um Paciente");
            System.out.println("6. Calcular custo do Centro");
            System.out.println("0. Voltar");
            opcao = Integer.parseInt(pedirInput("Opção: ", "int"));
            switch (opcao) {
                case 0 -> {return;}
                case 1 -> {
                    int numeroUtente;
                    boolean respostaValida = false;
                    do {
                        numeroUtente = Integer.parseInt(pedirInput("Número de Utente: ", "int"));
                        boolean existe = false;
                        for (Paciente p : c.getPacientes()) {
                            if (p.getNumeroUtente() == numeroUtente) {
                                System.out.println("Erro: Já existe um paciente com esse número de utente.");
                                existe = true;
                                break;
                            }
                        }
                        if (!existe) {
                            respostaValida = true;
                        }
                    } while (!respostaValida);
                    String nome = pedirInput("Nome: ", "string_letras");
                    char sexo = pedirInput("Sexo (M/F/O): ", "char").toUpperCase().charAt(0);
                    String[] partes = pedirInput("Data de nascimento (AAAA-MM-DD): ", "data").split("-");
                    int ano = Integer.parseInt(partes[0]);
                    int mes = Integer.parseInt(partes[1]);
                    int dia = Integer.parseInt(partes[2]);
                    Paciente novo = new Paciente(numeroUtente, nome, sexo, new Data(ano, mes, dia));
                    c.getPacientes().add(novo);
                    System.out.println("Paciente adicionado com sucesso.");
                }
                case 2 -> {
                    String cedula;
                    boolean cedulaValida = false;
                    do {
                        cedula = pedirInput("Cédula: ", "string");
                        boolean existe = false;
                        for (Tecnico t : c.getTecnicos()) {
                            if (t.getCedulaProfissional().equals(cedula)) {
                                System.out.println("Erro: Já existe um tecnico com essa cedula.");
                                existe = true;
                                break;
                            }
                        }
                        if (!existe) {
                            cedulaValida = true;
                        }
                    } while (!cedulaValida);
                    String nome = pedirInput("Nome: ", "string_letras");
                    String[] partes = pedirInput("Data de nacimento (AAAA-MM-DD): ", "data").split("-");
                    int ano = Integer.parseInt(partes[0]);
                    int mes = Integer.parseInt(partes[1]);
                    int dia = Integer.parseInt(partes[2]);
                    Data dataNascimento = new Data(ano, mes, dia);
                    float salario = Float.parseFloat(pedirInput("Insira o salário: ", "float"));
                    float subsidio = Float.parseFloat(pedirInput("Insira o subsídio (0 a 50): ", "float"));
                    String tipo;
                    boolean respostaValida = false;
                    do {
                        tipo = pedirInput("Especialidade do tecnico (RM/RX/TAC): ", "string_letras");
                        if (tipo.equals("RM") || tipo.equals("RX") || tipo.equals("TAC")) respostaValida = true;
                        else System.out.println("Erro: Tipo de especialidade inválida.");
                    } while (!respostaValida);
                    Especialidade especialidade = null;
                    switch (tipo) {
                        case "RM" -> especialidade = Especialidade.RM;
                        case "RX" -> especialidade = Especialidade.RX;
                        case "TAC" -> especialidade = Especialidade.TAC;
                        default -> System.out.println("Erro: Tipo de exame inválido.");
                    }
                    Tecnico novo = new Tecnico(cedula, nome, salario, subsidio, dataNascimento, especialidade);
                }
                case 3 -> {
                    if (c.getPacientes().isEmpty()) {
                        System.out.println("Erro: Não há pacientes. Adicione primeiro.");
                        break;
                    }
                    if (c.getTecnicos().isEmpty()) {
                        System.out.println("Erro: Não há técnicos. Adicione primeiro.");
                        break;
                    }
                    int numeroUtente;
                    Paciente paciente = null;
                    boolean numeroValido = false;
                    do {
                        numeroUtente = Integer.parseInt(pedirInput("Número de utente do paciente: ", "int"));
                        for (Paciente p : c.getPacientes()) {
                            if (p.getNumeroUtente() == numeroUtente) {
                                numeroValido = true;
                                paciente = p;
                                break;
                            }
                        }
                        if (!numeroValido) {
                            System.out.println("Erro: Não existe um utente com esse número!");
                        }
                    } while (!numeroValido);
                    String[] partes = pedirInput("Data do exame (AAAA-MM-DD): ", "data").split("-");
                    int ano = Integer.parseInt(partes[0]);
                    int mes = Integer.parseInt(partes[1]);
                    int dia = Integer.parseInt(partes[2]);
                    Data data = new Data(ano, mes, dia);
                    String cedulaTecnico;
                    Tecnico tecnico = null;
                    boolean cedulaValida = false;
                    do {
                        cedulaTecnico = pedirInput("Cédula do técnico: ", "string");
                        for (Tecnico t : c.getTecnicos()) {
                            if (t.getCedulaProfissional().equals(cedulaTecnico)) {
                                cedulaValida = true;
                                tecnico = t;
                                break;
                            }
                        }
                        if (!cedulaValida) {
                            System.out.println("Erro: O técnico inserido não existe!");
                        }
                    } while (!cedulaValida);
                    boolean respostaValida = false;
                    String tipo;
                    do {
                        tipo = pedirInput("Tipo de exame (RM/RX/TAC): ", "string_letras");
                        if (tipo.equals("RM") || tipo.equals("RX") || tipo.equals("TAC")) respostaValida = true;
                        else System.out.println("Erro: Tipo de exame inválido.");
                    } while (!respostaValida);
                    switch (tipo) {
                        case "RM" -> {
                            System.out.print("Usa contraste? (true/false): ");
                            boolean contraste = Boolean.parseBoolean(scanner.nextLine());
                            c.getExames().add(new ExameRM(data, paciente, tecnico, contraste));
                        }
                        case "RX" -> {
                            System.out.print("Zona do corpo: ");
                            String zona = scanner.nextLine();
                            c.getExames().add(new ExameRX(data, paciente, tecnico, zona));
                        }
                        case "TAC" -> {
                            System.out.print("Custo unitário: ");
                            double custo = Double.parseDouble(scanner.nextLine());
                            c.getExames().add(new ExameTAC(data, paciente, tecnico, custo));
                        }
                        default -> System.out.println("Erro: Tipo de exame inválido.");
                    }
                    System.out.println("Exame adicionado com sucesso!");
                }
                case 4 -> {
                    String[] partes = pedirInput("Data da filtrar (AAAA-MM-DD): ", "data").split("-");
                    int ano = Integer.parseInt(partes[0]);
                    int mes = Integer.parseInt(partes[1]);
                    int dia = Integer.parseInt(partes[2]);
                    Data dataFiltrar = new Data(ano, mes, dia);
                    System.out.println("Exames realizados em " + dataFiltrar + ":");
                    List<Exame> examesFiltrados = new ArrayList<>();
                    for (Exame e : c.getExames()) {
                        if (e.getDataRealizacao().equals(dataFiltrar)) {
                            examesFiltrados.add(e);
                        }
                    }
                    examesFiltrados.sort(Comparator.comparing(e -> e.getTecnicoResponsavel().getNome()));
                    for (Exame e : examesFiltrados) {
                        System.out.println(e);
                    }
                }
                case 5 -> {
                    int numeroUtente;
                    boolean numeroValido = false;
                    Paciente paciente = null;
                    do {
                        numeroUtente = Integer.parseInt(pedirInput("Número de utente do paciente: ", "int"));
                        for (Paciente p : c.getPacientes()) {
                            if (p.getNumeroUtente() == numeroUtente) {
                                numeroValido = true;
                                paciente = p;
                                break;
                            }
                        }
                        if (!numeroValido) {
                            System.out.println("Erro: Não existe um utente com esse número!");
                        }
                    } while (!numeroValido);
                    System.out.println("Exames do paciente: " + paciente.getNome());
                    System.out.printf("%-6s | %-12s | %-10s | %-8s | %-6s%n", "Código", "Técnico", "Data", "Tipo", "Custo");
                    double total = 0;
                    for (Exame e : c.getExames()) {
                        if (e.getPaciente().getNumeroUtente() == numeroUtente) {
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
                case 6 -> {
                    float custo = 0.0f;
                    if (c.getTecnicos().size() > 0 ) {
                        for (Tecnico t: c.getTecnicos()) {
                            custo += t.calcularCusto();
                        }
                    }

                    boolean respostaValida = false;
                    Data data = Data.now();
                    do {
                        System.out.println("Insira o ano e mês que quer calcular (AAAA-MM): ");
                        String valor = scanner.nextLine();
                        if (valor.matches("\\d{4}-\\d{2}")) {
                            try {
                                String[] partes = valor.split("-");
                                int ano = Integer.parseInt(partes[0]);
                                int mes = Integer.parseInt(partes[1]);
                                data = new Data(ano, mes, 1);
                            } catch (Exception e) {
                                System.out.println("Erro: Data inválida.");
                            }
                        } else {
                            System.out.println("Erro: Formato inválido. Use AAAA-MM.");
                        }
                    } while (!respostaValida);

                    ArrayList<Exame> examesFiltrados = new ArrayList<>();
                    for (Exame e : c.getExames()) {
                        if (e.getDataRealizacao().getAno() == data.getAno() && e.getDataRealizacao().getMes() == data.getMes()) {
                            examesFiltrados.add(e);
                        }
                    }

                    if (c.getExames().size() > 0 ) {
                        for (Exame e: examesFiltrados) {
                            custo += e.calcularCusto();
                        }
                    }

                    System.out.printf("O custo deste centro é de %.2f€\n", custo);
                }
                default -> System.out.println("Erro: Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void guardarDadosEmTexto() {
        try (Formatter formatter = new Formatter("centros.txt")) {
            for (Centro centro : centros) {
                formatter.format("centro,%s,%d,%s,%d,%s%n",
                        centro.getNome(),
                        centro.getNif(),
                        centro.getMorada(),
                        centro.getTelefone(),
                        centro.getEmail());

                for (Paciente p : centro.getPacientes()) {
                    formatter.format("paciente,%d,%s,%c,%s%n",
                            p.getNumeroUtente(),
                            p.getNome(),
                            p.getGenero(),
                            p.getDataNascimento().toString());
                }
                for (Tecnico t : centro.getTecnicos()) {
                    formatter.format("tecnico,%s,%s,%s,%s,%s,%s%n",
                            t.getCedulaProfissional(),
                            t.getNome(),
                            String.format(Locale.US, "%.2f", t.getSalarioBase()),
                            String.format(Locale.US, "%.2f", t.getSubsidio()),
                            t.getEspecialidade().getNomeEspecialidade(),
                            t.getDataNascimento().toString());
                }

                for (Exame e : centro.getExames()) {
                    String linhaExame = String.format("exame,%d,%d,%s,%s",
                            e.getCodigo(),
                            e.getPaciente().getNumeroUtente(),
                            e.getTecnicoResponsavel().getCedulaProfissional(),
                            e.getDataRealizacao().toString());

                    if (e instanceof ExameRX) {
                        linhaExame += "," + ((ExameRX) e).getZonaCorpo();
                    } else if (e instanceof ExameRM) {
                        linhaExame += "," + ((ExameRM) e).getContraste();
                    } else if (e instanceof ExameTAC) {
                        linhaExame += "," + ((ExameTAC) e).getCustoUnitario();
                    }

                    formatter.format("%s%n", linhaExame);
                }
                formatter.format("-----------------------------------------------------------------------------------%n");
            }

            System.out.println("Dados guardados com sucesso no ficheiro 'centros.txt'.");

        } catch (FileNotFoundException e) {
            System.err.println("Erro: Não foi possível criar ou escrever no ficheiro.");
            e.printStackTrace();
        }
    }

    private static ArrayList<Centro> lerDadosDeTexto() throws Excecao {
        ArrayList<Centro> listaCentros = new ArrayList<>();
        Centro centroAtual = null;
        File ficheiro = new File("centros.txt");

        if (!ficheiro.exists()) {
            System.out.println("Ficheiro 'centros.txt' não encontrado. A iniciar com dados padrão.");
            return new ArrayList<>();
        }

        try (Scanner scannerFicheiro = new Scanner(ficheiro)) {
            while (scannerFicheiro.hasNextLine()) {
                String linha = scannerFicheiro.nextLine();

                if (linha.trim().isEmpty() || linha.startsWith("---")) {
                    continue;
                }

                String[] partes = linha.split(",");
                String tipoEntidade = partes[0];

                switch (tipoEntidade) {
                    case "centro" -> {
                        String nomeCentro = partes[1];
                        int nif = Integer.parseInt(partes[2]);
                        String morada = partes[3];
                        int telefone = Integer.parseInt(partes[4]);
                        String email = partes[5];
                        centroAtual = new Centro(nomeCentro, nif, morada, telefone, email);
                        listaCentros.add(centroAtual);
                        break;
                    }

                    case "paciente" -> {
                        if (centroAtual != null) {
                            int numUtente = Integer.parseInt(partes[1]);
                            String nomePac = partes[2];
                            char genero = partes[3].charAt(0);
                            Data dataNasc = new Data(partes[4]);
                            Paciente p = new Paciente(numUtente, nomePac, genero, dataNasc);
                            centroAtual.adicionarPaciente(p);
                        }
                        break;
                    }

                    case "tecnico" -> {
                        if (centroAtual != null) {
                            String cedula = partes[1];
                            String nomeTec = partes[2];
                            float salario = Float.parseFloat(partes[3]);
                            float subsidio = Float.parseFloat(partes[4]);
                            Especialidade esp = Especialidade.fromNomeDescritivo(partes[5]);
                            Data dataNascTec = new Data(partes[6]);
                            Tecnico t = new Tecnico(cedula, nomeTec, salario, subsidio, dataNascTec, esp);
                            centroAtual.adicionarTecnico(t);
                        }
                        break;
                    }
                    case "exame" -> {
                        if (centroAtual != null) {
                            String detalhe = partes[5];
                            String tipoExame;

                            if (detalhe.equalsIgnoreCase("true") || detalhe.equalsIgnoreCase("false")) {
                                tipoExame = "RM";
                            } else {
                                try {
                                    Double.parseDouble(detalhe);
                                    tipoExame = "TAC";
                                } catch (NumberFormatException e) {
                                    tipoExame = "RX";
                                }
                            }

                            int numUtenteExame = Integer.parseInt(partes[2]);
                            String cedulaTecExame = partes[3];
                            Data dataExame = new Data(partes[4]);

                            Paciente pacDoExame = encontrarPacientePorNumero(centroAtual, numUtenteExame);
                            Tecnico tecDoExame = encontrarTecnicoPorCedula(centroAtual, cedulaTecExame);

                            if (pacDoExame != null && tecDoExame != null) {
                                Exame novoExame = null;
                                switch (tipoExame) {
                                    case "RX":
                                        novoExame = new ExameRX(dataExame, pacDoExame, tecDoExame, detalhe);
                                        break;
                                    case "RM":
                                        novoExame = new ExameRM(dataExame, pacDoExame, tecDoExame, Boolean.parseBoolean(detalhe));
                                        break;
                                    case "TAC":
                                        novoExame = new ExameTAC(dataExame, pacDoExame, tecDoExame, Double.parseDouble(detalhe));
                                        break;
                                }
                                if (novoExame != null) {
                                    centroAtual.adicionarExame(novoExame);
                                }
                            }
                        }
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {

        }
        return listaCentros;
    }

    private static Paciente encontrarPacientePorNumero(Centro centro, int numeroUtente) {
        for (Paciente p : centro.getPacientes()) {
            if (p.getNumeroUtente() == numeroUtente) {
                return p;
            }
        }
        return null;
    }

    private static Tecnico encontrarTecnicoPorCedula(Centro centro, String cedula) {
        for (Tecnico t : centro.getTecnicos()) {
            if (t.getCedulaProfissional().equals(cedula)) {
                return t;
            }
        }
        return null;
    }
}