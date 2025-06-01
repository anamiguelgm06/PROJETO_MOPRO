public class ExameRM extends Exame {
    private boolean usaContraste;
    private final double CUSTO_BASE;

    // Construtor completo
    public ExameRM(Data dataRealizacao, Paciente paciente, String tecnicoResponsavel, boolean usaContraste) {
        super(dataRealizacao, paciente, validarTecnico(tecnicoResponsavel));
        this.usaContraste = usaContraste;
        this.CUSTO_BASE = 32.0; // Valor fixo não negativo
    }

    // Construtor sem usaContraste
    public ExameRM(Data dataRealizacao, Paciente paciente, String tecnicoResponsavel) {
        this(dataRealizacao, paciente, tecnicoResponsavel, false);
    }

    // Construtor sem técnico e usaContraste
    public ExameRM(Data dataRealizacao, Paciente paciente) {
        this(dataRealizacao, paciente, "Técnico Desconhecido", false);
    }

    // Construtor só com data
    public ExameRM(Data dataRealizacao) {
        this(dataRealizacao, new Paciente(), "Técnico Desconhecido", false);
    }

    // Construtor sem argumentos (data atual)
    public ExameRM() {
        this(Data.now(), new Paciente(), "Técnico Desconhecido", false);
    }

    @Override
    public double calcularCusto() {
        if (CUSTO_BASE < 0) {
            System.out.println("Erro: Custo base não pode ser negativo.");
            return 0;
        }
        return usaContraste ? CUSTO_BASE * 1.4 : CUSTO_BASE;
    }

    @Override
    public String toString() {
        return super.toString() + ", ExameRM{" +
                "usaContraste=" + usaContraste +
                ", custo=" + calcularCusto() +
                '}';
    }

    // Validação: nomes só com letras e espaços
    private static String validarTecnico(String nome) {
        if (nome != null && nome.matches("[a-zA-ZÀ-ÿ ]+")) {
            return nome;
        } else {
            System.out.println("Aviso: Nome de técnico inválido. Substituído por 'Técnico Desconhecido'.");
            return "Técnico Desconhecido";
        }
    }
}
