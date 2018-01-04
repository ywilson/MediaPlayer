package mediaapp;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.stage.FileChooser;

/**
 *
 * @author ywils
 */
public class MediaApp extends Application {
        
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        BorderPane videoPane = new BorderPane();
        BorderPane musicPane = new BorderPane();
       
        Scene mainScene = new Scene(root, 1280, 800);
        Scene vidScene = new Scene(videoPane, 1280, 800);
        Scene musicScene = new Scene(musicPane, 1280, 800);
        
        MediaView music_mv = new MediaView();
        
        MenuBar music_menuBar = new MenuBar();      
        Menu music_menuFile = new Menu("File");
        MenuItem open = new MenuItem("Open File...");       
        open.setOnAction((ActionEvent  t) -> {
            FileChooser m_chooser = new FileChooser();
            File file = m_chooser.showOpenDialog(primaryStage);
            if (file != null) {
                Media music_source = new Media(file.toURI().toString());
                MediaPlayer music_mp = new MediaPlayer(music_source);
                music_mv.setMediaPlayer(music_mp);
                music_mp.play();
                MediaControls mediaControl = new MediaControls(music_mp);
                musicScene.setRoot(mediaControl);
            }
        });
        music_menuFile.getItems().addAll(open);
        
        Menu music_menuEdit = new Menu("Edit");
       
        Menu menuView = new Menu("View");
        MenuItem videoPage = new MenuItem("Video Player");
        videoPage.setOnAction(e -> primaryStage.setScene(vidScene));
        MenuItem mainPage1 = new MenuItem("Main Menu");
        mainPage1.setOnAction(e -> primaryStage.setScene(mainScene));
        menuView.getItems().addAll(videoPage, mainPage1);
        
        
        music_menuBar.getMenus().addAll(music_menuFile, music_menuEdit, menuView);
        music_menuBar.prefWidthProperty().bind(musicPane.widthProperty());

        
        MenuBar video_menuBar = new MenuBar();       
        Menu video_menuFile = new Menu("File");
        
        MediaView video_mv = new MediaView();        
        
        MenuItem video_open = new MenuItem("Open File...");       
        video_open.setOnAction((ActionEvent  t) -> {
            FileChooser v_chooser = new FileChooser();
            File file = v_chooser.showOpenDialog(primaryStage);
            if (file != null) {
                Media video_source = new Media(file.toURI().toString());
                MediaPlayer video_mp = new MediaPlayer(video_source);
                video_mv.setMediaPlayer(video_mp);
                video_mp.play();
                MediaControls mediaControl = new MediaControls(video_mp);
                vidScene.setRoot(mediaControl);
            }
              
        });
        video_menuFile.getItems().addAll(video_open);
        
        Menu video_menuEdit = new Menu("Edit");
        
        Menu video_menuView = new Menu("View");
        MenuItem musicPage = new MenuItem("Music Player");
        musicPage.setOnAction(e -> primaryStage.setScene(musicScene));
        MenuItem mainPage = new MenuItem("Main Menu");
        mainPage.setOnAction(e -> primaryStage.setScene(mainScene));
        video_menuView.getItems().addAll(musicPage, mainPage);
        
        video_menuBar.getMenus().addAll(video_menuFile, video_menuEdit, video_menuView);
        video_menuBar.prefWidthProperty().bind(videoPane.widthProperty());

        musicPane.setTop(music_menuBar);
        videoPane.setTop(video_menuBar);
        
          
        Button musicButton = new Button("Play Music");
        musicButton.setOnAction(e -> primaryStage.setScene(musicScene));
        
        Button videoButton = new Button("Play Video");
        videoButton.setOnAction(e -> primaryStage.setScene(vidScene));

        musicButton.setLayoutX(250);
        musicButton.setLayoutY(150);
        
        videoButton.setLayoutX(250);
        videoButton.setLayoutY(250); 
        
        root.getChildren().add(musicButton);
        root.getChildren().add(videoButton);
        

        primaryStage.setScene(mainScene);
        root.getStylesheets().add
                (MediaApp.class.getResource("mediaapp.css").toExternalForm());
        musicPane.getStylesheets().add
                (MediaApp.class.getResource("MusicSheet.css").toExternalForm());
        videoPane.getStylesheets().add
                (MediaApp.class.getResource("VideoSheet.css").toExternalForm());
        primaryStage.setTitle("Media Player");
        primaryStage.show();    
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
    
}
