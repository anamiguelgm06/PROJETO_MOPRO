import Exceptions.*;

public class ExameRX extends Exame {

    /* --------- valores por omissao --------- */
    private static final String ZONA_POR_OMISSAO = "Zona indefinida";

    /* --------- atributos --------- */
    private String zonaCorpo;
    private final double CUSTO_UNITARIO;

    /* --------- construtor completo --------- */
    public ExameRX(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel, String zonaCorpo) {
        super(dataRealizacao, paciente, tecnicoResponsavel);
        setZonaCorpo(zonaCorpo);
        this.CUSTO_UNITARIO = 24.0;
    }

    /* --------- construtores mais simples --------- */
    public ExameRX(Data dataRealizacao, Paciente paciente, Tecnico tecnicoResponsavel) {
        this(dataRealizacao, paciente, tecnicoResponsavel, ZONA_POR_OMISSAO);
    }

    public ExameRX(Data dataRealizacao, Paciente paciente) {
        this(dataRealizacao, paciente, TECNICO_POR_OMISSAO, ZONA_POR_OMISSAO);
    }

    public ExameRX(Data dataRealizacao) {
        this(dataRealizacao, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO, ZONA_POR_OMISSAO);
    }

    public ExameRX() {
        this(DATA_POR_OMISSAO, PACIENTE_POR_OMISSAO, TECNICO_POR_OMISSAO, ZONA_POR_OMISSAO);
    }

    /* --------- getters e setters --------- */
    public String getZonaCorpo(){
        return this.zonaCorpo;
    }

    public void setZonaCorpo(String zona) {
        try {
            if (!(zona != null && zona.matches("[a-zA-ZÀ-ÿ ]+"))) {
                throw new Excecao("Erro: Zona do corpo inválida.");
            }
            this.zonaCorpo = zona;
        } catch (Excecao e) {
            this.zonaCorpo = ZONA_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public double getCusto(){
        return this.CUSTO_UNITARIO;
    }

    /* --------- calcular custo --------- */
    @Override
    public double calcularCusto() {
        try {
            if (CUSTO_UNITARIO < 0) {
                throw new ExcecaoRuntime("Erro: Custo unitário não pode ser negativo.");
            }
            return CUSTO_UNITARIO;
        } catch (ExcecaoRuntime e) {
            System.out.println(e + " Usando valor por omissão.");
            return 0;
        }
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
