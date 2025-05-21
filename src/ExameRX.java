import java.time.LocalDate;

public class ExameRX extends Exame {
    private String zonaCorpo;
    private final double CUSTO_UNITARIO = 24.0;

    //Construtor
    public ExameRX(LocalDate dataRealizacao, Pacientes paciente, String tecnicoResponsavel, String zonaCorpo) {
        super(dataRealizacao, paciente, tecnicoResponsavel); //Herda os atributos da superclasse Exame
        this.zonaCorpo = zonaCorpo;
    }

    //Método que devolve o custo final do exame
    @Override
    public double calcularCusto() {
        return CUSTO_UNITARIO;
    }

    //ToString
    @Override
    public String toString() {
        return super.toString() + ", ExameRX{" +
                "zonaCorpo='" + zonaCorpo + '\'' +
                ", custo=" + calcularCusto() +
                '}';
    }
}

