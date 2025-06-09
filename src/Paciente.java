import java.util.ArrayList;
import Exceptions.Excecao;

public class Paciente {

    /* --------- valores por omissão--------- */
    private static final int NUMERO_POR_OMISSAO   = 0;
    private static final String NOME_POR_OMISSAO  = "Sem nome";
    private static final char GENERO_POR_OMISSAO  = 'X';
    private static final Data DATA_POR_OMISSAO    = new Data(2000, 1, 1);

    /* --------- campo estático para garantir unicidade --------- */
    private static ArrayList<Integer> numerosUtenteUsados = new ArrayList<>();

    /* --------- atributos --------- */
    private int numeroUtente;
    private String nome;
    private char genero;
    private Data dataNascimento;

    /* --------- construtor completo --------- */
    public Paciente(int numeroUtente, String nome, char genero, Data dataNascimento) throws Excecao{
        try {
            setNumeroUtente(numeroUtente);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.numeroUtente = NUMERO_POR_OMISSAO;
        }

        try {
            setNome(nome);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.nome = NOME_POR_OMISSAO;
        }

        try {
            setGenero(genero);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.genero = GENERO_POR_OMISSAO;
        }

        try {
            setDataNascimento(dataNascimento);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.dataNascimento = DATA_POR_OMISSAO;
        }
    }

    /* --------- construtores mais simples --------- */
    public Paciente(int numeroUtente, String nome, char genero) throws Excecao {
        this(numeroUtente, nome, genero, DATA_POR_OMISSAO);   // data padrão
    }

    public Paciente(int numeroUtente, String nome) throws Excecao {
        this(numeroUtente, nome, GENERO_POR_OMISSAO, DATA_POR_OMISSAO);
    }

    public Paciente(int numeroUtente) throws Excecao {
        this(numeroUtente, NOME_POR_OMISSAO, GENERO_POR_OMISSAO, DATA_POR_OMISSAO);
    }

    public Paciente() throws Excecao {                                           // construtor por omissão
        this(NUMERO_POR_OMISSAO, NOME_POR_OMISSAO, GENERO_POR_OMISSAO, DATA_POR_OMISSAO);
    }

    /* --------- getters / setters --------- */
    public int getNumeroUtente() { return numeroUtente; }

    public void setNumeroUtente(int numeroUtente) throws Excecao {
        if (numeroUtente <= 0) {
            throw new Excecao("Erro: número de utente tem de ser maior do que 0.");
        }
        if (!numerosUtenteUsados.add(numeroUtente)) {
            throw new Excecao("Erro: número de utente já existe!");
        }
        this.numeroUtente = numeroUtente;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) throws Excecao {
        if (nome == null || nome.isEmpty() || !nome.matches("[\\p{L} ]+")) { //Aceita qualquer letra, desde com a até com acentos
            throw new Excecao("Erro: O nome deve conter apenas letras (incluindo acentos) e espaços, e não pode ser vazio.");
        }
        this.nome = nome;
    }

    public char getGenero() { return genero; }

    public void setGenero(char genero) throws Excecao{
        if (!(genero == 'M' || genero == 'F' || genero == 'O')) {
            throw new Excecao("Erro: Género inválido. Use 'M', 'F' ou 'O'.");
        }
        this.genero = genero;
    }

    public Data getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(Data dataNascimento) throws Excecao{
        if (dataNascimento == null) {
            throw new Excecao("Erro: Data de nascimento inválida.");
        }
        this.dataNascimento = dataNascimento;
    }

    /* --------- toString --------- */
    @Override
    public String toString() {
        return "Paciente{ " +
                "numeroUtente =" + getNumeroUtente() +
                ", nome =" + getNome() +
                ", género =" + getGenero() +
                ", dataNascimento =" + getDataNascimento() +
                " }";
    }

    /* --------- equals --------- */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;
        Paciente outro = (Paciente) o;
        return this.getNumeroUtente() == outro.getNumeroUtente();
    }

}
