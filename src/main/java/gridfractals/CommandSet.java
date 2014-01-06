package gridfractals;


import gridfractals.commands.NewGridCommand;

import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import javax.swing.*;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class CommandSet {

  ArrayList commands = new ArrayList();
  private Command currentSelection = null;
  Thread updateThread = null;

  private CommandSetView commandSetView = new CommandSetView();

  public CommandSet()
  {

  }

  public void addAfterSelection( Command command )
  {
    int index = commands.indexOf( currentSelection );
    if( index == -1 )
      {
      commands.add( command );
      }
    else
      commands.add( index + 1, command );


    relinkCommands();
    commandSetView.rebuild(this);
    setCurrentSelection( command );

    command.setNeedUpdate();
  }

  public void relinkCommands()
  {
  Iterator iter = commands.iterator();
  if( ! iter.hasNext() )
    return;
  Command last = (Command) iter.next();
  Command current = null;
  last.setPrevious( null );
  last.setNext( null );
  while (iter.hasNext())
  {
    current = (Command) iter.next();
    last.setNext( current );
    current.setPrevious( last );
    last = current;
  }

  if( current != null )
    current.setNext(null);
/*
System.out.println("_________");
  iter = commands.iterator();
  while (iter.hasNext())
  {
    current = (Command) iter.next();
    System.out.println("c=" + current);
    System.out.println("next = " + current.next );
    System.out.println("prev = " + current.previous );
  }
*/
  }



  public void setCurrentSelection( Command command )
  {
  if( currentSelection != null && currentSelection != command )
    currentSelection.commandView.commandStatusView.clearSelect();
  currentSelection = command;
  currentSelection.commandView.commandStatusView.setSelect();
  updateGridsAndRepaint();
  }

  public CommandSetView getView() { return commandSetView;}
  public Command getSelection() { return currentSelection;}


  public void rebildView()
  {
    if( commands.size() == 0 )
      commands.add( new NewGridCommand() );

    commandSetView.rebuild( this );

    if( ! commands.contains( currentSelection ) )
      {
      setCurrentSelection( (Command) commands.get(commands.size()-1) );
      }

    currentSelection.commandView.commandStatusView.setSelect();
  }



  public void updateGridsAndRepaint()
  {
  if( updateThread != null )
    updateThread.interrupt();

  updateThread = new Thread()
  {
  public void run()
  {
    Grid grid = currentSelection.getGrid();

    if( Thread.currentThread().isInterrupted() )
      return;

    Startup.viewWindow.gridView.setGrid( grid );

    if( Thread.currentThread().isInterrupted() )
      return;

    Startup.viewWindow.gridView.repaint();
  }
  };

  updateThread.setPriority( Thread.MIN_PRIORITY );
  updateThread.start();
  }

  public void load( File file ) throws Exception
  {
  commands.clear();

  BufferedReader br = new BufferedReader( new FileReader( file ) );

  String line = null;
  while( ( line = br.readLine() ) != null )
  {
    int index = line.indexOf('|');
    if( index == -1 )
      continue;

    String className = line.substring(0, index);
    try {
      Class c = Class.forName(className);
      Constructor constructor = c.getConstructor( new Class[0] );
      Command command = (Command) constructor.newInstance( new Object[0] );
      command.fromString( line.substring(index+1) );
      commands.add( command );
    }
    catch (Exception ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error parsing line \"" + line + "\":" + ex);
    }


  }
  relinkCommands();
  rebildView();

  br.close();
  }



  public void save( File file ) throws Exception
  {

  BufferedWriter bw = new BufferedWriter( new FileWriter( file ) );

  Iterator iter = commands.iterator();
  while (iter.hasNext()) {
    Command item = (Command) iter.next();
    bw.write( item.toString() );
    bw.newLine( );
  }

  bw.flush();
  bw.close();
  }


  public void deleteSelected( )
  {
    currentSelection.setNeedUpdate();

    commands.remove( currentSelection );
    if( currentSelection.previous != null )
      currentSelection = currentSelection.previous;
    else if( currentSelection.next != null )
      currentSelection = currentSelection.next;

    relinkCommands();
    rebildView();
    setCurrentSelection( currentSelection );
  }


  public void clear()
  {
    commands.clear();
    addAfterSelection( new NewGridCommand() );
    rebildView();
  }

}