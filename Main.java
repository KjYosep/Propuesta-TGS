import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== SISTEMA DE CONTROL DE ADICCION DIGITAL ===");

        System.out.print("Nombre del usuario: ");
        String nombre = sc.nextLine();

        Usuario usuario = new Usuario(nombre);

        System.out.print("Horas en TikTok: ");
        double tikTok = sc.nextDouble();

        System.out.print("Horas en Instagram: ");
        double instagram = sc.nextDouble();

        System.out.print("Horas en YouTube: ");
        double youtube = sc.nextDouble();

        System.out.print("Horas en Juegos: ");
        double juegos = sc.nextDouble();

        MonitorUso monitor = new MonitorUso(
                tikTok,
                instagram,
                youtube,
                juegos
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