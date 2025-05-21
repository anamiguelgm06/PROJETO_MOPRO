import java.time.LocalDate;

public class ExameRM extends Exame {
    private boolean usaContraste;
    private final double CUSTO_BASE = 32.0;

    //Construtor
    public ExameRM(LocalDate dataRealizacao, Pacientes paciente, String tecnicoResponsavel, boolean usaContraste) {
        super(dataRealizacao, paciente, tecnicoResponsavel);
        this.usaContraste = usaContraste;
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
