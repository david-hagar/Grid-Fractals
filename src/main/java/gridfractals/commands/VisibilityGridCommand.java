package gridfractals.commands;

import gridfractals.Command;
import gridfractals.IntGrid;
import params.IntParameter;

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

public class VisibilityGridCommand extends Command
{
	IntParameter center = new IntParameter("Center as origin 0/1", 1 );

	public VisibilityGridCommand()
	{
		super("Visibility Grid");
		
		addParameter( center );
		
		rebuildParameterView();
	}

	public void run()
	{
		if (Thread.currentThread().isInterrupted())
			return;

		if (previous == null)
		{
			System.out.println("null prev");
			grid = null;
			return;
		}

		IntGrid prevGrid = (IntGrid) previous.getGrid();
		
		IntGrid grid = new IntGrid( prevGrid.width, prevGrid.height);
		this.grid = grid;

//		if (grid.width % 2 != 1 || grid.height % 2 != 1)
//		{
//			System.out.println("Aborting. Grid has even dimensions");
//			return;
//		}
		
		System.out.println("Grid ok");

		int xOffset = (center.getInt() == 0) ? 0: grid.width / 2;
		int yOffset = (center.getInt() == 0) ? 0: grid.height / 2;

		int onCount = 0;
		int offCount = 0;
		int total = 0;
		
		for (int i = 0; i < grid.width; i++)
			for (int j = 0; j < grid.height; j++)
			{
				int x = i - xOffset;
				int y = j - xOffset;
				
				int gcd = gcd( Math.abs(x), Math.abs(y));
				
				if( gcd != 1 )
				{
					grid.grid[i][j]= Short.MAX_VALUE;
					onCount++;
				}
				else
				{
					grid.grid[i][j]= 0;
					offCount++;
				}
				
				total++;
				//grid.grid[i][j]= (short) i; 
			}

		System.out.println("onCount=" + onCount);
		System.out.println("offCount=" + offCount);
		System.out.println("total=" + total);
		float ratio = ((float) offCount )/total;
		System.out.println("ratio=" + ratio);
		System.out.println("ratio *P1=" + ratio * Math.PI);
		System.out.println("ratio / pi=" + ratio / Math.PI);
		System.out.println("ratio * P1^2=" + ratio * Math.PI* Math.PI);
		System.out.println("ratio / pi^2=" + ratio / Math.PI / Math.PI);

		clearNeedUpdate();
	}

	int gcd(int a, int b)
	{
		while (b != 0)
		{
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}

}