package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private ChoiceBox<String> mnLingua;

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
		mnLingua.getItems().addAll("Italian","English");
	
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
    	String language = mnLingua.getValue();
    	model.loadDictionary(language);
    	
    	List<String> words = new LinkedList<String>();
    	
    	
    	String dato[] = txtInput.getText().toLowerCase().split(" ");
    	
    	for(int i = 0; i<dato.length; i++) {
    		String p;
    		p = dato[i].replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
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
