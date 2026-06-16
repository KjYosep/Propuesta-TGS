public class Main {

    private static final String[] NOMBRES_USUARIOS = {
        "Sofía", "Mateo", "Valentina", "Sebastián", "Isabella", "Daniel", "Camila", "Matías", "Lucía", "Santiago"       // lista de nombres de usuarios para la simulación
    };

    public static void main(String[] args) throws InterruptedException {    

        System.out.println("============================================================");
        System.out.println("\tSISTEMA DE CONTROL DE ADICCIÓN DIGITAL");
        System.out.println("  Simulación continua — cierra la terminal para detener");
        System.out.println("============================================================\n");

        int[] intervalos = {6, 6, 6, 6, 6, 6, 6, 6, 6, 6}; // cada usuario genera una nueva planificación cada 6 segundos

        for (int i = 0; i < NOMBRES_USUARIOS.length; i++) {         // por cada nombre, crear y arrancar un hilo de usuario
            HiloUsuario hilo = new HiloUsuario(NOMBRES_USUARIOS[i], intervalos[i]);
            hilo.start();
            System.out.println("▶ Hilo iniciado: " + NOMBRES_USUARIOS[i]
                    + " (cada " + intervalos[i] + "s)");
        }

        System.out.println("\nTodos los hilos corriendo. Cierra la terminal para detener.\n");      // termina al cerrar la terminal

        
        Thread.currentThread().join(); // Mantener el hilo principal vivo 
    }
}