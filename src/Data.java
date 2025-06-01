import java.time.LocalDate;

public class Data {
    private int ano;
    private int mes;
    private int dia;

    public Data(int ano, int mes, int dia) {
        if (dataValida(ano, mes, dia)) {
            this.ano = ano;
            this.mes = mes;
            this.dia = dia;
        } else {
            System.out.println("Data inválida. Usando data padrão 2000/01/01.");
            this.ano = 2000;
            this.mes = 1;
            this.dia = 1;
        }
    }

    public static Data now() {
        LocalDate hoje = LocalDate.now();
        return new Data(hoje.getYear(), hoje.getMonthValue(), hoje.getDayOfMonth());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Data other = (Data) obj;
        return this.ano == other.ano && this.mes == other.mes && this.dia == other.dia;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(ano, mes, dia);
    }


    private boolean dataValida(int ano, int mes, int dia) {
        if (mes < 1 || mes > 12 || dia < 1) return false;

        int[] diasPorMes = {31, (ehAnoBissexto(ano) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return dia <= diasPorMes[mes - 1];
    }

    private boolean ehAnoBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }

    @Override
    public String toString() {
        return String.format("%04d/%02d/%02d", ano, mes, dia);
    }


    // Getters, se quiseres usar
    public int getAno() { return ano; }
    public int getMes() { return mes; }
    public int getDia() { return dia; }
}
