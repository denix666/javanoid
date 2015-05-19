package javanoid;

public class Level {
    String[] a,b,c,d,e,f,g,h,i;

    public Level(int Level) {
        int numOfLevels = 6;
        
        a = new String[numOfLevels];
        b = new String[numOfLevels];
        c = new String[numOfLevels];
        d = new String[numOfLevels];
        e = new String[numOfLevels];
        f = new String[numOfLevels];
        g = new String[numOfLevels];
        h = new String[numOfLevels];
        i = new String[numOfLevels];
        
        // Level 0 - not in use
        a[0] = "oooooooooooooo";
        b[0] = "oooooooooooooo";
        c[0] = "oooooooooooooo";
        d[0] = "oooooooooooooo";
        e[0] = "oooooooooooooo";
        f[0] = "oooooooooooooo";
        g[0] = "oooooooooooooo";
        h[0] = "oooooooooooooo";
        i[0] = "oooooooooooooo";
        
        // Level 1
        a[1] = "oooooooooooooo";
        b[1] = "oooooooooooooo";
        c[1] = "oooooooooooroo";
        d[1] = "oooooooooooooo";
        e[1] = "oooooooooooooo";
        f[1] = "oooooooooooooo";
        g[1] = "oooooooooooooo";
        h[1] = "oooooooooooooo";
        i[1] = "oooooooooooooo";
        
        // Level 2
        a[2] = "rrrrrrrrrrrrrr";
        b[2] = "bbbbbbbbbbbbbb";
        c[2] = "oooooooooooooo";
        d[2] = "oooooooooooooo";
        e[2] = "oooooooooooooo";
        f[2] = "oooooooooooooo";
        g[2] = "oooooooooooooo";
        h[2] = "oooooooooooooo";
        i[2] = "oooooooooooooo";
        
        // Level 3
        a[3] = "cccccccccccccc";
        b[3] = "bbbbbbbbbbbbbb";
        c[3] = "gggggggggggggg";
        d[3] = "rrrrrrrrrrrrrr";
        e[3] = "oooooooooooooo";
        f[3] = "oooooooooooooo";
        g[3] = "oooooooooooooo";
        h[3] = "oooooooooooooo";
        i[3] = "oooooooooooooo";
        
        // Level 4
        a[4] = "rrrrrrrrrrrrrr";
        b[4] = "rrrrrrrrrrrrrr";
        c[4] = "gggggggggggggg";
        d[4] = "gggggggggggggg";
        e[4] = "cccccccccccccc";
        f[4] = "cccccccccccccc";
        g[4] = "yyyyyyyyyyyyyy";
        h[4] = "pppppppppppppp";
        i[4] = "cccccccccccccc";
        
        // Level 5
        a[5] = "cccccccccccccc";
        b[5] = "cccccccccccccc";
        c[5] = "cccccccccccccc";
        d[5] = "gggggggggggggg";
        e[5] = "gggggggggggggg";
        f[5] = "gggggggggggggg";
        g[5] = "rrrrrrrrrrrrrr";
        h[5] = "rrrrrrrrrrrrrr";
        i[5] = "rrrrrrrrrrrrrr";
    }
}