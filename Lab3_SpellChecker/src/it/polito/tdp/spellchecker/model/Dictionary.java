package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	private List<String> parole = new LinkedList<String>();
	private List<RichWord> richWord = new LinkedList<RichWord>();
	//private List<String> parole = new ArrayList<String>();
	//private List<RichWord> richWord = new ArrayList<RichWord>();
	private int contatore=0;

	
	public void loadDictionary (String language) {
		
		
		
		try {
			FileReader fr = new FileReader("rsc/"+language+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				parole.add(word);
			}
			br.close();
			 } catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
		
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		for(String parolaInput : inputTextList) {
			RichWord rw = new RichWord();
			richWord.add(rw);
			rw.setParola(parolaInput);
			if(parole.contains(parolaInput)) {
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
			for(String parolaSalvata:parole) {
				if(parolaInput.equals(parolaSalvata)) {
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
			int high = parole.size()-1;
			
			while (low<=high) {
				int mid = (low+high)/2;
				if(parolaInput.compareTo(parole.get(mid))==0) {
					rw.setCorretta(true); //valore trovato nella posizione mid
					return null;
			        }
				else if (parolaInput.compareTo(parole.get(mid))>0) {
					low = mid + 1;
				}
				else {
					high = mid - 1;
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
