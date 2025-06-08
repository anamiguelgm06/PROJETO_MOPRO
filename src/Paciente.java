import java.util.ArrayList;
import Exceptions.*;

public class Paciente {

    private static final int NUMERO_POR_OMISSAO   = 999999999;
    private static final String NOME_POR_OMISSAO  = "Sem nome";
    private static final char GENERO_POR_OMISSAO  = 'O';
    private static final Data DATA_POR_OMISSAO    = new Data(2000, 1, 1);

    /* --------- campo estático para garantir unicidade --------- */
    private static ArrayList<Integer> numerosUtenteUsados = new ArrayList<>();

    /* --------- atributos --------- */
    private int numeroUtente;
    private String nome;
    private char genero;          // 'M', 'F' ou 'O'
    private Data dataNascimento;  // classe Data do enunciado

    /* --------- construtor completo --------- */
    public Paciente(int numeroUtente, String nome, char genero, Data dataNascimento) throws Excecao{
        setNumeroUtente(numeroUtente);
        setNome(nome);
        setGenero(genero);
        setDataNascimento(dataNascimento);
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

    public void setNumeroUtente(int numeroUtente) throws Excecao{
        if (numeroUtente <= 0) {
            throw new Excecao("Erro: Número de utente não pode ser 0 ou negativo.");
        }
        if (numerosUtenteUsados.contains(numeroUtente)) {
            throw new Excecao("Erro: Número de utente já existe!");
        }
        this.numeroUtente = numeroUtente;
        numerosUtenteUsados.add(numeroUtente);
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
                ", genero =" + getGenero() +
                ", dataNascimento =" + getDataNascimento() +
                "} ";
    }
}
