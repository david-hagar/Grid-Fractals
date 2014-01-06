package gridfractals;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class GridView extends JPanel {

  Grid grid = null;
  int numTilesX = 1;
  int numTilesY = 1;

  public GridView( Grid grid ) {
    this.grid = grid;
  }
  public GridView( ) {
    this.grid = null;
  }

  public void setGrid( Grid grid )
  {
    this.grid = grid;
  }

  public void paint(Graphics g0)
  {
   super.paint(g0);
   Graphics2D g = (Graphics2D) g0;

   if( grid == null )
     return;

   System.out.println("Get Image");
   Image image = grid.getImage( this );
   //AffineTransform at = new AffineTransform();
   //at.scale(2.0,2.0);
   //RenderingHints h = new RenderingHints( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC  );
   //g.drawImage(image,new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR ) ,10,10);

   Rectangle panelBounds = this.getBounds();

   float gridAspect = (float)grid.height/grid.width;
   float windowAspect = (float)panelBounds.height/panelBounds.width;

   int width;
   int height;
   int x = 0;
   int y = 0;
   if( windowAspect > gridAspect )
     {
     width = panelBounds.width;
     height = (int) (width * gridAspect);
     y = (int)(( panelBounds.height - height )/2);
     }
   else
     {
     height = panelBounds.height;
     width = (int) (height / gridAspect);
     x = (int) ( ( panelBounds.width - width )/2 );
     }

   System.out.println("rescale");
   Image image2 = image.getScaledInstance(width/numTilesX, height/numTilesY, Image.SCALE_SMOOTH);
   System.out.println("draw");
   for (int a = 0; a < numTilesX; a++)
     for (int b = 0; b < numTilesY; b++)
     {
       g.drawImage(image2, x + a*width/numTilesX, y + b*height/numTilesY, null);
     }

   System.out.println("draw finish");
  }



  static AffineTransform calcBoundsTF( Dimension viewWin , Rectangle.Float bounds  )
  {
  AffineTransform tf = new AffineTransform();

  float boundsAspect = bounds.height/bounds.width;
  float windowAspect = (float)viewWin.height/viewWin.width;

  float scale;
  if( windowAspect > boundsAspect )
    scale = viewWin.width/bounds.width;
  else
    scale = viewWin.height/bounds.height;

  scale *= 0.9f;

  float centerX = bounds.x + bounds.width/2.0f;
  float centerY =  bounds.y + bounds.height/2.0f;

  tf.translate( viewWin.width/2 , viewWin.height/2  );
  tf.scale( 1.0, -1.0  );
  tf.scale( scale, scale  );
  tf.translate( - centerX, -centerY );

  return tf;
  }


}