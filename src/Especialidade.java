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

    /* --------- "toString" --------- */
    @Override
    public String getNomeEspecialidade() {
        return this.nome;
    }
}