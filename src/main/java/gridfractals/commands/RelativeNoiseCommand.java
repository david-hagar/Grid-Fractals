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

public class RelativeNoiseCommand extends Command {

  FloatParameter coverage = new FloatParameter("Coverage", 0.25f );
  FloatParameter maxChange = new FloatParameter("Max Change", 0.25f );
  IntParameter seed = new IntParameter("Random Seed", 123 );

  public RelativeNoiseCommand() {
  super( "Relative Noise" );

  addParameter(coverage);
  addParameter(maxChange);
  addParameter(seed);

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

  grid = previous.getGrid().addRelativeNoise(
      coverage.getFloat(), maxChange.getFloat(), seed.getInt() );

  clearNeedUpdate();

  }


  public void fromString( String string ) throws Exception
  {
    super.fromString( string );

    coverage = (FloatParameter) parameters.get(0);
    maxChange = (FloatParameter) parameters.get(1);
    seed = (IntParameter) parameters.get(2);
  }



}