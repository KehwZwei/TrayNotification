package com.github.plushaze.traynotification;

import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import com.github.plushaze.traynotification.animations.Animations;
import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public final class ReadMeTest extends Application {

    private volatile TrayNotification tray;

    @Override
    public void start(Stage primaryStage) throws Exception {
        TrayNotification notification = new TrayNotification();
        notification.setTitle("Test");
        notification.setMessage("test message.\nnew line.\ttab");
        notification.setNotification(Notifications.NOTICE);
        notification.setAnimation(Animations.FADE);
        notification.setImage(null);
        notification.setOpacity(0.7);
        notification.showAndDismiss(Duration.seconds(3));
    }

    public static void main(String[] args) {
        launch(args);
    }

    // @BeforeClass
    public static void initializeJavaFX() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // initializes JavaFX environment
            latch.countDown();
        });

        latch.await();
    }

    // @AfterClass
    public static void shutdownJavaFX() {
        Platform.exit();
    }

    // @Before
    public void initializeTray() {
        Platform.runLater(() -> tray = new TrayNotification());
    }

    // @Test
    public void creatingANewTrayNotification() {
        String title = "Congratulations sir";
        String message = "You've successfully created your first Tray Notification";
        Notification notification = Notifications.SUCCESS;

        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotification(notification);
        tray.showAndWait();
    }

    // @Test
    public void usingDifferentAnimationsAndNotifications() {
        String title = "Download quota reached";
        String message = "Your download quota has been reached. Panic.";
        Notification notification = Notifications.NOTICE;

        Platform.runLater(() -> {
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotification(notification);
            tray.setAnimation(Animations.FADE);
            tray.showAndWait();
        });
    }

    // @Test
    public void creatingACustomTrayNotification() {
        Image whatsAppImg = new Image(
                "https://cdn4.iconfinder.com/data/icons/iconsimple-logotypes/512/whatsapp-128.png");

        Platform.runLater(() -> {
            tray.setTitle("New WhatsApp Message");
            tray.setMessage("Github - I like your new notification release. Nice one.");
            tray.setRectangleFill(Paint.valueOf("#2A9A84"));
            tray.setAnimation(Animations.POPUP);
            tray.setImage(whatsAppImg);
            tray.showAndDismiss(Duration.seconds(2));
        });
    }

}
