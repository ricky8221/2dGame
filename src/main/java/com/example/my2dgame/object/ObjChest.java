package com.example.my2dgame.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjChest extends SuperObject{
    public ObjChest() {
        name = "chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}