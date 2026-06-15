

public class Main {

    private static final String[] NOMBRES_USUARIOS = {
        "Sofía", "Mateo", "Valentina", "Sebastián", "Isabella", "Daniel", "Camila", "Matías", "Lucía", "Santiago"
    };

    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("   SISTEMA DE CONTROL DE ADICCIÓN DIGITAL");
        System.out.println("   Simulación continua — cierra la terminal para detener");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        int[] intervalos = {5, 7, 9, 11, 13, 15, 17, 19, 21, 23}; // cada usuario genera una nueva planificación cada X segundos

        for (int i = 0; i < NOMBRES_USUARIOS.length; i++) {
            HiloUsuario hilo = new HiloUsuario(NOMBRES_USUARIOS[i], intervalos[i]);
            hilo.start();
            System.out.println("▶ Hilo iniciado: " + NOMBRES_USUARIOS[i]
                    + " (cada " + intervalos[i] + "s)");
        }

        System.out.println("\nTodos los hilos corriendo. Cierra la terminal para detener.\n");

        // Mantener el hilo principal vivo para que los daemon threads no mueran
        Thread.currentThread().join(); // espera indefinidamente
    }
}