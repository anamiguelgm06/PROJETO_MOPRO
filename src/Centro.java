public class Centro {
    private String nome, morada, email;
    private int nif, telefone;

    // Construtor completo
    public Centro(String nome, int nif, String morada, int telefone, String email) {
        setNome(nome);       // Validação Nome
        setNif(nif);         // Validação NIF
        this.morada = morada;
        setTelefone(telefone);
        setEmail(email);     // Validação Email
    }

    // Construtor sem email
    public Centro(String nome, int nif, String morada, int telefone) {
        this(nome, nif, morada, telefone, "Email não disponível");
    }

    // Construtor sem telefone e email
    public Centro(String nome, int nif, String morada) {
        this(nome, nif, morada, 0, "Email não disponível");
    }

    // Construtor sem argumentos
    public Centro() {
        this("Nome não definido", 000000000, "Morada não definida", 91000000, "Email não disponível");
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getNif() {
        return nif;
    }

    public String getMorada() {
        return morada;
    }

    public int getTelefone() {
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
    public void setNif(int nif) {
        if (nif != 0 && String.valueOf(nif).length() == 9) {
            this.nif = nif;
        } else {
            System.out.println("Erro: NIF inválido. Deve conter exatamente 9 dígitos numéricos.");
            System.out.println(nif);
            this.nif = 0;
        }
    }

    public void setTelefone(int telefone) {
        if (telefone != 0 && String.valueOf(telefone).length() == 9) { // Só aceita 9 dígitos
            this.telefone = telefone;  // Corrigido aqui!
        } else {
            System.out.println("Erro: Telefone inválido. Deve conter exatamente 9 dígitos numéricos.");
            this.telefone = 0;
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
