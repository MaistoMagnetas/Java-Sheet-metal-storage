package application;

import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Register {
	private BorderPane root;
	private Stage primaryStage; 
		
	Register(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.root = new BorderPane();
		
		Scene scene = new Scene(this.root,450,320);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		this.primaryStage.setScene(scene);
		this.primaryStage.setTitle("Registracija");
		this.primaryStage.setResizable(false);
		this.primaryStage.centerOnScreen();
		
		addElementsToScene();						
		primaryStage.show();
		
	}
	
	private void addElementsToScene (){
		Label lblUsername = new Label("Prisijungimo vardas:");
		TextField tfUsername = new TextField();
		tfUsername.setTooltip(new Tooltip("Prisijungimo vardas"));
		tfUsername.setPromptText("Prisijungimo vardas");
		tfUsername.setFocusTraversable(false);
		
		HBox hbRegisterText = new HBox();
		
		//drop shadow effect
		DropShadow dropshadow = new DropShadow();
		dropshadow.setOffsetX(5);
		dropshadow.setOffsetY(5);
		
		
		hbRegisterText.setPadding(new Insets(10,10,10,10));
		hbRegisterText.setAlignment(Pos.CENTER);
		Text txtRegister = new Text("Vartotojo registracija");
		txtRegister.setFont(Font.font("Courier New", FontWeight.BOLD, 30));
		//txtRegister.setEffect(dropshadow);
	
		hbRegisterText.getChildren().add(txtRegister);
		
		Label lblEmail = new Label("El.paštas:");
		TextField tfEmail = new TextField();
		tfEmail.setTooltip(new Tooltip("El.paštas"));
		tfEmail.setPromptText("El.paštas");
		tfEmail.setFocusTraversable(false);
		
		Label lblPassword = new Label("Slaptažodis:");
		PasswordField pfPassword = new PasswordField();
		pfPassword.setTooltip(new Tooltip("Slaptažodis"));
		pfPassword.setPromptText("Slaptažodis");
		pfPassword.setFocusTraversable(false);
		
		Label lblPassword1 = new Label("Pakartokite slaptažodį:");
		PasswordField pfPassword1 = new PasswordField();
		pfPassword1.setTooltip(new Tooltip("Slaptažodis"));
		pfPassword1.setPromptText("Slaptažodis");
		pfPassword1.setFocusTraversable(false);
		
		Button btnRegister = new Button("Registruotis");
		btnRegister.setMinWidth(100);
		btnRegister.setAlignment(Pos.CENTER);
		
		GridPane gpRegister = new GridPane();
		gpRegister.add(lblUsername,0,0);
		gpRegister.add(tfUsername,1,0);
		gpRegister.add(lblEmail,0,1);
		gpRegister.add(tfEmail,1,1);
		gpRegister.add(lblPassword,0,2);
		gpRegister.add(pfPassword,1,2);
		gpRegister.add(lblPassword1,0,3);
		gpRegister.add(pfPassword1,1,3);
		gpRegister.add(btnRegister, 1, 4);
		gpRegister.setPadding(new Insets(10,10,10,10));
		gpRegister.setVgap(10);
		gpRegister.setHgap(10);
				
		//susiejimas su style.css
		gpRegister.setId("gridPane");
		root.setId("bpRootDashboard");
		btnRegister.setId("buttons");
		txtRegister.setId("text");		
		
		this.root.setTop(hbRegisterText);
		this.root.setCenter(gpRegister);
		
		btnRegister.setOnAction(new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event){
			if(!Validation.isValidCredentials(tfUsername.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Form Klaida!", "Įveskite vardą.");
			return;
			}
		if(!Validation.isValidEmail(tfEmail.getText().toString())){
			showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Form Klaida!", "Įveskite el.paštą.");
			return;
			}
		if(!Validation.isValidCredentials(pfPassword.getText().toString())){
			showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Form Klaida!", "Netinkamas slaptažodis.");
			return;
			}
		if(!pfPassword.getText().toString().equals(pfPassword1.getText().toString())){
			showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Form Klaida!", "Slaptažodžiai nesutampa.");
			return;
			}
		showAlert(Alert.AlertType.INFORMATION, gpRegister.getScene().getWindow(), "Registracija sėkminga!", "Sveiki " + tfUsername.getText().toString());
		//String username, String password, int userlevel, String email
		User useris = new User(tfUsername.getText().toString(),pfPassword.getText().toString(),1,tfEmail.getText().toString());
		UsersDao userDao = new UsersDao();
		userDao.addUser(useris);
		
		try {
			Login login = new Login(primaryStage);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	});
	
	
	}
	private void showAlert(Alert.AlertType alerType, Window owner, String title, String message){
		Alert alert = new Alert(alerType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
		}
}
