package fileios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {


    public static boolean isFileExist(String path)
    {
        if(path == null)
        return false;
        File file = new File(path);
        if(file.exists())
        return true;
        return false;
    }

    public static boolean createFolder(String path)
    {
        File file = new File(path);
        boolean success = true;
        if(!isFileExist(path))
            success = file.mkdir();
        return success;
    }

    public static boolean validExtension(String path, String[] extensions)
    {
        String selectedExtension = path.substring(path.lastIndexOf(".") + 1);
        boolean value = false;
        for (String string : extensions) {
            if(string.equals(selectedExtension))
             {
                value = true;
                break;
             }
        }
        return value;
    }

    public static boolean copyFile(String source, String destination)
    {
        try {
        String selectedFolder = source.substring(0, source.lastIndexOf("\\"));
        String name = source.substring(source.lastIndexOf("\\") + 1);
        Path sourcePath = Paths.get(selectedFolder, name);
        Path destinationPath = Paths.get(destination, name);
        Files.copy(sourcePath, destinationPath);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteFile(String path)
    {
        if(path == null)
        return false;
        File file = new File(path);
        return file.delete();
    }

    public static void saveObject(String path, Object object) throws IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();   
    }
}