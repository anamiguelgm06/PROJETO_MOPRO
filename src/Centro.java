public class Centro {
    private String nome, nif, morada, telefone, email;

    // Construtor completo
    public Centro(String nome, String nif, String morada, String telefone, String email) {
        setNome(nome);       // Validação Nome
        setNif(nif);         // Validação NIF
        this.morada = morada;
        setTelefone(telefone);
        setEmail(email);     // Validação Email
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
        this("Nome não definido", "000000000", "Morada não definida", "Telefone não disponível", "Email não disponível");
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

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty() || !nome.matches("[\\p{L} ]+")) {
            System.out.println("Erro: O nome deve conter apenas letras (incluindo acentos) e espaços, e não pode ser vazio.");
        } else {
            this.nome = nome;
        }
    }

    // Setters com validações
    public void setNif(String nif) {
        if (nif != null && nif.matches("\\d{9}")) {
            this.nif = nif;
        } else {
            System.out.println("Erro: NIF inválido. Deve conter exatamente 9 dígitos numéricos.");
            System.out.println(nif);
            this.nif = "000000000";
        }
    }

    public void setTelefone(String telefone) {
        if (telefone != null && telefone.matches("\\d{9}")) { // Só aceita 9 dígitos
            this.telefone = telefone;  // Corrigido aqui!
        } else {
            System.out.println("Erro: Telefone inválido. Deve conter exatamente 9 dígitos numéricos.");
            this.telefone = "000000000";
        }
    }

    public void setEmail(String email) {
        if (email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.pt$") && !email.contains(" ")) {
            this.email = email;
        } else {
            System.out.println("Erro: Email inválido. Deve estar no formato texto@dominio.pt e sem espaços.");
            this.email = "Email não disponível";
        }
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
