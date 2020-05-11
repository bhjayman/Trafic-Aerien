
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;

public class Vol implements Comparator<Vol> {

    private String numVol;
    private String villeDep;
    private String villeArr;
    private LocalDateTime dateDep;
    private LocalDateTime dateArr;
    private String type;
    private Avion avion;
    private String piste;



    public Vol(String nv, String vd, String va, LocalDateTime dd, LocalDateTime da, String type, Avion av,String piste) {
        this.numVol = nv;
        this.villeDep = vd;
        this.villeArr = va;
        this.dateDep = dd;
        this.dateArr = da;
        this.type = type;
        this.avion = av;
        this.piste=piste;
    }

    /**
     * @return the numVol
     */
    public String getNumVol() {
        return numVol;
    }

    public String getPiste() {
        return piste;
    }
    public void setPiste(String piste) {
        this.piste=piste;
    }
    /**
     * @param numVol the numVol to set
     */
    public void setNumVol(String numVol) {
        this.numVol = numVol;
    }

    /**
     * @return the villeDep
     */
    public String getVilleDep() {
        return villeDep;
    }

    /**
     * @param villeDep the villeDep to set
     */
    public void setVilleDep(String villeDep) {
        this.villeDep = villeDep;
    }

    /**
     * @return the villeArr
     */
    public String getVilleArr() {
        return villeArr;
    }

    /**
     * @param villeArr the villeArr to set
     */
    public void setVilleArr(String villeArr) {
        this.villeArr = villeArr;
    }

    /**
     * @return the dateDep
     */
    public LocalDateTime getDateDep() {
        return dateDep;
    }

    /**
     * @param dateDep the dateDep to set
     */
    public void setDateDep(LocalDateTime dateDep) {
        this.dateDep = dateDep;
    }

    /**
     * @return the dateArr
     */
    public LocalDateTime getDateArr() {
        return dateArr;
    }

    /**
     * @param dateArr the dateArr to set
     */
    public void setDateArr(LocalDateTime dateArr) {
        this.dateArr = dateArr;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the avion
     */
    public Avion getAvion() {
        return avion;
    }

    /**
     * @param avion the avion to set
     */
    public void setAvion(Avion avion) {
        this.avion = avion;
    }


    @Override
    public int compare(Vol o1, Vol o2) {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
    **Comparer les vols par leur niveau de kerosene
    */
    static Comparator<Vol> compNK() {
        return new Comparator<Vol>() {

            @Override
            public int compare(Vol v1, Vol v2) {
                // TODO Auto-generated method stub
                return (v1.avion.getNK() - v2.getAvion().getNK());
            }
            // compare using attribute 1
        };
    }

    /*
    **Comparer en utilisant la date de départ
    */

    static Comparator<Vol> compDd() {
        return new Comparator<Vol>() {

            @Override
            public int compare(Vol v1, Vol v2) {
                // TODO Auto-generated method stub
                Duration dr = Duration.between(v2.getDateDep(), v1.getDateDep());
                return (int)(dr.getSeconds());
            }
            // compare using attribute 1
        };
    }

    
}
