package TamagothiProject;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoStart {
    private JPanel panel1;
    private JTextField nameInput;
    private JButton startButton;
    private JButton kontynuujGręButton;
    private JLabel zdjpandy;
    ImageIcon pandaZdj = new ImageIcon("panda2.png");

    public OknoStart() {
        JFrame okno1 = new JFrame("Tamagotchi");
        okno1.setIconImage(pandaZdj.getImage());
        zdjpandy.setIcon(pandaZdj);
        Tamagotchi panda = new Tamagotchi();
        panda.read();
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                panda.createsave(name);
                panda.read();
                OknoGry2 oknoGry = new OknoGry2(name, panda);
                OknoGry2.pokaz();
                okno1.dispose();





            }
        });

        kontynuujGręButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = panda.getImie();
                OknoGry2 oknoGry = new OknoGry2(name, panda);
                OknoGry2.pokaz();
                okno1.dispose();

            }
        });
            if (panda.getImie()==null | panda.getPicie() <= 0 | panda.getJedzenie() <= 0 | panda.getSpanie() <= 0 | panda.getZabawa() <= 0)
                kontynuujGręButton.setEnabled(false);


        okno1.setContentPane(panel1);
        okno1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        okno1.setBounds(10,10,900,500);
        okno1.setResizable(false);
        okno1.show();
    }
    public static void pokaz() {
        //Metoda otwierająca okno
        JFrame.setDefaultLookAndFeelDecorated(true);
    }






    public static void main(String[] args){
        new OknoStart();
    }
}




