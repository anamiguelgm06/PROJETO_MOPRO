import Exceptions.Excecao;

public abstract class Exame implements Calculavel {

    /* --------- valores por omissão--------- */
    protected static final Data DATA_POR_OMISSAO = Data.now();

    protected static final Paciente PACIENTE_POR_OMISSAO;
    static {
        try {
            PACIENTE_POR_OMISSAO = new Paciente();
        } catch (Excecao e) {
            throw new RuntimeException(e);
        }
    }

    protected static final Tecnico TECNICO_POR_OMISSAO;
    static {
        try {
            TECNICO_POR_OMISSAO = new Tecnico();
        } catch (Excecao e) {
            throw new RuntimeException(e);
        }
    }

    /* --------- contador de codigos para garantir unicidade --------- */
    private static int contadorCodigos = 0;

    /* --------- atributos --------- */
    private int codigo;
    private Data dataRealizacao;
    private Paciente paciente;
    private Tecnico tecnicoResponsavel;

    /* --------- construtor completo --------- */
    public Exame(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel) throws Excecao {
        this.codigo = contadorCodigos++;

        try {
            setDataRealizacao(dataRealizacao);
        } catch (Excecao e) {
            System.out.println(e + " Usando valores por omissão.");
            this.dataRealizacao =DATA_POR_OMISSAO;
        }

        try {
            setPaciente(paciente);
        } catch (Excecao e) {
            System.out.println(e + " Usando valores por omissão.");
            this.paciente = PACIENTE_POR_OMISSAO;
        }

        try {
            setTecnicoResponsavel(tecnicoResponsavel);
        } catch (Excecao e) {
            System.out.println(e + " Usando valores por omissão.");
            this.tecnicoResponsavel = TECNICO_POR_OMISSAO;
        }

    }

    /* --------- construtores mais simples --------- */
    public Exame(Data dataRealizacao, Paciente paciente) throws Excecao {
        this(dataRealizacao, paciente, TECNICO_POR_OMISSAO);
    }

    public Exame(Data dataRealizacao) throws Excecao {
        this(dataRealizacao, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO);
    }

    public Exame() throws Excecao {
        this(DATA_POR_OMISSAO, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO);
    }

    /* --------- getters e setters --------- */
    public int getCodigo() { return codigo; }

    public Data getDataRealizacao() { return dataRealizacao; }

    public void setDataRealizacao(Data data) throws Excecao {
        if (data == null) {
            throw new Excecao("Erro: Data inválida.");
        }
        this.dataRealizacao = data;
    }

    public Paciente getPaciente() { return paciente; }

    public void setPaciente(Paciente paciente) throws Excecao {
        if (paciente == null) {
            throw new Excecao("Erro: Paciente inválido.");
        }
        this.paciente = paciente;
    }

    public Tecnico getTecnicoResponsavel() { return tecnicoResponsavel; }

    public void setTecnicoResponsavel(Tecnico tecnico) throws Excecao {
        if (tecnico == null) {
            throw new Excecao("Erro: Técnico inválido.");
        }
        this.tecnicoResponsavel = tecnico;
    }


    /* --------- toString --------- */
    @Override
    public String toString() {
        return "Exame{ " +
                "código=" + getCodigo() +
                ", dataRealização=" + getDataRealizacao() +
                ", paciente=" + getPaciente().getNome() +
                ", técnicoResponsável=" + getTecnicoResponsavel().getCedulaProfissional() +
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
}
