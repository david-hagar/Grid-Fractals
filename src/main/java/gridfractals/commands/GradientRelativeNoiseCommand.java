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

public class GradientRelativeNoiseCommand extends Command {

  FloatParameter minGradient = new FloatParameter("Min Gradient", 0.6f );
  FloatParameter maxGradient = new FloatParameter("Max Gradient", 1.0f );
  FloatParameter maxChange = new FloatParameter("Max Change", 0.2f );
  IntParameter seed = new IntParameter("Random Seed", 123 );

  public GradientRelativeNoiseCommand() {
  super( "Gradient Relative Noise" );

  addParameter(minGradient);
  addParameter(maxGradient);
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

  grid = previous.getGrid().addGradientRelativeNoise(
      minGradient.getFloat(), maxGradient.getFloat(), maxChange.getFloat(), seed.getInt() );

  clearNeedUpdate();

  }


  public void fromString( String string ) throws Exception
  {
    super.fromString( string );

    minGradient = (FloatParameter) parameters.get(0);
    maxGradient = (FloatParameter) parameters.get(0);
    maxChange = (FloatParameter) parameters.get(1);
    seed = (IntParameter) parameters.get(2);
  }

}