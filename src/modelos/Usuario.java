import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/* Esta es la clase Usuario, representa a los usuarios del sistema
 --» Cadad usuario tiene un nombre y una lista de actividades diarias que se muestran en el hilo de forma aleatoria
 --» Tambien se tiene un tiempo de uso que se une aleatoriamente con cada actividad con el fin de detectar el nivel de riesgo del usuario 
 y de generar un horario recomendado por el asistente de IA
 --» Se asegura de que no se repitan actividades para un mismo usuario en un horario
*/

public class Usuario {
    protected String nombre;
    protected ArrayList<Actividad> actividades;

    private static final String[] BANCO_ACTIVIDADES = {
        "Estudiar", "Entrenar en el gimnasio", "Reunión de equipo",
        "Leer un libro", "Meditar", "Avanzar en proyecto",
        "Desayuno tranquilo", "Salir a caminar", "Practicar guitarra",      // actividades de cada usuario
        "Revisar correos", "Cocinar", "Videollamada familiar",
        "Escribir en el diario", "Aprender idioma", "Yoga",
        "Ver documental", "Dibujar / diseñar", "Investigación académica",
        "Deporte al aire libre", "Organizar el espacio de trabajo"
    };

    private static final double[] DURACIONES_POSIBLES = {0.5, 1.0, 1.5, 2.0, 2.5, 3.0}; // Tiempo de uso de cada actividad (en horas)

    private static final int HORA_MIN = 6;
    private static final int HORA_MAX = 22;     // horario de 6 AM «--» 10 PM para actividades

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.actividades = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public ArrayList<Actividad> getActividades() { return actividades; }

    // Agregar actividad manualmente (ya no se usa, se reemplazo por el banco de actividades aleatorias)
    public void agregarActividad(String nombre, double horas) {
        actividades.add(new Actividad(nombre, horas));
    }

    // cantidad son las actividades a generar, rng es la fuente de aleatoriedad (puede ser null para crear una nueva)
    public void generarActividadesAleatorias(int cantidad, Random rng) {    
        if (rng == null) rng = new Random();
        actividades.clear();        // limpiar las actividades previas para evitar que se muestren repetidas

        ArrayList<String> bancoMezclado = new ArrayList<>();
        for (String a : BANCO_ACTIVIDADES) bancoMezclado.add(a);        // aqui se mezclas las actividades para que sean distintas cada vez
        Collections.shuffle(bancoMezclado, rng);

        int intentosMaximos = cantidad * 10; // para evitar loops infinitos si no se pueden ubicar actividades sin que sean iguales 
        int actividadesAgregadas = 0;      
        int idx = 0;

        for (int intento = 0; intento < intentosMaximos && actividadesAgregadas < cantidad; intento++) {
            if (idx >= bancoMezclado.size()) break; //si ya usamos todas las actividades disponibles, no podemos agregar más

            String nombre  = bancoMezclado.get(idx);        // se toma el nombre de la actividad del banco mezclado (no se repiten hasta que se acaben)
            double duracion = DURACIONES_POSIBLES[rng.nextInt(DURACIONES_POSIBLES.length)];     // se asigna una duración aleatoria a la actividad (en horas)
            int horaInicio  = HORA_MIN + rng.nextInt(HORA_MAX - HORA_MIN - (int) Math.ceil(duracion)); // se asigna una hora de inicio aleatoria, asegurando que la actividad termine antes de HORA_MAX

            

            if (!hayConflicto(horaInicio, duracion)) {  // si no hay conflicto con actividades ya programadas, se agrega al horario del usuario
                actividades.add(new Actividad(nombre, duracion, horaInicio));
                actividadesAgregadas++;
                idx++; // solo avanzamos en el banco cuando el nombre fue aceptado
            }
            // Si hay conflicto intentamos otra hora con el mismo nombre (sin avanzar idx)
        }

        // Ordenar cronológicamente para mostrar limpio
        actividades.sort((a, b) -> Integer.compare(a.getHoraInicio(), b.getHoraInicio()));
    }

    /**
     * Devuelve true si el nuevo bloque (horaInicio, duración) se solapa
     * con alguna actividad ya programada.
     */
    private boolean hayConflicto(int horaInicio, double duracion) {
        int nuevaFin = horaInicio + (int) Math.ceil(duracion);
        for (Actividad existente : actividades) {
            // Solapamiento si los intervalos se intersectan
            if (horaInicio < existente.getHoraFin() && nuevaFin > existente.getHoraInicio()) {
                return true;
            }
        }
        return false;
    }
}