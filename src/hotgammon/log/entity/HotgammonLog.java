package hotgammon.log.entity;
import hotgammon.Color;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING)
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQuery(name="find.all.hotgammonlogs", query="select log from HotgammonLog log")
public abstract class HotgammonLog {
	@Id
	@GeneratedValue
	private int id;

	//instansieres for at undgå at skulle sætte det hvergang man opretter et nyt objekt
	@Temporal(TemporalType.TIMESTAMP)
	private Date created = new Date();
	
	@Enumerated(EnumType.STRING)
	private Color player;
	
	public int getId() {
		return id;
	}

	public Date getCreated() {
		return created;
	}

	public Color getPlayer() {
		return player;
	}

	public void setPlayer(Color player) {
		this.player = player;
	}
}
