package br.com.livro.domain;

import java.sql.SQLException;
import java.util.List;

public class CarroService {
	private CarroDAO db = new CarroDAO();

	public Carro getCarroById(Long id) {
		try {
			return db.getCarroById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Carro> getCarros() {

		try {
			return db.getCarros();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean save(Carro c) {
		try {
			db.save(c);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public List<Carro> findByName(String name) {
		try {
			return db.getCarroByName(name);
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Carro> findByTipo(String tipo) {
		try {
			return db.getCarroByTipo(tipo);
		} catch (SQLException e) {
			return null;
		}
	}
}
