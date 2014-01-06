package gridfractals;

import java.awt.image.*;
import java.awt.*;


/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

abstract public class Grid {

  public int width;
  public int height;
  protected String type;

  public Grid( int width, int height, String type)
  {
  this.height = height;
  this.width = width;
  this.type = type;
  }

  public abstract BufferedImage getBufferedImage();
  public abstract Image getImage(  Component component );


  public int getWidth() { return width; }
  public int getHeight() { return height; }
  public String getType() { return type; }


  abstract public Grid addRelativeNoise( float coverage, float maxChange, int seed );
  abstract public Grid clip( float top, float bottom );
  abstract public Grid addGradientRelativeNoise( float minGradient, float maxGradient, float maxChange, int seed );







}


