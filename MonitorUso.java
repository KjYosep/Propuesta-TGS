public class MonitorUso {

    private double tikTok;
    private double instagram;
    private double youtube;
    private double juegos;

    public MonitorUso(double tikTok, double instagram, double youtube, double juegos) {
        this.tikTok = tikTok;
        this.instagram = instagram;
        this.youtube = youtube;
        this.juegos = juegos;
    }

    public double getTiempoTotal() {
        return tikTok + instagram + youtube + juegos;
    }

    public void mostrarUso() {
        System.out.println("TikTok: " + tikTok + " horas");
        System.out.println("Instagram: " + instagram + " horas");
        System.out.println("YouTube: " + youtube + " horas");
        System.out.println("Juegos: " + juegos + " horas");
    }
}