public class AsistenteIA {

    public void generarHorario(String riesgo) {

        System.out.println("\nHORARIO RECOMENDADO");

        if (riesgo.equals("ALTO")) {

            System.out.println("08:00 - 12:00 Estudio");
            System.out.println("12:00 - 13:00 Almuerzo");
            System.out.println("13:00 - 14:00 Ejercicio");
            System.out.println("14:00 - 17:00 Estudio");
            System.out.println("17:00 - 18:00 Ocio saludable");
            System.out.println("22:00 Dormir");

        } else if (riesgo.equals("MEDIO")) {

            System.out.println("08:00 - 12:00 Estudio");
            System.out.println("13:00 - 14:00 Ejercicio");
            System.out.println("18:00 - 19:00 Redes Sociales");
            System.out.println("22:00 Dormir");

        } else {

            System.out.println("Mantenga sus hábitos actuales.");
        }
    }
}