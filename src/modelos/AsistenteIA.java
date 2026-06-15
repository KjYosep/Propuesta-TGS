import java.util.ArrayList;

public class AsistenteIA {

    public void generarHorario(Usuario usuario, String riesgo) {
        ArrayList<Actividad> lista = usuario.getActividades();

        System.out.println("\n================================================");
        System.out.println("  HORARIO RECOMENDADO — " + usuario.getNombre());
        System.out.println("  Nivel de riesgo digital: " + riesgo);
        System.out.println("=====================================================");

        if (lista.isEmpty()) {
            System.out.println("  (No se generaron actividades para este usuario)");
            return;
        }

        // Encabezado antes del primer bloque (madrugada / mañana temprana)
        imprimirBloqueInicial(riesgo);

        // Actividades del día ordenadas cronológicamente
        for (Actividad act : lista) {
            System.out.println("  " + act);
        }

        // Pie de horario según nivel de riesgo
        imprimirBloqueFinal(riesgo);
    }

    // ── Bloques fijos de apertura según riesgo ────────────────────────────────
    private void imprimirBloqueInicial(String riesgo) {
        System.out.println();
        if (riesgo.equals("ALTO")) {
            System.out.println("  06:00 - 07:00 | Levantarse y ejercicio (SIN pantallas)  (1.0 h)");
            System.out.println("  07:00 - 08:00 | Desayuno sin dispositivos                (1.0 h)");
        } else if (riesgo.equals("MEDIO")) {
            System.out.println("  07:00 - 07:30 | Levantarse                               (0.5 h)");
            System.out.println("  07:30 - 08:00 | Desayuno                                 (0.5 h)");
        } else {
            System.out.println("  07:00 - 08:00 | Rutina matutina libre                    (1.0 h)");
        }
        System.out.println("  ──────────────────────────────────────────────");
    }

    // ── Bloques fijos de cierre según riesgo ──────────────────────────────────
    private void imprimirBloqueFinal(String riesgo) {
        System.out.println("  ──────────────────────────────────────────────");
        if (riesgo.equals("ALTO")) {
            System.out.println("  20:00 - 21:00 | Tiempo al aire libre                     (1.0 h)");
            System.out.println("  21:00 - 21:30 | Cena sin pantallas                       (0.5 h)");
            System.out.println("  21:30 - 22:00 | Máx. 30 min de pantalla permitidos       (0.5 h)");
            System.out.println("  22:00         | DORMIR (sin dispositivos en el cuarto)");
        } else if (riesgo.equals("MEDIO")) {
            System.out.println("  20:00 - 21:00 | Tiempo libre (máx. 1 h de pantalla)      (1.0 h)");
            System.out.println("  21:00 - 21:30 | Cena                                     (0.5 h)");
            System.out.println("  21:30 - 22:00 | Redes sociales (máx. 30 min)             (0.5 h)");
            System.out.println("  23:00         | DORMIR");
        } else {
            System.out.println("  20:00 - 22:00 | Tiempo personal / ocio digital moderado  (2.0 h)");
            System.out.println("  23:00         | DORMIR");
        }
        System.out.println();
    }
}