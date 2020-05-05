import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Timer;
import java.util.TimerTask;
import java.time.Duration;

public class Main {

        static void menuPrinc() {
                trierVolEnt();
                System.out.print("\033[H\033[2J");
                System.out.flush();     
                System.out.println("\t\t\t\t"+" _____          __ _         ___            _");                                                                         
                System.out.println("\t\t\t\t"+"|_   _|        / _(_)       / _ \\          (_) ");
                System.out.println("\t\t\t\t"+"  | |_ __ __ _| |_ _  ___  / /_\\ \\ ___ _ __ _  ___ _ __");
                System.out.println("\t\t\t\t"+"  | | '__/ _` |  _| |/ __| |  _  |/ _ | '__| |/ _ | '_ \\");
                System.out.println("\t\t\t\t"+"  | | | | (_| | | | | (__  | | | |  __| |  | |  __| | | |");
                System.out.println("\t\t\t\t"+"  \\_|_|  \\__,_|_| |_|\\___| \\_| |_/\\___|_|  |_|\\___|_| |_|");
              //  System.out.println("\t\t\t\t\t"+"\033[4;32m"+"Application de Gestion du Trafic Aerien"+"\033[0m"+"\033[1;32m");
                System.out.println("\n");
                System.out.println("---------------------------------------------------Statistiques---------------------------------------------------------");
                System.out.println("|                                                                                                                      |");
                System.out.println("|\t\tNombre de vol entrant : "+volTriEnt.size()+"\t\t|\t\tNombre de vol sortant : "+listVolSort.size()+"\t\t\t|");
                System.out.println("|                                                                                                                      |");
                System.out.println(
                                "------------------------------------------------------------------------------------------------------------------------");
                System.out.println("\n");
                System.out.println("\n\t\t\t\t\t    -------------------------");
                System.out.println("\t\t\t\t\t    |Commencer le controle :|");
                System.out.println("\t\t\t\t\t    |                       |");
                System.out.println("\t\t\t\t\t    | 1: Atterrissage       |");
                System.out.println("\t\t\t\t\t    | 2: Decollage          |");
                System.out.println("\t\t\t\t\t    | 3: Liste pistes       |");
                System.out.println("\t\t\t\t\t    | 4: Radar de controle  |");
                System.out.println("\t\t\t\t\t    -------------------------");
                System.out.print("\t\t\t\t\t    Votre choix : ");
                Scanner sc = new Scanner(System.in);
                String choix = sc.nextLine();
                
                switch(choix)
                {
                        case "1":atterrissage();
                        case "2":decollage();
                        case "3":listePiste();
                        case "4":radar();
                        default:menuPrinc();
                }
                // System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        }

        static void radar(){
                
                Radar.getav();
                
                TimerTask task = new TimerTask() {
                        

                        @Override
                        public void run() {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                Radar.aff();
                        }
                    };
                    Timer timer = new Timer();
                    timer.schedule(task, new Date(), 1500);
                    Scanner sc =new Scanner(System.in);
                    String s = sc.nextLine();
                    timer.cancel();
                    menuPrinc();
                
        }

        static List<Vol> volTriEnt = new ArrayList<Vol>();

        static void trierVolEnt() {
                Iterator<Vol> it = listVolEnt.iterator();
                LocalDateTime timeNow = LocalDateTime.now();
                while (it.hasNext()) {

                        Vol v = it.next();
                        Duration dr = Duration.between(timeNow, v.getDateArr());
                        if (dr.getSeconds() <= 600 && dr.getSeconds() >= -600) {
                                if (v.getAvion().getNK() < 10) {
                                        volTriEnt.add(v);
                                        it.remove();
                                }
                        }
                }
                Collections.sort(listVolEnt, Vol.compNK());
                it = listVolEnt.iterator();
                while (it.hasNext()) {
                        Vol v = it.next();
                        Duration dr = Duration.between(timeNow, v.getDateArr());
                        if (dr.getSeconds() <= 600 && dr.getSeconds() >= -600) {
                                if (v.getAvion().getEtatUrgence() == 1) {
                                        volTriEnt.add(v);
                                        it.remove();
                                }
                        }
                }
                it = listVolEnt.iterator();
                while (it.hasNext()) {
                        Vol v = it.next();
                        Duration dr = Duration.between(timeNow, v.getDateArr());
                        if (dr.getSeconds() <= 600 && dr.getSeconds() >= -600) {
                                if (v.getAvion().getEtatUrgence() == 2) {
                                        volTriEnt.add(v);
                                        it.remove();
                                }
                        }
                }
                it = listVolEnt.iterator();
                while (it.hasNext()) {
                        Vol v = it.next();
                        Duration dr = Duration.between(timeNow, v.getDateArr());
                        if (dr.getSeconds() <= 0 && dr.getSeconds() >= -600) {
                                volTriEnt.add(v);
                                it.remove();
                        }
                }
                it = listVolEnt.iterator();
                while (it.hasNext()) {
                        Vol v = it.next();
                        Duration dr = Duration.between(timeNow, v.getDateArr());
                        if (dr.getSeconds() <= 600 && dr.getSeconds() >= 1) {
                                volTriEnt.add(v);
                                it.remove();
                        }
                }
                

        }

        static void atterrissage() {
                trierVolEnt();
                List<String> vols=new ArrayList<>();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t"+"\033[4;32m"+"Application de Gestion du Trafic Aerien"+"\033[0m"+"\033[1;32m");
                System.out.println("\n");
                System.out.println(
                                "----------------------------------------------------Atterrissage--------------------------------------------------------");
                System.out.println("\n");
                System.out.println(
                                "   Avion   |   Vol    |    Ville de depart    |    Heure d'arrive    |    Niveau kerosene    |    Urgence    |    Piste");
                System.out.println(
                                "           |          |                       |                      |                       |               |         ");
                Iterator<Vol> it = volTriEnt.iterator();
                
                while (it.hasNext()) {
                        Vol v = it.next();
                        vols.add(v.getNumVol());
                        String mm=v.getDateArr().getMinute()+"";
                        String hh=v.getDateArr().getHour()+"";
                        if(v.getDateArr().getMinute()<10)
                        mm="0"+mm;
                        if(v.getDateArr().getHour()<10)
                        hh="0"+hh;

                        System.out.format("%-11s|%-10s|%-23s|%-22s|%-23s|%-15s|%-9s\n",
                                        v.getAvion().getMarque() + " " + v.getAvion().getModele(), v.getNumVol(),
                                        v.getVilleDep(), hh + ":" + mm,
                                        v.getAvion().getNK() + "%",
                                        v.getAvion().getEtatUrgence() == 0 ? "-"
                                                        : v.getAvion().getEtatUrgence() == 1 ? "Technique"
                                                                        : "Sanitaire",
                                        v.getPiste());
                }
                System.out.println(
                                "           |          |                       |                      |                       |               |         ");
                // System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\t\t\t\t\t    Numero de vol : ");
                Scanner sc = new Scanner(System.in);
                String numV = sc.nextLine();
                if(numV.equals("q"))
                menuPrinc();
                if (vols.contains(numV)) {
                        it = volTriEnt.iterator();
                        while (it.hasNext()) {
                                Vol v = it.next();
                                if (v.getNumVol().equals(numV))
                                        pistes(v, 1);
                        }
                }else
                {
                        atterrissage();
                }
                
        }

        static void decollage() {
                Collections.sort(listVolSort, Vol.compDd());
                LocalDateTime timeNow = LocalDateTime.now();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t"+"\033[4;32m"+"Application de Gestion du Trafic Aerien"+"\033[0m"+"\033[1;32m");
                System.out.println("\n");
                System.out.println(
                                "--------------------------------------------------Decollage-------------------------------------------------------------");
                System.out.println("\n");
                System.out.println(
                                "   Avion   |   Vol    |    Destination        |    Heure d'depart    |    Niveau kerosene    |    Retard     |    Piste");
                System.out.println(
                                "           |          |                       |                      |                       |               |         ");
                Iterator<Vol> it = listVolSort.iterator();
                List<String> vols=new ArrayList<>();
                while (it.hasNext()) {
                        Vol v = it.next();
                        vols.add(v.getNumVol());
                        Duration dr = Duration.between(timeNow, v.getDateDep());
                        if (dr.getSeconds() <= 600 && dr.getSeconds() >= -600)
                                System.out.format("%-11s|%-10s|%-23s|%-22s|%-23s|%-15s|%-9s\n",
                                                v.getAvion().getMarque() + " " + v.getAvion().getModele(),
                                                v.getNumVol(), v.getVilleArr(),
                                                v.getDateDep().getHour() + ":" + v.getDateDep().getMinute(),
                                                v.getAvion().getNK() + "%",
                                                dr.getSeconds() < 0 ? "En retard" : "a l'heure", v.getPiste());
                }
                System.out.println(
                                "           |          |                       |                      |                       |               |         ");
                System.out.print("\n\n\t\t\t\t\t    Numero de vol : ");
                Scanner sc = new Scanner(System.in);
                String numV = sc.nextLine();
                if(numV.equals("q"))
                menuPrinc();
                it = listVolSort.iterator();
                if (vols.contains(numV)) {
                while(it.hasNext())
                {
                        Vol v =it.next();
                        if(v.getNumVol().equals(numV))
                                pistes(v,1);
                }
        }
        else
        {
                decollage();
        }
        }

        static void resetPiste(){
                Iterator<Piste> it=listPs.iterator();
                while(it.hasNext())
                {
                        it.next().setVol(null);
                }
        }

        static void listePiste(){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t"+"\033[4;32m"+"Application de Gestion du Trafic Aerien"+"\033[0m"+"\033[1;32m");
                System.out.println("\n");
                System.out.println(
                                "---------------------------------------------------Liste des Pistes-----------------------------------------------------");
                System.out.println("\n");
                Iterator<Piste> it = listPs.iterator();
                while (it.hasNext()) {
                        Piste p = it.next();
                        if(p.getVol()!=null)
                        System.out.println("                                                  Piste "+p.getCode()+" -- Vol : "+p.getVol().getNumVol());
                        else
                        System.out.println("                                                  Piste "+p.getCode()+" -- Libre");

                }
                Scanner sc = new Scanner(System.in);
                String piste = sc.nextLine();
                menuPrinc();

        }

        static void pistes(Vol v, int type) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t"+"\033[4;32m"+"Application de Gestion du Trafic Aerien"+"\033[0m"+"\033[1;32m");
                System.out.println("\n");
                System.out.println(
                                "------------------------------------------------------Pistes Libres-----------------------------------------------------");
                System.out.println("\n");
                Iterator<Piste> it = listPs.iterator();
                List<String> lesPistes=new ArrayList<>();
                while (it.hasNext()) {
                        Piste p = it.next();
                        lesPistes.add(p.getCode());
                        if (p.getVol() == null)
                                System.out.println("                                                         Piste "+ p.getCode());
                }
                Scanner sc = new Scanner(System.in);
                System.out.print("\n\t\t\t\t\tAffecter une piste pour vol numero " + v.getNumVol() + " : ");
                String piste = sc.nextLine();
                if(piste.equals("q"))
                menuPrinc();
                /*if(type==1){
                
                Piste p = null;
                Iterator<Piste> itp = listPs.iterator();
                while (itp.hasNext()) {
                        p = itp.next();
                        if (p.getCode().equals(piste))
                                break;
                }
                p.setVol(v);
                v.setPiste(piste);}
                else{
                      /*  Vol v = null;
                Iterator<Vol> itv = listVolSort.iterator();
                while (itv.hasNext()) {
                        v = itv.next();
                        if (v.getNumVol().equals(numV))
                                break;
                }
                Piste p = null;
                Iterator<Piste> itp = listPs.iterator();
                while (itp.hasNext()) {
                        p = itp.next();
                        if (p.getCode().equals(piste))
                                break;
                }
                p.setVol(v);
                v.setPiste(piste);

                }*/
              if (lesPistes.contains(piste)) {
                Piste p = null;
                Iterator<Piste> itp = listPs.iterator();
                while (itp.hasNext()) {
                        p = itp.next();
                        if (p.getCode().equals(piste))
                                break;
                }
                p.setVol(v);
                v.setPiste(piste);
                menuPrinc();
        }else
        {
                pistes(v, type);
        }
        }

        static List<Avion> listAv = new ArrayList<Avion>();
        static List<Vol> listVolEnt = new ArrayList<Vol>();
        static List<Vol> listVolSort = new ArrayList<Vol>();
        static List<Piste> listPs = new ArrayList<Piste>();

        static void chargerDonnees() {
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
                                // System.out.println(c.format(v.getDateArr()));
                                if (v.getType().equals("entrant"))
                                        listVolEnt.add(v);
                                else
                                        listVolSort.add(v);
                                Collections.sort(listVolEnt, Vol.compNK());
                        }
                        myReader.close();
                } catch (FileNotFoundException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                }

                try {
                        File myObj = new File("pistes.txt");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine()) {
                                String data = myReader.nextLine();
                                String[] values = data.split(",");
                                Piste p = new Piste(values[0], null);
                                Iterator<Vol> it = listVolEnt.iterator();
                                while (it.hasNext()) {
                                        Vol v = it.next();
                                        if (!values[1].equals("null")) {
                                                if (values[1].equals(v.getNumVol())) {
                                                        p.setVol(v);
                                                        break;
                                                }
                                        }
                                }
                                listPs.add(p);
                        }
                        myReader.close();
                } catch (FileNotFoundException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                }

        }

        public static void main(String[] args) {
                chargerDonnees();
                System.out.print("\033[1;32m"+"\033[1;32m");
                TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                                resetPiste();
                        }
                    };
                Timer timer = new Timer();
                timer.schedule(task, new Date(), 600000);
                menuPrinc();
                
        }
}
