package serializ;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }


    public static void saveGame(String str, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(str);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipFile(String fileZip, String... str) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(fileZip))) {
            for (String s : str) {
                try (FileInputStream fis = new FileInputStream(s)) {
                    ZipEntry entry = new ZipEntry(new File(s).getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFileZip(String... str) {
        for (String s : str) {
            File file = new File(s);
            file.delete();
        }
    }

    public static void openZip(String zip, String dirZip) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fos = new FileOutputStream(dirZip + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fos.write(c);
                }
                fos.flush();
                zin.closeEntry();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GameProgress openProgress(String str) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(str))) {
            return (GameProgress) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}







