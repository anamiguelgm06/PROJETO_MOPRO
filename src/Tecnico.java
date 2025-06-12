import Exceptions.Excecao;

import java.util.ArrayList;

public class Tecnico implements Calculavel {

    /* --------- valores por omissão--------- */
    private static final String CEDULA_POR_OMISSAO               = "Sem cédula";
    private static final String NOME_POR_OMISSAO                 = "Sem nome";
    private static final Data DATA_POR_OMISSAO                   = new Data(2000, 1, 1);
    private static final float SALARIO_POR_OMISSAO               = 0.0f;
    private static final float SUBSIDIO_POR_OMISSAO              = 0.0f;
    private static final Especialidade ESPECIALIDADE_POR_OMISSAO = Especialidade.RX;

    /* --------- campo estático para garantir unicidade --------- */
    private static ArrayList<String> cedulasUsadas = new ArrayList<>();

    /* --------- atributos --------- */
    private String cedulaProfissional, nome;
    private Data dataNascimento;
    private float salarioBase, subsidio;
    private Especialidade especialidade;

    /* --------- construtor completo --------- */
    public Tecnico(String cedulaProfissional, String nome, float salarioBase, float subsidio, Data dataNascimento, Especialidade especialidade) throws Excecao {
        try {
            setCedulaProfissional(cedulaProfissional);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.cedulaProfissional = CEDULA_POR_OMISSAO;
        }

        try {
            setNome(nome);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.nome = NOME_POR_OMISSAO;
        }

        try {
            setSalarioBase(salarioBase);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.salarioBase = SALARIO_POR_OMISSAO;
        }

        try {
            setSubsidio(subsidio);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.subsidio = SUBSIDIO_POR_OMISSAO;
        }

        try {
            setDataNascimento(dataNascimento);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.dataNascimento = DATA_POR_OMISSAO;
        }

        try {
            setEspecialidade(especialidade);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.especialidade = ESPECIALIDADE_POR_OMISSAO;
        }
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

    /* --------- getters e setters --------- */
    public String getCedulaProfissional(){ return this.cedulaProfissional; }

    public void setCedulaProfissional(String cedulaProfissional) throws Excecao {
        if (cedulaProfissional == null || cedulaProfissional.isBlank()) {
            throw new Excecao("Erro: Cédula inválida, não pode estar vazia.");
        }
        if (cedulasUsadas.contains(cedulaProfissional)) {
            throw new Excecao("Erro: Cédula já está em uso por outro técnico");
        }
        cedulasUsadas.add(cedulaProfissional);
        this.cedulaProfissional = cedulaProfissional;
    }

    public String getNome(){ return this.nome; }

    public void setNome(String nome) throws Excecao {
        if (nome == null || nome.isEmpty() || !nome.matches("[a-zA-ZÀ-ÿ ]+")) {
            throw new Excecao("Erro: O nome deve conter apenas letras (incluindo acentos) e espaços, e não pode estar vazio.");
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

    /* --------- toString --------- */
    @Override
    public String toString() {
        return "Técnico{ " +
                "cédulaProfissional = " + getCedulaProfissional() +
                ", nome = " + getNome() +
                ", salárioBase = " + getSalarioBase() +
                ", subsidio = " + getSubsidio() +
                ", dataNascimento = " + getDataNascimento() +
                ", especialidade = " + getEspecialidade() +
                " }";
    }

    /* --------- equals --------- */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tecnico)) return false;
        Tecnico outro = (Tecnico) o;
        return this.getCedulaProfissional().equals(outro.getCedulaProfissional());
    }
}

