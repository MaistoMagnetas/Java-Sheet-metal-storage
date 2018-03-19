package application;

import application.LakstaiDao;
import application.Lakstai;
import application.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
	private RadioButton rb11;
	private RadioButton rb21;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private GridPane gpValdymoSkydelis;
	private RadioButton selectedRadioButton;
	private RadioButton selectedRadioButton2;
	private TextField taipNeField;
	private TextField cbStoris;
	private TextField medziagaField;
	private User user;
	private  ObservableList<Lakstai> data;
	 public final static int ADMIN_LEVEL = 9;
	 
	 
	public Dashboard(Stage primaryStage, User user){
		this.bpRoot = new BorderPane();
		scene = new Scene(this.bpRoot,1300,500);

		this.primaryStage=primaryStage;
		this.primaryStage.setScene(scene);
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
		Label reikiaPapildytiLabel = new Label("Reidkia papildyti?:");
		
		idField = new TextField ();
		likutisField = new TextField();
		lakstoMatmenysField = new TextField();	
		taipNeField = new TextField();
		cbStoris = new TextField();
		medziagaField = new TextField();
		 		
//		group = new ToggleGroup();
//		rb1 = new RadioButton();
//		rb2 = new RadioButton();
//		rb3 = new RadioButton();
//		rb1.setText("AISI 304");
//		rb2.setText("AISI 201");
//		rb3.setText("AISI 306");
//		rb1.setSelected(true);
//		rb1.setToggleGroup(group);
//		rb2.setToggleGroup(group);
//		rb3.setToggleGroup(group);
		
				
//		ComboBox cbStoris = new ComboBox();
//		cbStoris.getItems().addAll(
//				 "0.5mm", "0.8mm","1.0mm", "1.2mm","1.5mm","2mm","3mm","4mm" );
//		cbStoris.setValue("0.8mm");	
		
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
		 
//		 if(user.getUserlevel() == ADMIN_LEVEL){//Adminas mato vartotojus
//				table.getColumns().addAll(IDCol,medziagaCol,lakstoStorisCol,lakstoMatmenysCol,likutisCol,uzsakymasCol);
//			}else{//Vartotojas nemato
//				table.getColumns().addAll(IDCol,medziagaCol,lakstoStorisCol,lakstoMatmenysCol,likutisCol,uzsakymasCol);
//			}
//			 table.setMaxHeight(350);

		 
		 table.getColumns().addAll(IDCol,lakstoStorisCol,medziagaCol,lakstoMatmenysCol,likutisCol,uzsakymasCol);
		 
		 table.setMaxHeight(350);
		
//		 GridPane gpMedziagosTipas = new GridPane();
//			gpMedziagosTipas.addRow(1,rb1);
//			gpMedziagosTipas.addRow(2,rb2);
//			gpMedziagosTipas.addRow(3,rb3);
//			
		
		 
		 
		 
				IDCol.setCellValueFactory(
						 new PropertyValueFactory<Lakstai,Integer>("id")
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
			 gpValdymoSkydelis.add(medziagaField, 1, 1);
			 gpValdymoSkydelis.add(lakstoStorisLabel, 0, 2);
			 gpValdymoSkydelis.add(cbStoris, 1, 2);
			 gpValdymoSkydelis.add(lakstoMatmenysLabel, 0, 3);
			 gpValdymoSkydelis.add(lakstoMatmenysField, 1,3);
			 gpValdymoSkydelis.add(likutisLabel, 0, 4);
			 gpValdymoSkydelis.add(likutisField, 1, 4);
			 gpValdymoSkydelis.add(reikiaPapildytiLabel, 0, 5);
			 gpValdymoSkydelis.add(taipNeField, 1, 5);
					
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
//				 rb1.setId("text"); 
//				 rb2.setId("text"); 
//				 rb3.setId("text"); 
				 btnAdd.setId("buttons");
					btnUpdate.setId("buttons");
					btnSearch.setId("buttons");
					btnDelete.setId("buttons");
					
				 
				bpRoot.setId("bpRootDashboard");
				bpRoot.setCenter(gpValdymoSkydelis);
				bpRoot.setRight(table);
				
//				idField = new TextField ();
//				likutisField = new TextField();
//				lakstoMatmenysField = new TextField();	
//				taipNeField = new TextField();
//				cbStoris = new TextField();
				
				//(int id, String user, String medziaga, String lakstoStoris, String lakstoMatmenys, int likutis,
				//String reikiaPapildyti
	
				btnAdd.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event){
						if(lakstai_validate("add"))
						{
						Lakstai lakstai = new Lakstai(Integer.parseInt(idField.getText()),
								medziagaField.getText().toString(),
								cbStoris.getText().toString(),
								lakstoMatmenysField.getText().toString(),
								Integer.parseInt(likutisField.getText().toString()),
								taipNeField.getText().toString());
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
								medziagaField.getText().toString(),
								cbStoris.getText().toString(),
								lakstoMatmenysField.getText().toString(),
								Integer.parseInt(likutisField.getText().toString()),
								taipNeField.getText().toString());	
								
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
								table.setItems(
										lakstaiDao.searchLakstaiByMedziaga(medziagaField.getText().toString()));	
										
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
			}else if(!Validation.isValidMedziaga(medziagaField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpValdymoSkydelis.getScene().getWindow(), "Form Klaida!", "Netinkama medziaga");
				return false;
			}else if(!Validation.isValidMatmenys(lakstoMatmenysField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpValdymoSkydelis.getScene().getWindow(), "Form Klaida!", "Netinkami matmenys");
				return false;
			}else if(!Validation.isValidstats(cbStoris.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpValdymoSkydelis.getScene().getWindow(), "Form Klaida!", "Netinkamai ivestas storis");
				return false;
			}else if(!Validation.isValidstats(likutisField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpValdymoSkydelis.getScene().getWindow(), "Form Klaida!", "Netinkamas likutis");
				return false;
			}else if(!Validation.isValidPapildymas(taipNeField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpValdymoSkydelis.getScene().getWindow(), "Form Klaida!", "Netinkamas papildymo pasirinkimas(Taip,Ne)");
				return false;
			}
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
