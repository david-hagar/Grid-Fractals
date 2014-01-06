package params;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

abstract public class StringConvertableParameter extends Parameter {

  public StringConvertableParameter( String name )
  {
  super(name);
  }

  public StringConvertableParameter( )
  {
  super("?");
  }

  abstract public String toString();
  abstract public void setString( String stringValue ) throws Exception;
}