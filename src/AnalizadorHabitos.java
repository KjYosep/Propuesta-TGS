public class AnalizadorHabitos {

    public String calcularRiesgo(double tiempoTotal) {

        if (tiempoTotal <= 4) {
            return "BAJO";
        } else if (tiempoTotal <= 7) {
            return "MEDIO";
        } else {
            return "ALTO";
        }
    }
}