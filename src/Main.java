import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== SISTEMA DE CONTROL DE ADICCION DIGITAL ===");
            System.out.print("Nombre del usuario (o 'salir' para terminar): ");
            String nombre = sc.nextLine();

            if (nombre.equalsIgnoreCase("salir")) {
                System.out.println("Hasta luego!");
                break;
            }

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
            sc.nextLine(); // limpiar buffer

            MonitorUso monitor = new MonitorUso(redesSociales, juegos, productividad, musica, streaming, otros);
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
            System.out.println("==============================");
        }

        sc.close();
    }
}