public class ExameTAC extends Exame {
    private double custoUnitario;

    // Construtor completo
    public ExameTAC(Data dataRealizacao, Paciente paciente, String tecnicoResponsavel, double custoUnitario) {
        super(dataRealizacao, paciente, validarTecnico(tecnicoResponsavel));
        this.custoUnitario = validarCusto(custoUnitario);
    }

    // Construtor com técnico padrão
    public ExameTAC(Data dataRealizacao, Paciente paciente, double custoUnitario) {
        this(dataRealizacao, paciente, "Técnico Desconhecido", custoUnitario);
    }

    // Construtor com paciente e técnico padrão
    public ExameTAC(Data dataRealizacao, double custoUnitario) {
        this(dataRealizacao, new Paciente(), "Técnico Desconhecido", custoUnitario);
    }

    // Construtor por omissão (data atual, custo 0)
    public ExameTAC() {
        this(Data.now(), new Paciente(), "Técnico Desconhecido", 0.0);
    }

    // Getters e Setters com validação
    public double getCustoUnitario() {
        return custoUnitario;
    }

    public void setCustoUnitario(double custoUnitario) {
        this.custoUnitario = validarCusto(custoUnitario);
    }

    @Override
    public double calcularCusto() {
        return custoUnitario;
    }

    @Override
    public String toString() {
        return super.toString() + ", ExameTAC{" +
                "custoUnitario=" + custoUnitario +
                ", custo=" + calcularCusto() +
                '}';
    }

    // Validação do custo (não pode ser negativo)
    private static double validarCusto(double custo) {
        if (custo < 0) {
            System.out.println("Aviso: Custo negativo fornecido. Substituído por 0.0");
            return 0.0;
        }
        return custo;
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
}
