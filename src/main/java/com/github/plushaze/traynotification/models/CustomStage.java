package com.github.plushaze.traynotification.models;

import com.github.plushaze.traynotification.notification.Position;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomStage extends Stage {

    private Location location;
    private double opa;

    public CustomStage(AnchorPane ap, StageStyle style, Position position) {
        initStyle(style);

        setSize(ap.getPrefWidth(), ap.getPrefHeight());

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double x, y;

        switch (position) {
            case BOTTOM_LEFT:
                x = screenBounds.getMinX() + 2;
                y = screenBounds.getMinY() + screenBounds.getHeight() - ap.getPrefHeight() - 2;
                break;
            case BOTTOM_MID:
                x = screenBounds.getMinX() + (screenBounds.getWidth() - ap.getPrefWidth() - 1)/2;
                y = screenBounds.getMinY() + screenBounds.getHeight() - ap.getPrefHeight() - 2;
                break;
            case BOTTOM_RIGHT:
                x = screenBounds.getMinX() + screenBounds.getWidth() - ap.getPrefWidth() - 2;
                y = screenBounds.getMinY() + screenBounds.getHeight() - ap.getPrefHeight() - 2;
                break;
            case CENTER:
                x = screenBounds.getMinX() + (screenBounds.getWidth() - ap.getPrefWidth() - 1)/2;
                y = screenBounds.getMinY() + (screenBounds.getHeight() - ap.getPrefHeight() - 2)/2;
                break;
            case MID_LEFT:
                x = screenBounds.getMinX() + 2;
                y = screenBounds.getMinY() + (screenBounds.getHeight() - ap.getPrefHeight() - 2)/2;
                break;
            case MID_RIGHT:
                x = screenBounds.getMinX() + screenBounds.getWidth() - ap.getPrefWidth() - 2;
                y = screenBounds.getMinY() + (screenBounds.getHeight() - ap.getPrefHeight() - 2)/2;
                break;
            case TOP_LEFT:
                x = screenBounds.getMinX() + 2;
                y = screenBounds.getMinY() + 2;
                break;
            case TOP_MID:
                x = screenBounds.getMinX() + (screenBounds.getWidth() - ap.getPrefWidth() - 1)/2;
                y = screenBounds.getMinY() + 2;
                break;
            case TOP_RIGHT:
            default:
                x = screenBounds.getMinX() + screenBounds.getWidth() - ap.getPrefWidth() - 2;
                y = screenBounds.getMinY() + 2;
                break;
        }

        location = Location.at(x, y);
    }

    public Location getLocation() {

        return location;
    }

    public void setSize(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public Location getOffScreenBounds() {
        Location loc = getLocation();

        return Location.at(loc.getX() + this.getWidth(), loc.getY());
    }

    public void setLocation(Location loc) {
        setX(loc.getX());
        setY(loc.getY());
    }

    private SimpleDoubleProperty xLocationProperty = new SimpleDoubleProperty() {
        @Override
        public void set(double newValue) {
            setX(newValue);
        }

        @Override
        public double get() {
            return getX();
        }
    };

    public SimpleDoubleProperty xLocationProperty() {
        return xLocationProperty;
    }

    private SimpleDoubleProperty yLocationProperty = new SimpleDoubleProperty() {
        @Override
        public void set(double newValue) {
            setY(newValue);
        }

        @Override
        public double get() {
            return getY();
        }
    };

    public SimpleDoubleProperty yLocationProperty() {
        return yLocationProperty;
    }

    public double getOpa() {
        return opa;
    }

    public void setOpa(double opa) {
        this.opa = opa;
    }

}
