import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class HiloUsuario extends Thread {

    private final String nombre;
    private final int intervaloSegundos; // cada cuántos segundos genera una nueva planificación

    private final AnalizadorHabitos analizador = new AnalizadorHabitos();
    private final GeneradorAlertas  alertas    = new GeneradorAlertas();
    private final AsistenteIA       asistente  = new AsistenteIA();

    public HiloUsuario(String nombre, int intervaloSegundos) {
        this.nombre             = nombre;
        this.intervaloSegundos  = intervaloSegundos;
        // Nombre visible en la traza de hilos
        setName("Hilo-" + nombre);
        // Hilo daemon: se cierra solo cuando el programa principal termina (Ctrl+C)
        setDaemon(true);
    }

    @Override
    public void run() {
        Random rng = new Random(); // cada hilo tiene su propio Random → resultados independientes

        while (!Thread.currentThread().isInterrupted()) {
            try {
                generarCiclo(rng);
                Thread.sleep(intervaloSegundos * 1000L);
            } catch (InterruptedException e) {
                // Ctrl+C o interrupt() externo: salimos limpiamente
                Thread.currentThread().interrupt();
            }
        }
    }

    // ── Un ciclo completo de simulación para este usuario ─────────────────────
    private void generarCiclo(Random rng) {
        LocalDate hoy = LocalDate.now();
        DayOfWeek dia = hoy.getDayOfWeek();

        // Crear usuario y generar actividades aleatorias
        Usuario usuario = new Usuario(nombre);
        int numActividades = 3 + rng.nextInt(4); // entre 3 y 6
        usuario.generarActividadesAleatorias(numActividades, rng);

        // Ordenar según día de la semana
        ordenarSegunDia(usuario.getActividades(), dia, rng);

        // Uso digital y riesgo
        MonitorUso monitor = new MonitorUso(rng);
        String riesgo = analizador.calcularRiesgo(monitor.getTiempoTotal());

        // Imprimir resultado (sincronizado para que los hilos no mezclen líneas)
        synchronized (System.out) {
            System.out.println("\n══════════════════════════════════════════════");
            System.out.println(" [" + getName() + "]  " + hoy + " (" + dia + ")");
            System.out.println(" Usuario: " + nombre
                    + "  |  Actividades: " + usuario.getActividades().size()
                    + "  |  Riesgo: " + riesgo);
            System.out.println("══════════════════════════════════════════════");

            System.out.println("\n── USO DIGITAL ──");
            monitor.mostrarUso();

            System.out.println("\n── ALERTA ──");
            alertas.mostrarAlerta(riesgo);

            asistente.generarHorario(usuario, riesgo);
        }
    }

    // ── Misma lógica de ordenamiento que Main ─────────────────────────────────
    private static void ordenarSegunDia(ArrayList<Actividad> lista,
                                        DayOfWeek dia, Random rng) {
        switch (dia) {
            case MONDAY:
            case WEDNESDAY:
                lista.sort(Comparator.comparingDouble(Actividad::getHoras).reversed());
                break;
            case TUESDAY:
            case THURSDAY:
                lista.sort(Comparator.comparingDouble(Actividad::getHoras));
                break;
            default:
                Collections.shuffle(lista, rng);
                break;
        }
    }
}
