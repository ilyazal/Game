package flappy;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Save {
    public static int x = 0;
    static boolean flag;
    static ArrayList<Integer> arrlist = new ArrayList<>();

    public static void record(int x) {
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter("for save/file.txt", true));
            writer.write(Integer.toString(x));
            writer.newLine();
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void saveNewFile(int score) {
        Date date = new Date();
        String s = new String(date.toString());
        s = s.replace(":", "-");
        File file = new File("for save/file.txt");
        if (file.exists()) {
            try {
                BufferedWriter writer;
                writer = new BufferedWriter(new FileWriter("for save/" + s + ".txt", true));
                writer.write(Integer.toString(score));
                writer.newLine();
                BufferedReader reader;
                reader = new BufferedReader((new FileReader(file)));
                String coord;
                while ((coord = reader.readLine()) != null) {
                    writer.write(coord);
                    writer.newLine();
                }
                writer.close();
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            System.out.println("File not found!");
        }
    }

    public static int recovery() {
        int score=0;
        if (arrlist.size() != 0) {
            arrlist = new ArrayList<>();
        }
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            flag = true;
            try {
                BufferedReader reader;
                reader = new BufferedReader((new FileReader(file)));
                String coord;
                while ((coord = reader.readLine()) != null) {
                    System.out.println(coord);
                    arrlist.add(Integer.parseInt(coord));
                }
                score=arrlist.get(0);
                arrlist.remove(0);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return score;
    }

    public static void value() {
        if (arrlist.size() != 0) {
            int column = arrlist.get(0);
            x = column;
            arrlist.remove(0);
        } else {
            if (flag) {
                Flappy.pause = true;
                Flappy.jumpflag = false;
                flag = false;
            }
            x = 0;
        }
    }
}
