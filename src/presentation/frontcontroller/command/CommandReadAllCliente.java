package presentation.frontcontroller.command;

import java.util.List;

import business.applicationservice.ApplicationServiceCliente;
import business.entity.Cliente;

public class CommandReadAllCliente implements Command<List<Cliente>>{

	@Override
	public List<Cliente> execute(List<Cliente> entity) {
		try {
			return new ApplicationServiceCliente().readAll();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}