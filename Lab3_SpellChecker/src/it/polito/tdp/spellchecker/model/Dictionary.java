package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	private List<String> paroleDizionario;	//lista delle parole del dizionario
	private String language;
	private List<RichWord> richWord = new LinkedList<RichWord>();	//lista delle parole di input
	
	//private List<String> parole = new ArrayList<String>();
	//private List<RichWord> richWord = new ArrayList<RichWord>();
	
	private int contatore=0;
	
	public boolean loadDictionary (String language) {
		
		if (paroleDizionario != null && this.language.equals(language)) {
			return true;
		}
		
		paroleDizionario = new LinkedList<String>();
		this.language = language;
		
		try {
			FileReader fr = new FileReader("rsc/"+language+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				paroleDizionario .add(word);
			}
			br.close();
			System.out.println("Dizionario " + language + " loaded.");
			
			return true;
		} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			return false;
		}
		
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		for(String parolaInput : inputTextList) {
			RichWord rw = new RichWord();
			richWord.add(rw);
			rw.setParola(parolaInput);
			if(paroleDizionario .contains(parolaInput)) {
				rw.setCorretta(true);					
			}
			
		}
		return richWord;
		
	}
	
	/*
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		for(String parolaInput : inputTextList) {
			RichWord rw = new RichWord();
			richWord.add(rw);
			rw.setParola(parolaInput);
			for(String parolaSalvata:paroleDizionario ) {
				if(parolaInput.equalsIgnoreCase(parolaSalvata)) { 
						//equalsIgnoreCase confronta due stringhe senza considerare maiusc/minusc
					rw.setCorretta(true);					
				}
			}
		}
		return richWord;
	}
	*/
	
	/*
	public List<RichWord>spellCheckTextDichotomic(List<String> inputTextList){
		
		
		for(String parolaInput : inputTextList) {
			RichWord rw = new RichWord();
			richWord.add(rw);
			rw.setParola(parolaInput);
			
			int low = 0;
			int high = paroleDizionario.size();
			
			while (low != high) {
				int mid = low+ (high - low)/2;
				if(parolaInput.compareToIgnoreCase(paroleDizionario.get(mid))==0) {
					rw.setCorretta(true); //valore trovato nella posizione mid
					return null;
			        }
				else if (parolaInput.compareTo(paroleDizionario .get(mid))>0) {
					low = mid + 1;
				}
				else {
					high = mid;
				}
			}
		}
		return richWord;
	}
	
	*/
	public String listaOutput() {
		String lista = "";
		for(RichWord r : richWord) {
			if(r.isCorretta()==false) {
				lista += r.getParola()+ "\n";
				contatore ++;
			}
		}
		return lista.trim();
	}

	public void removeTutto() {
		richWord.clear();
		contatore = 0;
	}
	
	public int getContatore() {
		return contatore;
	}
	
	

}
