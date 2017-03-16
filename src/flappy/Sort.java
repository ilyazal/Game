package flappy;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;


public class Sort {

    SaveInfo sortMas[];
    SaveInfo fileObject = new SaveInfo();
    private int i = 0;
    private long time;
    ArrayList<Integer> gameStat;

    Sort() {
        File myFolder = new File("for save");
        processFilesFromFolder(myFolder);
    }

    public void sortInJava() {
        Date data = new Date();
        time = data.getTime();
        doSort(0, i - 1);
        data = new Date();
        time = data.getTime() - time;
        recordList("FileSortInJava.txt");
    }


    public void processFilesFromFolder(File folder) {
        File[] folderEntries = folder.listFiles();
        int n = folderEntries.length;
        sortMas = new SaveInfo[n];
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                processFilesFromFolder(entry);
                continue;
            }
            fileObject.setName(new String(entry.toString()));
            try {
                BufferedReader reader;
                reader = new BufferedReader((new FileReader(fileObject.getName())));
                fileObject.setScore(Integer.parseInt(reader.readLine()));
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sortMas[i] = new SaveInfo(fileObject.getName(), fileObject.getScore());
            i++;
        }
    }

    public void doSort(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (sortMas[i].getScore() >= sortMas[cur].getScore())) {
                i++;
            }
            while (j > cur && (sortMas[cur].getScore() >= sortMas[j].getScore())) {
                j--;
            }
            if (i < j) {
                SaveInfo temp = sortMas[i];
                sortMas[i] = sortMas[j];
                sortMas[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur);
        doSort(cur + 1, end);
    }

    public void recordList(String nameFile) {
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(nameFile, false));
            writer.write("time: " + Long.toString(time) + " ms");
            writer.newLine();
            writer.write("length dir: " + Integer.toString(i));
            writer.newLine();
            for (SaveInfo fileObject : sortMas) {
                writer.write(Integer.toString(fileObject.getScore()));
                writer.write(": " + fileObject.getName());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
