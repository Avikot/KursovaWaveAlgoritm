import java.util.Date;

public class WaveAlgoritm {

    private int XS, YS, XE, YE;
    private int X, Y, I;
    int Map[][] = new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},

    };
    private int[][] MapM = new int[15][15];
    private int Moves;

    public WaveAlgoritm(int[][] map, int xs, int ys, int xe, int ye){
        this.Map = map;
        this.XS = xs;
        this.YS = ys;
        this.XE = xe;
        this.YE = ye;

        if ((Map[XS][YS] == 1) || (Map[XE][YE]) == 1){
            System.err.println("Error! Try again!");
            return;
        }

        MapM[XS][YS] = 1;
        I = 1;

        do{
            I = I + 1;
            for (X = 0; X < Map.length; X++) {
                for (Y = 0; Y < Map[0].length; Y++){
                    if (MapM[X][Y] == (I - 1)){
                        if ((Y < (Map[0].length - 1)) && (MapM[X][Y + 1] == 0) && (Map[X][Y + 1] == 0)){MapM[X][Y + 1] = I;}
                        if ((Y > 0) && (MapM[X][Y - 1] == 0) && (Map[X][Y - 1] == 0)){MapM[X][Y - 1] = I;}
                        if ((X < (Map.length - 1)) && (MapM[X + 1][Y] == 0) && (Map[X + 1][Y] == 0)){MapM[X + 1][Y] = I;}
                        if ((X > 0) && (MapM[X - 1][Y] == 0) && (Map[X - 1][Y] == 0)){MapM[X - 1][Y] = I;}
                    }
                    if (I == 1000){
                        System.err.println("You can't go there!");
                        Moves = 0;
                        return;
                    }
                }
            }
        }while(MapM[XE][YE] == 0);
        Moves = I - 1;
    }

    public void showTablesToOut(){
        MapM[XS][YS] = -MapM[XS][YS];
        MapM[XE][YE] = -MapM[XE][YE];
        System.out.println("Total: " + Moves + " moves; time: " + new Date());
        for (X = 0; X < MapM.length; X++){
            StringBuilder builder = new StringBuilder();
            for (Y = 0; Y < MapM[0].length; Y++){
                builder.append(MapM[X][Y]);
                if ((MapM[X][Y] < 0) && (MapM[X][Y] > -10)){
                    builder.append("   ");
                } else if ((MapM[X][Y] <= -10) && (MapM[X][Y] > -100)){
                    builder.append("  ");
                } else if ((MapM[X][Y] <= -100) && (MapM[X][Y] > -1000)){
                    builder.append(" ");
                } else if (MapM[X][Y] < 10){
                    builder.append("    ");
                } else if (MapM[X][Y] < 100){
                    builder.append("   ");
                } else if (MapM[X][Y] < 1000){
                    builder.append("  ");
                }
            }
            System.out.println(builder);
        }
    }

    public int getXE() {
        return XE;
    }

    public void setXE(int XE) {
        this.XE = XE;
    }

    public int getYE() {
        return YE;
    }

    public void setYE(int YE) {
        this.YE = YE;
    }

    public int getMoves() {
        return Moves;
    }

    public void setMoves(int moves) {
        Moves = moves;
    }

    public int[][] getMapM() {
        return MapM;
    }

    public void setMapM(int[][] mapM) {
        MapM = mapM;
    }
}
