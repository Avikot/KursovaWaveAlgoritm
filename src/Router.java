public class Router {
    int[][] MapM;
    int Moves;
    int XE;
    int YE;
    public Router(int [][] mas, int move, int K, int L){
        this.MapM = mas;
        this.Moves = move;
        this.XE = K;
        this.YE = L;
    }
    public void createRoute(){
        int k = XE;
        int l = YE;
        MapM[k][l] = -MapM[k][l];
        for(int i = Moves; i > 0; i--){
            if((Math.abs(MapM[k][l]) > MapM[k + 1][l]) && (MapM[k + 1][l] != 0)) {
                MapM[k + 1][l] = -MapM[k + 1][l];
                k = k+1;
            } else if(Math.abs(MapM[k][l]) > MapM[k][l + 1] && (MapM[k][l + 1] != 0)){
                MapM[k][l + 1] = -MapM[k][l + 1];
                l = l + 1;
            } else if(Math.abs(MapM[k][l]) > MapM[k - 1][l] && (MapM[k - 1][l] != 0)){
                MapM[k - 1][l] = -MapM[k - 1][l];
                k = k -1;
            } else if(Math.abs(MapM[k][l]) > MapM[k][l - 1] && (MapM[k][l - 1] != 0)){
                MapM[k][l - 1] = -MapM[k][l - 1];
                l = l - 1;
            }
        }
    }
}
