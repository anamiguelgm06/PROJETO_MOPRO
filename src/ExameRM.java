import java.time.LocalDate;

public class ExameRM extends Exame {
    private boolean usaContraste;
    private final double CUSTO_BASE = 32.0;

    // Construtor completo
    public ExameRM(LocalDate dataRealizacao, Pacientes paciente, String tecnicoResponsavel, boolean usaContraste) {
        super(dataRealizacao, paciente, tecnicoResponsavel);
        this.usaContraste = usaContraste;
    }

    // Construtor sem usaContraste
    public ExameRM(LocalDate dataRealizacao, Pacientes paciente, String tecnicoResponsavel) {
        this(dataRealizacao, paciente, tecnicoResponsavel, false);
    }

    // Construtor sem tecnicoResponsavel e usaContraste
    public ExameRM(LocalDate dataRealizacao, Pacientes paciente) {
        this(dataRealizacao, paciente, "Técnico Desconhecido", false);
    }

    // Construtor só com data
    public ExameRM(LocalDate dataRealizacao) {
        this(dataRealizacao, new Pacientes(), "Técnico Desconhecido", false);
    }

    // Construtor por omissão (sem parâmetros)
    public ExameRM() {
        this(LocalDate.now(), new Pacientes(), "Técnico Desconhecido", false);
    }

    @Override
    public double calcularCusto() {
        if (usaContraste) {
            return CUSTO_BASE * 1.4; // aumenta 40%
        }
        return CUSTO_BASE;
    }

    @Override
    public String toString() {
        return super.toString() + ", ExameRM{" +
                "usaContraste=" + usaContraste +
                ", custo=" + calcularCusto() +
                '}';
    }
}
