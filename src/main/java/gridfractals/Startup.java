package gridfractals;

import gridfractals.commands.NewGridCommand;
import gridfractals.commands.VisibilityGridCommand;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 *
 * @author David Hagar
 * @version 1.0
 */

public class Startup {
    boolean packFrame = false;

    static ToolsFrame toolsFrame = null;
    static ViewWindow viewWindow = null;

    //Construct the application
    public Startup() {
        toolsFrame = new ToolsFrame();
        //Validate frames that have preset sizes
        //Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            toolsFrame.pack();
        } else {
            toolsFrame.validate();
        }
        //Center the window
        int margin = 10;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension toolFrameSize = toolsFrame.getSize();
        if (toolFrameSize.height > screenSize.height) {
            toolFrameSize.height = screenSize.height;
        }
        if (toolFrameSize.width > screenSize.width) {
            toolFrameSize.width = screenSize.width;
        }
        toolsFrame.setLocation((screenSize.width - toolFrameSize.width) - margin, margin);
        toolsFrame.setSize(new Dimension(toolFrameSize.width-margin, screenSize.height - margin*2));
        toolsFrame.setVisible(true);

        viewWindow = new ViewWindow();
        if (packFrame) {
            viewWindow.pack();
        } else {
            viewWindow.validate();
        }
        int width = screenSize.width - toolFrameSize.width - margin;
        viewWindow.setLocation(margin, margin);
        int w = width - margin*2;
        int h = w;
        if (h> screenSize.height) {
                    h = screenSize.height;
                }
        viewWindow.setSize(w, h);
        viewWindow.setVisible(true);

        //IntGrid grid = new IntGrid( 20,20 );
        //grid.randomize(12);

        //viewWindow.gridView.setGrid( grid );

        toolsFrame.commandSet.addAfterSelection(new NewGridCommand(11, 11));
        //toolsFrame.commandSet.addAfterSelection( new AverageCommand() );
        toolsFrame.commandSet.addAfterSelection(new VisibilityGridCommand());

    }

    //Main method
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Startup();
    }
}