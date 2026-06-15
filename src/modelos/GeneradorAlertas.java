public class GeneradorAlertas {

    public void mostrarAlerta(String riesgo) {

        switch (riesgo) {   //riesgo se calcula en la clase AnalizadorHabitos
            case "ALTO":
                System.out.println("\tALERTA: Uso excesivo del celular. ¡Tome medidas hoy!");       // se generan alertas dependiendo del nivel de riesgo que presente el usuario
                break;
            case "MEDIO":
                System.out.println("\tADVERTENCIA: Controle su tiempo de pantalla.");
                break;
            default:
                System.out.println("\tBuen manejo del tiempo digital. ¡Siga así!");
        }
    }
}