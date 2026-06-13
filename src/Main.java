import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Main {

    // ── Nombres de usuarios de ejemplo ───────────────────────────────────────
    private static final String[] NOMBRES_USUARIOS = {
        "Alejo", "Valeria", "Santiago", "Mariana", "Esteban"
    };

    // ── Cantidad de actividades por usuario: entre MIN y MAX ─────────────────
    private static final int MIN_ACTIVIDADES = 3;
    private static final int MAX_ACTIVIDADES = 6;

    public static void main(String[] args) {

        // ── 1. Semilla aleatoria única por ejecución ──────────────────────────
        Random rng = new Random();          // sin semilla fija → diferente cada vez
        // Random rng = new Random(42);     // descomenta para resultados repetibles

        // ── 2. Día actual del sistema ─────────────────────────────────────────
        LocalDate hoy = LocalDate.now();
        DayOfWeek dia = hoy.getDayOfWeek();

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("   SISTEMA DE CONTROL DE ADICCIÓN DIGITAL");
        System.out.println("   Hoy: " + dia + " (" + hoy + ")");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // ── 3. Instanciar colaboradores ───────────────────────────────────────
        AnalizadorHabitos analizador  = new AnalizadorHabitos();
        GeneradorAlertas  alertas     = new GeneradorAlertas();
        AsistenteIA       asistente   = new AsistenteIA();

        // ── 4. Seleccionar cuántos usuarios simular (2 a 5) ──────────────────
        int cantidadUsuarios = 2 + rng.nextInt(4); // entre 2 y 5 usuarios

        for (int u = 0; u < cantidadUsuarios; u++) {

            // ── 4a. Crear usuario con nombre aleatorio (sin repetir) ──────────
            String nombreUsuario = NOMBRES_USUARIOS[u % NOMBRES_USUARIOS.length];
            Usuario usuario = new Usuario(nombreUsuario);

            // ── 4b. Número de actividades aleatorio para este usuario ─────────
            int numActividades = MIN_ACTIVIDADES + rng.nextInt(MAX_ACTIVIDADES - MIN_ACTIVIDADES + 1);

            // ── 4c. Generar actividades sin conflictos ────────────────────────
            usuario.generarActividadesAleatorias(numActividades, rng);

            // ── 4d. Ordenar la lista según el día de la semana ────────────────
            ordenarSegunDia(usuario.getActividades(), dia, rng);

            // ── 4e. Generar uso digital aleatorio y calcular riesgo ───────────
            MonitorUso monitor = new MonitorUso(rng);
            String riesgo = analizador.calcularRiesgo(monitor.getTiempoTotal());

            // ── 4f. Mostrar todo el resumen del usuario ───────────────────────
            System.out.println("══════════════════════════════════════════════");
            System.out.println(" USUARIO: " + nombreUsuario
                    + "  |  Actividades: " + usuario.getActividades().size()
                    + "  |  Riesgo: " + riesgo);
            System.out.println("══════════════════════════════════════════════");

            System.out.println("\n── USO DIGITAL DEL DÍA ──");
            monitor.mostrarUso();

            System.out.println("\n── ALERTA ──");
            alertas.mostrarAlerta(riesgo);

            // ── 4g. Horario generado por el AsistenteIA ───────────────────────
            asistente.generarHorario(usuario, riesgo);
        }

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("   Planificación completada. ¡Buen día!");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    // ── Lógica de ordenamiento por día (igual que tu versión original) ────────
    private static void ordenarSegunDia(ArrayList<Actividad> lista,
                                        DayOfWeek dia, Random rng) {
        switch (dia) {
            case MONDAY:
            case WEDNESDAY:
                // Primero lo más largo
                lista.sort(Comparator.comparingDouble(Actividad::getHoras).reversed());
                break;
            case TUESDAY:
            case THURSDAY:
                // Victorias rápidas primero
                lista.sort(Comparator.comparingDouble(Actividad::getHoras));
                break;
            case FRIDAY:
            case SATURDAY:
            case SUNDAY:
                // Fin de semana: orden aleatorio
                Collections.shuffle(lista, rng);
                break;
        }
    }
}