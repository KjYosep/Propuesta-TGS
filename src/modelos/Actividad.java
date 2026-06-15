public class Actividad {
    private String nombre;
    private double horas;
    private int horaInicio; // hora del día en que comienza (0-23)

    public Actividad(String nombre, double horas, int horaInicio) {
        this.nombre = nombre;
        this.horas = horas;
        this.horaInicio = horaInicio;
    }

    // Constructor de compatibilidad sin hora de inicio
    public Actividad(String nombre, double horas) {
        this(nombre, horas, 8); // valor por defecto: 8am
    }

    public String getNombre() { return nombre; }
    public double getHoras()   { return horas; }
    public int getHoraInicio() { return horaInicio; }

    // Hora en que termina actividad (horaInicio + duración redondeada) 
    public int getHoraFin() {
        return horaInicio + (int) Math.ceil(horas);
    }

    @Override
    public String toString() {
        return String.format("%02d:00 - %02d:00 | %-25s (%.1f h)",
                horaInicio, getHoraFin(), nombre, horas);
    }
}