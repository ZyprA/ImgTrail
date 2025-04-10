package net.zypr.imgTrail;


import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Trail {
    private final List<LocMap> locMapList;
    private int width;
    private int height;

    public Trail(String url) {
        this.locMapList = new ArrayList<>();
        this.height = 0;
        this.width = 0;
        try {
            BufferedImage image = ImageIO.read(new URL(url));
            this.width = image.getWidth();
            this.height = image.getHeight();

            for (int x = 0; x < this.width; x++) {
                for (int y = 0; y < this.height; y++) {
                    int rgb = image.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    if (red == 0 && green == 0 && blue == 0) {
                        continue;
                    }
                    Color color = Color.fromRGB(red, green, blue);
                    locMapList.add(new LocMap(x, y, color));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<LocMap> getLocMapList() {
        return locMapList;
    }

    public void display(Player player) {
        Location playerLocation = player.getLocation();
        World world = player.getWorld();

        int stepX = 15; // 画像サイズによってスキップ間隔を調整
        int stepY = 15;
        double scale = 0.01; // スケールを調整

        for (LocMap locMap : locMapList) {
            int x = locMap.getX();
            int y = locMap.getY();

            if (x % stepX != 0 || y % stepY != 0) {
                continue; // 間引いて負荷を軽減
            }

            Color color = locMap.getColor();
            double calculatedX = playerLocation.getX() + (x - (double) width / 2) * scale;
            double calculatedY = playerLocation.getY();
            double calculatedZ = playerLocation.getZ() + (y - (double) height / 2) * scale;
            Particle.DustOptions dustOptions = new Particle.DustOptions(org.bukkit.Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue()), 1);
            world.spawnParticle(Particle.DUST, calculatedX, calculatedY, calculatedZ, 1, dustOptions);
        }
    }

}
