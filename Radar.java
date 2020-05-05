import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Radar {

    static List<Vol> listVol = new ArrayList<Vol>();
    static List<Avion> listAv = new ArrayList<Avion>();

    static void getav() {
        listVol = new ArrayList<Vol>();
        listAv = new ArrayList<Avion>();
        try {
            File myObj = new File("avions.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] values = data.split(",");
                    Avion av = new Avion(Integer.parseInt(values[0]), values[1], values[2],
                                    Integer.parseInt(values[3]), Integer.parseInt(values[4]),
                                    Integer.parseInt(values[5]), Integer.parseInt(values[6]));
                    listAv.add(av);
            }
            myReader.close();
    } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
    }
    try {
            File myObj = new File("vols.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] values = data.split(",");
                    String[] dds = values[3].split(";");
                    String[] das = values[4].split(";");
                    LocalDate dd = LocalDate.of(Integer.parseInt(dds[2]), Integer.parseInt(dds[1]),
                                    Integer.parseInt(dds[0]));
                    LocalDateTime ddt = dd.atTime(Integer.parseInt(dds[3]), Integer.parseInt(dds[4]));
                    LocalDate da = LocalDate.of(Integer.parseInt(das[2]), Integer.parseInt(das[1]),
                                    Integer.parseInt(das[0]));
                    LocalDateTime dat = da.atTime(Integer.parseInt(das[3]), Integer.parseInt(das[4]));
                    Vol v = new Vol(values[0], values[1], values[2], ddt, dat, values[5], null, values[7]);
                    Iterator<Avion> it = listAv.iterator();
                    while (it.hasNext()) {
                            Avion a = it.next();
                            if (a.getId() == Integer.parseInt(values[6])) {
                                    v.setAvion(a);
                                    break;
                            }
                    }
                    if(v.getType().equals("entrant"))
                            listVol.add(v);
                    
            }
            myReader.close();
    } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
    }
    }

    static void aff(){
        
        String[][] s = new String[19][59];

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 59; j++)
                s[i][j] = " ";
        }
        for (int i = 0; i < 19; i++) {

            s[i][0] = "█";
            s[i][58] = "█";

        }
        for (int i = 0; i < 59; i++) {

            s[0][i] = "▄";
            s[18][i] = "▀";

        }
       
        Iterator<Vol> it = listVol.iterator();
        String signe;
        int x=0;
        
        while (it.hasNext()) {
            Vol v = it.next();
            Avion a=v.getAvion();
            // System.out.println("cccc");
            if( a.getLatitude()!= 10 || a.getLongitude()!=29){
            if (a.getLongitude() != 29) {
                if (a.getLongitude() < 29) {

                    signe = "►";
                    a.setLongitude(a.getLongitude() + 1);

                } else {
                    signe = "◄";
                    a.setLongitude(a.getLongitude() - 1);
                }
            }
            else {
                
                if (a.getLatitude() < 10) {

                    signe = "▼";
                    a.setLatitude(a.getLatitude() + 1);

                } else {
                    signe = "▲";
                    a.setLatitude(a.getLatitude() - 1);
                }
            }
            s[a.getLatitude() - 1][a.getLongitude()] = signe;
            s[a.getLatitude() - 1][a.getLongitude()+1] = " ";
            String num = v.getNumVol();
            
            s[a.getLatitude()][a.getLongitude()] = num.charAt(0) + "";
            s[a.getLatitude()][a.getLongitude() + 1] = num.charAt(1) + "";
            s[a.getLatitude()][a.getLongitude() + 2] = num.charAt(2) + "";
            s[a.getLatitude()][a.getLongitude() + 3] = num.charAt(3) + "";
            s[a.getLatitude()][a.getLongitude() + 4] = num.charAt(4) + "";
            s[a.getLatitude()+1][a.getLongitude()] = " ";
            s[a.getLatitude()+1][a.getLongitude() + 1] = " ";
            s[a.getLatitude()+1][a.getLongitude() + 2] = " ";
            s[a.getLatitude()+1][a.getLongitude() + 3] = " ";
            s[a.getLatitude()+1][a.getLongitude() + 4] = " ";
            v.setAvion(a);
            listVol.set(x,v);
            x++;
        }
        }

        s[9][29] = ".";
        s[9][30] = "█";
        s[9][28] = "█";
        s[10][28] = "█";
        s[10][29] = ".";
        s[10][30] = "█";
        System.out.println("\t\t\t\t\t"+"\033[4;32m"+"Application de Gestion du Trafic Aerien"+"\033[0m"+"\033[1;32m");
        System.out.println("\n");
        System.out.println(
                        "--------------------------------------------------Radar de Surveillance-------------------------------------------------");
        System.out.println("\n");
        for (int i = 0; i < 19; i++) {
            System.out.print("\t\t\t\t");
            for (int j = 0; j < 59; j++)
               System.out.print("\033[1;32m"+s[i][j]+"\033[1;32m");
            System.out.println();
        }
        // System.out.println(s[15][15]);
    }
    
}