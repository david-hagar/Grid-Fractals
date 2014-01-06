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

public class NoiseCommand extends Command {


  FloatParameter coverage = new FloatParameter("Coverage", 0.25f );
  IntParameter seed = new IntParameter("Random Seed", 123 );

  public NoiseCommand()
  {
  super( "Noise" );

  addParameter(coverage);
  addParameter(seed);

  rebuildParameterView();
  }


  public void run()
  {
  if( Thread.currentThread().isInterrupted() )
    return;

  if( previous == null )
    {
    System.out.println("null prev on Noise Command");
    grid = null;
    return;
    }

  grid = ( (IntGrid) previous.getGrid()).addNoise( coverage.getFloat(), seed.getInt() );

  clearNeedUpdate();
  }



  public void fromString( String string ) throws Exception
  {
    super.fromString( string );

    coverage = (FloatParameter) parameters.get(0);
    seed = (IntParameter) parameters.get(1);
  }


}