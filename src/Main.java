import Exceptions.Excecao;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        // carregar dados iniciais
        Centro centro = new Centro("Centro de Saúde Lisboa", 123456789, "Rua das Flores, 123", 218765432, "contato@centrolisboa.pt");
        Paciente p1 = new Paciente(1, "Joana Silva", 'F', new Data(1990, 5, 12));
        Paciente p2 = new Paciente(2, "Carlos Mendes", 'M', new Data(1985, 3, 22));
        Paciente p3 = new Paciente(3, "Rita Carvalho", 'F', new Data(2001, 12, 5));
        Paciente p4 = new Paciente(4, "Alex Rocha", 'M', new Data(1998, 7, 30));
        Tecnico t1 = new Tecnico("Cedula2", "Marco Barbosa", 1000.0f, 25, new Data(2005, 9, 22), "RX");
        Tecnico t2 = new Tecnico("Cedula1", "Helena Barbosa", 999.9f, 24.9f, new Data(2006, 10, 12), "RM");
        centro.adicionarPaciente(p1);
        centro.adicionarPaciente(p2);
        centro.adicionarPaciente(p3);
        centro.adicionarPaciente(p4);
        centro.adicionarTecnico(t1);
        centro.adicionarTecnico(t2);
        centro.adicionarExame(new ExameRM(new Data(2025, 6, 1), p1, t1, true));
        centro.adicionarExame(new ExameRX(new Data(2025, 6, 1), p1, t2, "Tórax"));
        centro.adicionarExame(new ExameTAC(new Data(2025, 6, 5), p2, t1, 40.0));
        centro.adicionarExame(new ExameRX(new Data(2025, 6, 7), p2, t2, "Joelho"));
        centro.adicionarExame(new ExameRX(new Data(2025, 6, 12), p3, t2, "Coluna"));
        centro.adicionarExame(new ExameRM(new Data(2025, 6, 1), p3, t1, false));
        centro.adicionarExame(new ExameTAC(new Data(2025, 6, 20), p4, t1, 45.5));
        centro.adicionarExame(new ExameRX(new Data(2025, 6, 5), p4, t2, "Cabeça"));

        // listar todos os exames realizados num dia, organizado por tecnico
        System.out.println("\nExames realizados no dia 01/06/2025 no centro '" + centro.getNome() + "': ");
        centro.listarExamePorDia(new Data(2025, 6, 1));

        // mostrar custo total de todos os exames de um certo paciente
        System.out.println("Custo do paciente '" + p1.getNome() + "': " + centro.calcularCustoPaciente(centro.getPacientes().getFirst()) + "€.");

        // mostrar custo total do centro num determinado mes
        System.out.println("\nCusto total do centro '" + centro.getNome() + "' no mês 06/2025: " + centro.calcularCustoMes(new Data(2025, 6, 1)) + "€.");

        // guardar dados num ficheiro
        guardarDadosEmTexto(centro);
    }

    private static void guardarDadosEmTexto(Centro centro) {
        try {
            Formatter formatter = new Formatter("centro.txt");
            formatter.format("centro,%s,%d,%s,%d,%s%n",
                    centro.getNome(),
                    centro.getNif(),
                    centro.getMorada(),
                    centro.getTelefone(),
                    centro.getEmail());

            for (Paciente p : centro.getPacientes()) {
                formatter.format("paciente,%d,%s,%c,%s%n",
                        p.getNumeroUtente(),
                        p.getNome(),
                        p.getGenero(),
                        p.getDataNascimento());
            }
            for (Tecnico t : centro.getTecnicos()) {
                formatter.format("tecnico,%s,%s,%s,%s,%s,%s%n",
                        t.getCedulaProfissional(),
                        t.getNome(),
                        t.getSalarioBase(),
                        t.getSubsidio(),
                        t.getNomeEspecialidade(),
                        t.getDataNascimento());
            }

            for (Exame e : centro.getExames()) {
                String linhaExame = String.format("exame,%d,%d,%s,%s",
                        e.getCodigo(),
                        e.getPaciente().getNumeroUtente(),
                        e.getTecnicoResponsavel().getCedulaProfissional(),
                        e.getDataRealizacao());

                if (e instanceof ExameRX) {
                    linhaExame += ",RX," + ((ExameRX) e).getZonaCorpo();
                } else if (e instanceof ExameRM) {
                    linhaExame += ",RM," + ((ExameRM) e).getContraste();
                } else if (e instanceof ExameTAC) {
                    linhaExame += ",TAC," + ((ExameTAC) e).getCustoUnitario();
                }

                formatter.format("%s%n", linhaExame);
            }
            formatter.format("-----------------------------------------------------------------------------------%n");

            System.out.println("\nDados guardados com sucesso no ficheiro 'centro.txt'.");
            formatter.close();

        } catch (FileNotFoundException e) {
            System.err.println("\nErro: Não foi possível criar ou escrever no ficheiro.");
            e.printStackTrace();
        }
    }
}