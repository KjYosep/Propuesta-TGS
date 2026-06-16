// la clase actividad es para representar las actividades diarias del usuario, cada actividad tiene un nombre, una duración en horas 
// y una hora de inicio (opcional, con valor por defecto a las 8am)

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
    public String toString() {      // formato de impresión: "08:00 - 10:30 | Nombre de actividad (2.5 h)"
        return String.format("%02d:00 - %02d:00 | %-25s (%.1f h)", 
                horaInicio, getHoraFin(), nombre, horas);
    }
}