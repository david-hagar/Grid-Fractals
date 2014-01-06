package gridfractals;

import java.util.*;
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

public class IntGrid extends Grid //implements Cloneable
{
  public short grid[][] = null; 

public IntGrid(  int width, int height )
{
  super( width, height, "Integer" );
  grid = new short[width][height];

}

public short getValue( int x, int y )
{
  if( x<0 )
    x+=width;
  else if( x>=width )
    x-=width;

  if( y<0 )
    y+=height;
  else if( y>=height )
    y-=height;

  return grid[x][y];
}


public void randomize( long seed )
{
Random r = new Random(seed * Integer.MAX_VALUE );

for (int i = 0; i < width; i++)
  for (int j = 0; j < height; j++)
  {
    grid[i][j] = (short) r.nextInt( Short.MAX_VALUE  );
  }

}

public IntGrid average( int reach )
{
IntGrid newigrid = new IntGrid( width, height);
short newgrid[][] = newigrid.grid;

int area = (reach *2 + 1 );
area *= area;

for (int i = 0; i < width; i++)
  for (int j = 0; j < height; j++)
    {
    int total = 0;
    for (int di = -reach; di <= reach; di++)
      for (int dj = -reach; dj <= reach; dj++)
      {
      total+= getValue( i+di, j+dj );
      }
      newgrid[i][j] = (short)(total / area);
    }

return newigrid;
}


public IntGrid addNoise( float coverage, int seed )
{
Random r = new Random(seed * Integer.MAX_VALUE );

IntGrid newigrid = new IntGrid( width, height);
short newgrid[][] = newigrid.grid;

for (int i = 0; i < width; i++)
  for (int j = 0; j < height; j++)
    {
      float rand = r.nextFloat();
      if( rand < coverage )
        newgrid[i][j] = (short)( r.nextFloat() * Short.MAX_VALUE );
      else
        newgrid[i][j] = getValue( i, j );
    }

return newigrid;
}


public IntGrid doubleResolution( )
{
IntGrid newigrid = new IntGrid( width * 2 , height * 2);
short newgrid[][] = newigrid.grid;

for (int i = 0; i < width; i++)
  for (int j = 0; j < height; j++)
    {
      short v00 = getValue( i, j );
      short v01 = getValue( i, j+1 );
      short v10 = getValue( i+1, j );
      short v11 = getValue( i+1, j+1 );
      int i2 = i<<1;
      int j2 = j<<1;
      newgrid[i2  ][j2  ] =  v00;
      //newgrid[i2  ][j2+2] =  v01;
      //newgrid[i2+2][j2  ] =  v11;
      //newgrid[i2+2][j2+2] =  v11;

      newgrid[i2  ][j2+1] = (short)(  ( ((int) v00 ) + v01 ) >> 1 );
      //newgrid[i2+2][j2+1] = (short)(  ( ((int) v10 ) + v11 ) >> 1 );
      newgrid[i2+1][j2  ] = (short)(  ( ((int) v00 ) + v10 ) >> 1 );
      //newgrid[i2+1][j2+2] = (short)(  ( ((int) v01 ) + v11 ) >> 1 );

      newgrid[i2+1][j2+1] = (short)(  ( ((int) v00 ) + v01 + v10 + v11 ) >> 2 );
    }

return newigrid;
}

public int[] getMinMax( )
{
int min = Short.MAX_VALUE;
int max = Short.MIN_VALUE;


for (int i = 0; i < width; i++)
  for (int j = 0; j < height; j++)
    {
      int v = grid[i][j];
      if( v > max )
        max = v;
      if( v < min )
        min = v;
    }

int ret[] = new int[2];
ret[0] = min;
ret[1] = max;
return ret;
}


public IntGrid maxContrast()
{
IntGrid newigrid = new IntGrid( width, height);
short newgrid[][] = newigrid.grid;

int mm[] = getMinMax();
int min = mm[0];
int max = mm[1];
float k = ((float) Short.MAX_VALUE ) / ( max - min );

for (int i = 0; i < width; i++)
  for (int j = 0; j < height; j++)
    {
    newgrid[i][j] = (short)( (grid[i][j]-min)*k );
    }

return newigrid;
}


public BufferedImage getBufferedImage()
{
  BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_USHORT_GRAY );
  WritableRaster raster = image.getRaster();
//System.out.println("" + raster.getBounds() );
//System.out.println("h=" + raster.getHeight() );
//System.out.println("w=" + raster.getWidth() );

  for (int i = 0; i < width; i++)
    for (int j = 0; j < height; j++)
    {
     //a[0] = (int)( (i+j)/(float)width * Short.MAX_VALUE ) ;
     raster.setSample(i,j ,0, grid[i][j] * 2 );
    }

  return image;
}

public Image getImage( Component component )
{
  int pix[] = new int[width * height];
  int index = 0;
  for (int j = 0; j < height; j++)
    for (int i = 0; i < width; i++)
    {
     int v =  grid[i][j] >>7;
     pix[index++] = (((int)255) << 24) | v | v <<8 | v<<16;
    }

  Image img = component.createImage(new MemoryImageSource(width, height, pix, 0, width));

  return img;
 }



public Grid addRelativeNoise( float coverage, float maxChange, int seed )
{
Random r = new Random(seed * Integer.MAX_VALUE );

IntGrid newigrid = new IntGrid( width, height);
short newgrid[][] = newigrid.grid;

for (int i = 0; i < width; i++)
  for (int j = 0; j < height; j++)
    {
      float rand = r.nextFloat();
      if( rand < coverage )
        {
        int newValue = getValue( i, j ) + (int)((r.nextFloat()*2.0f-1.0f) * Short.MAX_VALUE * maxChange);
        if( newValue > Short.MAX_VALUE )
          newValue = Short.MAX_VALUE;
        if( newValue < 0  )
          newValue = 0;
        newgrid[i][j] = (short) newValue;
        }
      else
        newgrid[i][j] = getValue( i, j );
    }

return newigrid;

}


public Grid clip( float top, float bottom )
{
IntGrid newigrid = new IntGrid( width, height);
short newgrid[][] = newigrid.grid;

top*= Short.MAX_VALUE;
bottom*=Short.MAX_VALUE;

float k = Short.MAX_VALUE/(top-bottom);

for (int i = 0; i < width; i++)
  for (int j = 0; j < height; j++)
  {
    int newValue = (int) ( (getValue( i, j )-bottom) * k);
    if( newValue > Short.MAX_VALUE )
      newValue = Short.MAX_VALUE;
    if( newValue < 0  )
      newValue = 0;
    newgrid[i][j] = (short) newValue;
  }

return newigrid;

}



public Grid getGradient( )
{
IntGrid newigrid = new IntGrid( width, height);
short newgrid[][] = newigrid.grid;


double k = Math.sqrt(2.0);

for (int i = 0; i < width; i++)
  for (int j = 0; j < height; j++)
  {
    //double value = getValue( i, j );
    double dx = getValue( i-1, j ) - getValue( i+1, j );
    double dy = getValue( i, j-1 ) - getValue( i, j+1 );
    newgrid[i][j] = (short) (Math.sqrt( dx*dx + dy*dy ) * k);
  }

return newigrid.maxContrast();

}


public Grid addGradientRelativeNoise( float minGradient, float maxGradient, float maxChange, int seed )
{
Random r = new Random(seed * Integer.MAX_VALUE );

IntGrid newigrid = new IntGrid( width, height);
short newgrid[][] = newigrid.grid;

IntGrid gradient = (IntGrid) getGradient();

minGradient *= Short.MAX_VALUE;
maxGradient *= Short.MAX_VALUE;

for (int i = 0; i < width; i++)
  for (int j = 0; j < height; j++)
    {
      float rand = r.nextFloat();
      int gradientValue = gradient.getValue(i,j);
      if( gradientValue > minGradient && gradientValue < maxGradient )
        {
        int newValue = getValue( i, j ) + (int)((r.nextFloat()*2.0f-1.0f) * Short.MAX_VALUE * maxChange);
        if( newValue > Short.MAX_VALUE )
          newValue = Short.MAX_VALUE;
        if( newValue < 0  )
          newValue = 0;
        newgrid[i][j] = (short) newValue;
        }
      else
        newgrid[i][j] = getValue( i, j );
    }

return newigrid;

}


}