import java.time.LocalDate;

public abstract class Exame implements Calculavel {
    private static int contadorCodigos = 1;

    // Permitir acesso às variáveis dentro e fora da pasta
    protected int codigo;
    protected LocalDate dataRealizacao;
    protected Pacientes paciente;
    protected String tecnicoResponsavel;

    // Construtor completo
    protected Exame(LocalDate dataRealizacao, Pacientes paciente, String tecnicoResponsavel) {
        this.codigo = contadorCodigos++;
        this.dataRealizacao = dataRealizacao;
        this.paciente = paciente;
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    // Construtor sem técnico
    protected Exame(LocalDate dataRealizacao, Pacientes paciente) {
        this(dataRealizacao, paciente, "Técnico Desconhecido");
    }

    // Construtor só com data
    protected Exame(LocalDate dataRealizacao) {
        this(dataRealizacao, new Pacientes(), "Técnico Desconhecido");
    }

    // Construtor sem argumentos
    protected Exame() {
        this(LocalDate.now(), new Pacientes(), "Técnico Desconhecido");
    }

    // Getters
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

    // toString
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
