import java.util.ArrayList;
import Exceptions.*;

public class Tecnico implements Calculavel {

    private static final String CEDULA_POR_OMISSAO               = "Sem cédula";
    private static final String NOME_POR_OMISSAO                 = "Sem nome";
    private static final Data DATA_POR_OMISSAO                   = new Data(2000, 1, 1);
    private static final float SALARIO_POR_OMISSAO               = 0.0f;
    private static final float SUBSIDIO_POR_OMISSAO              = 0.0f;
    private static final Especialidade ESPECIALIDADE_POR_OMISSAO = null;

    /* --------- atributos --------- */
    private String cedulaProfissional, nome;
    private Data dataNascimento;
    private float salarioBase, subsidio;
    private Especialidade especialidade;

    /* --------- funcao calcular custo --------- */
    @Override
    public double calcularCusto() {
        if (getSalarioBase() < 0 ) {
            System.out.println("Erro: Salário base não pode ser negativo.");
            return 0;
        }
        if (getSubsidio() < 0 || getSubsidio() > 50){
            System.out.println("Erro: Subsídio tem de estar entre 0% e 50% do salário base.");
            return 0;
        }

        return getSalarioBase() * (1 + (getSubsidio() / 100));
    }

    /* --------- getters / setters --------- */
    public String getCedulaProfissional(){ return this.cedulaProfissional; }

    public void setCedulaProfissional(String cedulaProfissional) throws Excecao {
        if (cedulaProfissional == null || cedulaProfissional.isBlank() || !cedulaProfissional.matches("\\d{6,}")) {
            throw new Excecao("Erro: Cédula inválida, deve ter pelo menos 6 dígitos numéricos");
        }
        this.cedulaProfissional = cedulaProfissional;
    }

    public String getNome(){ return this.nome; }

    public void setNome(String nome) throws Excecao {
        if (nome == null || nome.isEmpty() || !nome.matches("[\\p{L} ]+")) { //Aceita qualquer letra, desde com a até com acentos
            throw new Excecao("Erro: O nome deve conter apenas letras (incluindo acentos) e espaços, e não pode ser vazio.");
        }
        this.nome = nome;
    }

    public Data getDataNascimento(){ return this.dataNascimento; }

    public void setDataNascimento(Data dataNascimento) throws Excecao{
        if (dataNascimento == null) {
            throw new Excecao("Erro: A data de nascimento não é valida.");
        }
        this.dataNascimento = dataNascimento;
    }

    public float getSalarioBase(){ return this.salarioBase; }

    public void setSalarioBase(float salarioBase) throws Excecao {
        if (salarioBase < 0 ) {
            throw new Excecao("Erro: O salário não pode ser negativo.");
        }
        this.salarioBase = salarioBase;
    }

    public float getSubsidio(){ return this.subsidio; }

    public void setSubsidio(float subsidio) throws Excecao {
        if (subsidio < 0 || subsidio > 50) {
            throw new Excecao("Erro: O subsídio tem de se entre 0% e 50%.");
        }
        this.subsidio = subsidio;
    }

    public Especialidade getEspecialidade(){ return especialidade; }

    public void setEspecialidade(Especialidade especialidade) throws Excecao{
        if (especialidade == null) {
            throw new Excecao("Erro: A especialidade não pode ser nula.");
        }
        this.especialidade = especialidade;
    }

    /* --------- toString --------- */
    @Override
    public String toString() {
        return "Tecnico{ " +
                "cedulaProfissional = " + getCedulaProfissional() +
                ", nome = " + getNome() +
                ", salarioBase = " + getSalarioBase() +
                ", subsidio = " + getSubsidio() +
                ", dataNascimento = " + getDataNascimento() +
                ", especialidade = " + getEspecialidade() +
                "} ";
    }

    /* --------- construtor completo --------- */
    public Tecnico(String cedulaProfissional, String nome, float salarioBase, float subsidio, Data dataNascimento, Especialidade especialidade) throws Excecao {
        setCedulaProfissional(cedulaProfissional);
        setNome(nome);
        setSalarioBase(salarioBase);
        setSubsidio(subsidio);
        setDataNascimento(dataNascimento);
        setEspecialidade(especialidade);
    }

    /* --------- construtores mais simples --------- */
    public Tecnico(String cedulaProfissional, String nome, Especialidade especialidade) throws Excecao {
        this(cedulaProfissional, nome, SALARIO_POR_OMISSAO, SUBSIDIO_POR_OMISSAO, DATA_POR_OMISSAO, especialidade);
    }

    public Tecnico(String cedulaProfissional, String nome, float salarioBase, Especialidade especialidade) throws Excecao {
        this(cedulaProfissional, nome, salarioBase, SUBSIDIO_POR_OMISSAO, DATA_POR_OMISSAO, especialidade);
    }

    public Tecnico(String nome, Especialidade especialidade) throws Excecao {
        this(CEDULA_POR_OMISSAO, nome, SALARIO_POR_OMISSAO, SUBSIDIO_POR_OMISSAO, DATA_POR_OMISSAO, especialidade);
    }

    public Tecnico() throws Excecao {
        this(CEDULA_POR_OMISSAO, NOME_POR_OMISSAO, SALARIO_POR_OMISSAO, SUBSIDIO_POR_OMISSAO, DATA_POR_OMISSAO, ESPECIALIDADE_POR_OMISSAO);
    }
}

