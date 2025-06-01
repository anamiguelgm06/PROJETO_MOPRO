import java.util.ArrayList;

public class Paciente {

    /* --------- campo estático para garantir unicidade --------- */
    private static ArrayList<String> numerosUtenteUsados = new ArrayList<>();

    /* --------- atributos --------- */
    private String numeroUtente;
    private String nome;
    private char genero;          // 'M', 'F' ou 'O'
    private Data dataNascimento;  // classe Data do enunciado

    /* --------- construtor completo --------- */
    public Paciente(String numeroUtente, String nome, char genero, Data dataNascimento) {
        setNumeroUtente(numeroUtente);
        setNome(nome);
        setGenero(genero);
        setDataNascimento(dataNascimento);
    }

    /* --------- construtores mais simples --------- */
    public Paciente(String numeroUtente, String nome, char genero) {
        this(numeroUtente, nome, genero, new Data(2000, 1, 1));   // data padrão
    }

    public Paciente(String numeroUtente, String nome) {
        this(numeroUtente, nome, 'O', new Data(2000, 1, 1));
    }

    public Paciente(String numeroUtente) {
        this(numeroUtente, "Sem Nome", 'O', new Data(2000, 1, 1));
    }

    public Paciente() {                                           // construtor por omissão
        this("000000000", "Sem Nome", 'O', new Data(2000, 1, 1));
    }

    /* --------- getters / setters --------- */
    public String getNumeroUtente() {
        return numeroUtente;
    }

    public void setNumeroUtente(String numeroUtente) {
        if (numeroUtente == null || numeroUtente.isEmpty()) {
            System.out.println("Erro: Número de utente não pode estar vazio.");
            return;
        }
        if (numerosUtenteUsados.contains(numeroUtente)) {
            System.out.println("Erro: Número de utente já existe!");
            return;
        }
        this.numeroUtente = numeroUtente;
        numerosUtenteUsados.add(numeroUtente);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty() || !nome.matches("[\\p{L} ]+")) { //Aceita qualquer letra, desde com a até com acentos
            System.out.println("Erro: O nome deve conter apenas letras (incluindo acentos) e espaços, e não pode ser vazio.");
        } else {
            this.nome = nome;
        }
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        if (genero == 'M' || genero == 'F' || genero == 'O') {
            this.genero = genero;
        } else {
            System.out.println("Erro: Género inválido. Use 'M', 'F' ou 'O'.");
            this.genero = 'O';   // valor por defeito
        }
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Data dataNascimento) {
        if (dataNascimento != null) {
            this.dataNascimento = dataNascimento;
        } else {
            this.dataNascimento = new Data(2000, 1, 1);   // valor por defeito
        }
    }

    /* --------- toString --------- */
    @Override
    public String toString() {
        return "Paciente{" +
                "numeroUtente='" + numeroUtente + '\'' +
                ", nome='" + nome + '\'' +
                ", genero=" + genero +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
