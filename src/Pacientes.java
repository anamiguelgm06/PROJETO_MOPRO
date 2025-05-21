import java.time.LocalDate;
public class Pacientes {
    private String numeroUtente;
    private String nome;
    private char genero; // 'M', 'F' ou 'O'
    private LocalDate dataNascimento;

    //Constructor
    public Pacientes(String numeroUtente, String nome, char genero, LocalDate dataNascimento) {

        // Verifica se o género é válido
        if (genero != 'M' && genero != 'F' && genero != 'O') {
            // Se não for, mostra erro e não deixa criar o paciente
            throw new IllegalArgumentException("Género inválido. Use 'M', 'F' ou 'O'.");
        }

        this.numeroUtente = numeroUtente;
        this.nome = nome;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
    }

    //Getters and Setters
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
