package presentation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import presentation.gui.CarloanMessage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import business.entity.Fascia;
import business.entity.Modello;
import business.entity.TipoCarburante;

public class SchermataModello extends SchermataDati<Modello>{

	@FXML
	private TextField marca;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField capacitÓBagagliaio;
	
	@FXML
	private TextField numeroPosti;
	
	@FXML
	private TextField potenza;
	
	@FXML
	private TextField emissioniCO2;
	
	@FXML 
	private TextField numeroPorte;
	
	@FXML
	private TextField peso;
	
	@FXML
	private Label fascia;
	
	@FXML
	private ChoiceBox<TipoCarburante> tipoCarburante;
	
	@FXML
	private CheckBox trasmissioneAutomatica;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tipoCarburante.setItems(FXCollections.observableArrayList(TipoCarburante.values()));
		tipoCarburante.getSelectionModel().select(0);
	}

	@Override
	public void onConferma() {
		Modello modello = buildEntity();
		if (modello != null) {
			if (edit) {
				controller.processRequest("ModificaModello", buildEntity());
			} else {
				controller.processRequest("AggiungiModello", buildEntity());
			}
			close();
		} else {
			CarloanMessage.showMessage(AlertType.WARNING, "I dati immessi non sono corretti");
		}
	}

	@Override
	public void initModifica(Modello entity) {
		edit = true;
		id = entity.getId();
		capacitÓBagagliaio.setText(Integer.toString(entity.getCapacitÓBagagliaio()));
		numeroPorte.setText(Integer.toString(entity.getNumeroPorte()));
		numeroPosti.setText(Integer.toString(entity.getNumeroPosti()));
		marca.setText(entity.getMarca());
		nome.setText(entity.getNome());
		emissioniCO2.setText(Double.toString(entity.getEmissioniCO2()));
		potenza.setText(Integer.toString(entity.getPotenza()));
		peso.setText(Integer.toString(entity.getPeso()));
		fascia.setText(Integer.toString(entity.getFascia().getId()));
		trasmissioneAutomatica.selectedProperty().set(entity.isTrasmissioneAutomatica());
	}

	
	@FXML
	public void onCalcolaFascia() {
		Integer idFascia = (Integer) controller.processRequest("CalcolaFascia", buildEntity());
		if (idFascia != null) fascia.setText(Integer.toString(idFascia));
	}
	
	
	@Override
	protected Modello buildEntity() {
		Modello modello = new Modello();
		try {
			if (id!=0) {
				modello.setId(id);
			}
			modello.setTipoCarburante(tipoCarburante.getValue());
			modello.setCapacitÓBagagliaio(Integer.parseInt(capacitÓBagagliaio.getText()));
			modello.setNumeroPorte(Integer.parseInt(numeroPorte.getText()));
			modello.setMarca(marca.getText());
			modello.setNome(nome.getText());
			modello.setEmissioniCO2(Double.parseDouble(emissioniCO2.getText()));
			modello.setNumeroPosti(Integer.parseInt(numeroPosti.getText()));
			modello.setPotenza(Integer.parseInt(potenza.getText()));
			modello.setPeso(Integer.parseInt(peso.getText()));
			modello.setTrasmissioneAutomatica(trasmissioneAutomatica.selectedProperty().get());
			if (!fascia.getText().isEmpty())
				modello.setFascia((Fascia) controller.processRequest("ReadFascia", fascia.getText()));
			return modello;
		} catch (Exception e) {
			return null;
		}
	}

}
