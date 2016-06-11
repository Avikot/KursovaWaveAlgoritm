import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Wave {

    private static int XS;
    private static int YS;
    private static int XE;
    private static int YE;
    private static int X, Y, I;
    private static int[][] Map = new int[15][15];
    static {
        Map = new int[][]{
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1},
                {0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1},
                {1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
        };
    }
    static int[][] MapW = new int[15][15];


    private static int Moves;
    public static void calculateRoute(){
            if ((Map[XS][YS] == 1) || (Map[XE][YE]) == 1) {
                System.err.println("Error! Try again!");
                return;
            }

            MapW[XS][YS] = 1;
            I = 1;

            do {
                I = I + 1;
                for (X = 0; X < Map.length; X++) {
                    for (Y = 0; Y < Map[0].length; Y++) {
                        if (MapW[X][Y] == (I - 1)) {
                            if ((Y < (Map[0].length - 1)) && (MapW[X][Y + 1] == 0) && (Map[X][Y + 1] == 0)) {
                                MapW[X][Y + 1] = I;
                            }
                            if ((Y > 0) && (MapW[X][Y - 1] == 0) && (Map[X][Y - 1] == 0)) {
                                MapW[X][Y - 1] = I;
                            }
                            if ((X < (Map.length - 1)) && (MapW[X + 1][Y] == 0) && (Map[X + 1][Y] == 0)) {
                                MapW[X + 1][Y] = I;
                            }
                            if ((X > 0) && (MapW[X - 1][Y] == 0) && (Map[X - 1][Y] == 0)) {
                                MapW[X - 1][Y] = I;
                            }
                        }
                        if (I == 1000) {
                            System.err.println("You can't go there!");
                            Moves = 0;
                            return;
                        }
                    }
                }
            } while (MapW[XE][YE] == 0);
            Moves = I - 1;

        int k = XE;
        int l = YE;
        for (int i = 0; i < Moves; i++) {
            MapW[k][l] = -MapW[k][l];
            if (k + 1 < Map.length && (Math.abs(MapW[k][l]) - MapW[k + 1][l]) == 1) {
                k = k + 1;
            } else if (l + 1 < Map[0].length && (Math.abs(MapW[k][l]) - MapW[k][l + 1]) == 1) {
                l = l + 1;
            } else if (k - 1 >= 0 && (Math.abs(MapW[k][l]) - MapW[k - 1][l]) == 1) {
                k = k - 1;
            } else if (l - 1 >= 0 && (Math.abs(MapW[k][l]) - MapW[k][l - 1]) == 1) {
                l = l - 1;
            }
        }
        System.out.println("Total: " + Moves + " moves; time: " + new Date());
        for (X = 0; X < MapW.length; X++) {
            StringBuilder builder = new StringBuilder();
            for (Y = 0; Y < MapW[0].length; Y++) {
                builder.append(MapW[X][Y]);
                if ((MapW[X][Y] < 0) && (MapW[X][Y] > -10)) {
                    builder.append("   ");
                } else if ((MapW[X][Y] <= -10) && (MapW[X][Y] > -100)) {
                    builder.append("  ");
                } else if ((MapW[X][Y] <= -100) && (MapW[X][Y] > -1000)) {
                    builder.append(" ");
                } else if (MapW[X][Y] < 10) {
                    builder.append("    ");
                } else if (MapW[X][Y] < 100) {
                    builder.append("   ");
                } else if (MapW[X][Y] < 1000) {
                    builder.append("  ");
                }
            }
            System.out.println(builder);
        }

    }
    private final static java.util.Map<Integer, Integer> lab = new HashMap<>();

    public static Map<Integer, Integer> creatLab() {
        int key = 0;
        for (int i = 0; i < 15; i ++){
            for (int j = 0; j < 15; j++){
                lab.put(key, MapW[i][j]);
                key += 1;
            }
        }
        return lab;
    }



    public static void setXS(int XS) {
        Wave.XS = XS;
    }

    public static void setYS(int YS) {
        Wave.YS = YS;
    }

    public static void setXE(int XE) {
        Wave.XE = XE;
    }

    public static void setYE(int YE) {
        Wave.YE = YE;
    }

    public static int getMoves() {
        return Moves;
    }

    public static java.util.Map<Integer, Integer> getLab() {
        return lab;
    }
}
