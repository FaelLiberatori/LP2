package interfaces;

import java.io.*;
import java.util.*;
import figures.*;

public interface ISavable {
    public boolean saveToFile(String filePath, ArrayList<Figure> figList, int width, int height);
}