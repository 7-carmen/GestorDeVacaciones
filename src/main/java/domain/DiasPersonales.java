package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class DiasPersonales  extends DomainEntity {
	// Attributes -------------------------------------------------------------
		private int dias_totales;
		private int dias_usados;
		
		public int getDias_totales() {
			return dias_totales;
		}
		
		public void setDias_totales(int dias_totales) {
			this.dias_totales = dias_totales;
		}
		
		public int getDias_usados() {
			return dias_usados;
		}
		
		public void setDias_usados(int dias_usados) {
			this.dias_usados = dias_usados;
		}
		
		
		// Relationships ----------------------------------------------------------



}