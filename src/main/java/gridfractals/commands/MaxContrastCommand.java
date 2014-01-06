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

public class MaxContrastCommand extends Command {

  public MaxContrastCommand()
  {
  super( "Max Contrast" );
  }


  public void run()
  {
  if( Thread.currentThread().isInterrupted() )
    return;

  if( previous == null )
    {
    System.out.println("null prev on max contrast command");
    grid = null;
    return;
    }

  grid = ( (IntGrid) previous.getGrid()).maxContrast( );

  clearNeedUpdate();
  }



}