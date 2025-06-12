import Exceptions.Excecao;
import java.util.ArrayList;

public class Centro{

    /* --------- valores por omissão--------- */
    private static final String NOME_POR_OMISSAO    = "Nome indefinido";
    private static final String MORADA_POR_OMISSAO  = "Morada indefinida";
    private static final String EMAIL_POR_OMISSAO   = "Email indefinido";
    private static final int TELEFONE_POR_OMISSAO = 910_000_000;
    private static final int NIF_POR_OMISSAO      = 000_000_000;

    /* --------- atributos --------- */
    private String nome, morada, email;
    private int nif, telefone;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Tecnico>  tecnicos;
    private ArrayList<Exame>    exames;

    /* --------- construtor completo --------- */
    public Centro(String nome, int nif, String morada, int telefone, String email, ArrayList<Paciente> pacientes, ArrayList<Tecnico> tecnicos, ArrayList<Exame> exames) throws Excecao {

        try {
            setNome(nome);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.nome = NOME_POR_OMISSAO;
        }

        try {
            setNif(nif);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.nif = NIF_POR_OMISSAO;
        }

        try {
            setMorada(morada);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.morada = MORADA_POR_OMISSAO;
        }

        try {
            setTelefone(telefone);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.telefone = TELEFONE_POR_OMISSAO;
        }

        try {
            setEmail(email);
        } catch (Excecao e) {
            System.out.println(e + " Usando valor por omissão.");
            this.email = EMAIL_POR_OMISSAO;
        }

        try {
            setPacientes(pacientes);
        } catch (Excecao e) {
            System.out.println(e + " Usando valores por omissão.");
            this.pacientes = new ArrayList<Paciente>();
        }

        try {
            setTecnicos(tecnicos);
        } catch (Excecao e) {
            System.out.println(e + " Usando valores por omissão.");
            this.tecnicos = new ArrayList<Tecnico>();
        }

        try {
            setExames(exames);
        } catch (Excecao e) {
            System.out.println(e + " Usando valores por omissão.");
            this.exames = new ArrayList<Exame>();
        }

    }

    /* --------- constutores mais simples --------- */
    public Centro(String nome, int nif, String morada, int telefone, String email) throws Excecao {
        this(nome, nif, morada, telefone, email, new ArrayList<Paciente>(), new ArrayList<Tecnico>(), new ArrayList<Exame>());
    }

    public Centro(String nome, int nif, String morada, int telefone) throws Excecao {
        this(nome, nif, morada, telefone, EMAIL_POR_OMISSAO, new ArrayList<Paciente>(), new ArrayList<Tecnico>(), new ArrayList<Exame>());
    }

    public Centro(String nome, int nif, String morada) throws Excecao {
        this(nome, nif, morada, TELEFONE_POR_OMISSAO, EMAIL_POR_OMISSAO, new ArrayList<Paciente>(), new ArrayList<Tecnico>(), new ArrayList<Exame>());
    }

    public Centro() throws Excecao {
        this(NOME_POR_OMISSAO, NIF_POR_OMISSAO, MORADA_POR_OMISSAO, TELEFONE_POR_OMISSAO, EMAIL_POR_OMISSAO, new ArrayList<Paciente>(), new ArrayList<Tecnico>(), new ArrayList<Exame>());
    }

    /* --------- getters e setters --------- */
    public String getNome() { return nome; }

    public void setNome(String nome) throws Excecao {
        if (nome == null || nome.isEmpty() || !nome.matches("[a-zA-ZÀ-ÿ ]+")) {
            throw new Excecao("Erro: O nome deve conter apenas letras (incluindo acentos) e espaços, e não pode ser vazio.");
        }
        this.nome = nome;
    }

    public int getNif() { return nif; }

    public void setNif(int nif) throws Excecao{
        if (!(nif != 0 && String.valueOf(nif).length() == 9)) {
            throw new Excecao("Erro: NIF inválido. Deve conter exatamente 9 dígitos numéricos.");
        }
        this.nif = nif;
    }

    public String getMorada() { return morada; }

    public void setMorada(String morada) throws Excecao{
        if (morada == null || morada.isEmpty()) {
            throw new Excecao("Erro: A morada não pode estar vazia.");
        }
        this.morada = morada;
    }

    public int getTelefone() { return telefone; }

    public void setTelefone(int telefone) throws Excecao {
        if (!(telefone != 0 && String.valueOf(telefone).length() == 9)) {
            throw new Excecao("Erro: Telefone inválido. Deve conter exatamente 9 dígitos numéricos.");
        }
        this.telefone = telefone;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) throws Excecao{
        if (!(email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.pt$") && !email.contains(" "))) {
            throw new Excecao("Erro: Email inválido. Deve estar no formato texto@dominio.pt e sem espaços.");
        }
        this.email = email;
    }

    public ArrayList<Paciente> getPacientes() { return pacientes; }

    public void setPacientes(ArrayList<Paciente> pacientes) throws Excecao {
        if(pacientes == null) {
            throw new Excecao("Erro: A lista de pacientes não pode ser vazia.");
        }
        this.pacientes = pacientes;
    }

    public ArrayList<Tecnico> getTecnicos() { return tecnicos; }

    public void setTecnicos(ArrayList<Tecnico> tecnicos) throws Excecao {
        if(tecnicos == null) {
            throw new Excecao("Erro: A lista de técnicos não pode ser vazia.");
        }
        this.tecnicos = tecnicos;
    }

    public ArrayList<Exame> getExames() { return exames; }

    public void setExames(ArrayList<Exame> exames) throws Excecao {
        if(exames == null) {
            throw new Excecao("Erro: A lista de ex não pode ser vazia.");
        }
        this.exames = exames;
    }

    /* --------- funcoes de listas --------- */
    public void adicionarPaciente(Paciente paciente) throws Excecao {
        if (paciente == null) {
            throw new Excecao("Erro: Paciente inválido.");
        }
        for (Paciente p : getPacientes()) {
            if (p.getNumeroUtente() == paciente.getNumeroUtente()) {
                throw new Excecao("Erro: Paciente já existe!");
            }
        }
        pacientes.add(paciente);
    }

    public void adicionarTecnico(Tecnico tecnico) throws Excecao {
        if (tecnico == null) {
            throw new Excecao("Erro: Técnico inválido.");
        }
        for (Tecnico t : getTecnicos()) {
            if (t.getCedulaProfissional().equals(tecnico.getCedulaProfissional())) {
                throw new Excecao("Erro: Técnico já existe!");
            }
        }
        tecnicos.add(tecnico);
    }

    public void adicionarExame(Exame exame) throws Excecao {
        if (exame == null) {
            throw new Excecao("Erro: Exame inválido.");
        }
        for (Exame e : getExames()) {
            if (e.getCodigo() == exame.getCodigo()) {
                throw new Excecao("Erro: Exame já existe!");
            }
        }
        exames.add(exame);
    }

    public void removerPaciente(Paciente paciente) throws Excecao {
        if (!pacientes.remove(paciente)) {
            throw new Excecao("Erro: Paciente não encontrado.");
        }
    }

    public void removerTecnico(Tecnico tecnico) throws Excecao {
        if (!tecnicos.remove(tecnico)) {
            throw new Excecao("Erro: Técnico não encontrado.");
        }
    }

    public void removerExame(Exame exame) throws Excecao {
        if (!exames.remove(exame)) {
            throw new Excecao("Erro: Exame não encontrado.");
        }
    }

    public void removerPaciente(int numeroUtente) throws Excecao {
        boolean removido = false;
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getNumeroUtente() == numeroUtente) {
                pacientes.remove(i);
                removido = true;
                break;
            }
        }
        if (!removido) {
            throw new Excecao("Erro: Paciente não encontrado.");
        }
    }

    public void removerTecnico(String cedula) throws Excecao {
        boolean removido = false;
        for (int i = 0; i < tecnicos.size(); i++) {
            if (tecnicos.get(i).getCedulaProfissional() == cedula) {
                tecnicos.remove(i);
                removido = true;
                break;
            }
        }
        if (!removido) {
            throw new Excecao("Erro: Técnico não encontrado.");
        }
    }

    public void removerExame(int codigo) throws Excecao {
        boolean removido = false;
        for (int i = 0; i < exames.size(); i++) {
            if (exames.get(i).getCodigo() == codigo) {
                exames.remove(i);
                removido = true;
                break;
            }
        }
        if (!removido) {
            throw new Excecao("Erro: Exames não encontrado.");
        }
    }

    /* --------- toString --------- */
    @Override
    public String toString() {
        return "Centro{ " +
                "nome=" + getNome() +
                ", nif=" + getNif() +
                ", morada=" + getMorada() +
                ", telefone=" + getTelefone() +
                ", email=" + getEmail()+
                ", pacientes=" + getPacientes()+
                ", técnicos=" + getTecnicos()+
                ", exames=" + getExames()+
                " }";
    }

    /* --------- equals --------- */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Centro)) return false;
        Centro outro = (Centro) o;
        return this.getNif() == outro.getNif();
    }
}
