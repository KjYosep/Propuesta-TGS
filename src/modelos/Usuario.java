import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Usuario {
    protected String nombre;
    protected ArrayList<Actividad> actividades;

    // ── Banco de nombres de actividades posibles ──────────────────────────────
    private static final String[] BANCO_ACTIVIDADES = {
        "Estudiar", "Entrenar en el gimnasio", "Reunión de equipo",
        "Leer un libro", "Meditar", "Avanzar en proyecto",
        "Desayuno tranquilo", "Salir a caminar", "Practicar guitarra",
        "Revisar correos", "Cocinar", "Videollamada familiar",
        "Escribir en el diario", "Aprender idioma", "Yoga",
        "Ver documental", "Dibujar / diseñar", "Investigación académica",
        "Deporte al aire libre", "Organizar el espacio de trabajo"
    };

    // ── Duraciones posibles en horas (0.5, 1.0, 1.5 … 3.0) ──────────────────
    private static final double[] DURACIONES_POSIBLES = {0.5, 1.0, 1.5, 2.0, 2.5, 3.0};

    // ── Rango horario permitido: 6 am – 10 pm ─────────────────────────────────
    private static final int HORA_MIN = 6;
    private static final int HORA_MAX = 22;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.actividades = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public ArrayList<Actividad> getActividades() { return actividades; }

    // ── Agregar actividad manualmente (mantiene compatibilidad) ────────────────
    public void agregarActividad(String nombre, double horas) {
        actividades.add(new Actividad(nombre, horas));
    }

    /**
     * Genera entre minAct y maxAct actividades aleatorias para este usuario,
     * garantizando que no haya conflictos de horario.
     *
     * @param cantidad número de actividades a intentar programar
     * @param rng      instancia de Random compartida (o nueva si se pasa null)
     */
    public void generarActividadesAleatorias(int cantidad, Random rng) {
        if (rng == null) rng = new Random();
        actividades.clear();

        // Mezclar el banco para que cada usuario tenga un subconjunto distinto
        ArrayList<String> bancoMezclado = new ArrayList<>();
        for (String a : BANCO_ACTIVIDADES) bancoMezclado.add(a);
        Collections.shuffle(bancoMezclado, rng);

        int intentosMaximos = cantidad * 5; // intentos para encontrar hueco libre
        int actividadesAgregadas = 0;
        int idx = 0;

        for (int intento = 0; intento < intentosMaximos && actividadesAgregadas < cantidad; intento++) {
            if (idx >= bancoMezclado.size()) break; // se agotaron los nombres

            String nombre  = bancoMezclado.get(idx);
            double duracion = DURACIONES_POSIBLES[rng.nextInt(DURACIONES_POSIBLES.length)];
            int horaInicio  = HORA_MIN + rng.nextInt(HORA_MAX - HORA_MIN - (int) Math.ceil(duracion));

            if (!hayConflicto(horaInicio, duracion)) {
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