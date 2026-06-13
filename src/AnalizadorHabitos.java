public class AnalizadorHabitos {

    /**
     * Calcula el nivel de riesgo según el tiempo total de uso digital diario.
     *
     * @param tiempoTotal horas totales de uso digital
     * @return "BAJO", "MEDIO" o "ALTO"
     */
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