import java.util.Scanner;

public class AsistenteIA {

    public void generarHorario(String riesgo) {

        System.out.println("\nHORARIO RECOMENDADO");

        Scanner actividades = new Scanner(System.in);

        if (riesgo.equals("ALTO")) {
            
            System.out.println("Su nivel de riesgo es " + riesgo );
            System.out.println("\nA continuacion, se le recomienda un horario personalizado para reducir su adicción digital y mejorar su bienestar general.");
            
            System.out.println("Digite cuantas actividades realiza al dia: ");
                int cantidad = actividades.nextInt();
    
                String[] listaActividades = new String[cantidad];
    
                for (int i = 0; i < cantidad; i++) {
                    System.out.println("Digite la actividad " + (i + 1) + ": ");
                    listaActividades[i] = actividades.next();
                }
    
                System.out.println("\nHorario personalizado basado en sus actividades:");
                System.out.println("Levantarse temprano para realizar actividades productivas y limitar el tiempo de pantalla durante el día. (recomendacion: 08:00 -hora de levantarse)");
    
                for (int i = 0; i < listaActividades.length; i++) {
                    System.out.println((i + 1) + ". " + listaActividades[i]);
                }
                // Horario recomendado para riesgo alto 

        } else if (riesgo.equals("MEDIO")) {

            System.out.println("Su nivel de riesgo es " + riesgo );
            System.out.println("\nA continuacion, se le recomienda un horario personalizado para reducir su adicción digital y mejorar su bienestar general.");

            System.out.println("Digite cuantas actividades realiza al dia: ");
                int cantidad = actividades.nextInt();
    
                String[] listaActividades = new String[cantidad];
    
                for (int i = 0; i < cantidad; i++) {
                    System.out.println("Digite la actividad " + (i + 1) + ": ");
                    listaActividades[i] = actividades.next();
                }
    
                System.out.println("\nHorario personalizado basado en sus actividades:");
                System.out.println("Levantarse temprano para realizar actividades productivas y limitar el tiempo de pantalla durante el día. (recomendacion: 08:00 -hora de levantarse)");
    
                for (int i = 0; i < listaActividades.length; i++) {
                    System.out.println((i + 1) + ". " + listaActividades[i]);
                }
                // Horario recomendado para riesgo medio 

        } else {
            System.out.println("Su nivel de riesgo es " + riesgo );
            System.out.println("Mantenga sus hábitos actuales, pero recuerde tomar descansos regulares y limitar el tiempo de pantalla para evitar futuros riesgos.");
        }
        actividades.close();
    }
}