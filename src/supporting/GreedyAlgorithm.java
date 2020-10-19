package supporting;

import java.util.ArrayDeque;
import java.util.Queue;

public class GreedyAlgorithm implements SearchPath{
	private int[][] h;
	private Point goal;
	private Point start;
	private int m;
	private int n;
	Queue<Point> result = new ArrayDeque<Point>();
	
	public GreedyAlgorithm(short[][] arr,Point start,Point goal) {
		this.goal = goal;
		this.start = start;
		m = arr.length;
		n = arr[1].length;
		h = new int[m][n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j]==0) h[i][j] = heuristic(i,j,goal.y,goal.x);
				else h[i][j] = Integer.MAX_VALUE;
			}
		}
		greed();
	}
	
	private void greed() {
		Point curPoint = new Point(start.x,start.y);
		result.add(curPoint);
		int tr = 0;
		while((curPoint.x!=goal.x || curPoint.y!=goal.y) && tr<15) {
			curPoint = findMinCoords(curPoint.x,curPoint.y);
			result.add(curPoint);
			tr++;
		}
	}
	
	private Point findMinCoords(int x,int y) {
		int minH = h[y][x];
		int minX = x;
		int minY = y;
		if(y+1<n)
			if(h[y+1][x]<minH) {
				minY = y+1;
				minH =h[y+1][x];
			}
		if(y-1>=0)
			if(h[y-1][x]<minH) {
				minY = y-1;
				minH =h[y-1][x];
			}
		if(x+1<m)
			if(h[y][x+1]<minH) {
				minX = x+1;
				minH =h[y][x+1];
			}
		if(x-1>=0)
			if(h[y][x-1]<minH) {
				minX = x-1;
				minH =h[y][x-1];
			}
		return new Point(minX,minY);
	}
	private int heuristic(int x1, int y1,int x2, int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}

	@Override
	public Point getNextVisualPoint() {
		return result.poll();
	}

	@Override
	public int getCountStepsToFind() {
		return result.size();
	}

	@Override
	public int getCountStepsFromStartToFinish() {
		return 0;
	}
}
