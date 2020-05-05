public class Avion {

    private int id;
    private String marque;
    private String modele;
    private int NK;
    private int latitude;
    private int longitude;
    private int etatUrgence;

public Avion(){

}
public Avion (int id,String marque,String modele,int NK,int lat, int longitude,int etatUrgence){
    this.id = id;
    this.marque = marque;
    this.modele = modele;
    this. NK = NK;
    this.latitude = lat;  
    this. longitude = longitude;
    this.etatUrgence = etatUrgence;
}
public int getId() {
return id;
}
public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getNK() {
        return NK;
    }

    public void setNK(int NK) {
        this.NK = NK;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int altitude) {
        this.latitude = altitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getEtatUrgence() {
        return etatUrgence;
    }

    public void setEtatUrgence(int EtatUrgence) {
        this.etatUrgence = EtatUrgence;
    }
}