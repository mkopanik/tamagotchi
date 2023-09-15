package TamagothiProject;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.io.IOException;

public class Tamagotchi {
    // klasa której obiekty to parametry naszego zwierzaka
    private int jedzenie, spanie, picie, zabawa, higiena;
    private String imie;

    // konstruktor naszego zwiwerzaka
    public Tamagotchi() {
        this.jedzenie = jedzenie;
        this.spanie = spanie;
        this.picie = picie;
        this.zabawa = zabawa;
        this.higiena = higiena;
        this.imie = imie;

    }

    public int getHigiena() {
        return higiena;
    }

    public int getJedzenie() {
        return jedzenie;
    }

    public int getZabawa() {
        return zabawa;
    }

    public int getPicie() {
        return picie;
    }
    public int getSpanie(){
        return spanie;
    }
    public String getImie(){return imie;}

    public void createsave(String imie){
        File file = new File("zapis.txt");
        try {
                FileWriter zapis = new FileWriter("zapis.txt");
                StringBuilder sb = new StringBuilder();
                sb.append(100);
                sb.append(",");
                sb.append(100);
                sb.append(",");
                sb.append(100);
                sb.append(",");
                sb.append(100);
                sb.append(",");
                sb.append(100);
                sb.append(",");
                if (imie.isEmpty())
                    sb.append("  ");
                else
                    sb.append(imie);
                zapis.write(String.valueOf(sb));
                zapis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // odczytanie wartości z pliku i przypisanie ich do parametrów naszego pupila
    public void read() {
        try {
            File plik = new File("zapis.txt");
            Scanner tekst = new Scanner(plik);
            String line = tekst.nextLine();
            String[] tmp = line.split(",");
            this.jedzenie = Integer.parseInt(tmp[0]);
            this.spanie = Integer.parseInt(tmp[1]);
            this.picie = Integer.parseInt(tmp[2]);
            this.zabawa = Integer.parseInt(tmp[3]);
            this.higiena = Integer.parseInt(tmp[4]);
            this.imie = tmp[5];


        } catch (FileNotFoundException e) {
            System.out.println("Witaj!!, Pierwsze uruchomienie programu");
        }
    }

    // zapis parametrów do pliku po zakończonej grze
    public void save() {
        try {
            FileWriter zapis = new FileWriter("zapis.txt");
            StringBuilder sb = new StringBuilder();
            sb.append(jedzenie);
            sb.append(",");
            sb.append(spanie);
            sb.append(",");
            sb.append(picie);
            sb.append(",");
            sb.append(zabawa);
            sb.append(",");
            sb.append(higiena);
            sb.append(",");
            sb.append(imie);
            zapis.write(String.valueOf(sb));
            zapis.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    // metody zaspokajające potrzeby naszego zwierzaka
    public void Karmienie() {
        jedzenie += 30;
        picie -= 10;
        spanie -=10;
        higiena -= 10;
        if (jedzenie >= 100)
            jedzenie = 100;
        if (picie <= 0)
            picie = 0;
        if (spanie <= 0)
            spanie = 0;
        if (higiena <= 0)
            higiena = 0;
    }

    public void Spanie() {
        spanie += 35;
        jedzenie -= 10;
        picie -= 10;
        zabawa -= 10;
        higiena -= 5;
        if (spanie >= 100)
            spanie = 100;
        if (jedzenie <= 0)
            jedzenie = 0;
        if (picie <= 0)
            picie = 0;
        if (zabawa <= 0)
            zabawa = 0;
        if (higiena <= 0)
            higiena = 0;
    }

    public void Picie() {
        picie += 25;
        higiena -= 5;
        if (picie >= 100)
            picie = 100;
        if (higiena <=0)
            higiena = 0;
    }

    public void Zabawa() {
        zabawa += 30;
        spanie -= 5;
        picie -= 10;
        jedzenie -= 10;
        higiena -= 15;
        if (zabawa >= 100)
            zabawa = 100;
        if (spanie < 0)
            spanie = 0;
        if (picie < 0)
            picie = 0;
        if (jedzenie < 0)
            jedzenie = 0;
        if (higiena < 0)
            higiena = 0;
    }

    public void Higiena() {
        higiena += 40;
        zabawa -= 10;
        if (higiena >= 100)
            higiena = 100;
        if (zabawa <= 0)
            zabawa = 0;

    }

    // zmiana parametrów w czasie co 5 sekund
    public void zmiana() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                higiena -= 1;
                zabawa -= 1;
                spanie -= 1;
                picie -= 1;
                jedzenie -= 1;
                if (higiena <= 0 | zabawa <= 0 | spanie <= 0 | picie <= 0 | jedzenie <= 0)
                    timer.cancel();
            };
        };
        timer.schedule(task,5000,5000);


    }
    // włączanie dźwięków podczas wykonywanych czynności
    public static void play(String filename)
    {
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }
}




