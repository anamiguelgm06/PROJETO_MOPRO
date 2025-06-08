public enum Especialidade implements EspecialidadeDiagnostico {
    RX("Raio-X"),
    RM("Ressonância Magnética"),
    TAC("Tomografia Axial Computadorizada");

    private final String nome;

    Especialidade(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNomeEspecialidade() {
        return this.nome;
    }
}