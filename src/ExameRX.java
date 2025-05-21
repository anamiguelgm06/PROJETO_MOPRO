import java.time.LocalDate;

public class ExameRX extends Exame {
    private String zonaCorpo;
    private final double CUSTO_UNITARIO = 24.0;

    // Construtor completo
    public ExameRX(LocalDate dataRealizacao, Pacientes paciente, String tecnicoResponsavel, String zonaCorpo) {
        super(dataRealizacao, paciente, tecnicoResponsavel);
        this.zonaCorpo = zonaCorpo;
    }

    // Construtor sem zonaCorpo
    public ExameRX(LocalDate dataRealizacao, Pacientes paciente, String tecnicoResponsavel) {
        this(dataRealizacao, paciente, tecnicoResponsavel, "Indefinida");
    }

    // Construtor sem tecnicoResponsavel e zonaCorpo
    public ExameRX(LocalDate dataRealizacao, Pacientes paciente) {
        this(dataRealizacao, paciente, "Técnico Desconhecido", "Indefinida");
    }

    // Construtor só com dataRealizacao
    public ExameRX(LocalDate dataRealizacao) {
        this(dataRealizacao, new Pacientes(), "Técnico Desconhecido", "Indefinida");
    }

    // Construtor por omissão (sem parâmetros)
    public ExameRX() {
        this(LocalDate.now(), new Pacientes(), "Técnico Desconhecido", "Indefinida");
    }

    @Override
    public double calcularCusto() {
        return CUSTO_UNITARIO;
    }

    @Override
    public String toString() {
        return super.toString() + ", ExameRX{" +
                "zonaCorpo='" + zonaCorpo + '\'' +
                ", custo=" + calcularCusto() +
                '}';
    }
}
