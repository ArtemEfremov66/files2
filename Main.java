import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(100, 1, 80, 215.2);
        GameProgress save2 = new GameProgress(90, 2, 80, 211.2);
        GameProgress save3 = new GameProgress(80, 3, 80, 217.2);
        saveGame(save1, "D://Java/Games/savegames/save1.dat");
        saveGame(save2, "D://Java/Games/savegames/save2.dat");
        saveGame(save3, "D://Java/Games/savegames/save3.dat");

        zipFiles("D://Java/Games/savegames/save.zip","D://Java/Games/savegames/save3.dat" );
        zipFiles("D://Java/Games/savegames/save.zip","D://Java/Games/savegames/save2.dat" );
        zipFiles("D://Java/Games/savegames/save.zip","D://Java/Games/savegames/save1.dat" );

        File delete1 = new File("D://Java/Games/savegames/save1.dat");
        File delete2 = new File("D://Java/Games/savegames/save2.dat");
        File delete3 = new File("D://Java/Games/savegames/save3.dat");
        delete1.delete();
        delete2.delete();
        delete3.delete();
    }
    public static void saveGame(GameProgress save, String way){
        try (FileOutputStream fos = new FileOutputStream(way);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void zipFiles(String wayZip, String waySave) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(wayZip));
             FileInputStream fis = new FileInputStream(waySave)) {
            ZipEntry entry = new ZipEntry("D://Java/Games/savegames/save1.zip");
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