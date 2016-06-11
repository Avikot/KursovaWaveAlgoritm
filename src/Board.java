import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Board {


    final static Map<Integer, Integer> board = new HashMap<>();

    public static Map<Integer, Integer> creatBoard() {
        int key = 0;
        for (int i = 0; i < 15; i ++){
            for (int j = 0; j < 15; j++){
                board.put(key, MapS.startMap[i][j]);
                key += 1;
            }
        }
        return board;
    }
    public static void mapBuilder(){
        final StringBuilder builder = new StringBuilder();
        creatBoard();
        System.out.println("Total: "  + " moves; time: " + new Date());
        for (int i = 0; i < 225; i++){
            builder.append(String.format("%3s", board.get(i)));
            if((i + 1) % 15 == 0){
                builder.append("\n");
            }
        }
        System.out.println(builder);

    }

}
