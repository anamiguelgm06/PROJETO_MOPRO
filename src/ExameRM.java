import Exceptions.Excecao;

public class ExameRM extends Exame {

    /* --------- valores por omissao--------- */
    private static final boolean CONTRASTE_POR_OMISSAO = false;

    /* --------- atributos --------- */
    private boolean usaContraste;
    private final double CUSTO_BASE;

    /* --------- construtor completo --------- */
    public ExameRM(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel, boolean usaContraste) throws Excecao {
        super(dataRealizacao, paciente, tecnicoResponsavel);
        setContraste(usaContraste);
        this.CUSTO_BASE = 32.0;
    }

    /* --------- construtores mais simples --------- */
    public ExameRM(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel) throws Excecao {
        this(dataRealizacao, paciente, tecnicoResponsavel, CONTRASTE_POR_OMISSAO);
    }

    public ExameRM(Data dataRealizacao, Paciente paciente) throws Excecao {
        this(dataRealizacao, paciente, TECNICO_POR_OMISSAO, CONTRASTE_POR_OMISSAO);
    }

    public ExameRM(Data dataRealizacao) throws Excecao {
        this(dataRealizacao, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO, CONTRASTE_POR_OMISSAO);
    }

    public ExameRM() throws Excecao {
        this(DATA_POR_OMISSAO, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO, CONTRASTE_POR_OMISSAO);
    }

    /* --------- getters e setters --------- */
    public boolean getContraste(){
        return this.usaContraste;
    }

    public void setContraste(boolean contraste) {
        this.usaContraste = contraste;
    }

    public double getCustoBase() {
        return this.CUSTO_BASE;
    }

    /* --------- calcular custo --------- */
    @Override
    public double calcularCusto() {
        if (CUSTO_BASE < 0) {
            System.out.println("Erro: Custo base não pode ser negativo.");
            return 0;
        }
        return usaContraste ? CUSTO_BASE * 1.4 : CUSTO_BASE;
    }

    /* --------- toString --------- */
    @Override
    public String toString() {
        return super.toString() + ", ExameRM{ " +
                "usaContraste=" + getContraste() +
                ", custo=" + calcularCusto() +
                " }";
    }
}
