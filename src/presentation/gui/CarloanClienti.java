package presentation.gui;

public class CarloanClienti extends CarloanStage{
	
	public CarloanClienti(){
		super("SchermataCliente.fxml", null);
		setTitle("Immissione cliente");
	}
	
	public CarloanClienti(Object entity) {
		super("SchermataCliente.fxml", entity);
		setTitle("Immissione cliente");
	}
}
