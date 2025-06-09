import Exceptions.Excecao;

public class ExameTAC extends Exame {

    /* --------- valores por omissão --------- */
    private static final double CUSTO_POR_OMISSAO = 0.0;

    /* --------- atributos --------- */
    private double custoUnitario;

    /* --------- construtor completo --------- */
    public ExameTAC(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel, double custoUnitario) throws Excecao {
        super(dataRealizacao, paciente, tecnicoResponsavel);

        try {
            setCustoUnitario(custoUnitario);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.custoUnitario = CUSTO_POR_OMISSAO;
        }
    }

    /* --------- construtores mais simples --------- */
    public ExameTAC(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel) throws Excecao {
        this(dataRealizacao, paciente, tecnicoResponsavel, CUSTO_POR_OMISSAO);
    }

    public ExameTAC(Data dataRealizacao, Paciente paciente) throws Excecao {
        this(dataRealizacao, paciente, TECNICO_POR_OMISSAO, CUSTO_POR_OMISSAO);
    }

    public ExameTAC(Data dataRealizacao) throws Excecao {
        this(dataRealizacao, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO, CUSTO_POR_OMISSAO);
    }

    public ExameTAC() throws Excecao {
        this(DATA_POR_OMISSAO, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO, CUSTO_POR_OMISSAO);
    }

    /* --------- getters e setters --------- */
    public double getCustoUnitario() {
        return this.custoUnitario;
    }

    public void setCustoUnitario(double custoUnitario) throws Excecao {
        if (custoUnitario < 0) {
            throw new Excecao("Erro: Custo unitário não pode ser negativo.");
        }
        this.custoUnitario = custoUnitario;
    }

    /* --------- calcular custo --------- */
    @Override
    public double calcularCusto() {
        if (custoUnitario < 0) {
            System.out.println(
                    "Erro: Custo unitário não pode ser negativo."
            );
            return 0;
        }
        return custoUnitario;
    }

    /* --------- toString --------- */
    @Override
    public String toString() {
        return super.toString() +
                ", ExameTAC{ " +
                "custoUnitário=" + getCustoUnitario() +
                ", custo=" + calcularCusto() +
                " }";
    }
}