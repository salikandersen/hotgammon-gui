package hotgammon.log.entity;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import hotgammon.Location;

@Entity
@DiscriminatorValue("Move")
@Table(name="MOVE_LOG")
public class MoveLog extends HotgammonLog {	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Location fromLocation;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Location toLocation;
	
	@Column(nullable=false)
	private boolean resultOfAction;

	public Location getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(Location fromLocation) {
		this.fromLocation = fromLocation;
	}

	public Location getToLocation() {
		return toLocation;
	}

	public void setToLocation(Location toLocation) {
		this.toLocation = toLocation;
	}

	public boolean getResultOfAction() {
		return resultOfAction;
	}

	public void setResultOfAction(boolean resultOfAction) {
		this.resultOfAction = resultOfAction;
	}

}
