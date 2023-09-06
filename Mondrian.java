// Vani Agarwal
// CSE 123
// C2: Mondrian Art
// 10th May 2023
// A class to generate random Mondrian Artwork 

import java.util.*;
import java.awt.*;

    public class Mondrian {

        // Method: Fills pixels with Color objects (using the java.awt.Color class) 
        // according to the basic algorithm 
        // Parameters: 
        // Array of pixels of a given blank canvas


        public void paintBasicMondrian(Color[][] pixels) { 
            int width = pixels[0].length - 2;
            int height = pixels.length - 2;
            recurs(0, 0, width, height, pixels);
        }

        // Method: Fills pixels with Color objects (using the java.awt.Color class) 
        // according to the chosen extension
        // The chosen extension is that if the given region is in the first half of 
        // the width of the whole canvas, the colors will be lighter.
        // If it is in the first half of the height of the whole canvas,
        // the colors will be even further lighter.
        // Parameters: 
        // Array of pixels of a given blank canvas

        public void paintComplexMondrian(Color[][] pixels) { 
            int width = pixels[0].length - 2;
            int height = pixels.length - 2;
            recursComplex(0, 0, width, height, pixels);
        }

        // Method: Implements the ComplexMondrian art by filling in the regions and splitting them
        // If the region being considered is at least one-fourth the height of the full canvas 
        // and at least one-fourth the width of the full canvas, it's randomly split into 4 regions
        // If the region being considered is at least one-fourth the height of the full canvas, 
        // it is split into  two smaller regions by choosing a horizontal dividing line at random. 
        // If the region being considered is at least one-fourth the width of the full canvas, 
        // it is split into two smaller regions by choosing a vertical dividing line at random.
        // If the region being considered is smaller than one-fourth the height of the full canvas 
        // and smaller than one-fourth the width of the full canvas, it is filled with a random color
        // If the chosen regions are in the first half of the width of the canvas 
        // and the first half of the height of the canvas, the colors are lighter than the other side 
        // of the canvas.
        // Parameters: 
        // 1. starting x-coordinate
        // 2. starting y-coordinate 
        // 3. ending x-coordinate
        // 4. ending y-coordinate 
        // 5. Array of pixels of the canvas 

        private void recursComplex(int x, int y, int width, int height, Color[][] pixels) {
            
            if (width - x < (pixels[0].length - 1) / 4 && height - y < (pixels.length - 1) / 4) { 
                 if (width < pixels[0].length / 2 && x < pixels[0].length / 2) { 
                     fillRegionTop(x, y, width, height, pixels);
                 } else if (height < pixels.length / 2 && y < pixels.length / 2) { 
                     fillRegionBottom(x, y, width, height, pixels);
                } else { 
                    fillRegion(x, y, width, height, pixels);
                }
            
            } else if (width - x >= (pixels[0].length - 1) / 4 && height - y >= (pixels.length - 1) / 4) { 
                int sideHor = width - x;
                int sideVer = height - y;
                int splitHor = split(x, sideHor);
                int splitVer = split(y, sideVer);
                
                recursComplex(x, y, splitHor, splitVer, pixels);
                recursComplex(x, splitVer, splitHor, height, pixels);
                recursComplex(splitHor, y, width, splitVer, pixels);
                recursComplex(splitHor, splitVer, width, height, pixels);
            
            } else if (width - x >= (pixels[0].length - 1) / 4) { 
                int sideHor = width - x; 
                int splitHor = split(x, sideHor);
                
                recursComplex(x, y, splitHor, height, pixels);
                recursComplex(splitHor, y, width, height, pixels);

            
            } else if (height - y >= (pixels.length - 1) / 4) { 
                int sideVer = height - y;
                int splitVer = split(y, sideVer);
        
                recursComplex(x, y, width, splitVer, pixels);
                recursComplex(x, splitVer, width, height, pixels);
            }
        }

        // Method: Implements the BasicMondrian art by filling in the regions and splitting them
        // If the region being considered is at least one-fourth the height of the full canvas 
        // and at least one-fourth the width of the full canvas, it's randomly split into 4 regions
        // If the region being considered is at least one-fourth the height of the full canvas, 
        // it is split into  two smaller regions by choosing a horizontal dividing line at random. 
        // If the region being considered is at least one-fourth the width of the full canvas, 
        // it is split into two smaller regions by choosing a vertical dividing line at random.
        // If the region being considered is smaller than one-fourth the height of the full canvas 
        // and smaller than one-fourth the width of the full canvas, it is filled with a random color
        // Parameters: 
        // 1. starting x-coordinate
        // 2. starting y-coordinate 
        // 3. ending x-coordinate
        // 4. ending y-coordinate 
        // 5. Array of pixels of the canvas 

        private void recurs(int x, int y, int width, int height, Color[][] pixels) {
            if (width - x < (pixels[0].length - 1) / 4 && height - y < (pixels.length - 1) / 4) { 
                fillRegion(x, y, width, height, pixels);
            } else if (width - x >= (pixels[0].length - 1) / 4 && height - y >= (pixels.length - 1) / 4) { 
                int sideHor = width - x;
                int sideVer = height - y;
                int splitHor = split(x, sideHor);
                int splitVer = split(y, sideVer);
                
                recurs(x, y, splitHor, splitVer, pixels);
                recurs(x, splitVer, splitHor, height, pixels);
                recurs(splitHor, y, width, splitVer, pixels);
                recurs(splitHor, splitVer, width, height, pixels);
            
            } else if (width - x >= (pixels[0].length - 1) / 4) { 
                int sideHor = width - x; 
                int splitHor = split(x, sideHor);
                
                recurs(x, y, splitHor, height, pixels);
                recurs(splitHor, y, width, height, pixels);

            
            } else if (height - y >= (pixels.length - 1) / 4) { 
                int sideVer = height - y;
                int splitVer = split(y, sideVer);
        
                recurs(x, y, width, splitVer, pixels);
                recurs(x, splitVer, width, height, pixels);
            }
        }

        // Method: generates a random number to split the regions
        // Paramaters: minimum value, maximum value between which 
        // we need to split. 
        // Returns: the generated random number

        private int split(int min, int side) {
            int randNum = min + (int)(Math.random() * (side + 1));
            return randNum;
        }

        // Method: fills the given region with a color
        // Parameters:
        // 1. starting x-coordinate
        // 2. starting y-coordinate 
        // 3. ending x-coordinate
        // 4. ending y-coordinate 
        // 5. Array of pixels of the canvas  

        private void fillRegion(int x, int y, int width, int height, Color[][] pixels) {
            Color color = getRandomColor();
            for (int i = y + 1; i < height - 2; i++) {
                for (int j = x + 1; j < width - 2; j++) {
                    pixels[i][j] = color;
                }
            }
        }

        // Method: fills the given region with a color
        // Makes the randomly generated color lighter
        // Parameters:
        // 1. starting x-coordinate
        // 2. starting y-coordinate 
        // 3. ending x-coordinate
        // 4. ending y-coordinate 
        // 5. Array of pixels of the canvas  

        private void fillRegionTop (int x, int y, int width, int height, Color[][] pixels) { 
            Color color = getRandomColor();
            int red = color.getRed();
            int blue = color.getBlue();
            int green = color.getGreen();
            int finRed = Math.max(0, red - 20);
            int finBlue = Math.max(0, blue - 20);
            int finGreen = Math.max(0, green - 20);
            Color complCol = new Color(finRed, finBlue, finGreen);
            for (int i = y + 1; i < height - 2; i++) {
                for (int j = x + 1; j < width - 2; j++) {
                    pixels[i][j] = complCol;
                }
            }
        }

        // Method: fills the given region with a color
        // Makes the randomly generated color further lighter
        // Parameters:
        // 1. starting x-coordinate
        // 2. starting y-coordinate 
        // 3. ending x-coordinate
        // 4. ending y-coordinate 
        // 5. Array of pixels of the canvas  

        private void fillRegionBottom (int x, int y, int width, int height, Color[][] pixels) { 
            Color color = getRandomColor();
            int red = color.getRed();
            int blue = color.getBlue();
            int green = color.getGreen();
            int finRed = Math.max(0, red - 10);
            int finBlue = Math.max(0, blue - 10);
            int finGreen = Math.max(0, green - 10);
            Color complCol = new Color(finRed, finBlue, finGreen);
            for (int i = y + 1; i < height - 2; i++) {
                for (int j = x + 1; j < width - 2; j++) {
                    pixels[i][j] = complCol;
                }
            }
        }

        // Method: Generates a random color to fill the region
        // Returns: generated random color

        private Color getRandomColor() { 
            int rand = 1 + (int)(Math.random() * (5));
        
            if (rand == 1) { 
               return Color.RED;
            } else if (rand == 2) { 
               return Color.CYAN;
            } else if (rand == 3) { 
               return Color.YELLOW;
            } else { 
               return Color.WHITE;
            }
        }
    }
