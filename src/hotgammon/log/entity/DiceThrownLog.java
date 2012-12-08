package hotgammon.log.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("DiceThrown")
@Table(name="DICE_THROWN_LOG")
public class DiceThrownLog extends HotgammonLog {
	@Column(nullable=false)
	private int diceOne;
	
	@Column(nullable=false)
	private int diceTwo;

	public int getDiceOne() {
		return diceOne;
	}

	public void setDiceOne(int diceOne) {
		this.diceOne = diceOne;
	}

	public int getDiceTwo() {
		return diceTwo;
	}

	public void setDiceTwo(int diceTwo) {
		this.diceTwo = diceTwo;
	}
}
