package senai.cronos.util.debug;

import static java.lang.System.out;

/**
 *
 * Classe usada para testes e debug
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class Debug {

    public static void countConnections() {
        println("\nContado as conexões ---------------------");
        println("Docentes DAO: " + Contador.docentes);
        println("Discipli DAO: " + Contador.disciplinas);
        println("Laborato DAO: " + Contador.laboratorios);
        println("Horarios DAO: " + Contador.horario);
        println("Turmas   DAO: " + Contador.turmas);
        println("Nucleos  DAO: " + Contador.nucleos);
    }
    
    public static void println(Object o) {
        out.println(o);
    }

    public static void print(Object o) {
        out.print(o);
    }
}
