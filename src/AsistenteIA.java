import java.util.Scanner;

public class AsistenteIA {
    public void generarHorario(String riesgo) {
        System.out.println("\nHORARIO RECOMENDADO");
        Scanner actividades = new Scanner(System.in);

        if (riesgo.equals("ALTO")) {
            System.out.println("Su nivel de riesgo es " + riesgo);
            System.out.println("\nA continuacion, se le recomienda un horario personalizado para reducir su adiccion digital y mejorar su bienestar general.");
            System.out.println("Digite cuantas actividades realiza al dia: ");
            int cantidad = actividades.nextInt();
            actividades.nextLine(); // limpiar buffer
            String[] listaActividades = new String[cantidad];
            for (int i = 0; i < cantidad; i++) {
                System.out.print("Digite la actividad " + (i + 1) + ": ");
                listaActividades[i] = actividades.nextLine();
            }

            System.out.println("\nHorario personalizado basado en sus actividades:");
            System.out.println("06:00 - Levantarse y ejercicio matutino (sin pantallas)");
            System.out.println("07:00 - Desayuno");
            System.out.println("08:00 - Inicio de actividades del dia:");

            int hora = 8;
            for (int i = 0; i < listaActividades.length; i++) {
                System.out.printf("%02d:00 - %s%n", hora, listaActividades[i]);
                hora++;
                if (hora == 12) {
                    System.out.println("12:00 - Almuerzo (sin pantallas)");
                    hora = 13;
                }
            }

            if (hora <= 17) hora = 17;
            System.out.printf("%02d:00 - Tiempo al aire libre o actividad fisica%n", hora);
            System.out.printf("%02d:00 - Cena%n", hora + 1);
            System.out.printf("%02d:00 - Maximo 30 minutos de pantalla permitidos%n", hora + 2);
            System.out.println("22:00 - Dormir (sin dispositivos en el cuarto)");

        } else if (riesgo.equals("MEDIO")) {
            System.out.println("Su nivel de riesgo es " + riesgo);
            System.out.println("\nA continuacion, se le recomienda un horario personalizado para reducir su adiccion digital y mejorar su bienestar general.");
            System.out.println("Digite cuantas actividades realiza al dia: ");
            int cantidad = actividades.nextInt();
            actividades.nextLine(); // limpiar buffer
            String[] listaActividades = new String[cantidad];
            for (int i = 0; i < cantidad; i++) {
                System.out.print("Digite la actividad " + (i + 1) + ": ");
                listaActividades[i] = actividades.nextLine();
            }

            System.out.println("\nHorario personalizado basado en sus actividades:");
            System.out.println("07:00 - Levantarse");
            System.out.println("07:30 - Desayuno");
            System.out.println("08:00 - Inicio de actividades del dia:");

            int hora = 8;
            for (int i = 0; i < listaActividades.length; i++) {
                System.out.printf("%02d:00 - %s%n", hora, listaActividades[i]);
                hora++;
                if (hora == 12) {
                    System.out.println("12:00 - Almuerzo");
                    hora = 13;
                }
            }

            if (hora <= 17) hora = 17;
            System.out.printf("%02d:00 - Tiempo libre (maximo 1 hora de pantalla)%n", hora);
            System.out.printf("%02d:00 - Cena%n", hora + 1);
            System.out.printf("%02d:00 - Redes sociales (maximo 30 minutos)%n", hora + 2);
            System.out.println("23:00 - Dormir");

        } else {
            System.out.println("Su nivel de riesgo es " + riesgo);
            System.out.println("Mantenga sus habitos actuales, pero recuerde tomar descansos regulares y limitar el tiempo de pantalla para evitar futuros riesgos.");
        }
    }
}