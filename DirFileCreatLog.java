package serializ;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DirFileCreatLog {

    StringBuilder sb = new StringBuilder();
    ArrayList<String> dirlist = new ArrayList<>();
    ArrayList<String> fileList = new ArrayList<>();

    public DirFileCreatLog() {
        dirlist.add("D:\\Games");
        dirlist.add("D:\\Games\\srs");
        dirlist.add("D:\\Games\\res");
        dirlist.add("D:\\Games\\savegames");
        dirlist.add("D:\\Games\\temp");
        dirlist.add("D:\\Games\\srs\\main");
        dirlist.add("D:\\Games\\srs\\test");
        dirlist.add("D:\\Games\\res\\drawables");
        dirlist.add("D:\\Games\\res\\vtctors");
        dirlist.add("D:\\Games\\res\\icons");
        fileList.add("D:\\Games\\srs\\Main.java");
        fileList.add("D:\\Games\\srs\\Util.java");
        fileList.add("D:\\Games\\temp\\temp.txt");
        dirCreat(dirlist);
        fileCreat(fileList);

    }

    public void dirCreat(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            File file = new File(str);
            if (!file.exists()) {
                if (file.mkdir()) {
                    sb.append(new Date() + " : создали каталог " + str + System.getProperty("line.separator"));
                } else {
                    sb.append(new Date() + " : Каталог " + str + " создать не удалось" +
                            System.getProperty("line.separator"));
                }
            } else {
                sb.append(new Date() + " : Каталог " + str + " уже есть " +
                        System.getProperty("line.separator"));
            }
        }
        sb.append(new Date() + " : записали файл D:\\Games\\temp\\temp.txt" + System.getProperty("line.separator"));
        logSave(sb);
        sb.delete(0,sb.length());
    }

    public void fileCreat(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            File file = new File(str);
            if (!file.exists()) {
                try {
                    if (file.createNewFile()) {
                        sb.append(new Date() + " : создали файл " + str + System.getProperty("line.separator"));
                    } else {
                        sb.append(new Date() + " : файл " + str + " создать не удалось" +
                                System.getProperty("line.separator"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(new Date() + " : файл " + str + " уже есть " +
                        System.getProperty("line.separator"));
            }
        }
        sb.append(new Date() + " : записали файл D:\\Games\\temp\\temp.txt" + System.getProperty("line.separator"));
        logSave(sb);
        sb.delete(0,sb.length());
    }

    public void logSave(StringBuilder sb) {
        try (FileWriter fw = new FileWriter("D:\\Games\\temp\\temp.txt",true)) {
            fw.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

