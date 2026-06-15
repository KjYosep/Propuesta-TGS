

public class Main {

    private static final String[] NOMBRES_USUARIOS = {
        "Sofía", "Mateo", "Valentina", "Sebastián", "Isabella", "Daniel", "Camila", "Matías", "Lucía", "Santiago", "Emilia", "Nicolás", "Martina", "Gabriel", 
        "Antonella", "Joaquín", "Victoria", "Lucas", "Renata", "Alejandro", "Daniela", "Andrés", "Florencia", "Benjamín", "Julia", "Francisco", "Emma", "Martín", 
        "Carla", "Thiago", "Mia", "Ignacio", "Clara", "Diego", "Luciana", "José", "Paula", "Manuel", "Martina", "Juan", "Emilia", "Pedro", "Camila", "Luis", "Ana", 
        "Carlos", "Laura", "Jorge", "Sofía", "Ricardo"
    };

    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("   SISTEMA DE CONTROL DE ADICCIÓN DIGITAL");
        System.out.println("   Simulación continua — cierra la terminal para detener");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // Lanzar un hilo por usuario, cada uno con un intervalo distinto (en segundos)
        int[] intervalos = {5, 7, 9, 11, 13};

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