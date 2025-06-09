import Exceptions.Excecao;

public class ExameRX extends Exame {

    /* --------- valores por omissao --------- */
    private static final String ZONA_POR_OMISSAO = "Zona indefinida";

    /* --------- atributos --------- */
    private String zonaCorpo;
    private final double CUSTO_UNITARIO;

    /* --------- construtor completo --------- */
    public ExameRX(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel, String zonaCorpo) throws Excecao {
        super(dataRealizacao, paciente, tecnicoResponsavel);

        try {
            setZonaCorpo(zonaCorpo);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.zonaCorpo = ZONA_POR_OMISSAO;
        }

        this.CUSTO_UNITARIO = 24.0;
    }

    /* --------- construtores mais simples --------- */
    public ExameRX(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel) throws Excecao {
        this(dataRealizacao, paciente, tecnicoResponsavel, ZONA_POR_OMISSAO);
    }

    public ExameRX(Data dataRealizacao, Paciente paciente) throws Excecao {
        this(dataRealizacao, paciente, TECNICO_POR_OMISSAO, ZONA_POR_OMISSAO);
    }

    public ExameRX(Data dataRealizacao) throws Excecao {
        this(dataRealizacao, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO, ZONA_POR_OMISSAO);
    }

    public ExameRX() throws Excecao {
        this(DATA_POR_OMISSAO, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO, ZONA_POR_OMISSAO);
    }

    /* --------- getters e setters --------- */
    public String getZonaCorpo(){
        return this.zonaCorpo;
    }

    public void setZonaCorpo(String zona) throws Excecao {
        if (!(zona != null && zona.matches("[a-zA-ZÀ-ÿ ]+"))) {
            throw new Excecao("Erro: Zona do corpo inválida.");
        }
        this.zonaCorpo = zona;
    }

    public double getCusto(){
        return this.CUSTO_UNITARIO;
    }

    /* --------- calcular custo --------- */
    @Override
    public double calcularCusto() {
        if (CUSTO_UNITARIO < 0) {
            System.out.println("Erro: Custo unitário não pode ser negativo.");
            return 0;
        }
        return CUSTO_UNITARIO;
    }

    /* --------- toString --------- */
    @Override
    public String toString() {
        return super.toString() + ", ExameRX{ " +
                "zonaCorpo=" + getZonaCorpo() +
                ", custo=" + calcularCusto() +
                " }";
    }
}
