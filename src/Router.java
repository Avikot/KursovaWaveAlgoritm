public class Router {
    int[][] MapM;
    int Moves;
    int XE;
    int YE;
    public Router(int [][] mas, int moves, int XE, int YE){
        this.MapM = mas;
        this.Moves = moves;
        this.XE = XE;
        this.YE = YE;
    }
    public void createRoute(){
        int k = XE;
        int l = YE;
        for(int i = 0; i < Moves + 1; i++){
            MapM[k][l] = -MapM[k][l];
            if((Math.abs(MapM[k][l]) - MapM[k + 1][l]) == 1) {
                k = k + 1;
            } else if((Math.abs(MapM[k][l]) - MapM[k][l + 1]) == 1){
                l = l + 1;
            } else if((Math.abs(MapM[k][l]) - MapM[k - 1][l]) == 1){
                k = k - 1;
            } else if((Math.abs(MapM[k][l]) - MapM[k][l - 1]) == 1){
                l = l - 1;
            }
        }
        System.out.println("Revers move of wave algoritm: ");
    }
}
