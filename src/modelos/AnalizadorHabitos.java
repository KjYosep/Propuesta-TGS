public class AnalizadorHabitos {

    // esta clase clasifica a los usuarios en niveles de riesgo segun su tiempo de uso
    
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