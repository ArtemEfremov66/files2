import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(100, 1, 80, 215.2);
        GameProgress save2 = new GameProgress(90, 2, 80, 211.2);
        GameProgress save3 = new GameProgress(80, 3, 80, 217.2);

        File file1 = new File("D://Java/Games/savegames/save1.dat");
        File file2 = new File("D://Java/Games/savegames/save2.dat");
        File file3 = new File("D://Java/Games/savegames/save3.dat");
        File[] files = {file1, file2, file3};

        saveGame(save1, "D://Java/Games/savegames/save1.dat");
        saveGame(save2, "D://Java/Games/savegames/save2.dat");
        saveGame(save3, "D://Java/Games/savegames/save3.dat");

        zipFiles("D://Java/Games/savegames/save.zip",files);
         file1.delete();
         file2.delete();
         file3.delete();
    }
    public static void saveGame(GameProgress save, String way){
        try (FileOutputStream fos = new FileOutputStream(way);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void zipFiles(String wayZip, File[] files) {
        for (File file : files) {
            try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(wayZip));
                 FileInputStream fis = new FileInputStream(file)) {
                ZipEntry entry = new ZipEntry(file.getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}