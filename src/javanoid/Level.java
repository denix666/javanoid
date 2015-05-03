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
        a[0] = "rrrrrrrrrrrrrrr";
        b[0] = "bbbbbbbbbbbbbbb";
        c[0] = "yyyyyyyyyyyyyyy";
        d[0] = "rrrrrrrrrrrrrrr";
        e[0] = "ppppppppppppppp";
        f[0] = "ccccccccccccccc";
        g[0] = "rrrrrrrrrrrrrrr";
        h[0] = "yyyyyyyyyyyyyyy";
        i[0] = "bbbbbbbbbbbbbbb";
        
        // Level 2
        a[1] = "oooooooooooobyy";
        b[1] = "ooppoppoppoobyy";
        c[1] = "yyyyyyyyyyyybyy";
        d[1] = "ggggggggggggbyy";
        e[1] = "rrrrrrrrrrrrbyy";
        f[1] = "oooooooooooobyy";
        g[1] = "ccccccccccccbyy";
        h[1] = "oooooooooooobyy";
        i[1] = "rrrrrrrrrrrrbyy";
        
        // Level 3
        a[2] = "ocoocoocoocobyy";
        b[2] = "ocoocooooocobyy";
        c[2] = "occccoocoocobyy";
        d[2] = "ocoocoocoooobyy";
        e[2] = "ocoocoocoocobyy";
        f[2] = "oooooooooooobyy";
        g[2] = "oooooooooooobyy";
        h[2] = "oooooooooooobyy";
        i[2] = "oooooooooooobyy";
    }
}
