import java.time.LocalDate;

public abstract class Exame implements Calculavel {
    private static int contadorCodigos = 0;

    protected int codigo;
    protected Data dataRealizacao;
    protected Paciente paciente;
    protected String tecnicoResponsavel;

    // Construtor completo
    protected Exame(Data dataRealizacao, Paciente paciente, String tecnicoResponsavel) {
        this.codigo = contadorCodigos++;
        this.dataRealizacao = dataRealizacao;
        this.paciente = paciente;
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    // Construtor sem técnico
    protected Exame(Data dataRealizacao, Paciente paciente) {
        this(dataRealizacao, paciente, "Técnico Desconhecido");
    }

    // Construtor só com data
    protected Exame(Data dataRealizacao) {
        this(dataRealizacao, new Paciente(), "Técnico Desconhecido");
    }

    // Construtor sem argumentos - usa a data atual do sistema
    protected Exame() {
        this.codigo = contadorCodigos++;
        this.dataRealizacao = Data.now();
        this.paciente = new Paciente();
        this.tecnicoResponsavel = "Técnico Desconhecido";
    }

    // Getters
    public int getCodigo() {
        return codigo;
    }

    public Data getDataRealizacao() {
        return dataRealizacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    @Override
    public String toString() {
        return "Exame{ " +
                "codigo=" + codigo +
                ", dataRealizacao=" + dataRealizacao.toString() +
                ", paciente=" + paciente.getNome() +
                ", tecnicoResponsavel='" + tecnicoResponsavel + '\'' +
                "} ";
    }
}
