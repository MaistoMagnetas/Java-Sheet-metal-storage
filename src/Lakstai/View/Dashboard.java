package Lakstai.View;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Lakstai.LakstaiDAO.Lakstai;
import Lakstai.LakstaiDAO.LakstaiDao;
import Lakstai.Vartotojai.User;
import Lakstai.Vartotojai.*;
import application.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.stage.Window;

public class Dashboard {
	private BorderPane bpRoot;
	private Scene scene;
	private Stage primaryStage;
	private String pokemonaiabilities = "";
	private TextField idField;
	private TextField lakstoMatmenysField;
	private TextField likutisField;
	private ToggleGroup group;
	private ToggleGroup group1;
	private RadioButton rbTaip;
	private RadioButton rbNe;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private GridPane gpValdymoSkydelis;
	private RadioButton selectedRadioButton;
	private RadioButton selectedRadioButton1;
	private TextField taipNeField;
	private TextField cbStoris;
	private TextField medziagaField;
	private User user;
	private  ObservableList<Lakstai> data;
	 public final static int ADMIN_LEVEL = 9;
	 
	 
	Dashboard(Stage primaryStage, User user){
		this.bpRoot = new BorderPane();
		scene = new Scene(this.bpRoot,1050,450);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		this.primaryStage=primaryStage;
		this.primaryStage.setScene(scene);
		this.user = user;
		
		this.primaryStage.setTitle("Sandėlio sistema");
		this.primaryStage.setResizable(false);
		this.primaryStage.centerOnScreen();
		addElementsToScene();
		this.primaryStage.show();	
	}
	
	
	private void addElementsToScene (){
		Label idLabel = new Label("ID:");
		Label medziagaLabel = new Label("Medžiaga:");
		Label lakstoStorisLabel = new Label("Lakšto storis:");
		Label lakstoMatmenysLabel = new Label("Lakšto matmenys:");
		Label likutisLabel = new Label("Likutis:");
		Label reikiaPapildytiLabel = new Label("Reikia papildyti?:");
		
		idField = new TextField ();
		idField.setPromptText("ID");
		idField.setTooltip(new Tooltip("Įveskite eilės numerį"));
		
		likutisField = new TextField();
		likutisField.setPromptText("Likutis");
		likutisField.setTooltip(new Tooltip("Įveskite likuti"));
		
		lakstoMatmenysField = new TextField();
		lakstoMatmenysField.setPromptText("Ilgis X Plotis");
		lakstoMatmenysField.setTooltip(new Tooltip("Iveskite ilgį X plotį"));
		
		Label lblUsername = new Label("Prisijungęs vartotojas:");
		Label lblUsername2 = new Label();
		lblUsername2.setText(user.getUsername());
		lblUsername2.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		lblUsername2.setTextFill(Color.web("#0076a3"));
		lblUsername2.setId("vartotojas");
		Button btnLogout = new Button("Atsijungti");
		btnLogout.setMaxWidth(100);
		btnLogout.setId("buttons");
		Button btnAtnaujinti = new Button("Atnaujinti");
		btnAtnaujinti.setId("buttons");
		btnAtnaujinti.setMaxWidth(100);
		
		GridPane gpVartotojas = new GridPane();
		gpVartotojas.add(lblUsername, 0, 0);
		gpVartotojas.add(lblUsername2, 1, 0);
		gpVartotojas.add(btnLogout, 0, 1,2,2);
		gpVartotojas.add(btnAtnaujinti, 1, 1, 2,2 );
		 		
		group = new ToggleGroup();
		rb1 = new RadioButton();
		rb2 = new RadioButton();
		rb3 = new RadioButton();
		rb1.setText("AISI 304");
		rb2.setText("AISI 201");
		rb3.setText("AISI 306");
		rb1.setSelected(true);
		rb1.setToggleGroup(group);
		rb2.setToggleGroup(group);
		rb3.setToggleGroup(group);
		
		group1 = new ToggleGroup();
		rbTaip = new RadioButton();
		rbNe = new RadioButton();
		rbTaip.setText("Taip");
		rbNe.setText("Ne");
		rbNe.setSelected(true);
		rbTaip.setToggleGroup(group1);
		rbNe.setToggleGroup(group1);		
				
		ComboBox cbStoris = new ComboBox();
		cbStoris.getItems().addAll(
				 "0.5", "0.8","1.0", "1.2","1.5","2.0","3.0","4.0" );
		cbStoris.setValue("0.8");	
		cbStoris.setTooltip(new Tooltip("Storis, mm"));
		
		Button btnAdd = new Button("Pridėti");
		btnAdd.setMinWidth(80);
		Button btnDelete = new Button("Ištrinti");
		btnDelete.setMinWidth(80);
		Button btnUpdate = new Button("Atnaujinti");
		btnUpdate.setMinWidth(80);
		Button btnSearch = new Button("Ieškoti");
		btnSearch.setMinWidth(80);
		
		GridPane gpMygtukai = new GridPane();
		gpMygtukai.add(btnAdd,0,0);
		gpMygtukai.add(btnDelete,1,0);
		gpMygtukai.add(btnUpdate,2,0);
		gpMygtukai.add(btnSearch,3,0);
		gpMygtukai.setHgap(10);		
		
		TableView table = new TableView();	
		
		 table.setEditable(true);
		 TableColumn IDCol = new TableColumn("ID");
		 IDCol.setMinWidth(20);
		 TableColumn userCol = new TableColumn("Vartotojas");
		 userCol.setMinWidth(20);
		 TableColumn medziagaCol = new TableColumn("Medžiaga");
		 medziagaCol.setMinWidth(50);
		 TableColumn lakstoStorisCol = new TableColumn("Lakšto storis, mm");
		 lakstoStorisCol.setMinWidth(40);
		 TableColumn lakstoMatmenysCol = new TableColumn("Lakšto matmenys, mm");
		 lakstoMatmenysCol.setMinWidth(100);
		 TableColumn likutisCol = new TableColumn("Likutis");
		 likutisCol.setMinWidth(40);
		 TableColumn uzsakymasCol = new TableColumn("Ar reikia užsakyti?");
		 uzsakymasCol.setMinWidth(120);
		 
		 if(user.getUserlevel() == ADMIN_LEVEL){//Adminas mato vartotojus
				table.getColumns().addAll(IDCol,userCol,medziagaCol,lakstoStorisCol,lakstoMatmenysCol,likutisCol,uzsakymasCol);
			}else{//Vartotojas nemato
				table.getColumns().addAll(IDCol,medziagaCol,lakstoStorisCol,lakstoMatmenysCol,likutisCol,uzsakymasCol);
			}
			 table.setMaxHeight(350);

		 
		 //table.getColumns().addAll(IDCol,lakstoStorisCol,medziagaCol,lakstoMatmenysCol,likutisCol,uzsakymasCol);
		 
		 //table.setMaxHeight(350);
		
		 GridPane gpMedziagosTipas = new GridPane();
			gpMedziagosTipas.addRow(1,rb1);
			gpMedziagosTipas.addRow(2,rb2);
			gpMedziagosTipas.addRow(3,rb3);
//			
		GridPane gpTaipNe = new GridPane();
		gpTaipNe.addRow(1,rbTaip);
		gpTaipNe.addRow(2,rbNe);
		 
		 
				IDCol.setCellValueFactory(
						 new PropertyValueFactory<Lakstai,Integer>("id")
						);
				userCol.setCellValueFactory(
						new PropertyValueFactory<Lakstai, String>("user")
						);
				 medziagaCol.setCellValueFactory(
						 new PropertyValueFactory<Lakstai,String>("medziaga")
						);
				 lakstoStorisCol.setCellValueFactory(
						 new PropertyValueFactory<Lakstai,String>("lakstoStoris")
						);
				 lakstoMatmenysCol.setCellValueFactory(
						 new PropertyValueFactory<Lakstai,String>("lakstoMatmenys")
						);
				 likutisCol.setCellValueFactory(
						 new PropertyValueFactory<Lakstai,Integer>("likutis")
						);
				 uzsakymasCol.setCellValueFactory(
						 new PropertyValueFactory<Lakstai,Boolean>("reikiaPapildyti")
						);			 		 				 
				 
				 	ObservableList<Lakstai> data = FXCollections.observableArrayList();
				 	LakstaiDao lakstaiDao = new LakstaiDao();
				 	lakstaiDao.showLakstai(data,user);
					table.setItems(data);
					
			 gpValdymoSkydelis = new GridPane();
			 
			 gpValdymoSkydelis.add(idLabel, 0, 0);
			 gpValdymoSkydelis.add(idField, 1, 0);
			 gpValdymoSkydelis.add(medziagaLabel, 0, 1);
			 gpValdymoSkydelis.add(gpMedziagosTipas, 1, 1);
			 gpValdymoSkydelis.add(lakstoStorisLabel, 0, 2);
			 gpValdymoSkydelis.add(cbStoris, 1, 2);
			 gpValdymoSkydelis.add(lakstoMatmenysLabel, 0, 3);
			 gpValdymoSkydelis.add(lakstoMatmenysField, 1,3);
			 gpValdymoSkydelis.add(likutisLabel, 0, 4);
			 gpValdymoSkydelis.add(likutisField, 1, 4);
			 gpValdymoSkydelis.add(reikiaPapildytiLabel, 0, 5);
			 gpValdymoSkydelis.add(gpTaipNe, 1, 5);
			 
			 
			 GridPane gpBendras = new GridPane();
			 gpBendras.setPadding(new Insets(0,0,0,70));
			 gpBendras.add(gpValdymoSkydelis, 0, 0);
			 gpBendras.add(table, 1, 0);			 		 
			 
			 HBox virsus = new HBox();
			Reflection reflection1 = new Reflection();
			reflection1.setFraction(0.7);
				

				virsus.setPadding(new Insets(10,10,10,10));
				virsus.setAlignment(Pos.CENTER);
				Text txtLogin = new Text("Sandėliavimo sistema");
				txtLogin.setFont(Font.font("Courier New", FontWeight.BOLD, 30));
			
			
				
				virsus.getChildren().add(txtLogin);
				gpVartotojas.setPadding(new Insets(0,0,0,300));
				virsus.setId("tekstas");
				virsus.getChildren().add(gpVartotojas);
				
			
					
			 gpValdymoSkydelis.add(gpMygtukai,0,7,2,1);
			 gpValdymoSkydelis.setPadding(new Insets(10,10,10,10));
			 gpValdymoSkydelis.setVgap(10);
			 gpValdymoSkydelis.setHgap(10);
				
				
//				PavadinimasField.setId("textfield"); 
//				  WeightField.setId("textfield");
//				  HeightField.setId("textfield");
				idField.setId("textfield");
				 idLabel.setId("text");
				 medziagaLabel.setId("text");
				 lakstoStorisLabel.setId("text");
				 lakstoMatmenysLabel.setId("text");
				 likutisLabel.setId("text");
				 reikiaPapildytiLabel.setId("text");
				 gpValdymoSkydelis.setId("gp");
//				 rb1.setId("text"); 
//				 rb2.setId("text"); 
//				 rb3.setId("text"); 
				 btnAdd.setId("buttons");
					btnUpdate.setId("buttons");
					btnSearch.setId("buttons");
					btnDelete.setId("buttons");
					
				 
				bpRoot.setId("bpRootDashboard");
				bpRoot.setTop(virsus);
				bpRoot.setCenter(gpBendras);
				//bpRoot.setRight(table);
				
//				idField = new TextField ();
//				likutisField = new TextField();
//				lakstoMatmenysField = new TextField();	
//				taipNeField = new TextField();
//				cbStoris = new TextField();
				
				//(int id, String user, String medziaga, String lakstoStoris, String lakstoMatmenys, int likutis,
				//String reikiaPapildyti
				btnAtnaujinti.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						lakstaiDao.showLakstai(data,user);
						table.setItems(data);
					}
				});
				
				btnLogout.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							Login login = new Login(primaryStage);
							showAlert(Alert.AlertType.CONFIRMATION, bpRoot.getScene().getWindow(), "Sveikiname,", "Sėkmingai atsijungėte.");
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
	
				btnAdd.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event){
						if(lakstai_validate("add"))
						{
						Lakstai lakstai = new Lakstai(Integer.parseInt(idField.getText()),
								user.getUsername(),
								selectedRadioButton.getText().toString(),
								cbStoris.getValue().toString(),
								lakstoMatmenysField.getText().toString(),
								Integer.parseInt(likutisField.getText().toString()),
								selectedRadioButton1.getText().toString());
						lakstaiDao.addLakstas(lakstai);	
						// isvalyti table ir sudeti is duombazes su naujai ideta reiksme
						table.getItems().clear();
						
						lakstaiDao.showLakstai(data, user);			
						}				
					}
					
			});
	
				
				btnUpdate.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event){
						if(lakstai_validate("update")) {
						int id = 0 ;
						Lakstai updateLakstas = new Lakstai(Integer.parseInt(idField.getText()),
								user.getUsername(),
								selectedRadioButton.getText().toString(),
								cbStoris.getValue().toString(),
								lakstoMatmenysField.getText().toString(),
								Integer.parseInt(likutisField.getText().toString()),
								selectedRadioButton1.getText().toString());	
								
						for(int i = 0 ;i<data.size();i++)
						{
							if(data.get(i).getId() ==Integer.parseInt(idField.getText()) )
							{
								
								lakstaiDao.updateLakstai(updateLakstas);
								table.getItems().clear();
								lakstaiDao.showLakstai(data, user);
							}
						}
			
					}
				}
			});
				
				btnSearch.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event){	 					
								table.getItems().clear();
								selectedRadioButton = (RadioButton)group.getSelectedToggle();
								table.setItems(
										lakstaiDao.searchLakstaiByMedziaga(selectedRadioButton.getText().toString()));	
										
				}
			});
				
				btnDelete.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event){
						lakstai_validate("delete");
						for(int i = 0 ;i<data.size();i++)
						{
							if(data.get(i).getId() == Integer.parseInt(idField.getText()) )
							{
								lakstaiDao.deleteLakstas(Integer.parseInt(idField.getText()));
								table.getItems().clear();
								lakstaiDao.showLakstai(data, user);
							}
						}
					}
			});	
		}

	private boolean lakstai_validate(String action) {
		pokemonaiabilities = "";
		switch(action) {
		case "delete":
			if(!Validation.isValidID(idField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpValdymoSkydelis.getScene().getWindow(), "Form Klaida!", "Neteisingas ID formatas");	
				return false;
			}				
			else return true;
		
		default:
			if(!Validation.isValidID(idField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpValdymoSkydelis.getScene().getWindow(), "Form Klaida!", "Neteisingas ID formatas");
				return false;
//			}else if(!Validation.isValidMedziaga(medziagaField.getText().toString())){
//				showAlert(Alert.AlertType.ERROR, gpValdymoSkydelis.getScene().getWindow(), "Form Klaida!", "Netinkama medziaga");
//				return false;
			}else if(!Validation.isValidMatmenys(lakstoMatmenysField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpValdymoSkydelis.getScene().getWindow(), "Form Klaida!", "Netinkami matmenys");
				return false;
//			}else if(!Validation.isValidstats(cbStoris.getText().toString())){
//				showAlert(Alert.AlertType.ERROR, gpValdymoSkydelis.getScene().getWindow(), "Form Klaida!", "Netinkamai ivestas storis");
//				return false;
			}else if(!Validation.isValidstats(likutisField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpValdymoSkydelis.getScene().getWindow(), "Form Klaida!", "Netinkamas likutis");
				return false;
//			}else if(!Validation.isValidPapildymas(taipNeField.getText().toString())){
//				showAlert(Alert.AlertType.ERROR, gpValdymoSkydelis.getScene().getWindow(), "Form Klaida!", "Netinkamas papildymo pasirinkimas(Taip,Ne)");
//				return false;
			}
			selectedRadioButton = (RadioButton)group.getSelectedToggle();
			selectedRadioButton1 = (RadioButton)group1.getSelectedToggle();	
			}
		return true;	
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
