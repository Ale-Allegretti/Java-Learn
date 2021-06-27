package saferepo.ui.javafx;

import java.io.File;
import java.nio.file.Paths;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import saferepo.model.VersionedDocument;
import saferepo.persistence.SadPersisterException;
import saferepo.ui.controller.Controller;



public class SafeRepoPane extends BorderPane {
	
	private Controller controller;
	private Button aggiungi;
	private Button elimina;
	private ListView<String> listaDocumenti;
	private ListView<VersionedDocument> listaVersioni;
	private String selectedDocumentID;
	@SuppressWarnings("unused")
	private VersionedDocument selectedVersion;
	private Stage stage;
	private VBox vBox1;
	private VBox vBox2;
	
	
	public SafeRepoPane(Controller controller, Stage stage) {
		this.controller = controller;
		this.stage=stage;
		//
		listaDocumenti = new ListView<>(controller.getDocumentIDs());
		listaDocumenti.setPrefHeight(150);
		listaDocumenti.setPrefWidth(150);
		vBox1 = new VBox();
		vBox1.getChildren().add(new Label(" Elenco documenti: "));
		vBox1.getChildren().add(listaDocumenti);
		listaDocumenti.getSelectionModel().selectedItemProperty().addListener(
				(obj, oldval, newval) -> { docSelHandler(newval); selectedDocumentID = newval; }
		);
		//
		listaVersioni = new ListView<>();
		listaVersioni.setPrefHeight(150);
		listaVersioni.setPrefWidth(350);
		vBox2 = new VBox();
		vBox2.getChildren().add(new Label(" Elenco versioni: "));
		vBox2.getChildren().add(listaVersioni);
		listaVersioni .getSelectionModel().selectedItemProperty().addListener(
				(obj, oldval, newval) -> { retrieveDocVersion(newval); selectedVersion = newval; }
		);
		//
		HBox topPane = new HBox();
		topPane.getChildren().addAll(vBox1, vBox2);
		this.setTop(topPane);
		//
		HBox centerPane = new HBox();
		aggiungi = new Button("Aggiungi file");
		aggiungi.setOnAction(e -> addDocument());
		elimina = new Button("Elimina file");
		elimina.setOnAction(e -> delDocument());
		elimina.setDisable(true);
		centerPane.getChildren().addAll(aggiungi, elimina);
		this.setCenter(centerPane);
	}

	private void docSelHandler(String documentID) {
		 if (documentID == null) 
			 return; 
		 
		 ObservableList<VersionedDocument> versioniDocumentID = controller.getVersions(documentID);
		 listaVersioni.setItems(versioniDocumentID);
		 elimina.setDisable(versioniDocumentID.size() == 0);
	}
	
	public void retrieveDocVersion(VersionedDocument vdoc) {
		if (vdoc.getDocument() == null) {
			Controller.alert("Errore: file cancellato", "Il file " + vdoc.toString() 
			+ "è stato eliminato", "(sono comunque disponibili le versioni precedenti)");
			return;
		}

		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Recupero versione #" + vdoc.getVersion() + " di " + vdoc.getID());
		chooser.setInitialDirectory(Paths.get(System.getProperty("user.dir"), "retrievedFiles").toFile());
		File selectedFile = chooser.showDialog(stage);
		
		if (selectedFile == null) 
			return; // utente ha annullato operazione
		try {
			VersionedDocument savedDoc = controller.retrieve(vdoc, selectedFile.toPath());
			Controller.info("Scaricamento completato", "Il documento è disponibile in " 
			+ savedDoc.getPath().toString(), "Saluti!");
		} catch (SadPersisterException e) {
			Controller.alert("Errore irrecuperabile", "Errore nel recupero di " 
		    + vdoc.toString(), "Peccato..!");
		}
	}

	private void addDocument() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Aggiunta documento");
		chooser.setInitialDirectory(Paths.get(System.getProperty("user.dir"), "testFiles").toFile());
		File selectedFile = chooser.showOpenDialog(stage);
		if (selectedFile == null) 
			return; // utente ha annullato operazione
		try {
			@SuppressWarnings("unused")
			VersionedDocument vdoc = controller.store(selectedFile.toPath().getFileName().toString(), selectedFile.toPath());
			listaDocumenti.setItems(controller.getDocumentIDs());
			
		} catch (SadPersisterException e) {
			Controller.alert("Errore irrecuperabile", "Errore nell'aggiunta di " + selectedFile, "Che peccato!");
		}
	}

	private void delDocument() {
		boolean confirmed = Controller.askConfirm("Richiesta eliminazione", "Eliminare davvero il documento " + selectedDocumentID + "?", "Pensaci bene..");
		if (confirmed) {
			try {
				controller.delete(selectedDocumentID);
				listaDocumenti.setItems(controller.getDocumentIDs());
			} catch (SadPersisterException e) {
				Controller.alert("Errore irrecuperabile", "Errore nell'eliminazione di " + selectedDocumentID, "Situazione inconsistente");
			}
		}
	}
	
	
	
	
}
