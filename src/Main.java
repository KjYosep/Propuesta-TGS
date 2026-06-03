import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== SISTEMA DE CONTROL DE ADICCION DIGITAL ===");
        System.out.print("Nombre del usuario: ");
        String nombre = sc.nextLine();

        Usuario usuario = new Usuario(nombre);

        System.out.print("Horas en Redes Sociales: ");
        double redesSociales = sc.nextDouble();

        System.out.print("Horas en Juegos: ");
        double juegos = sc.nextDouble();

        System.out.print("Horas en Productividad: ");
        double productividad = sc.nextDouble();

        System.out.print("Horas en Música: ");
        double musica = sc.nextDouble();

        System.out.print("Horas en Streaming: ");
        double streaming = sc.nextDouble();

        System.out.print("Horas en Otros: ");
        double otros = sc.nextDouble();

        MonitorUso monitor = new MonitorUso(
            redesSociales, juegos, productividad, musica, streaming, otros
        );

        AnalizadorHabitos analizador = new AnalizadorHabitos();
        GeneradorAlertas alertas = new GeneradorAlertas();
        AsistenteIA ia = new AsistenteIA();

        double total = monitor.getTiempoTotal();
        String riesgo = analizador.calcularRiesgo(total);

        System.out.println("\n==============================");
        System.out.println("USUARIO: " + usuario.getNombre());
        monitor.mostrarUso();
        System.out.println("\nTiempo total: " + total + " horas");
        System.out.println("Nivel de riesgo: " + riesgo);
        alertas.mostrarAlerta(riesgo);
        ia.generarHorario(riesgo);

        System.out.println("\nFin de la simulacion.");
        sc.close();
    }
}