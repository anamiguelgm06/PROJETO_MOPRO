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
    public Paciente(int numeroUtente, String nome, char genero, Data dataNascimento)  {
        setNumeroUtente(numeroUtente);
        setNome(nome);
        setGenero(genero);
        setDataNascimento(dataNascimento);
    }

    /* --------- construtores mais simples --------- */
    public Paciente(int numeroUtente, String nome, char genero)  {
        this(numeroUtente, nome, genero, DATA_POR_OMISSAO);   // data padrão
    }

    public Paciente(int numeroUtente, String nome)  {
        this(numeroUtente, nome, GENERO_POR_OMISSAO, DATA_POR_OMISSAO);
    }

    public Paciente(int numeroUtente)   {
        this(numeroUtente, NOME_POR_OMISSAO, GENERO_POR_OMISSAO, DATA_POR_OMISSAO);
    }

    public Paciente()  {
        this(NUMERO_POR_OMISSAO, NOME_POR_OMISSAO, GENERO_POR_OMISSAO, DATA_POR_OMISSAO);
    }

    /* --------- getters / setters --------- */
    public int getNumeroUtente() { return numeroUtente; }

    public void setNumeroUtente(int numeroUtente)  {
        try{

            if (numeroUtente == NUMERO_POR_OMISSAO) {
                this.numeroUtente = numeroUtente;
                return;
            }

            if (numeroUtente <= 0) {
                throw new Excecao("Erro: número de utente tem de ser maior do que 0.");
            }

            if (numerosUtenteUsados.contains(numeroUtente)) {
                throw new Excecao("Erro: número de utente já existe!");
            }
            numerosUtenteUsados.add(numeroUtente);
            this.numeroUtente = numeroUtente;

        } catch (Excecao e) {
            this.numeroUtente = numeroUtente;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public String getNome() { return nome; }

    public void setNome(String nome)  {
        try {
            if (nome == null || nome.isEmpty() || !nome.matches("[\\p{L} ]+")) { //Aceita qualquer letra, desde a até com acentos
                throw new Excecao("Erro: O nome deve conter apenas letras (incluindo acentos) e espaços, e não pode ser vazio.");
            }
            this.nome = nome;
        } catch (Excecao e) {
            this.nome = NOME_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public char getGenero() { return genero; }

    public void setGenero(char genero) {
        try {
            if (genero == GENERO_POR_OMISSAO) {
                this.genero = GENERO_POR_OMISSAO;
                return;
            }

            if (!(genero == 'M' || genero == 'F' || genero == 'O')) {
                throw new Excecao("Erro: Género inválido. Use 'M', 'F' ou 'O'.");
            }
            this.genero = genero;
        } catch (Excecao e) {
            this.genero = GENERO_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public Data getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(Data dataNascimento) {
        try {
            if (dataNascimento == null) {
                throw new Excecao("Erro: Data de nascimento inválida.");
            }
            this.dataNascimento = dataNascimento;
        } catch (Excecao e) {
            this.dataNascimento = DATA_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
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
