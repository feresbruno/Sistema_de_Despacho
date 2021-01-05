package gui.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSearch {

	public void Search() {
		/**implementa os tipos de arquivo que aparecerão para escolha*/
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("csv");
		
		/**Instanciando o selecionador de arquivo*/
		JFileChooser fc = new JFileChooser();
		
		/**Adicionando os arquivos que poderão ser selecionados*/
		fc.setFileFilter(fileNameExtensionFilter);
		
		/**Nome da tela localizadora*/
		fc.setDialogTitle("File selecting");
		
		/**Recebe uma resposta caso algum evento do localizador de arquivo seja acessado*/
		int resposta = fc.showOpenDialog(null);
		
		/**Verifica se resposta recebida é igual a ok*/
		if (resposta == JFileChooser.APPROVE_OPTION) {
			
			/** Se sim abre um buffer do arquivo e joga na tela*/
			File file = new File(fc.getSelectedFile().getAbsolutePath());
			FileReader fis;
			try {
				fis = new FileReader(file);
				BufferedReader bis = new BufferedReader(fis);
				while(bis.ready()){
					System.out.println(bis.readLine()+"\n");
				}
				bis.close();
				fis.close();
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
	
