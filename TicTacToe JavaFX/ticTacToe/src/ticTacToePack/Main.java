package ticTacToePack;
	
import java.io.File;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	char turn = 'X';
	int button_row;
	int button_col;
	boolean tieFlag;
	Button buttons[][] = new Button[3][3];
	char clicked [][] = new char [3][3];
	
	String pathX = "x.jpg";
	File x = new File(pathX);
	String pathO = "o.jpg";
	File o = new File(pathO);
	
	Image imageX = new Image(x.toURI().toString(),100, 100, false, false);
    ImageView ivx0 = new ImageView(imageX);
    ImageView ivx1 = new ImageView(imageX);
    ImageView ivx2 = new ImageView(imageX);
    ImageView ivx3 = new ImageView(imageX);
    ImageView ivx4 = new ImageView(imageX);
    ImageView ivx5 = new ImageView(imageX);
    ImageView ivx6 = new ImageView(imageX);
    ImageView ivx7 = new ImageView(imageX);
    ImageView ivx8 = new ImageView(imageX);
    
    Image imageO = new Image(o.toURI().toString(),100, 100, false, false);
    ImageView ivo0 = new ImageView(imageO);
    ImageView ivo1 = new ImageView(imageO);
    ImageView ivo2 = new ImageView(imageO);
    ImageView ivo3 = new ImageView(imageO);
    ImageView ivo4 = new ImageView(imageO);
    ImageView ivo5 = new ImageView(imageO);
    ImageView ivo6 = new ImageView(imageO);
    ImageView ivo7 = new ImageView(imageO);
    ImageView ivo8 = new ImageView(imageO);

	
	@Override
	public void start(Stage primaryStage) {
		try {
			Label label = new Label("Turn: " + turn);
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(50, 50, 50, 50));
			
			for(int i = 0;i < 3;i++){
				for (int j = 0; j < 3;j++){
					clicked[i][j] = 'N';
					buttons[i][j] = new Button();
					buttons[i][j].setMinWidth(100);
					buttons[i][j].setMinHeight(100);
				}
			}
			
			buttons[0][0].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(clicked[0][0] == 'N'){
						if(turn == 'X'){
							clicked[0][0] = 'X';
							buttons[0][0].setGraphic(ivx0);
							if(game_end()){
								stop_game(grid);
							} else
							turn = 'O';
						} else{
							clicked[0][0] = 'O';
							buttons[0][0].setGraphic(ivo0);
							if(game_end()){
								stop_game(grid);
							} else
							turn = 'X';
						}
						label.setText("Turn: " + turn);
					}
				}
			});
			
			buttons[0][1].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(clicked[0][1] == 'N'){
						if(turn == 'X'){
							clicked[0][1] = 'X';
							buttons[0][1].setGraphic(ivx1);
							if(game_end()){
								stop_game(grid);
							} else
							turn = 'O';
						} else{
							clicked[0][1] = 'O';
							buttons[0][1].setGraphic(ivo1);
							if(game_end()){
								stop_game(grid);
							} else
							turn = 'X';
						}
						label.setText("Turn: " + turn);
					}
				}
			});
			
			buttons[0][2].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(clicked[0][2] == 'N'){
						if(turn == 'X'){
							clicked[0][2] = 'X';
							buttons[0][2].setGraphic(ivx2);
							if(game_end()){
								stop_game(grid);
							} else
							turn = 'O';
						} else{
							clicked[0][2] = 'O';
							buttons[0][2].setGraphic(ivo2);
							if(game_end()){
								stop_game(grid);
							} else
							turn = 'X';
						}
						label.setText("Turn: " + turn);
					}
				}
			});
			
			buttons[1][0].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(clicked[1][0] == 'N'){
						if(turn == 'X'){
							clicked[1][0] = 'X';
							buttons[1][0].setGraphic(ivx3);
							if(game_end()){
								stop_game(grid);
							} else
							turn = 'O';
						} else{
							clicked[1][0] = 'O';
							buttons[1][0].setGraphic(ivo3);
							if(game_end()){
								stop_game(grid);
							}else
							turn = 'X';
						}
						label.setText("Turn: " + turn);
					}
				}
			});
			
			buttons[1][1].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(clicked[1][1] == 'N'){
						if(turn == 'X'){
							clicked[1][1] = 'X';
							buttons[1][1].setGraphic(ivx4);
							if(game_end()){
								stop_game(grid);
							}else
							turn = 'O';
						} else{
							clicked[1][1] = 'O';
							buttons[1][1].setGraphic(ivo4);
							if(game_end()){
								stop_game(grid);
							}else
							turn = 'X';
						}
						label.setText("Turn: " + turn);
					}
				}
			});
			
			buttons[1][2].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(clicked[1][2] == 'N'){
						if(turn == 'X'){
							clicked[1][2] = 'X';
							buttons[1][2].setGraphic(ivx5);
							if(game_end()){
								stop_game(grid);
							}else
							turn = 'O';
						} else{
							clicked[1][2] = 'O';
							buttons[1][2].setGraphic(ivo5);
							if(game_end()){
								stop_game(grid);
							}else
							turn = 'X';
						}
						label.setText("Turn: " + turn);
					}
				}
			});
			
			buttons[2][0].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(clicked[2][0] == 'N'){
						if(turn == 'X'){
							clicked[2][0] = 'X';
							buttons[2][0].setGraphic(ivx6);
							if(game_end()){
								stop_game(grid);
							}else
							turn = 'O';
						} else{
							clicked[2][0] = 'O';
							buttons[2][0].setGraphic(ivo6);
							if(game_end()){
								stop_game(grid);
							}else
							turn = 'X';
						}
						label.setText("Turn: " + turn);
					}
				}
			});
			
			buttons[2][1].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(clicked[2][1] == 'N'){
						if(turn == 'X'){
							clicked[2][1] = 'X';
							buttons[2][1].setGraphic(ivx7);
							if(game_end()){
								stop_game(grid);
							}else
							turn = 'O';
						} else{
							clicked[2][1] = 'O';
							buttons[2][1].setGraphic(ivo7);
							if(game_end()){
								stop_game(grid);
							}else
							turn = 'X';
						}
						label.setText("Turn: " + turn);
					}
				}
			});
			
			buttons[2][2].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(clicked[2][2] == 'N'){
						if(turn == 'X'){
							clicked[2][2] = 'X';
							buttons[2][2].setGraphic(ivx8);
							if(game_end()){
								stop_game(grid);
							}else
							turn = 'O';
						} else{
							clicked[2][2] = 'O';
							buttons[2][2].setGraphic(ivo8);
							if(game_end()){
								stop_game(grid);
							}else
							turn = 'X';
						}
						label.setText("Turn: " + turn);
					}
				}
			});
			
			for(int i = 0;i < 3;i++){
				for (int j = 0; j < 3;j++){
					grid.setConstraints(buttons[i][j], j,i);
					grid.getChildren().add(buttons[i][j]);
				}
			}
			
			BorderPane root = new BorderPane();
			root.setCenter(grid);
			
			grid.setConstraints(label,0,4);
			grid.getChildren().add(label);
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean game_end(){
		int count = 0;
		for(int k = 0; k < 3;k++){
			for(int l = 0;l < 3;l++){
				if(clicked[k][l] == turn){
					count++;
					if(count == 3){
						return true;
					} else if(l == 2){
						count = 0;
					}
				} else {
					count = 0;
				}	
			}
		}
		count = 0;
		for(int k = 0; k < 3;k++){
			for(int l = 0;l < 3;l++){
				if(clicked[l][k] == turn){
					count++;
					if(count == 3){
						return true;
					} else if(l == 2){
						count = 0;
					}
				} else {
					count = 0;
				}	
			}
		}
		count = 0;
		for(int i = 0;i< 3;i++){
			
			if(clicked[i][i] == turn){
				count++;
				if(count == 3){
					return true;
				}
			} else {
				count = 0;
			}
		}
		count = 0;
		int k = 0;
		for(int i = 2;i >= 0;i--){
			if(clicked[i][k] == turn){
				count++;
				k++;
				if(count == 3){
					return true;
				}
			} else {
				count = 0;
			}
		}
		count = 0;
		for(int i = 0;i<3;i++){
			for(int j = 0;j<3;j++){
				if(clicked[i][j] == 'X' || clicked[i][j] == 'O'){
					count++;
					if(count == 9){
						tieFlag = true;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void stop_game(GridPane grid){
		for(int i = 0;i < 3;i++){
			for(int j = 0;j < 3;j++){
				clicked[i][j] = 'W';
			}
		}
		Label end = new Label("Draw");
		grid.setConstraints(end, 1,6);
		grid.getChildren().add(end);
		if(tieFlag == false){
		end.setText(turn + " wins congrats!");
		}
		Button reset = new Button("Reset");
		grid.setConstraints(reset,0,6);
		grid.getChildren().add(reset);
		
		reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for(int i = 0;i < 3;i++){
					for (int j = 0; j < 3;j++){
						clicked[i][j] = 'N';
						buttons[i][j].setGraphic(null);;
					}
				}
				turn = 'X';
				tieFlag = false;
				
				grid.getChildren().remove(reset);
				grid.getChildren().remove(end);
			}
		});
	}
	public static void main(String[] args) {
		launch(args);
	}
}
