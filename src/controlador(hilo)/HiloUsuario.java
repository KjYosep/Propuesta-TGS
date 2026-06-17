import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/* Esta es la clase del hilo, aqui se maneja todo el entorno*/

public class HiloUsuario extends Thread {

    private final String nombre;
    private final int intervaloSegundos; // cada cuántos segundos genera una nueva planificación

    // se crean instancias de las clases necesarias para el ciclo de simulación: el analizador de hábitos, el generador de alertas y el asistente de IA
    private final AnalizadorHabitos analizador = new AnalizadorHabitos();   
    private final GeneradorAlertas  alertas    = new GeneradorAlertas();
    private final AsistenteIA       asistente  = new AsistenteIA();

    public HiloUsuario(String nombre, int intervaloSegundos) {
        this.nombre = nombre;       
        this.intervaloSegundos = intervaloSegundos;

        setName("Hilo-" + nombre);      // se asigna un nombre al hilo para identificarlo en la salida de la consola
        setDaemon(true);    // lo que hace que el hilo se cierre solo al cerrar la terminal
    }

    @Override
    public void run() {     // iniciar el ciclo de simulación para este usuario, que se repite cada intervaloSegundos segundos
        Random rng = new Random();      // cada hilo tiene su propio Random → resultados independientes

        while (!Thread.currentThread().isInterrupted()) {   // mientras el hilo no sea interrumpido se seguiran generando planificaciones para el usuario cada intervaloSegundos segundos
            try {
                generarCiclo(rng);
                Thread.sleep(intervaloSegundos * 1000L);    // esperar el intervalo antes de generar la siguiente planificación
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();     // poner el hilo en estado de interrupción para salir del ciclo (si se interrumpe desde fuera)
            }
        }
    }

    
    private void generarCiclo(Random rng) {     // este método genera una planificación completa para el usuario:
        LocalDate hoy = LocalDate.now();        
        DayOfWeek dia = hoy.getDayOfWeek();     // usa la fecha actual

        Usuario usuario = new Usuario(nombre);          // se crea el usuario
        int numActividades = 3 + rng.nextInt(4);    // entre 3 y 6 actividades diarias aleatorias para cada usuario
        usuario.generarActividadesAleatorias(numActividades, rng);  // se generan las actividades teniendo en cuenta el numero de activiades 

        ordenarSegunDia(usuario.getActividades(), dia, rng);    // ordena las actividades según el día de la semana 
        // (lunes y miércoles: más tiempo en actividades largas, martes y jueves: más tiempo en actividades cortas, fines de semana: orden aleatorio)

        // Uso digital y riesgo
        MonitorUso monitor = new MonitorUso(rng);
        String riesgo = analizador.calcularRiesgo(monitor.getTiempoTotal());    // ejecuta el analizador dde habitos

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

//  ordenar actividades segun el dia de la semana
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
