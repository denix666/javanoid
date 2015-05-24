package javanoid;

/*
    Игра похожая на старый арканоид
    Пишу в свободное время. 
*/

public class Main {
    public static final String version = "JAVANOID v1.0";
    
    public static final int GAME_WINDOW_WIDTH = 805;
    public static final int GAME_WINDOW_HEIGHT = 630;
    
    public static final int GAME_AREA_WIDTH = 790;
    public static final int GAME_AREA_HEIGHT = 620;
    
    public static int curLevel = 1;
    public static int numOfLevels = 5;
    public static int numOfLives = 2;
    public static int score = 0;
    
    
    public static void main(String[] args) {
        new Window(version+" | Level - "+curLevel+" | Lives - "+numOfLives+" | Score - "+score, GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
    }
}
