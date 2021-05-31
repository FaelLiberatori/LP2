package io;

import interfaces.*;
import java.io.*;
import java.util.*;
import figures.*;
import org.jfree.svg.*;

public class SVG implements ISavable {

    public SVG () {}

    @Override
    public boolean saveToFile(String filePath, ArrayList<Figure> figList, int width, int height) {
        try {
            SVGGraphics2D gSVG = new SVGGraphics2D(width, height);

            for (Figure fig: figList) {
                fig.paint(gSVG, 0);
            }

            File svgFile = new File(filePath);
            svgFile.delete();
            svgFile.createNewFile();
            SVGUtils.writeToSVG(svgFile, gSVG.getSVGElement());
        } catch (Exception error) {
            System.out.println("Não foi possível salvar o projeto em SVG.\nError: " + error);
        }
        
        return false;
    }
}
