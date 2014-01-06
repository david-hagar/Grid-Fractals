package gridfractals.commands;

import gridfractals.Command;
import gridfractals.IntGrid;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class GradientMagnitudeCommand extends Command {

  public GradientMagnitudeCommand() {
  super( "Gradient Magnitude" );
  }

  public void run()
  {
  if( Thread.currentThread().isInterrupted() )
    return;

  if( previous == null )
    {
    System.out.println("null prev");
    grid = null;
    return;
    }

  grid = ( (IntGrid) previous.getGrid()).getGradient( );

  clearNeedUpdate();
  }





}