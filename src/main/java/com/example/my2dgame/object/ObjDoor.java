package com.example.my2dgame.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjDoor extends SuperObject{
    public ObjDoor() {
        name = "door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
