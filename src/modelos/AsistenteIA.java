import java.util.ArrayList;

// Esta clase representa al asistente de IA, que genera un horario recomendado para el usuario basado en su nivel de riesgo digital
// como las actividades diarias del usuario se muestran en el hilo de forma aleatoria, el asistente de IA se encarga de generar un horario 
// recomendado para el usuario basado en su nivel de riesgo digital, que se calcula en la clase AnalizadorHabitos

// hay que tener en cuenta que ya las actividades diarias fueron asignadas aleatoriamente en la clase usuario, entonce aqui solamente se muestran los 3 horarios unidos

public class AsistenteIA {

    public void generarHorario(Usuario usuario, String riesgo) {
        ArrayList<Actividad> lista = usuario.getActividades();

        System.out.println("\n================================================");
        System.out.println("  HORARIO RECOMENDADO — " + usuario.getNombre());       
        System.out.println("  Nivel de riesgo digital: " + riesgo);
        System.out.println("=====================================================");

        if (lista.isEmpty()) {
            System.out.println("  (No se generaron actividades para este usuario)");    // si el usuario no tiene actividades, se muestra un mensaje y se termina la función
            return;
        }

        // horario antes de las actividades diarias
        imprimirBloqueInicial(riesgo);

        // Actividades del día ordenadas cronológicamente
        for (Actividad act : lista) {       
            System.out.println("  " + act);  
        }

        // horario después de las actividades diarias
        imprimirBloqueFinal(riesgo);
    }

    private void imprimirBloqueInicial(String riesgo) {         // si el riesgo es alto, este es el horario recomendado antes de las actividades diarias
        System.out.println();
        if (riesgo.equals("ALTO")) {
            System.out.println("  06:00 - 07:00 | Levantarse y ejercicio (SIN pantallas)  (1.0 h)");        
            System.out.println("  07:00 - 08:00 | Desayuno sin dispositivos                (1.0 h)");
        } else if (riesgo.equals("MEDIO")) {                                   // este para el riesgo medio
            System.out.println("  07:00 - 07:30 | Levantarse                               (0.5 h)");
            System.out.println("  07:30 - 08:00 | Desayuno                                 (0.5 h)");
        } else {                                                                // este para el riesgo bajo
            System.out.println("  07:00 - 08:00 | Rutina matutina libre                    (1.0 h)");
        }
        System.out.println("  ──────────────────────────────────────────────");
    }

    private void imprimirBloqueFinal(String riesgo) {       // si el riesgo es alto, estes es el horario para el final del dia que se recomienda
        System.out.println("  ──────────────────────────────────────────────");
        if (riesgo.equals("ALTO")) {
            System.out.println("  20:00 - 21:00 | Tiempo al aire libre                     (1.0 h)");
            System.out.println("  21:00 - 21:30 | Cena sin pantallas                       (0.5 h)");
            System.out.println("  21:30 - 22:00 | Máx. 30 min de pantalla permitidos       (0.5 h)");
            System.out.println("  22:00         | DORMIR (sin dispositivos en el cuarto)");
        } else if (riesgo.equals("MEDIO")) {                                        // este para el riesgo medio
            System.out.println("  20:00 - 21:00 | Tiempo libre (máx. 1 h de pantalla)      (1.0 h)");
            System.out.println("  21:00 - 21:30 | Cena                                     (0.5 h)");
            System.out.println("  21:30 - 22:00 | Redes sociales (máx. 30 min)             (0.5 h)");
            System.out.println("  23:00         | DORMIR");
        } else {                                                            // este para el riesgo bajo                                         
            System.out.println("  20:00 - 22:00 | Tiempo personal / ocio digital moderado  (2.0 h)");
            System.out.println("  23:00         | DORMIR");
        }
        System.out.println();
    }
}