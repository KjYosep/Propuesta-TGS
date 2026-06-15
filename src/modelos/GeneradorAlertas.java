public class GeneradorAlertas {

    /**
     * Muestra una alerta según el nivel de riesgo calculado.
     *
     * @param riesgo "ALTO", "MEDIO" o cualquier otro valor (tratado como BAJO)
     */
    public void mostrarAlerta(String riesgo) {
        switch (riesgo) {
            case "ALTO":
                System.out.println("  ⚠  ALERTA    : Uso excesivo del celular. ¡Tome medidas hoy!");
                break;
            case "MEDIO":
                System.out.println("  ⚠  ADVERTENCIA: Controle su tiempo de pantalla.");
                break;
            default:
                System.out.println("  ✓  Buen manejo del tiempo digital. ¡Siga así!");
        }
    }
}