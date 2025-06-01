public class ExameRX extends Exame {
    private String zonaCorpo;
    private final double CUSTO_UNITARIO;

    // Construtor completo
    public ExameRX(Data dataRealizacao, Paciente paciente, String tecnicoResponsavel, String zonaCorpo) {
        super(dataRealizacao, paciente, validarTecnico(tecnicoResponsavel));
        this.zonaCorpo = validarZona(zonaCorpo);
        this.CUSTO_UNITARIO = 24.0;
    }

    // Construtor sem zonaCorpo
    public ExameRX(Data dataRealizacao, Paciente paciente, String tecnicoResponsavel) {
        this(dataRealizacao, paciente, tecnicoResponsavel, "Indefinida");
    }

    // Construtor sem técnico e zonaCorpo
    public ExameRX(Data dataRealizacao, Paciente paciente) {
        this(dataRealizacao, paciente, "Técnico Desconhecido", "Indefinida");
    }

    // Construtor só com data
    public ExameRX(Data dataRealizacao) {
        this(dataRealizacao, new Paciente(), "Técnico Desconhecido", "Indefinida");
    }

    // Construtor por omissão (data atual)
    public ExameRX() {
        this(Data.now(), new Paciente(), "Técnico Desconhecido", "Indefinida");
    }

    @Override
    public double calcularCusto() {
        if (CUSTO_UNITARIO < 0) {
            System.out.println("Erro: Custo unitário não pode ser negativo.");
            return 0;
        }
        return CUSTO_UNITARIO;
    }

    @Override
    public String toString() {
        return super.toString() + ", ExameRX{" +
                "zonaCorpo='" + zonaCorpo + '\'' +
                ", custo=" + calcularCusto() +
                '}';
    }

    // Validação do nome do técnico
    private static String validarTecnico(String nome) {
        if (nome != null && nome.matches("[a-zA-ZÀ-ÿ ]+")) {
            return nome;
        } else {
            System.out.println("Aviso: Nome de técnico inválido. Substituído por 'Técnico Desconhecido'.");
            return "Técnico Desconhecido";
        }
    }

    // Validação da zona do corpo
    private static String validarZona(String zona) {
        if (zona != null && zona.matches("[a-zA-ZÀ-ÿ ]+")) {
            return zona;
        } else {
            System.out.println("Aviso: Zona do corpo inválida. Substituída por 'Indefinida'.");
            return "Indefinida";
        }
    }
}
