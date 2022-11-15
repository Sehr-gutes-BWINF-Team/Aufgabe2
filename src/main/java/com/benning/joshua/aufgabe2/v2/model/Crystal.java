package com.benning.joshua.aufgabe2.v2.model;

import lombok.Data;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

@Data
public class Crystal {

    //private final Color color;

    private final float[] speeds;
    private final Position globalPos;

    // Position of corners relative to global pos
    private Position[] corners;

    public Crystal(float speedN, float speedS, float speedW, float speedE, Position globalPos) {
        this.globalPos = globalPos;
        this.speeds = new float[]{speedN, speedS, speedW, speedE};

        this.corners = new Position[]{
                new Position(0, 1),
                new Position(1, 0),
                new Position(0, -1),
                new Position(-1, 0)};
    }

    public void grow() {
        corners[0].addY(+this.speeds[0]);
        corners[1].addX(+this.speeds[1]);
        corners[2].addY(-this.speeds[2]);
        corners[3].addX(-this.speeds[3]);
    }

    public void draw(Graphics2D graphics2D) {
        drawOutline(graphics2D);
    }

    private void drawOutline(Graphics2D graphics) {
        //graphics.setColor(this.color);
        graphics.translate(globalPos.getX(), globalPos.getY());
        GeneralPath generalPath = new GeneralPath(Path2D.WIND_EVEN_ODD, 4);
        generalPath.moveTo(corners[0].getX(), corners[0].getY());
        generalPath.lineTo(corners[1].getX(), corners[1].getY());
        generalPath.lineTo(corners[2].getX(), corners[2].getY());
        generalPath.lineTo(corners[3].getX(), corners[3].getY());
        generalPath.closePath();
        graphics.fill(generalPath);
    }

    private int getGlobalX(Position pos) {
        return this.globalPos.getX() + pos.getX();
    }
    private int getGlobalY(Position pos) {
        return this.globalPos.getY() + pos.getY();
    }
}
