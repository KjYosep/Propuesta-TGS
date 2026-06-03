public class GeneradorAlertas {

    public void mostrarAlerta(String riesgo) {

        switch (riesgo) {

            case "ALTO":
                System.out.println("\nALERTA: Uso excesivo del celular.");
                break;

            case "MEDIO":
                System.out.println("\nADVERTENCIA: Controle su tiempo de pantalla.");
                break;

            default:
                System.out.println("\nBuen manejo del tiempo digital.");
        }
    }
}