package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
	private Dictionary model;
	 Long inizio;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> mnLingua;

    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtErrate;
    
    @FXML
    private Button btnCheck;

    
    @FXML
    private Button btnClear;

    @FXML
    private Label lblErrori;

    @FXML
    private Label lblTempo;
    
    public void setModel(Dictionary model) {
    	
		this.model = model;
		txtInput.setDisable(true);
		txtInput.setText("Selezionare una lingua");

		txtErrate.setDisable(true);
		mnLingua.getItems().addAll("Italian","English");
		
		btnCheck.setDisable(true);
		btnClear.setDisable(true);
	
		}

    @FXML
	void doActivation(ActionEvent event) {
		if (mnLingua.getValue() != null) {
			txtInput.setDisable(false);
			txtErrate.setDisable(false);
			btnCheck.setDisable(false);
			btnClear.setDisable(false);
			txtInput.clear();
			txtErrate.clear();
		} else {
			txtInput.setDisable(true);
			txtErrate.setDisable(true);
			btnCheck.setDisable(true);
			btnClear.setDisable(true);
			txtInput.setText("Seleziona una lingua!");
		}
	}
    
    @FXML
    void doClearText(ActionEvent event) {
    	txtInput.clear();
    	txtErrate.clear();
    	model.removeTutto();
    	lblErrori.setText(" ");
    	lblTempo.setText(" ");
    	 
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	inizio = System.nanoTime();
    	txtErrate.clear();
    	String language = mnLingua.getValue();
    	
    	if(!model.loadDictionary(language)) {
    		txtInput.setText("Errore nel caricamento del dizionario!");
			return;
    	}
    	
    	List<String> words = new LinkedList<String>();
    	
    	String inputText = txtInput.getText().toLowerCase();
		
    	if (inputText.isEmpty()) {
			 txtInput.setText("Inserire un testo da correggere!");
			return;
		}
    	
		inputText = inputText.replaceAll("\n", " ");
		inputText = inputText.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");
    	
		String dato[] = inputText.split(" ");
    	
    	for(int i = 0; i<dato.length; i++) {
    		String p = dato [i];
    		words.add(p);
    		
    	}
    	model.spellCheckText(words);
    	//model.spellCheckTextLinear(words);
    	//model.spellCheckTextDichotomic(words);
    	
    	txtErrate.setText(model.listaOutput());
    	
    	//double potenza = Math.pow(10, (-9));
    	
    	lblErrori.setText("The text contains"+ model.getContatore()+ "  errors");
    	lblTempo.setText("Spell check completed in "+ Long.toString((System.nanoTime()-inizio)) +" nanoseconds");

    }

    @FXML
    void initialize() {
        assert mnLingua != null : "fx:id=\"mnLingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
      
        assert txtErrate != null : "fx:id=\"txtErrate\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
}
