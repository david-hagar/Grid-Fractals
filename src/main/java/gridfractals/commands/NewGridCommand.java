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

public class NewGridCommand extends Command {

  IntParameter width = new IntParameter("Width", 10 );
  IntParameter height = new IntParameter("Height", 10 );
  IntParameter randomSeed = new IntParameter("Random Seed", 123 );

  public NewGridCommand()
  {
  super( "New Grid" );

  addParameter(width);
  addParameter(height);
  addParameter(randomSeed);

  rebuildParameterView();
  }
  
  public NewGridCommand( int w, int h )
  {
  super( "New Grid" );
  
  width.setInt(w);
  height.setInt(h);

  addParameter(width);
  addParameter(height);
  addParameter(randomSeed);

  rebuildParameterView();
  }



  public void run()
  {
  if( Thread.currentThread().isInterrupted() )
    return;

  IntGrid igrid = new IntGrid( width.getInt(), height.getInt() );
  grid = igrid;
  igrid.randomize( (long) randomSeed.getInt() );
  clearNeedUpdate();
  }


  public void fromString( String string ) throws Exception
  {
    super.fromString( string );

    width = (IntParameter) parameters.get(0);
    height = (IntParameter) parameters.get(1);
    randomSeed = (IntParameter) parameters.get(2);
  }


}