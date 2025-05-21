import java.time.LocalDate;

public abstract class Exame implements Calculavel {
    private static int contadorCodigos = 1;

    //Permitir acesso às variáveis dentro e fora da pasta
    protected int codigo;
    protected LocalDate dataRealizacao;
    protected Pacientes paciente;
    protected String tecnicoResponsavel;

    //Construtor
    public Exame(LocalDate dataRealizacao, Pacientes paciente, String tecnicoResponsavel) {
        this.codigo = contadorCodigos++;
        this.dataRealizacao = dataRealizacao;
        this.paciente = paciente;
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    //Get
    public int getCodigo() {
        return codigo;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public String getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    //ToString
    @Override
    public String toString() {
        return "Exame{" +
                "codigo=" + codigo +
                ", dataRealizacao=" + dataRealizacao +
                ", paciente=" + paciente.getNome() +
                ", tecnicoResponsavel='" + tecnicoResponsavel + '\'' +
                '}';
    }
}

