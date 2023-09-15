package TamagothiProject;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TimerTask;
import javax.swing.border.Border;




public class OknoGry2 {
    private String nazwa;
    private Integer pragnienie;
    private Integer glod;
    private Integer sennosc;
    private Integer sport;
    String[] statystykiNazwy = {"pragnienie:", "glod:", "sennosc:", "zabawa:", "higiena:"};
    ImageIcon pandaZdj = new ImageIcon("panda.png");
    ImageIcon pandasmutna = new ImageIcon("pandka_smutna.png");
    ImageIcon pandagg = new ImageIcon("pandkagg.png");
    ImageIcon prysznicZdj = new ImageIcon("prysznic.png");
    ImageIcon pandaprysznicZdj = new ImageIcon("prysznicpanda.png");
    ImageIcon tlo = new ImageIcon("tło.png");
    ImageIcon[] ikony = {
            new ImageIcon("drink.jpg"),
            new ImageIcon("eat.jpg"),
            new ImageIcon("sleep.jpg"),
            new ImageIcon("play.jpg")
    };
    Border border = BorderFactory.createLineBorder(new Color(207, 107, 8), 4);
    // opis przycisków
    String[] opis = {"Pić", "Jeść", "Spać", "Grać"};
    ImageIcon[] ikonyAkcji = {
            new ImageIcon("pandaDrink.png"),
            new ImageIcon("pandaEat.png"),
            new ImageIcon("pandaSleep.png"),
            new ImageIcon("pandaPlay.png"),
    };


    public OknoGry2(String nazwa, Tamagotchi panda) {


        this.nazwa = nazwa;
        panda.zmiana();
        // Imie pupila i jego obrazek
        JLabel labelNazwa = new JLabel();
        labelNazwa.setText(nazwa);
        if (((panda.getZabawa() <= 40) | (panda.getSpanie() <= 40)  | (panda.getPicie() <= 40) | (panda.getJedzenie() <=40  ) | (panda.getHigiena() <= 40))&(panda.getSpanie()>0 & panda.getJedzenie()>0 & panda.getZabawa()>0 & panda.getPicie()>0) & panda.getHigiena() >0)
            labelNazwa.setIcon(pandasmutna);
        else if (panda.getZabawa() <= 0 | panda.getSpanie() <= 0 | panda.getPicie() <= 0 | panda.getJedzenie() <=0 | panda.getHigiena() <= 0)
            labelNazwa.setIcon(pandagg);
        else
            labelNazwa.setIcon(pandaZdj);
        labelNazwa.setHorizontalTextPosition(JLabel.CENTER);
        labelNazwa.setVerticalTextPosition(JLabel.TOP);
        labelNazwa.setBounds(230, 0, 900, 500);
        labelNazwa.setFont(new Font("MV Boli", Font.PLAIN, 30));

        // Panel na przyciski akcji pupila
        JPanel panelPrzyciski = new JPanel();
        panelPrzyciski.setBackground(new Color(225, 252, 144));
        panelPrzyciski.setBounds(600,60,100,350);
        panelPrzyciski.setBorder(border);

        // Dodawanie przycisków do panelu

        JButton napoj = new JButton(opis[0], ikony[0]);
        napoj.setBackground(Color.white);
        napoj.setVerticalTextPosition(SwingConstants.BOTTOM);
        napoj.setHorizontalTextPosition(SwingConstants.CENTER);
        napoj.setBackground(new Color(225, 252, 144));
        napoj.setBorder(border);


        panelPrzyciski.add(napoj);

        JButton jedz = new JButton(opis[1], ikony[1]);
        jedz.setBackground(Color.white);
        jedz.setVerticalTextPosition(SwingConstants.BOTTOM);
        jedz.setHorizontalTextPosition(SwingConstants.CENTER);
        jedz.setBackground(new Color(225, 252, 144));
        jedz.setBorder(border);


        panelPrzyciski.add(jedz);

        JButton spij = new JButton(opis[2], ikony[2]);
        spij.setBackground(Color.white);
        spij.setVerticalTextPosition(SwingConstants.BOTTOM);
        spij.setHorizontalTextPosition(SwingConstants.CENTER);
        spij.setBackground(new Color(225, 252, 144));
        spij.setBorder(border);


        panelPrzyciski.add(spij);

        JButton baw = new JButton(opis[3], ikony[3]);
        baw.setBackground(Color.white);
        baw.setVerticalTextPosition(SwingConstants.BOTTOM);
        baw.setHorizontalTextPosition(SwingConstants.CENTER);
        baw.setBackground(new Color(225, 252, 144));
        baw.setBorder(border);


        panelPrzyciski.add(baw);

        // Główne okno gry
        JFrame okno = new JFrame();
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(800, 550);
        okno.setLayout(null);
        okno.setResizable(false);
        okno.setContentPane(new JLabel(tlo));
        okno.setVisible(true);
        okno.setResizable(false);
        okno.add(labelNazwa);


        okno.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int zamkniecieZapis = JOptionPane.showConfirmDialog(okno, "Czy przed wyjściem chcesz zapisać postęp gry?", "Zapis", JOptionPane.YES_NO_OPTION);
                if (zamkniecieZapis == JOptionPane.YES_OPTION) {
                    panda.save();
                    System.exit(0);
                }
            }
        });



        // Prysznic
        JButton prysznic = new JButton();
        prysznic.setContentAreaFilled(false);
        prysznic.setBackground(new Color(0,0,0,0));
        prysznic.setBounds(0,130,250,250);
        prysznic.setIcon(prysznicZdj);
        prysznic.setBorderPainted(false);
        prysznic.setDisabledIcon(prysznicZdj);

        okno.add(prysznic);



        // Smutna pandka, kiedy któraś ze statystyk spadnie poniźej 40
        Timer smutek = new Timer(1000,f ->{
            if (((panda.getZabawa() <= 40) | (panda.getSpanie() <= 40)  | (panda.getPicie() <= 40) | (panda.getJedzenie() <=40  ) | (panda.getHigiena() <= 40))&(panda.getSpanie()>0 & panda.getJedzenie()>0 & panda.getZabawa()>0 & panda.getPicie()>0) & panda.getHigiena() >0)
                labelNazwa.setIcon(pandasmutna);
        });
        smutek.start();


        // dodanie metod zmieniających pupila do przycisków
        napoj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelNazwa.setIcon(ikonyAkcji[0]);
                panda.Picie();
                napoj.setEnabled(false);
                jedz.setEnabled(false);
                spij.setEnabled(false);
                baw.setEnabled(false);
                prysznic.setEnabled(false);
                panda.play("siorbanie.wav");
                smutek.stop();

                Timer powrot = new Timer(5000,f ->{
                    if (((panda.getZabawa() <= 40) | (panda.getSpanie() <= 40)  | (panda.getPicie() <= 40) | (panda.getJedzenie() <=40  ) | (panda.getHigiena() <= 40))&(panda.getSpanie()>0 & panda.getJedzenie()>0 & panda.getZabawa()>0 & panda.getPicie()>0 & panda.getHigiena() >0))
                        labelNazwa.setIcon(pandasmutna);
                    else if (panda.getZabawa() <= 0 | panda.getSpanie() <= 0 | panda.getPicie() <= 0 | panda.getJedzenie() <=0  | panda.getHigiena() <= 0)
                        labelNazwa.setIcon(pandagg);
                    else
                        labelNazwa.setIcon(pandaZdj);
                    napoj.setEnabled(true);
                    jedz.setEnabled(true);
                    spij.setEnabled(true);
                    baw.setEnabled(true);
                    prysznic.setEnabled(true);
                });
                powrot.setRepeats(false);
                powrot.start();

            }
        });
        smutek.start();

        jedz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelNazwa.setIcon(ikonyAkcji[1]);
                panda.Karmienie();
                napoj.setEnabled(false);
                jedz.setEnabled(false);
                spij.setEnabled(false);
                baw.setEnabled(false);
                prysznic.setEnabled(false);
                panda.play("chrupanie.wav");
                smutek.stop();
                Timer powrot = new Timer(10000,f ->{
                    if (((panda.getZabawa() <= 40) | (panda.getSpanie() <= 40)  | (panda.getPicie() <= 40) | (panda.getJedzenie() <=40  ) | (panda.getHigiena() <= 40))&(panda.getSpanie()>0 & panda.getJedzenie()>0 & panda.getZabawa()>0 & panda.getPicie()>0 & panda.getHigiena() >0))
                        labelNazwa.setIcon(pandasmutna);
                    else if (panda.getZabawa() <= 0 | panda.getSpanie() <= 0 | panda.getPicie() <= 0 | panda.getJedzenie() <=0 | panda.getHigiena() <= 0)
                        labelNazwa.setIcon(pandagg);
                    else
                        labelNazwa.setIcon(pandaZdj);
                    napoj.setEnabled(true);
                    jedz.setEnabled(true);
                    spij.setEnabled(true);
                    baw.setEnabled(true);
                    prysznic.setEnabled(true);
                });
                powrot.setRepeats(false);
                powrot.start();

            }
        });
        smutek.start();

        spij.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelNazwa.setIcon(ikonyAkcji[2]);
                panda.Spanie();
                napoj.setEnabled(false);
                jedz.setEnabled(false);
                spij.setEnabled(false);
                baw.setEnabled(false);
                prysznic.setEnabled(false);
                panda.play("snore.wav");
                smutek.stop();
                Timer powrot = new Timer(5000,f ->{
                    if (((panda.getZabawa() <= 40) | (panda.getSpanie() <= 40)  | (panda.getPicie() <= 40) | (panda.getJedzenie() <=40  ) | (panda.getHigiena() <= 40))&(panda.getSpanie()>0 & panda.getJedzenie()>0 & panda.getZabawa()>0 & panda.getPicie()>0 & panda.getHigiena() >0))
                        labelNazwa.setIcon(pandasmutna);
                    else if (panda.getZabawa() <= 0 | panda.getSpanie() <= 0 | panda.getPicie() <= 0 | panda.getJedzenie() <=0 | panda.getHigiena() <= 0)
                        labelNazwa.setIcon(pandagg);
                    else
                        labelNazwa.setIcon(pandaZdj);
                    napoj.setEnabled(true);
                    jedz.setEnabled(true);
                    spij.setEnabled(true);
                    baw.setEnabled(true);
                    prysznic.setEnabled(true);
                });
                powrot.setRepeats(false);
                powrot.start();

            }
        });
        smutek.start();

        baw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelNazwa.setIcon(ikonyAkcji[3]);
                panda.Zabawa();
                napoj.setEnabled(false);
                jedz.setEnabled(false);
                spij.setEnabled(false);
                baw.setEnabled(false);
                prysznic.setEnabled(false);
                panda.play("smiech.wav");
                smutek.stop();
                Timer powrot = new Timer(5000,f ->{
                    if (((panda.getZabawa() <= 40) | (panda.getSpanie() <= 40)  | (panda.getPicie() <= 40) | (panda.getJedzenie() <=40  ) | (panda.getHigiena() <= 40))&(panda.getSpanie()>0 & panda.getJedzenie()>0 & panda.getZabawa()>0 & panda.getPicie()>0 & panda.getHigiena() >0))
                        labelNazwa.setIcon(pandasmutna);
                    else if (panda.getZabawa() <= 0 | panda.getSpanie() <= 0 | panda.getPicie() <= 0 | panda.getJedzenie() <=0 | panda.getHigiena() <= 0)
                        labelNazwa.setIcon(pandagg);
                    else
                        labelNazwa.setIcon(pandaZdj);
                    napoj.setEnabled(true);
                    jedz.setEnabled(true);
                    spij.setEnabled(true);
                    baw.setEnabled(true);
                    prysznic.setEnabled(true);
                });
                powrot.setRepeats(false);
                powrot.start();
            }
        });
        smutek.start();
        prysznic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                prysznic.setIcon(pandaprysznicZdj);
                panda.play("myju.wav");
                panda.Higiena();
                okno.remove(labelNazwa);
                okno.revalidate();
                okno.repaint();
                napoj.setEnabled(false);
                jedz.setEnabled(false);
                spij.setEnabled(false);
                baw.setEnabled(false);
                prysznic.setEnabled(false);
                prysznic.setDisabledIcon(pandaprysznicZdj);
                smutek.stop();

                Timer powrot = new Timer(8500,f ->{
                    if (((panda.getZabawa() <= 40) | (panda.getSpanie() <= 40)  | (panda.getPicie() <= 40) | (panda.getJedzenie() <=40  ) | (panda.getHigiena() <= 40))&(panda.getSpanie()>0 & panda.getJedzenie()>0 & panda.getZabawa()>0 & panda.getPicie()>0 & panda.getHigiena() >0)){
                        prysznic.setIcon(prysznicZdj);
                        okno.add(labelNazwa);
                        labelNazwa.setIcon(pandasmutna);
                        okno.revalidate();
                        okno.repaint();}
                    else if (panda.getZabawa() <= 0 | panda.getSpanie() <= 0 | panda.getPicie() <= 0 | panda.getJedzenie() <=0 | panda.getHigiena() <= 0){
                        prysznic.setIcon(prysznicZdj);
                        okno.add(labelNazwa);
                        labelNazwa.setIcon(pandagg);
                        okno.revalidate();
                        okno.repaint();}
                    else {
                        prysznic.setIcon(prysznicZdj);
                        okno.add(labelNazwa);
                        labelNazwa.setIcon(pandaZdj);
                        okno.revalidate();
                        okno.repaint();
                    }
                    napoj.setEnabled(true);
                    jedz.setEnabled(true);
                    spij.setEnabled(true);
                    baw.setEnabled(true);
                    prysznic.setEnabled(true);
                    prysznic.setDisabledIcon(prysznicZdj);
                });
                powrot.setRepeats(false);
                powrot.start();
            }
        });
        smutek.start();




        // Panel na nazwy statystyk pupila
        JPanel panelStatystyk = new JPanel();
        panelStatystyk.setBackground(new Color(225, 252, 144));
        panelStatystyk.setBounds(50,430,600,25);
        panelStatystyk.setLayout(new FlowLayout(FlowLayout.CENTER, 70,0));
        panelStatystyk.setBorder(border);

        // Dodawanie nazw statystyk do panelu
        for (String value : statystykiNazwy) {
            JLabel s = new JLabel(value);
            panelStatystyk.add(s);
        }


        // Panel na wartości statystyk pupila
        JPanel panelStatystyk2 = new JPanel();
        panelStatystyk2.setBackground(new Color(225, 252, 144));
        panelStatystyk2.setBounds(50,451,600,25);
        panelStatystyk2.setLayout(new FlowLayout(FlowLayout.CENTER, 94,0));
        panelStatystyk2.setBorder(border);

        // Dodawanie wartości statystyk do panelu

        JLabel picieW = new JLabel(String.valueOf(panda.getPicie()));
        panelStatystyk2.add(picieW);

        JLabel jedzenieW = new JLabel(String.valueOf(panda.getJedzenie()));
        panelStatystyk2.add(jedzenieW);

        JLabel spanieW = new JLabel(String.valueOf(panda.getSpanie()));
        panelStatystyk2.add(spanieW);

        JLabel zabawaW = new JLabel(String.valueOf(panda.getZabawa()));
        panelStatystyk2.add(zabawaW);

        JLabel mycieW = new JLabel(String.valueOf(panda.getHigiena()));
        panelStatystyk2.add(mycieW);

        Timer timer = new Timer(1000,e ->{
            picieW.setText(String.valueOf(panda.getPicie()));
            jedzenieW.setText(String.valueOf(panda.getJedzenie()));
            spanieW.setText(String.valueOf(panda.getSpanie()));
            zabawaW.setText(String.valueOf(panda.getZabawa()));
            mycieW.setText(String.valueOf(panda.getHigiena()));

        });
        timer.start();

        // Dodawanie paneli do głównego okna
        okno.add(panelPrzyciski);
        okno.add(panelStatystyk);
        okno.add(panelStatystyk2);

        Timer przegrana= new Timer(1000,f ->{
            if (panda.getZabawa() <= 0 | panda.getSpanie() <= 0 | panda.getPicie() <= 0 | panda.getJedzenie() <=0 | panda.getHigiena() <= 0) {
                labelNazwa.setIcon(pandagg);
                panda.play("smierc.wav");
                okno.remove(panelPrzyciski);
                okno.remove(panelStatystyk);
                okno.remove(panelStatystyk2);
                okno.remove(prysznic);
                okno.revalidate();
                okno.repaint();



            }
        });
        przegrana.start();


    }
        public static void pokaz() {
            //Metoda otwierająca okno
            JFrame.setDefaultLookAndFeelDecorated(true);
        }


}
