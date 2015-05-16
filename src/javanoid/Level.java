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
        a[0] = "ooooooooooooooo";
        b[0] = "ooooooooooooooo";
        c[0] = "ooooooooooooooo";
        d[0] = "ooooooooooooooo";
        e[0] = "ooooooooooooooo";
        f[0] = "ooooooooooooooo";
        g[0] = "ooooooooooooooo";
        h[0] = "ooooooooooooooo";
        i[0] = "ooooooooooooooo";
        
        // Level 1
        a[1] = "ooooooooooooooo";
        b[1] = "ooooooooooooooo";
        c[1] = "ooooooooororooo";
        d[1] = "ooooooooooooooo";
        e[1] = "ooooooooooooooo";
        f[1] = "ooooooooooooooo";
        g[1] = "ooooooooooooooo";
        h[1] = "ooooooooooooooo";
        i[1] = "ooooooooooooooo";
        
        // Level 2
        a[2] = "ooooooooooooooo";
        b[2] = "ooorooooooooooo";
        c[2] = "ooocooooooooooo";
        d[2] = "oooooooyooooooo";
        e[2] = "ooooooooorooooo";
        f[2] = "ooopooooooooooo";
        g[2] = "oooooooooooogoo";
        h[2] = "ooooooooooooooo";
        i[2] = "ooooooooooooooo";
        
        // Level 3
        a[3] = "ooooooooooooooo";
        b[3] = "ooooooooooooooo";
        c[3] = "ooooooooooogooo";
        d[3] = "ooooooooooooooo";
        e[3] = "ooooooroooooooo";
        f[3] = "ooooooooooooooo";
        g[3] = "oooooooocoooooo";
        h[3] = "ooooooooooooooo";
        i[3] = "ooooooooooooooo";
        
        // Level 4
        a[4] = "ooooooooooooooo";
        b[4] = "ooooooooooooooo";
        c[4] = "ooooooooooogooo";
        d[4] = "ooooooooooooooo";
        e[4] = "ooooooroooooooo";
        f[4] = "ooooooooooooooo";
        g[4] = "oooooooocoooooo";
        h[4] = "ooooooooooooooo";
        i[4] = "ooooooooooooooo";
        
        // Level 5
        a[5] = "ooooooyoooooooo";
        b[5] = "oooroooooyooooo";
        c[5] = "ooooooooooogooo";
        d[5] = "ooooooooooooooo";
        e[5] = "ooyooorooyooooo";
        f[5] = "ooooooooooooooo";
        g[5] = "ooroooooroooooo";
        h[5] = "ooooyoooooooooo";
        i[5] = "oooooooooooooro";
    }
}