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

public class DoubleResolutionCommand extends Command {

  public DoubleResolutionCommand()
  {
  super( "Double Resolution" );
  }


  public void run()
  {
  if( Thread.currentThread().isInterrupted() )
    return;

  if( previous == null )
    {
    System.out.println("null prev in DoubleResolutionCommand");
    grid = null;
    return;
    }

  grid = ( (IntGrid) previous.getGrid()).doubleResolution( );

  clearNeedUpdate();
  }




}