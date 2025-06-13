import Exceptions.*;

public class ExameRM extends Exame{

    /* --------- valores por omissao--------- */
    private static final boolean CONTRASTE_POR_OMISSAO = false;

    /* --------- atributos --------- */
    private boolean usaContraste;
    private final double CUSTO_BASE;

    /* --------- construtor completo --------- */
    public ExameRM(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel, boolean usaContraste) {
        super(dataRealizacao, paciente, tecnicoResponsavel);
        setContraste(usaContraste);
        this.CUSTO_BASE = 32.0;
    }

    /* --------- construtores mais simples --------- */
    public ExameRM(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel) {
        this(dataRealizacao, paciente, tecnicoResponsavel, CONTRASTE_POR_OMISSAO);
    }

    public ExameRM(Data dataRealizacao, Paciente paciente) {
        this(dataRealizacao, paciente, TECNICO_POR_OMISSAO, CONTRASTE_POR_OMISSAO);
    }

    public ExameRM(Data dataRealizacao) {
        this(dataRealizacao, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO, CONTRASTE_POR_OMISSAO);
    }

    public ExameRM() {
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
    public double calcularCusto(){
        try {
            if (CUSTO_BASE < 0) {
                throw new ExcecaoRuntime("Erro: Custo base não pode ser negativo.");
            }

            if (usaContraste) return CUSTO_BASE * 1.4;
            else return CUSTO_BASE;
        } catch (ExcecaoRuntime e) {
            System.out.println(e + " Usando valor por omissão.");
            return 0;
        }
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
