import Exceptions.Excecao;
import Exceptions.ExcecaoRuntime;

import java.util.ArrayList;

public class Tecnico implements Calculavel, EspecialidadeDiagnostico {

    /* --------- valores por omissão--------- */
    private static final String CEDULA_POR_OMISSAO               = "Sem cédula";
    private static final String NOME_POR_OMISSAO                 = "Sem nome";
    private static final Data DATA_POR_OMISSAO                   = new Data(2000, 1, 1);
    private static final float SALARIO_POR_OMISSAO               = 0.0f;
    private static final float SUBSIDIO_POR_OMISSAO              = 0.0f;
    private static final String ESPECIALIDADE_POR_OMISSAO        = "Sem especialidade";

    /* --------- campo estático para garantir unicidade --------- */
    private static ArrayList<String> cedulasUsadas = new ArrayList<>();

    /* --------- atributos --------- */
    private String cedulaProfissional, nome;
    private Data dataNascimento;
    private float salarioBase, subsidio;
    private String especialidade;

    /* --------- construtor completo --------- */
    public Tecnico(String cedulaProfissional, String nome, float salarioBase, float subsidio, Data dataNascimento, String especialidade) {
        setCedulaProfissional(cedulaProfissional);
        setNome(nome);
        setSalarioBase(salarioBase);
        setSubsidio(subsidio);
        setDataNascimento(dataNascimento);
        setEspecialidade(especialidade);
    }

    /* --------- construtores mais simples --------- */
    public Tecnico(String cedulaProfissional, String nome, String especialidade){
        this(cedulaProfissional, nome, SALARIO_POR_OMISSAO, SUBSIDIO_POR_OMISSAO, DATA_POR_OMISSAO, especialidade);
    }

    public Tecnico(String cedulaProfissional, String nome, float salarioBase, String especialidade){
        this(cedulaProfissional, nome, salarioBase, SUBSIDIO_POR_OMISSAO, DATA_POR_OMISSAO, especialidade);
    }

    public Tecnico(String nome, String especialidade){
        this(CEDULA_POR_OMISSAO, nome, SALARIO_POR_OMISSAO, SUBSIDIO_POR_OMISSAO, DATA_POR_OMISSAO, especialidade);
    }

    public Tecnico(){
        this(CEDULA_POR_OMISSAO, NOME_POR_OMISSAO, SALARIO_POR_OMISSAO, SUBSIDIO_POR_OMISSAO, DATA_POR_OMISSAO, ESPECIALIDADE_POR_OMISSAO);
    }

    /* --------- getters e setters --------- */
    public String getCedulaProfissional(){ return this.cedulaProfissional; }

    public void setCedulaProfissional(String cedulaProfissional) {
        try {

            if(cedulaProfissional.equals(CEDULA_POR_OMISSAO)){
                this.cedulaProfissional = CEDULA_POR_OMISSAO;
                return;
            }

            if (cedulaProfissional == null || cedulaProfissional.isBlank()) {
                throw new Excecao("Erro: Cédula inválida, não pode estar vazia.");
            }
            if (cedulasUsadas.contains(cedulaProfissional)) {
                throw new Excecao("Erro: Cédula já está em uso por outro técnico");
            }
            cedulasUsadas.add(cedulaProfissional);
            this.cedulaProfissional = cedulaProfissional;

        } catch (Excecao e) {
            this.cedulaProfissional = CEDULA_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public String getNome(){ return this.nome; }

    public void setNome(String nome){
        try {
            if (nome == null || nome.isEmpty() || !nome.matches("[a-zA-ZÀ-ÿ ]+")) {
                throw new Excecao("Erro: O nome deve conter apenas letras (incluindo acentos) e espaços, e não pode estar vazio.");
            }
            this.nome = nome;
        } catch (Excecao e) {
            this.nome = NOME_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public Data getDataNascimento(){ return this.dataNascimento; }

    public void setDataNascimento(Data dataNascimento){
        try {
            if (dataNascimento == null) {
                throw new Excecao("Erro: A data de nascimento não é valida.");
            }
            this.dataNascimento = dataNascimento;
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public float getSalarioBase(){ return this.salarioBase; }

    public void setSalarioBase(float salarioBase){
        try {
            if (salarioBase < 0 ) {
                throw new Excecao("Erro: O salário não pode ser negativo.");
            }
            this.salarioBase = salarioBase;
        } catch (Excecao e) {
            this.salarioBase = SALARIO_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public float getSubsidio(){ return this.subsidio; }

    public void setSubsidio(float subsidio){
        try {
            if (subsidio < 0 || subsidio > 50) {
                throw new Excecao("Erro: O subsídio tem de se entre 0% e 50%.");
            }
            this.subsidio = subsidio;
        } catch (Excecao e) {
            this.subsidio = SUBSIDIO_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

     public void setEspecialidade(String especialidade){
         try {
             if (especialidade == null) {
                 throw new Excecao("Erro: A especialidade não pode ser nula.");
             }
             this.especialidade = especialidade;
         } catch (Excecao e) {
             System.out.println(e + " Usando valor por omissão.");
         }
     }

    /* --------- funcao calcular custo --------- */
    @Override
    public double calcularCusto() {
        try {
            if (getSalarioBase() < 0 ) {
                throw new ExcecaoRuntime("Erro: Salário base não pode ser negativo.");
            }
            if (getSubsidio() < 0 || getSubsidio() > 50){
                throw new ExcecaoRuntime("Erro: Subsídio tem de estar entre 0% e 50% do salário base.");
            }

            return getSalarioBase() * (1 + (getSubsidio() / 100));
        } catch (ExcecaoRuntime e) {
            System.out.println(e + " Usando valor por omissão.");
            return 0;
        }
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
                ", especialidade = " + getNomeEspecialidade() +
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

    /* --------- funcao da interface --------- */
    @Override
    public String getNomeEspecialidade() {
        return this.nome;
    }
}

