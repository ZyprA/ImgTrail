package net.zypr.imgTrail;

import org.bukkit.Color;

public class LocMap {
    private final int x;
    private final int y;
    private final Color color;

    public LocMap(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
}
