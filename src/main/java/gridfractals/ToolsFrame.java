package gridfractals;

import gridfractals.commands.AverageCommand;
import gridfractals.commands.ClipCommand;
import gridfractals.commands.DoubleResolutionCommand;
import gridfractals.commands.GradientMagnitudeCommand;
import gridfractals.commands.GradientRelativeNoiseCommand;
import gridfractals.commands.MaxContrastCommand;
import gridfractals.commands.NewGridCommand;
import gridfractals.commands.NoiseCommand;
import gridfractals.commands.RelativeNoiseCommand;
import gridfractals.commands.VisibilityGridCommand;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

/**
 * <p>
 * Title: Grid Fractals
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author David Hagar
 * @version 1.0
 */

public class ToolsFrame extends JFrame
{

	public CommandSet commandSet = new CommandSet();

	JPanel contentPane;

	JMenuBar jMenuBar1 = new JMenuBar();

	JMenu jMenuFile = new JMenu();

	JMenuItem jMenuFileExit = new JMenuItem();

	JMenu jMenuHelp = new JMenu();

	JMenuItem jMenuHelpAbout = new JMenuItem();

	BorderLayout borderLayout1 = new BorderLayout();

	JScrollPane libraryScrollPane = new JScrollPane();

	JPanel libraryPanel = new JPanel();

	JPanel jPanel1 = new JPanel();

	CommandSetView commandSetPanel = commandSet.getView();

	JScrollPane cspScrollPane = new JScrollPane();

	BorderLayout borderLayout2 = new BorderLayout();

	JPanel jPanel2 = new JPanel();

	BorderLayout borderLayout3 = new BorderLayout();

	JPanel jPanel3 = new JPanel();

	BorderLayout borderLayout4 = new BorderLayout();

	JMenu jMenu2 = new JMenu();

	JMenu jMenu3 = new JMenu();

	JMenu jMenu4 = new JMenu();

	JMenuItem view1x1MenuItem = new JMenuItem();

	JMenuItem view2x2MenuItem = new JMenuItem();

	JMenuItem view4x4MenuItem = new JMenuItem();

	JMenuItem deleteCommandMenuItem = new JMenuItem();

	JMenuItem moveUpCommandMenuItem = new JMenuItem();

	JMenuItem moveDownCommandMenuItem = new JMenuItem();

	JMenuItem saveCommandSetMenuItem = new JMenuItem();

	JMenuItem newCommandSetMenuItem = new JMenuItem();

	JMenuItem loadCommandSetMenuItem = new JMenuItem();

	JMenuItem newGridMenuItem = new JMenuItem();

	JMenuItem averageMenuItem = new JMenuItem();

	JMenuItem maxContrastMenuItem = new JMenuItem();

	JMenuItem doubleResMenuItem = new JMenuItem();

	JMenuItem addNoiseMenuItem = new JMenuItem();

	JMenuItem relativeNoiseMenuItem = new JMenuItem();

	JMenuItem clipMenuItem = new JMenuItem();

	JMenuItem gradientRelativeNoiseMenuItem = new JMenuItem();

	JMenuItem gradientMagnitudeMenuItem = new JMenuItem();

	JMenuItem visibilityGridMenuItem = new JMenuItem();

	JMenuItem pngSaveMenuItem = new JMenuItem();

	// Construct the frame
	public ToolsFrame()
	{
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try
		{
			jbInit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	// Component initialization
	private void jbInit() throws Exception
	{
		// setIconImage(Toolkit.getDefaultToolkit().createImage(ToolsFrame.class.getResource("[Your
		// Icon]")));
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(400, 600));
		this.setTitle("Tools Window");
		jMenuFile.setText("File");
		jMenuFileExit.setText("Exit");
		jMenuFileExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuFileExit_actionPerformed(e);
            }
        });
		jMenuHelp.setText("Help");
		jMenuHelpAbout.setText("About");
		jMenuHelpAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpAbout_actionPerformed(e);
            }
        });
		jPanel1.setLayout(borderLayout2);
		jPanel2.setLayout(borderLayout3);
		jPanel3.setLayout(borderLayout4);
		jMenu2.setText("Command Set");
		jMenu3.setText("Commands");
		jMenu4.setText("View");
		view1x1MenuItem.setText("1x1");
		view1x1MenuItem.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				view1x1MenuItem_actionPerformed(e);
			}
		});
		view2x2MenuItem.setText("2x2");
		view2x2MenuItem.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				view2x2MenuItem_actionPerformed(e);
			}
		});
		view4x4MenuItem.setText("4x4");
		view4x4MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view4x4MenuItem_actionPerformed(e);
            }
        });
		deleteCommandMenuItem.setText("Delete");
		deleteCommandMenuItem
				.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						deleteCommandMenuItem_actionPerformed(e);
					}
				});
		moveUpCommandMenuItem.setText("Move Up");
		moveUpCommandMenuItem
				.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						moveUpCommandMenuItem_actionPerformed(e);
					}
				});
		moveDownCommandMenuItem.setText("Move Down");
		moveDownCommandMenuItem
				.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						moveDownCommandMenuItem_actionPerformed(e);
					}
				});
		saveCommandSetMenuItem.setText("Save");
		saveCommandSetMenuItem
				.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						saveCommandSetMenuItem_actionPerformed(e);
					}
				});
		newCommandSetMenuItem.setText("New");
		newCommandSetMenuItem
				.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						newCommandSetMenuItem_actionPerformed(e);
					}
				});
		loadCommandSetMenuItem.setText("Load");
		loadCommandSetMenuItem
				.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        loadCommandSetMenuItem_actionPerformed(e);
                    }
                });
		newGridMenuItem.setText("New Grid");
		newGridMenuItem.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				newGridMenuItem_actionPerformed(e);
			}
		});
		averageMenuItem.setText("Average");
		averageMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                averageMenuItem_actionPerformed(e);
            }
        });
		maxContrastMenuItem.setText("Max Contrast");
		maxContrastMenuItem
				.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        maxContrastMenuItem_actionPerformed(e);
                    }
                });
		doubleResMenuItem.setText("Double Resolution");
		doubleResMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doubleResMenuItem_actionPerformed(e);
            }
        });
		addNoiseMenuItem.setText("Add Noise");
		addNoiseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNoiseMenuItem_actionPerformed(e);
            }
        });
		relativeNoiseMenuItem.setText("Add Relative Noise");
		relativeNoiseMenuItem
				.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        relativeNoiseMenuItem_actionPerformed(e);
                    }
                });
		clipMenuItem.setText("Clip");
		clipMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clipMenuItem_actionPerformed(e);
            }
        });
		gradientRelativeNoiseMenuItem.setText("Gradient Relative Noise");
		gradientRelativeNoiseMenuItem
				.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						gradientRelativeNoiseMenuItem_actionPerformed(e);
					}
				});
		gradientMagnitudeMenuItem.setText("Gradient Magnitude");
		gradientMagnitudeMenuItem
				.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						gradientMagnitudeMenuItem_actionPerformed(e);
					}
				});

		visibilityGridMenuItem.setText("Visibility Grid");
		visibilityGridMenuItem
				.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						visibilityGridMenuItem_actionPerformed(e);
					}
				});

		pngSaveMenuItem.setText("Save As PNG Image");
		pngSaveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pngSaveMenuItem_actionPerformed(e);
            }
        });
		jMenuFile.add(pngSaveMenuItem);
		jMenuFile.add(jMenuFileExit);
		jMenuHelp.add(jMenuHelpAbout);
		jMenuBar1.add(jMenuFile);
		jMenuBar1.add(jMenu2);
		jMenuBar1.add(jMenu3);
		jMenuBar1.add(jMenu4);
		jMenuBar1.add(jMenuHelp);
		contentPane.add(libraryScrollPane, BorderLayout.WEST);
		libraryScrollPane.getViewport().add(libraryPanel, null);
		contentPane.add(jPanel1, BorderLayout.CENTER);
		jPanel1.add(cspScrollPane, BorderLayout.CENTER);
		cspScrollPane.getViewport().add(jPanel3, null);
		jPanel3.add(commandSetPanel, BorderLayout.CENTER);
		jPanel1.add(jPanel2, BorderLayout.NORTH);
		jMenu4.add(view1x1MenuItem);
		jMenu4.add(view2x2MenuItem);
		jMenu4.add(view4x4MenuItem);
		jMenu3.add(deleteCommandMenuItem);
		jMenu3.add(moveUpCommandMenuItem);
		jMenu3.add(moveDownCommandMenuItem);
		jMenu3.addSeparator();
		jMenu3.add(newGridMenuItem);
		jMenu3.add(averageMenuItem);
		jMenu3.add(maxContrastMenuItem);
		jMenu3.add(doubleResMenuItem);
		jMenu3.add(addNoiseMenuItem);
		jMenu3.add(relativeNoiseMenuItem);
		jMenu3.add(clipMenuItem);
		jMenu3.add(gradientRelativeNoiseMenuItem);
		jMenu3.add(gradientMagnitudeMenuItem);
		jMenu3.add(visibilityGridMenuItem);
		jMenu2.add(newCommandSetMenuItem);
		jMenu2.add(saveCommandSetMenuItem);
		jMenu2.add(loadCommandSetMenuItem);
		this.setJMenuBar(jMenuBar1);
	}

	// File | Exit action performed
	public void jMenuFileExit_actionPerformed(ActionEvent e)
	{
		System.exit(0);
	}

	// Help | About action performed
	public void jMenuHelpAbout_actionPerformed(ActionEvent e)
	{
		ToolsFrame_AboutBox dlg = new ToolsFrame_AboutBox(this);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.show();
	}

	// Overridden so we can exit when window is closed
	protected void processWindowEvent(WindowEvent e)
	{
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
		{
			jMenuFileExit_actionPerformed(null);
		}
	}

	void newCommandSetMenuItem_actionPerformed(ActionEvent e)
	{
		commandSet.clear();
	}

	void saveCommandSetMenuItem_actionPerformed(ActionEvent e)
	{

		JFileChooser fc = new JFileChooser(".");
		int ret = fc.showSaveDialog(this);
		if (ret != JFileChooser.APPROVE_OPTION)
			return;

        File f = fc.getSelectedFile();
        if( !f.toString().endsWith(".txt"))
            f = new File(f.toString() + ".txt");

		try
		{
			commandSet.save(f);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error saving file:" + ex);
		}

	}

	void loadCommandSetMenuItem_actionPerformed(ActionEvent e)
	{

		JFileChooser fc = new JFileChooser(".");
		int ret = fc.showOpenDialog(this);
		if (ret != JFileChooser.APPROVE_OPTION)
			return;

		try
		{
			commandSet.load(fc.getSelectedFile());
		} catch (Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error saving file:" + ex);
		}

	}

	void deleteCommandMenuItem_actionPerformed(ActionEvent e)
	{
		commandSet.deleteSelected();
	}

	void moveUpCommandMenuItem_actionPerformed(ActionEvent e)
	{

	}

	void moveDownCommandMenuItem_actionPerformed(ActionEvent e)
	{

	}

	void newGridMenuItem_actionPerformed(ActionEvent e)
	{
		commandSet.addAfterSelection(new NewGridCommand());

	}

	void averageMenuItem_actionPerformed(ActionEvent e)
	{
		commandSet.addAfterSelection(new AverageCommand());
	}

	void maxContrastMenuItem_actionPerformed(ActionEvent e)
	{
		commandSet.addAfterSelection(new MaxContrastCommand());
	}

	void doubleResMenuItem_actionPerformed(ActionEvent e)
	{
		commandSet.addAfterSelection(new DoubleResolutionCommand());
	}

	void addNoiseMenuItem_actionPerformed(ActionEvent e)
	{
		commandSet.addAfterSelection(new NoiseCommand());
	}

	void relativeNoiseMenuItem_actionPerformed(ActionEvent e)
	{
		commandSet.addAfterSelection(new RelativeNoiseCommand());
	}

	void clipMenuItem_actionPerformed(ActionEvent e)
	{
		commandSet.addAfterSelection(new ClipCommand());
	}

	void gradientRelativeNoiseMenuItem_actionPerformed(ActionEvent e)
	{
		commandSet.addAfterSelection(new GradientRelativeNoiseCommand());
	}

	void gradientMagnitudeMenuItem_actionPerformed(ActionEvent e)
	{
		commandSet.addAfterSelection(new GradientMagnitudeCommand());
	}

	void visibilityGridMenuItem_actionPerformed(ActionEvent e)
	{
		commandSet.addAfterSelection(new VisibilityGridCommand());

	}

	void view1x1MenuItem_actionPerformed(ActionEvent e)
	{

		GridView gridView = Startup.viewWindow.gridView;

		gridView.numTilesX = 1;
		gridView.numTilesY = 1;
		gridView.repaint();
	}

	void view2x2MenuItem_actionPerformed(ActionEvent e)
	{
		GridView gridView = Startup.viewWindow.gridView;

		gridView.numTilesX = 2;
		gridView.numTilesY = 2;
		gridView.repaint();
	}

	void view4x4MenuItem_actionPerformed(ActionEvent e)
	{
		GridView gridView = Startup.viewWindow.gridView;

		gridView.numTilesX = 4;
		gridView.numTilesY = 4;
		gridView.repaint();
	}

	void pngSaveMenuItem_actionPerformed(ActionEvent e)
	{

		JFileChooser fc = new JFileChooser(".");
		int ret = fc.showSaveDialog(this);
		if (ret != JFileChooser.APPROVE_OPTION)
			return;

        String fileName = fc.getSelectedFile().getAbsolutePath();
        if( !fileName.endsWith(".png"))
            fileName+=".png";
		try
		{
			PNGSaver.saveToPNG(Startup.viewWindow.gridView.grid, fileName);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error saving PNG:" + ex);
		}

	}

}