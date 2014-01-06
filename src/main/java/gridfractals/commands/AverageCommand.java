package gridfractals.commands;

import gridfractals.Command;
import gridfractals.IntGrid;
import params.*;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class AverageCommand extends Command {
  IntParameter reach = new IntParameter("Reach", 1 );

  public AverageCommand()
  {
  super( "Average" );

  addParameter(reach);

  rebuildParameterView();
  }


  public void run()
  {
  if( Thread.currentThread().isInterrupted() )
    return;

  if( previous == null )
    {
    System.out.println("null prev on average command");
    grid = null;
    return;
    }

  grid = ( (IntGrid) previous.getGrid()).average( reach.getInt() ).maxContrast();

  clearNeedUpdate();
  }



  public void fromString( String string ) throws Exception
  {
    super.fromString( string );

    reach = (IntParameter) parameters.get(0);
  }

}



