package presentation.frontcontroller.command;

import business.applicationservice.ApplicationServiceContratto;
import business.entity.Contratto;

public class CommandChiudiContratto implements Command<Contratto> {

	@Override
	public Contratto execute(Contratto entity) {
		try {
			new ApplicationServiceContratto().chiudi(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
}