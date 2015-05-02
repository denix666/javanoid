package javanoid;

public class Level {
    String[] a,b,c,d,e,f,g,h,i;

    public Level(int Level) {
        int numOfLevels = 3;
        
        a = new String[numOfLevels];
        b = new String[numOfLevels];
        c = new String[numOfLevels];
        d = new String[numOfLevels];
        e = new String[numOfLevels];
        f = new String[numOfLevels];
        g = new String[numOfLevels];
        h = new String[numOfLevels];
        i = new String[numOfLevels];
        
        // Level 1
        a[0] = "rrrrrrrrrrrr";
        b[0] = "ryybbyybbyyr";
        c[0] = "rcoocoocoocr";
        d[0] = "roppoppoppor";
        e[0] = "rcoocoocoocr";
        f[0] = "ryybbyybbyyr";
        g[0] = "rrrrrrrrrrrr";
        h[0] = "oooooooooooo";
        i[0] = "rrrrrrrrrrrr";
        
        // Level 2
        a[1] = "oooooooooooo";
        b[1] = "ooppoppoppoo";
        c[1] = "yyyyyyyyyyyy";
        d[1] = "gggggggggggg";
        e[1] = "rrrrrrrrrrrr";
        f[1] = "oooooooooooo";
        g[1] = "cccccccccccc";
        h[0] = "oooooooooooo";
        i[0] = "rrrrrrrrrrrr";
    }
}
