import Exceptions.*;

public class ExameTAC extends Exame {

    /* --------- valores por omissão --------- */
    private static final double CUSTO_POR_OMISSAO = 0.0;

    /* --------- atributos --------- */
    private double custoUnitario;

    /* --------- construtor completo --------- */
    public ExameTAC(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel, double custoUnitario) {
        super(dataRealizacao, paciente, tecnicoResponsavel);
        setCustoUnitario(custoUnitario);
    }

    /* --------- construtores mais simples --------- */
    public ExameTAC(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel) {
        this(dataRealizacao, paciente, tecnicoResponsavel, CUSTO_POR_OMISSAO);
    }

    public ExameTAC(Data dataRealizacao, Paciente paciente) {
        this(dataRealizacao, paciente, TECNICO_POR_OMISSAO, CUSTO_POR_OMISSAO);
    }

    public ExameTAC(Data dataRealizacao) {
        this(dataRealizacao, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO, CUSTO_POR_OMISSAO);
    }

    public ExameTAC() {
        this(DATA_POR_OMISSAO, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO, CUSTO_POR_OMISSAO);
    }

    /* --------- getters e setters --------- */
    public double getCustoUnitario() {
        return this.custoUnitario;
    }

    public void setCustoUnitario(double custoUnitario) {
        try {
            if (custoUnitario < 0) {
                throw new Excecao("Erro: Custo unitário não pode ser negativo.");
            }
            this.custoUnitario = custoUnitario;
        } catch (Excecao e) {
            this.custoUnitario = CUSTO_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    /* --------- calcular custo --------- */
    @Override
    public double calcularCusto() {
        try {
            if (custoUnitario < 0) {
                throw new ExcecaoRuntime("Erro: Custo unitário não pode ser negativo.");
            }
            return custoUnitario;
        } catch (ExcecaoRuntime e) {
            System.out.println(e + " Usando valor por omissão.");
            return 0;
        }
    }

    /* --------- toString --------- */
    @Override
    public String toString() {
        try {
            return super.toString() +
                    ", ExameTAC{ " +
                    "custoUnitário=" + getCustoUnitario() +
                    ", custo=" + calcularCusto() +
                    " }";
        } catch (ExcecaoRuntime e) {
            throw new RuntimeException(e);
        }
    }
}