package app.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.model.Database;
import app.model.Material;
import app.model.RSHandle;
import javafx.collections.ObservableList;

public class MaterialControl {

	public static boolean addMaterial(long idmaterial, String name, String standard, double storeAmount, String unit) {
		try {
			List<Long> idmaterials = (List<Long>) Database.use("SELECT idmaterial FROM MaterialManagement.material", new RSHandle() {
				
				@Override
				public List handle(ResultSet resultSet) throws SQLException {
					List<Long> list = new ArrayList<Long>();
					while (resultSet.next()) {
						list.add(resultSet.getLong("idmaterial"));
					}
					return list;
				}
			});
			
			if (idmaterials.contains(idmaterial)) {
				Database.use("UPDATE MaterialManagement.material SET ("+"name = '"+name+"'"
						+",standard = '"+standard+"',storeAmount="+String.valueOf(storeAmount)
						+",unit='"+unit+"') WHERE (idmaterial = " + String.valueOf(idmaterial) + ")"
						, null);
			} else {
				Database.use("INSERT INTO MaterialManagement.material VALUES ("
						+String.valueOf(idmaterial)+",'"+name+"','"+standard+"',"+String.valueOf(storeAmount)+",'"+
						unit+"')", null);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void showMaterials(ObservableList<Material> materials) {
		materials.removeAll();
		
		List<Material> list = (List<Material>) Database.use("SELECT * FROM MaterialManagement.material", new RSHandle() {
			
			@Override
			public List handle(ResultSet resultSet) throws SQLException {
				List<Material> list = new ArrayList<Material>();
				while(resultSet.next()) {
					Material material = new Material();
					material.setIdmaterial(resultSet.getLong("idmaterial"));
					material.setName(resultSet.getString("name"));
					material.setStandard(resultSet.getString("standard"));
					material.setStoreAmount(resultSet.getLong("storeAmount"));
					material.setUnit(resultSet.getString("unit"));
					list.add(material);
				}
				return list;
			}
		});
		
		Iterator<Material> iterator = list.iterator();
		while (iterator.hasNext()) {
			Material material = (Material) iterator.next();
			materials.add(material);
		}
	}

}
