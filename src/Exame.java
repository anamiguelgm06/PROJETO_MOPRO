import Exceptions.Excecao;

public abstract class Exame implements Calculavel, Comparable<Exame> {

    /* --------- valores por omissão--------- */
    protected static final Data DATA_POR_OMISSAO = Data.now();

    protected static final Paciente PACIENTE_POR_OMISSAO;
    static { PACIENTE_POR_OMISSAO = new Paciente(); }

    protected static final Tecnico TECNICO_POR_OMISSAO;
    static { TECNICO_POR_OMISSAO = new Tecnico(); }

    /* --------- contador de codigos para garantir unicidade --------- */
    private static int contadorCodigos = 1;

    /* --------- atributos --------- */
    private int codigo;
    private Data dataRealizacao;
    private Paciente paciente;
    private Tecnico tecnicoResponsavel;

    /* --------- construtor completo --------- */
    public Exame(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel) {
        this.codigo = contadorCodigos++;
        setDataRealizacao(dataRealizacao);
        setPaciente(paciente);
        setTecnicoResponsavel(tecnicoResponsavel);

    }

    /* --------- construtores mais simples --------- */
    public Exame(Data dataRealizacao, Paciente paciente) {
        this(dataRealizacao, paciente, TECNICO_POR_OMISSAO);
    }

    public Exame(Data dataRealizacao) {
        this(dataRealizacao, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO);
    }

    public Exame() {
        this(DATA_POR_OMISSAO, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO);
    }

    /* --------- getters e setters --------- */
    public int getCodigo() { return codigo; }

    public Data getDataRealizacao() { return dataRealizacao; }

    public void setDataRealizacao(Data data) {
        try {
            if (data == null) {
                throw new Excecao("Erro: Data inválida.");
            }
            this.dataRealizacao = data;
        } catch (Excecao e) {
            this.dataRealizacao = DATA_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public Paciente getPaciente() { return paciente; }

    public void setPaciente(Paciente paciente) {
        try {
            if (paciente == null) {
                throw new Excecao("Erro: Paciente inválido.");
            }
            this.paciente = paciente;
        } catch (Excecao e) {
            this.paciente = PACIENTE_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public Tecnico getTecnicoResponsavel() { return tecnicoResponsavel; }

    public void setTecnicoResponsavel(Tecnico tecnico) {
        try {
            if (tecnico == null) {
                throw new Excecao("Erro: Técnico inválido.");
            }
            this.tecnicoResponsavel = tecnico;
        } catch (Excecao e) {
            this.tecnicoResponsavel = TECNICO_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }


    /* --------- toString --------- */
    @Override
    public String toString() {
        return "Exame{ " +
                "código=" + getCodigo() +
                ", dataRealização=" + getDataRealizacao() +
                ", paciente=" + getPaciente().getNome() +
                ", técnicoResponsável=" + getTecnicoResponsavel().getNome() +
                " }";
    }

    /* --------- equals --------- */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exame)) return false;
        Exame outro = (Exame) o;
        return this.getCodigo() == outro.getCodigo();
    }

    @Override
    public int compareTo(Exame outro){
        return getTecnicoResponsavel().getNome().compareTo(outro.getTecnicoResponsavel().getNome());
    }
}
