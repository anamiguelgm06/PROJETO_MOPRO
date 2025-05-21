import java.time.LocalDate;

public class Pacientes {
    private String numeroUtente;
    private String nome;
    private char genero; // 'M', 'F' ou 'O'
    private LocalDate dataNascimento;

    // Construtor completo
    public Pacientes(String numeroUtente, String nome, char genero, LocalDate dataNascimento) {
        if (genero != 'M' && genero != 'F' && genero != 'O') {
            throw new IllegalArgumentException("Género inválido. Use 'M', 'F' ou 'O'.");
        }

        this.numeroUtente = numeroUtente;
        this.nome = nome;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
    }

    // Construtor com 3 argumentos (sem data de nascimento)
    public Pacientes(String numeroUtente, String nome, char genero) {
        this(numeroUtente, nome, genero, LocalDate.of(2000, 1, 1)); // data padrão
    }

    // Construtor com 2 argumentos (sem género e data de nascimento)
    public Pacientes(String numeroUtente, String nome) {
        this(numeroUtente, nome, 'O', LocalDate.of(2000, 1, 1));
    }

    // Construtor com 1 argumento (apenas número de utente)
    public Pacientes(String numeroUtente) {
        this(numeroUtente, "Sem Nome", 'O', LocalDate.of(2000, 1, 1));
    }

    // Construtor por omissão (sem argumentos)
    public Pacientes() {
        this("000000000", "Sem Nome", 'O', LocalDate.of(2000, 1, 1));
    }

    // Getters e Setters
    public String getNumeroUtente() {
        return numeroUtente;
    }

    public void setNumeroUtente(String numeroUtente) {
        this.numeroUtente = numeroUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        if (genero != 'M' && genero != 'F' && genero != 'O') {
            throw new IllegalArgumentException("Género inválido. Use 'M', 'F' ou 'O'.");
        }
        this.genero = genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Pacientes{" +
                "numeroUtente='" + numeroUtente + '\'' +
                ", nome='" + nome + '\'' +
                ", genero=" + genero +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
