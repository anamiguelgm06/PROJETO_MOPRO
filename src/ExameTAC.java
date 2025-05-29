import java.time.LocalDate;

public class ExameTAC extends Exame {
    private double custoUnitario;  // custo variável definido no momento da criação do exame

    // Construtor completo, onde o custoUnitario é obrigatório
    public ExameTAC(LocalDate dataRealizacao, Paciente paciente, String tecnicoResponsavel, double custoUnitario) {
        super(dataRealizacao, paciente, tecnicoResponsavel);
        this.custoUnitario = custoUnitario;
    }

    // Construtor com custoUnitario e tecnicoResponsavel padrão
    public ExameTAC(LocalDate dataRealizacao, Paciente paciente, double custoUnitario) {
        this(dataRealizacao, paciente, "Técnico Desconhecido", custoUnitario);
    }

    // Construtor com custoUnitario, paciente e tecnico padrão
    public ExameTAC(LocalDate dataRealizacao, double custoUnitario) {
        this(dataRealizacao, new Paciente(), "Técnico Desconhecido", custoUnitario);
    }

    // Construtor por omissão, com custoUnitario zero
    public ExameTAC() {
        this(LocalDate.now(), new Paciente(), "Técnico Desconhecido", 0.0);
    }

    // Getter e setter do custoUnitario (se quiseres permitir alteração)
    public double getCustoUnitario() {
        return custoUnitario;
    }

    public void setCustoUnitario(double custoUnitario) {
        this.custoUnitario = custoUnitario;
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
}
