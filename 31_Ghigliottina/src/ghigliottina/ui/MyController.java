package ghigliottina.ui;

import java.util.List;
import java.util.Random;

import ghigliottina.model.Ghigliottina;

public class MyController implements Controller {

	private List<Ghigliottina> ghiottine;
	
	@Override
	public Ghigliottina sorteggiaGhigliottina() {
		Random random = new Random();
		return this.ghiottine.get(random.nextInt(this.ghiottine.size()));
	}

	@Override
	public List<Ghigliottina> listaGhigliottine() {
		return this.ghiottine;
	}

	public MyController(List<Ghigliottina> ghiottine) {
		this.ghiottine = ghiottine;
	}

}
