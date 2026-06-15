import java.util.Random;

public class MonitorUso {

    private double redesSociales;
    private double juegos;
    private double productividad;
    private double musica;
    private double streaming;
    private double otros;
    private double tiempoTotal;

    // ── Constructor original (se mantiene para compatibilidad) ────────────────
    public MonitorUso(double redesSociales, double juegos, double productividad,
                      double musica, double streaming, double otros) {
        this.redesSociales  = redesSociales;
        this.juegos         = juegos;
        this.productividad  = productividad;
        this.musica         = musica;
        this.streaming      = streaming;
        this.otros          = otros;
        this.tiempoTotal    = redesSociales + juegos + productividad + musica + streaming + otros;
    }

    /**
     * Constructor de generación aleatoria.
     * Cada categoría recibe entre 0 y maxHorasPorCategoria horas (con paso de 0.5).
     *
     * @param rng instancia Random; si es null crea una nueva
     */
    public MonitorUso(Random rng) {
        if (rng == null) rng = new Random();
        this.redesSociales  = redondearMedio(rng.nextDouble() * 4);   // 0 – 4 h
        this.juegos         = redondearMedio(rng.nextDouble() * 3);   // 0 – 3 h
        this.productividad  = redondearMedio(rng.nextDouble() * 3);   // 0 – 3 h
        this.musica         = redondearMedio(rng.nextDouble() * 2);   // 0 – 2 h
        this.streaming      = redondearMedio(rng.nextDouble() * 3);   // 0 – 3 h
        this.otros          = redondearMedio(rng.nextDouble() * 2);   // 0 – 2 h
        this.tiempoTotal    = redesSociales + juegos + productividad + musica + streaming + otros;
    }

    /** Redondea al múltiplo de 0.5 más cercano */
    private double redondearMedio(double valor) {
        return Math.round(valor * 2) / 2.0;
    }

    public double getTiempoTotal() { return tiempoTotal; }

    public void mostrarUso() {
        System.out.println("  Redes Sociales : " + redesSociales  + " h");
        System.out.println("  Juegos         : " + juegos          + " h");
        System.out.println("  Productividad  : " + productividad   + " h");
        System.out.println("  Música         : " + musica          + " h");
        System.out.println("  Streaming      : " + streaming       + " h");
        System.out.println("  Otros          : " + otros           + " h");
        System.out.printf ("  TOTAL          : %.1f h%n", tiempoTotal);
    }
}
        