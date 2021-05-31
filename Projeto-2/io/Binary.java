package io;

import interfaces.*;
import java.io.*;
import java.util.*;
import figures.*;

public class Binary implements ISavable {

    public Binary () {}

    public boolean saveToFile(String filePath, ArrayList<Figure> figList) {
        try {
            FileOutputStream f = new FileOutputStream(filePath);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(figList);
            o.flush();
            o.close();
            return true;
        } catch (Exception error) {
            System.out.println("Não foi possível salvar o projeto atual.\nError: " + error);
        }

        return false;
    }

    @Override
    public boolean saveToFile(String filePath, ArrayList<Figure> figList, int width, int height) {
        return saveToFile(filePath, figList);
    }

    public ArrayList<Figure> loadFromFile(String filePath) {
        try {
            FileInputStream f = new FileInputStream(filePath);
            ObjectInputStream o = new ObjectInputStream(f);
            Object obj = o.readObject();
            o.close();
            if (obj instanceof ArrayList) {
                ArrayList<Figure> test = (ArrayList<Figure>) obj;
                if (test.get(0) instanceof Figure)
                    return (ArrayList<Figure>) obj;
                else
                    throw new Exception("Error: o arquivo proj.bin não é um arquivo válido! O arquivo pode estar corrompido ou alterado.");
            }
            else
                throw new Exception("Error: o arquivo proj.bin não é um arquivo válido! O arquivo pode estar corrompido ou alterado.");
        } catch (Exception error) {
            System.out.println("Não foi possível carregar um projeto salvo.\nError: " + error);
        }

        return null;
    }
}
