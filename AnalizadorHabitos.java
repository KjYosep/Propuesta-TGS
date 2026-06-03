public class AnalizadorHabitos {

    public String calcularRiesgo(double horas) {

        if (horas < 4) {
            return "BAJO";
        } else if (horas <= 7) {
            return "MEDIO";
        } else {
            return "ALTO";
        }
    }
}