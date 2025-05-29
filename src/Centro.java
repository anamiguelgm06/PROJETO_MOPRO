public class Centro {
    private String nome, nif, morada, telefone, email;

    // Construtor completo
    public Centro(String nome, String nif, String morada, String telefone, String email) {
        this.nome = nome;
        this.nif = nif;
        this.morada = morada;
        this.telefone = telefone;
        this.email = email;
    }

    // Construtor sem email
    public Centro(String nome, String nif, String morada, String telefone) {
        this(nome, nif, morada, telefone, "Email não disponível");
    }

    // Construtor sem telefone e email
    public Centro(String nome, String nif, String morada) {
        this(nome, nif, morada, "Telefone não disponível", "Email não disponível");
    }

    // Construtor sem argumentos
    public Centro() {
        this("Nome não definido", "NIF não definido", "Morada não definida", "Telefone não disponível", "Email não disponível");
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getNif() {
        return nif;
    }

    public String getMorada() {
        return morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    // toString
    @Override
    public String toString() {
        return "Centro{" +
                "nome='" + nome + '\'' +
                ", nif='" + nif + '\'' +
                ", morada='" + morada + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
