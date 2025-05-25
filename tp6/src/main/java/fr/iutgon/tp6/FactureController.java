package fr.iutgon.tp6;

import fr.iutgon.tp6.modele.FabriqueProduits;
import fr.iutgon.tp6.modele.Ligne;
import fr.iutgon.tp6.modele.Produit;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class FactureController implements Initializable {
  public TableView<Ligne> table;
  public TableColumn<Ligne, Integer> qte;
  public TableColumn<Ligne, Produit> produit;
  public TableColumn<Ligne, Number> prixUnitaire;
  public TableColumn<Ligne, Number> totalHT;
  public TableColumn<Ligne, Number> totalTTC;
  public TextField sommeFacture;
  
  /**
   Called to initialize a controller after its root element has been completely processed.

   @param location  The location used to resolve relative paths for the root object, or
   {@code null} if the location is not known.
   @param resources The resources used to localize the root object, or {@code null} if
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
	  this.qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
		
	  Callback<CellDataFeatures<Ligne, Produit>, ObservableValue<Produit>> p = new Callback<TableColumn.CellDataFeatures<Ligne,Produit>, ObservableValue<Produit>>() {
		
		@Override
		public ObservableValue<Produit> call(CellDataFeatures<Ligne, Produit> param) {
			return param.getValue().produitProperty();
		}
	  };
	  this.produit.setCellValueFactory(p);
	
	  Callback<CellDataFeatures<Ligne, Number>, ObservableValue<Number>> n = new Callback<TableColumn.CellDataFeatures<Ligne,Number>, ObservableValue<Number>>() {
			
		@Override
		public ObservableValue<Number> call(CellDataFeatures<Ligne, Number> param) {
			return param.getValue().getProduit().prixProperty();
		}
	  };
	  this.prixUnitaire.setCellValueFactory(n);
	  
	  Callback<CellDataFeatures<Ligne, Number>, ObservableValue<Number>> ht = new Callback<TableColumn.CellDataFeatures<Ligne,Number>, ObservableValue<Number>>() {
			
		@Override
		public ObservableValue<Number> call(CellDataFeatures<Ligne, Number> param) {
			return param.getValue().totalHTProperty();
		}
	  };
	  this.totalHT.setCellValueFactory(ht);
		  
	  Callback<CellDataFeatures<Ligne, Number>, ObservableValue<Number>> ttc = new Callback<TableColumn.CellDataFeatures<Ligne,Number>, ObservableValue<Number>>() {
				
			@Override
			public ObservableValue<Number> call(CellDataFeatures<Ligne, Number> param) {
				return param.getValue().totalTTCProperty();
			}
	  };
	  this.totalTTC.setCellValueFactory(ttc);
	  
	  
	  qte.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	  
	  produit.setCellValueFactory(param -> {
		  return param.getValue().produitProperty();
	  });
	  produit.setCellFactory(cell -> new ChoiceBoxTableCell<>(new StringConverter<Produit>() {
			  	
		public String toString(Produit produit) {
			return produit == null ? "" : produit.toString();
		}
		
		public Produit fromString(String string) {
			return FabriqueProduits.getProduits()
				.stream()
				.filter(p -> p.toString().equals(string))
				.findFirst()
				.orElse(null);
		}
	  }, FXCollections.observableArrayList(FabriqueProduits.getProduits())
	 ));
  }

  public void onAjouter(ActionEvent actionEvent) {
	Random random = new Random();
	int qte = random.nextInt(100);
    Ligne ligne = new Ligne(qte, new Produit("Balle de squash",4,1.2f));
    table.getItems().add(ligne);
  }
  }
