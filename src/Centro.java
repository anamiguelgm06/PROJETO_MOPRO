import Exceptions.*;
import java.util.ArrayList;
import java.util.Collections;

public class Centro{

    /* --------- valores por omissão--------- */
    private static final String NOME_POR_OMISSAO    = "Nome indefinido";
    private static final String MORADA_POR_OMISSAO  = "Morada indefinida";
    private static final String EMAIL_POR_OMISSAO   = "Email indefinido";
    private static final int TELEFONE_POR_OMISSAO   = 910_000_000;
    private static final int NIF_POR_OMISSAO        = 0;
    private static final ArrayList<Paciente> PACIENTES_POR_OMISSAO;
    static { PACIENTES_POR_OMISSAO = new ArrayList<>(); }
    private static final ArrayList<Tecnico> TECNICOS_POR_OMISSAO;
    static { TECNICOS_POR_OMISSAO = new ArrayList<>(); }
    private static final ArrayList<Exame> EXAMES_POR_OMISSAO;
    static { EXAMES_POR_OMISSAO = new ArrayList<>(); }
    

    /* --------- atributos --------- */
    private String nome, morada, email;
    private int nif, telefone;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Tecnico>  tecnicos;
    private ArrayList<Exame>    exames;

    /* --------- construtor completo --------- */
    public Centro(String nome, int nif, String morada, int telefone, String email, ArrayList<Paciente> pacientes, ArrayList<Tecnico> tecnicos, ArrayList<Exame> exames) {
        setNome(nome);
        setNif(nif);
        setMorada(morada);
        setTelefone(telefone);
        setEmail(email);
        setPacientes(pacientes);
        setTecnicos(tecnicos);
        setExames(exames);
    }

    /* --------- constutores mais simples --------- */
    public Centro(String nome, int nif, String morada, int telefone, String email) {
        this(nome, nif, morada, telefone, email, new ArrayList<Paciente>(), new ArrayList<Tecnico>(), new ArrayList<Exame>());
    }

    public Centro(String nome, int nif, String morada, int telefone) {
        this(nome, nif, morada, telefone, EMAIL_POR_OMISSAO, new ArrayList<Paciente>(), new ArrayList<Tecnico>(), new ArrayList<Exame>());
    }

    public Centro(String nome, int nif, String morada) {
        this(nome, nif, morada, TELEFONE_POR_OMISSAO, EMAIL_POR_OMISSAO, new ArrayList<Paciente>(), new ArrayList<Tecnico>(), new ArrayList<Exame>());
    }

    public Centro() {
        this(NOME_POR_OMISSAO, NIF_POR_OMISSAO, MORADA_POR_OMISSAO, TELEFONE_POR_OMISSAO, EMAIL_POR_OMISSAO, new ArrayList<Paciente>(), new ArrayList<Tecnico>(), new ArrayList<Exame>());
    }

    /* --------- getters e setters --------- */
    public String getNome() { return nome; }

    public void setNome(String nome) {
        try {
            if (nome == null || nome.isEmpty() || !nome.matches("[a-zA-ZÀ-ÿ ]+")) {
                throw new Excecao("Erro: O nome deve conter apenas letras (incluindo acentos) e espaços, e não pode ser vazio.");
            }
            this.nome = nome;
        } catch (Excecao e) {
            this.nome = NOME_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public int getNif() { return nif; }

    public void setNif(int nif) {
        try {
            if (nif == NIF_POR_OMISSAO) {
                this.nif = NIF_POR_OMISSAO;
                return;
            }
            if (String.valueOf(nif).length() != 9) {
                throw new Excecao("Erro: NIF inválido. Deve conter exatamente 9 dígitos numéricos.");
            }
            this.nif = nif;
        } catch (Excecao e) {
            this.nif = NIF_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public String getMorada() { return morada; }

    public void setMorada(String morada) {
        try {
            if (morada == null || morada.isEmpty()) {
                throw new Excecao("Erro: A morada não pode estar vazia.");
            }
            this.morada = morada;
        } catch (Excecao e) {
            this.morada = MORADA_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public int getTelefone() { return telefone; }

    public void setTelefone(int telefone) {
        try {
            if (!(telefone != 0 && String.valueOf(telefone).length() == 9)) {
                throw new Excecao("Erro: Telefone inválido. Deve conter exatamente 9 dígitos numéricos.");
            }
            this.telefone = telefone;
        } catch (Excecao e) {
            this.telefone = TELEFONE_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        try {
            if (!(email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.pt$") && !email.contains(" "))) {
                throw new Excecao("Erro: Email inválido. Deve estar no formato texto@dominio.pt e sem espaços.");
            }
            this.email = email;
        } catch (Excecao e) {
            this.email = EMAIL_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public ArrayList<Paciente> getPacientes() { return pacientes; }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        try {
            if(pacientes == null) {
                throw new Excecao("Erro: A lista de pacientes não pode ser vazia.");
            }
            this.pacientes = pacientes;
        } catch (Excecao e) {
            this.pacientes = PACIENTES_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public ArrayList<Tecnico> getTecnicos() { return tecnicos; }

    public void setTecnicos(ArrayList<Tecnico> tecnicos) {
        try {
            if(tecnicos == null) {
                throw new Excecao("Erro: A lista de técnicos não pode ser vazia.");
            }
            this.tecnicos = tecnicos;
        } catch (Excecao e) {
            this.tecnicos = TECNICOS_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    public ArrayList<Exame> getExames() { return exames; }

    public void setExames(ArrayList<Exame> exames) {
        try {
            if(exames == null) {
                throw new Excecao("Erro: A lista de exames não pode ser vazia.");
            }
            this.exames = exames;
        } catch (Excecao e) {
            this.exames = EXAMES_POR_OMISSAO;
            System.out.println(e + " Usando valor por omissão.");
        }
    }

    /* --------- funcoes de listas --------- */
    public void adicionarPaciente(Paciente paciente) {
        try {
            if (paciente == null) {
                throw new Excecao("Erro: Paciente inválido.");
            }
            for (Paciente p : getPacientes()) {
                if (p.getNumeroUtente() == paciente.getNumeroUtente()) {
                    throw new Excecao("Erro: Paciente já existe!");
                }
            }
            pacientes.add(paciente);
        } catch (Excecao e) {
            System.out.println(e);
        }
    }

    public void adicionarTecnico(Tecnico tecnico) {
        try {
            if (tecnico == null) {
                throw new Excecao("Erro: Técnico inválido.");
            }
            for (Tecnico t : getTecnicos()) {
                if (t.getCedulaProfissional().equals(tecnico.getCedulaProfissional())) {
                    throw new Excecao("Erro: Técnico já existe!");
                }
            }
            tecnicos.add(tecnico);
        } catch (Excecao e) {
            System.out.println(e);
        }
    }

    public void adicionarExame(Exame exame) {
        try {
            if (exame == null) {
                throw new Excecao("Erro: Exame inválido.");
            }
            for (Exame e : getExames()) {
                if (e.getCodigo() == exame.getCodigo()) {
                    throw new Excecao("Erro: Exame já existe!");
                }
            }
            exames.add(exame);
        } catch (Excecao e) {
            System.out.println(e);
        }
    }

    public void removerPaciente(Paciente paciente) {
        try {
            if (!pacientes.remove(paciente)) {
                throw new Excecao("Erro: Paciente não encontrado.");
            }
        } catch (Excecao e) {
            System.out.println(e);
        }
    }

    public void removerTecnico(Tecnico tecnico) {
        try {
            if (!tecnicos.remove(tecnico)) {
                throw new Excecao("Erro: Técnico não encontrado.");
            }
        } catch (Excecao e) {
            System.out.println(e);
        }
    }

    public void removerExame(Exame exame) {
        try {
            if (!exames.remove(exame)) {
                throw new Excecao("Erro: Exame não encontrado.");
            }
        } catch (Excecao e) {
            System.out.println(e);
        }
    }

    public void removerPaciente(int numeroUtente) {
        try {
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
        } catch (Excecao e) {
            System.out.println(e);
        }
    }

    public void removerTecnico(String cedula) {
        try {
            boolean removido = false;
            for (int i = 0; i < tecnicos.size(); i++) {
                if (tecnicos.get(i).getCedulaProfissional().equals(cedula)) {
                    tecnicos.remove(i);
                    removido = true;
                    break;
                }
            }
            if (!removido) {
                throw new Excecao("Erro: Técnico não encontrado.");
            }
        } catch (Excecao e) {
            System.out.println(e);
        }
    }

    public void removerExame(int codigo) {
        try {
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
        } catch (Excecao e) {
            System.out.println(e);
        }
    }

    public void listarExamePorDia(Data data) {

        try {
            ArrayList<Exame> examesFiltrados = new ArrayList<>();
            for (Exame e : getExames()) {
                if (e.getDataRealizacao().equals(data)) {
                    examesFiltrados.add(e);
                }
            }

            Collections.sort(examesFiltrados);

            System.out.printf("%-6s | %-15s | %-15s | %-10s | %-8s | %-6s%n", "Código", "Técnico", "Paciente", "Data", "Tipo", "Custo");
            System.out.println("------------------------------------------------------------------------------");
            for (Exame e: examesFiltrados){

                String tipo;

                if (e instanceof ExameRM) tipo = "RM";
                else if (e instanceof ExameRX) tipo = "RX";
                else if (e instanceof ExameTAC) tipo = "TAC";
                else tipo = "Sem tipo";

                System.out.printf("%-6d | %-15s | %-15s | %-10s | %-8s | €%6.2f%n",
                        e.getCodigo(),
                        e.getTecnicoResponsavel().getNome(),
                        e.getPaciente().getNome(),
                        e.getDataRealizacao(),
                        tipo,
                        e.calcularCusto());
            }

            if(examesFiltrados.isEmpty()) throw new Excecao("Não foram encontrados exames nesta data.");

            System.out.println();
        } catch (Excecao e) {
            System.out.println(e);
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

    /* --------- calcular custo por paciente --------- */
    public double calcularCustoPaciente(Paciente paciente) {

        double custo = 0;

        for (Exame e: exames){
            if(e.getPaciente().equals(paciente)){
                custo += e.calcularCusto();
            }
        }

        return custo;
    }

    /* --------- calcular custo por mes --------- */
    public double calcularCustoMes(Data data)  {
        double custo = 0;

        for (Exame e: exames){
            if(e.getDataRealizacao().getMes() == data.getMes() && e.getDataRealizacao().getAno() == data.getAno()){
                custo += e.calcularCusto();
            }
        }

        return custo;
    }
}
