package gridfractals.commands;

import gridfractals.Command;
import params.*;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class ClipCommand extends Command {

  FloatParameter top = new FloatParameter("Top", 0.7f );
  FloatParameter bottom = new FloatParameter("Bottom", 0.2f );

  public ClipCommand() {
  super( "Clip" );

  addParameter(top);
  addParameter(bottom);

  rebuildParameterView();
  }


  public void run()
  {
  if( Thread.currentThread().isInterrupted() )
    return;

  if( previous == null )
    {
    System.out.println("null prev on " + title + " Command");
    grid = null;
    return;
    }

  grid = previous.getGrid().clip( top.getFloat(), bottom.getFloat() );

  clearNeedUpdate();

  }


  public void fromString( String string ) throws Exception
  {
    super.fromString( string );

    top = (FloatParameter) parameters.get(0);
    bottom = (FloatParameter) parameters.get(1);
  }




}