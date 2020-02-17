import java.awt.Color;
import java.util.Arrays;

//import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

   private Picture picture;
   private double[][] energies;
   private int width;
   private int height;
   private final double maxEnergy = 1000.0;
   private double energy;
   double[][] finalarr;
   int edgeto[][];
   double total[][];
   private Color[][] colors;

// create a seam carver object based on the given picture
   public SeamCarver(Picture picture) {
	   this.picture = picture;
	   this.width = picture.width();
	   this.height = picture.height();
	   this.edgeto = new int[width][height];
	   this.total = new double[width][height];
	   this.energies = new double[width][height];
	   this.colors = new Color[width][height];
	   for (int i = 0; i < width; i++) {
		   for (int j = 0; j < height; j++) {
			   energies[i][j] = energy(i,j);
		   }
	   }
	   for(int i = 0;i < width;i++) {
			for(int j = 0;j < height;j++) {
				colors[i][j] = picture.get(i, j);
			}
		}
   }

   // current picture
   public Picture picture() {
	return picture;
	   
   }

   // width of current picture
   public int width() {
	return picture.width();
	   
   }

   // height of current picture
   public int height() {
	return picture.height();
	   
   }

   // energy of pixel at column x and row y
   public double energy(int x, int y) {
	   if(x < 0 || x > (width - 1) || y < 0 || y > (height - 1))
			throw new IndexOutOfBoundsException();
		else if(x == 0)
			return maxEnergy;
		else if(x == width - 1)
			return maxEnergy;
		else if(y == 0)
			return maxEnergy;
		else if(y == height - 1)
			return maxEnergy;
		else {
		   double Rx = picture.get(x + 1, y).getRed() -  picture.get(x - 1, y).getRed();
		   double Gx = picture.get(x + 1, y).getGreen() -  picture.get(x - 1, y).getGreen();
		   double Bx = picture.get(x + 1, y).getBlue() -  picture.get(x - 1, y).getBlue();
		   double Ry = picture.get(x, y + 1).getRed() -  picture.get(x, y - 1).getRed();
		   double Gy = picture.get(x, y + 1).getGreen() -  picture.get(x, y - 1).getGreen();
		   double By = picture.get(x, y + 1).getBlue() -  picture.get(x, y - 1).getBlue();
		   energy = Math.sqrt(((Rx * Rx) + (Gx * Gx) + (Bx * Bx)) + ((Ry * Ry) + (Gy * Gy) + (By * By)));
		}
	return energy;
	   
   }
   
   // sequence of indices for horizontal seam
   public int[] findHorizontalSeam() {
			double[][] copyEnergies = new double[width][height];
			for(int i = 0;i < width;i++) {
				for(int j = 0;j < height;j++) {
					copyEnergies[i][j] = energies[i][j];
				}
			}
			energies = new double[height][width];
			for(int i = 0;i < width;i++) {
				for(int j = 0;j < height;j++) {
					energies[j][i] =  copyEnergies[i][j];
				}
			}
			int temp = width;
			width = height;
			height = temp;
			int[] result = findVerticalSeam();
			energies = copyEnergies;
			temp = width;
			width = height;
			height = temp;
			return result;
		}
   
   // sequence of indices for vertical seam
   public int[] findVerticalSeam() {
			int edgeto[][] = new int[width][height];
			double total[][] = new double[width][height];
			for(int i = 0;i < width;i++) {
				total[i][0] = maxEnergy;
			}
			for(int j = 0;j < (height-1);j++) {
				for(int i = 0;i < width;i++) {
					if(i == 0) {
						if(total[i][j] <= total[i+1][j]) {
							total[i][j+1] = total[i][j] + energies[i][j+1];
							edgeto[i][j+1] = 0;
						} else {
							total[i][j+1] = total[i+1][j] + energies[i][j+1];
							edgeto[i][j+1] = 1;
						}
					}
					
					else if(i == (width - 1) ) {
						if(total[i-1][j] <= total[i][j]) {
							total[i][j+1] = total[i-1][j] + energies[i][j+1];
							edgeto[i][j+1] = -1;
						} else {
							total[i][j+1] = total[i][j] + energies[i][j+1];
							edgeto[i][j+1] = 0;
						}
					}
					
					else {
						int index = maxInThreeNum(total[i-1][j], total[i][j], total[i+1][j]);
						edgeto[i][j+1] = index;
						total[i][j+1] = total[i+index][j] + energies[i][j+1];
					}
				}
			}
			System.out.println(Arrays.toString(edgeto[2]));
			int index = 0;
			double minEnergy = total[0][height-1];
			for(int i = 1;i < width;i++) {
				if(total[i][height-1] < minEnergy ) {
					index = i;
					minEnergy = total[i][height-1];
				}
			}
			int[] path = new int[height];
			for(int j = height-1; j>= 0;j--) {
				path[j] = index;
				int key = edgeto[index][j];
				index = index+key;
			}
			
			return path;
		}
   private int maxInThreeNum(double a, double b, double c)
	{
		if(a <= b && a <= c)
			return -1;
		else if (b <= a && b <= c)
			return 0;
		return 1;
	}
   
   // remove horizontal seam from current picture
   public void removeHorizontalSeam(int[] seam) {
	   double[][] copyEnergies = new double[width][height];
		Color[][] copyColors = new Color[width][height];
		for(int i = 0;i < width;i++) {
			for(int j = 0;j < height;j++) {
				copyEnergies[i][j] = energies[i][j];
				copyColors[i][j] = colors[i][j];
			}
		}
		energies = new double[height][width];
		colors = new Color[height][width];
		for(int i = 0;i < width;i++) {
			for(int j = 0;j < height;j++) {
				energies[j][i] =  copyEnergies[i][j];
				colors[j][i] = copyColors[i][j];
			}
		}
		
		int temp = width;
		width = height;
		height = temp;
		removeVerticalSeam(seam);
		temp = width;
		width = height;
		height = temp;
		copyEnergies = energies;
		copyColors = colors;
		energies = new double[width][height];
		colors = new Color[width][height];
		for(int i = 0;i < height;i++) {
			for(int j = 0;j < width;j++) {
				energies[j][i] = copyEnergies[i][j];
				colors[j][i] = copyColors[i][j];
			}
		}
	}

   // remove vertical seam from current picture
   public void removeVerticalSeam(int[] seam) {
	   for(int j = 0;j < height;j++) {
			for(int n = seam[j]; n < (width-1); n++) {
				colors[n][j] = colors[n+1][j];
				energies[n][j] = energies[n+1][j];
			}
		}
		width = width-1;
		for(int i = 0;i < height;i++) {
			if(seam[i] == 0) {
				energies[0][i] = energy(0,i);
			} else if(seam[i] == width ) {
				energies[seam[i]-1][i] = energy(seam[i]-1,i);
			} else {
				energies[seam[i]-1][i] = energy(seam[i]-1,i);
				energies[seam[i]][i] = energy(seam[i],i);
			}
		}
	}
	

   //  unit testing (optional)
   public static void main(String[] args) {
	   Picture p = new Picture("file:///C:/Users/Divya/Downloads/seam/4x6.png");
	   SeamCarver s = new SeamCarver(p);
	   System.out.println(Arrays.toString(s.findHorizontalSeam()));
   }

}
