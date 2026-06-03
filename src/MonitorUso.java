public class MonitorUso {

    private double redesSociales;
    private double juegos;
    private double productividad;
    private double Musica;
    private double streaming;
    private double otros;
    private double tiempoTotal;


    public MonitorUso(double redesSociales, double juegos, double productividad, double Musica, double streaming, double otros) {
        this.redesSociales = redesSociales;
        this.juegos = juegos;
        this.productividad = productividad;
        this.Musica = Musica;
        this.streaming = streaming;
        this.otros = otros;
        this.tiempoTotal = redesSociales + juegos + productividad + Musica + streaming + otros;
    }

    public double getTiempoTotal() {
        return tiempoTotal;
    }

    public void mostrarUso() {
        System.out.println("Redes Sociales: " + redesSociales + " horas");
        System.out.println("Juegos: " + juegos + " horas");
        System.out.println("Productividad: " + productividad + " horas");
        System.out.println("Música: " + Musica + " horas");
        System.out.println("Streaming: " + streaming + " horas");
        System.out.println("Otros: " + otros + " horas");
    }
}
        