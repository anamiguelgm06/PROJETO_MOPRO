public enum Especialidade implements EspecialidadeDiagnostico {

    /* --------- tipos de enums --------- */
    RX("Raio-X"),
    RM("Ressonância Magnética"),
    TAC("Tomografia Axial Computadorizada");

    /* --------- atributos --------- */
    private final String nome;

    /* --------- construtor --------- */
    Especialidade(String nome) {
        this.nome = nome;
    }

    /* --------- enum por nome e nome descritivo --------- */
    public static Especialidade fromNomeDescritivo(String nome) {
        if (nome == null) {
            return null;
        }
        for (Especialidade esp : Especialidade.values()) {
            if (esp.name().equalsIgnoreCase(nome)) {
                return esp;
            }
        }
        for (Especialidade esp : Especialidade.values()) {
            if (esp.getNomeEspecialidade().equalsIgnoreCase(nome)) {
                return esp;
            }
        }
        return null;
    }

    /* --------- funcao da interface --------- */
    @Override
    public String getNomeEspecialidade() {
        return this.nome;
    }
}