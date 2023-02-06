package rx.graborder.jpaentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * At the beginning, assume it as word entry, i.e. not a phrase
 * @author rui
 *
 */
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "hospitalName", "outPatientVisitItem"}) })
@SequenceGenerator(sequenceName = "order_id_seq", initialValue= 1, name = "order_id_sequence", allocationSize=1)
@Entity
public class OutPatientVisitOrder extends AbstractEntity {
	private String hospitalName;
	private String outPatientVisitItem;
	@Id @GeneratedValue(generator="order_id_sequence", strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public String getHospitalName() {
		return hospitalName;
	}


	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}


	public String getOutPatientVisitItem() {
		return outPatientVisitItem;
	}


	public void setOutPatientVisitItem(String outPatientVisitItem) {
		this.outPatientVisitItem = outPatientVisitItem;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OutPatientVisitOrder) {
			OutPatientVisitOrder that = (OutPatientVisitOrder) obj;
			return new EqualsBuilder()
					.append(hospitalName, that.hospitalName)
					.build();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17,37)
				.append(hospitalName)
				.hashCode();
	}
	
}
