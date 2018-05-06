/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandler {

    private File myFile;
    private boolean isNull;

    public FileHandler(String nameFile) {
        myFile = new File(nameFile);

        if (!myFile.exists()) {
            try {
                myFile.createNewFile();

            } catch (IOException ioe) {
                System.out.println("Error al crear el archivo " + myFile.getName());
                ioe.printStackTrace();
            }
            isNull = false;
        } else {
            isNull = true;
        }
    }

    // Se implementó al principio queriendo mantener los archivos agregados en una sola carpeta
    /*public static void copyFile(File newSong) {
        try {
            Path originPath = Paths.get(newSong.getPath());
            Path destinyPath = Paths.get("src\\tunes\\".concat(newSong.getName()));
            Files.copy(originPath, destinyPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public LinkedList readFile() {
        LinkedList<SongMetadata> songsList = null;
        try {
            if (myFile != null) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(myFile));
                songsList = (LinkedList<SongMetadata>) ois.readObject();

                ois.close();
            }

        } catch (IOException e) {
            System.out.println("No pudo leerse el archivo (vacío) " + myFile.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songsList;
    }

    public void writeFile(LinkedList<SongMetadata> songsList) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(myFile));
            oos.writeObject(songsList);

            oos.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Ocurrió un error en la escritura del archivo " + myFile.getName());
            fnfe.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getIsNull() {
        return isNull;
    }

}
