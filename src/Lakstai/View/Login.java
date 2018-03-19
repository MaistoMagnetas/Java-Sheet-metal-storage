package Lakstai.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Lakstai.Vartotojai.User;
import Lakstai.Vartotojai.UsersDao;
import application.Validation;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Login {
	private BorderPane bpRoot;
	private Stage primaryStage;
	private User user;
	
	public Login(Stage primaryStage) throws FileNotFoundException {
		this.primaryStage = primaryStage;
		
		addElementsToScene();
		
		primaryStage.show();
		
	}
	private void addElementsToScene () throws FileNotFoundException{
		
		
		primaryStage.setTitle("Metalo lakštai");
		//Image anotherIcon = new Image("http://goo.gl/kYEQl");
        //primaryStage.getIcons().add(anotherIcon);
		this.bpRoot = new BorderPane();
		Scene scene = new Scene(this.bpRoot,650,300);
		//butinai reikia sitos eilutes kad veiktu css
		
		
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		Label lblUsername = new Label("Prisijungimo vardas:");
		TextField tfUsername = new TextField();
		
		Image loginImage = new Image(new FileInputStream("C:\\Users\\Mindaugas\\Desktop\\loginImage.png"));
		ImageView loginImageView = new ImageView(loginImage);
		loginImageView.setFitHeight(40); 
	    loginImageView.setFitWidth(40); 
		
		Image passwordImage = new Image(new FileInputStream("C:\\\\Users\\\\Mindaugas\\\\Desktop\\\\passwordImage.png"));
		ImageView passwordImageView = new ImageView(passwordImage); 
		passwordImageView.setFitHeight(40); 
	    passwordImageView.setFitWidth(40); 
	      
		tfUsername.setPromptText("Prisijungimo vardas");
		tfUsername.setFocusTraversable(false);
		
		Label lblPassword = new Label("Slaptažodis:");
		PasswordField pfPassword = new PasswordField();
		pfPassword.setPromptText("Slaptažodis");
		pfPassword.setFocusTraversable(false);
		
		Button btnLogin = new Button("Prisijungti");
		btnLogin.setMinWidth(100);
		btnLogin.setAlignment(Pos.CENTER);
		btnLogin.setTooltip(new Tooltip("Prisijungti"));
		
		Button btnRegister = new Button("Registruotis");
		btnRegister.setMinWidth(100);
		btnRegister.setAlignment(Pos.CENTER);
		btnRegister.setTooltip(new Tooltip("Registruotis"));
		
		Label lblMessage = new Label ();
		btnRegister.setOnAction((ActionEvent e)->{
			Register register = new Register(this.primaryStage);
		});
		
		btnLogin.setOnAction((ActionEvent e)->{
			
			UsersDao userDao = new UsersDao();
			//Dashboard dashboard = new Dashboard(this.primaryStage, user);
			//String passwordencoded = encodePassword();
			User user = userDao.getUser(tfUsername.getText().toString(), 
					pfPassword.getText().toString());
			Dashboard dashBoard = new Dashboard(this.primaryStage, user);
				if (user.getUsername() != null) { // CIA GALI BUTI BEDA 
					//Dashboard dashBoard = new Dashboard(this.primaryStage, user);
			}else if(!Validation.isValidCredentials(tfUsername.getText().toString())){
				lblMessage.setText("Neteisingas vartotojo vardas");
				lblMessage.setTextFill(Color.RED);
			}else if(!Validation.isValidCredentials(pfPassword.getText().toString())){
				lblMessage.setText("Neteisingas slaptaÅ¾odis");
				lblMessage.setTextFill(Color.RED);
			}else{
				lblMessage.setText("Blogi prisijungimo duomenys");
				lblMessage.setTextFill(Color.RED);
			}
			
		});

		HBox hbLoginText = new HBox();

		
		//Reflection effect
		
		Reflection reflection1 = new Reflection();
		reflection1.setFraction(0.7);
		
		Image logoImage = new Image(new FileInputStream("C:\\\\Users\\\\Mindaugas\\\\Desktop\\\\logoImage.png"));
		ImageView logoImageView = new ImageView(logoImage); 
		logoImageView.setFitHeight(70); 
	    logoImageView.setFitWidth(70);
	    
		hbLoginText.setPadding(new Insets(10,10,10,10));
		hbLoginText.setAlignment(Pos.CENTER);
		Text txtLogin = new Text("Sandėliavimo sistema");
		txtLogin.setFont(Font.font("Courier New", FontWeight.BOLD, 30));
	
		hbLoginText.getChildren().add(logoImageView);
		hbLoginText.getChildren().add(txtLogin);
		//hbLoginText.setEffect(reflection1);
		
		
		HBox hboxGPLogin = new HBox();
		GridPane gpLogin = new GridPane();
		gpLogin.add(loginImageView,0,0);
		gpLogin.add(tfUsername,1,0);
		gpLogin.add(passwordImageView,0,1);
		gpLogin.add(pfPassword,1,1);
		gpLogin.add(lblMessage, 2, 1);
		HBox gpMygtukai = new HBox();
		gpMygtukai.getChildren().add(btnLogin);
		gpMygtukai.getChildren().add(btnRegister);
		gpMygtukai.setAlignment(Pos.BOTTOM_RIGHT);
		gpLogin.add(gpMygtukai, 1, 2);
//		gpLogin.add(btnLogin,1, 2);
//		gpLogin.add(btnRegister, 2, 2);
		gpLogin.setPadding(new Insets(10,10,10,10));
		gpLogin.setVgap(10);
		gpLogin.setHgap(10);
		hboxGPLogin.getChildren().add(gpLogin);
		hboxGPLogin.setAlignment(Pos.CENTER);
		
		//reflection for GridPane
		Reflection reflection = new Reflection();
		reflection.setFraction(0.3);
		gpLogin.setEffect(reflection);
		
		//sudedam elementus i BorderPane konteineri
		
		bpRoot.setTop(hbLoginText);
		bpRoot.setCenter(hboxGPLogin);
		
		//susiejimas su style.css
		gpLogin.setId("gridPane");
		bpRoot.setId("borderPane");
		btnLogin.setId("buttons");
		btnRegister.setId("buttons");
		txtLogin.setId("text");
		bpRoot.setId("bpRootDashboard");
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		hbLoginText.setId("tekstas");
		bpRoot.setMargin(hbLoginText, new Insets(5,100,5,100));
		bpRoot.setMargin(hboxGPLogin, new Insets(0,0,50,0));
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public String encodePassword(String password){
		//TODO sutvarkyti 
		String str = "";
		MessageDigest md;
		try {
			byte[] bytesOfMessage = password.getBytes("UTF-8");
			md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			 str = new String(thedigest, StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return str;
	}
}