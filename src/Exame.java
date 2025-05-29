import java.time.LocalDate;

public abstract class Exame implements Calculavel {
    private static int contadorCodigos = 1;

    // Permitir acesso às variáveis dentro e fora da pasta
    protected int codigo;
    protected LocalDate dataRealizacao;
    protected Paciente paciente;
    protected String tecnicoResponsavel;

    // Construtor completo
    protected Exame(LocalDate dataRealizacao, Paciente paciente, String tecnicoResponsavel) {
        this.codigo = contadorCodigos++;
        this.dataRealizacao = dataRealizacao;
        this.paciente = paciente;
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    // Construtor sem técnico
    protected Exame(LocalDate dataRealizacao, Paciente paciente) {
        this(dataRealizacao, paciente, "Técnico Desconhecido");
    }

    // Construtor só com data
    protected Exame(LocalDate dataRealizacao) {
        this(dataRealizacao, new Paciente(), "Técnico Desconhecido");
    }

    // Construtor sem argumentos
    protected Exame() {
        this(LocalDate.now(), new Paciente(), "Técnico Desconhecido");
    }

    // Getters
    public int getCodigo() {
        return codigo;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public Paciente getPaciente() {
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
