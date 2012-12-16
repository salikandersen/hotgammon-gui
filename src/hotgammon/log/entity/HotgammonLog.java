package hotgammon.log.entity;
import hotgammon.Color;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING)
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries ( {
	@NamedQuery(name="find.all.hotgammonlogs", query="select log from HotgammonLog log"),
	@NamedQuery(name="delete.all.hotgammonlogs", query="delete from HotgammonLog log")
}
)
public abstract class HotgammonLog implements Comparable<HotgammonLog> {
	@Id 
	@GeneratedValue
	private int id;

	//instansieres for at undgå at skulle sætte det hvergang man opretter et nyt objekt
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date created = new Date();
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Color player;
	
	public int getId() {
		return id;
	}
	
	//dette er kun nødvendigt, da vi genbruger de samme klasser til DirectSql og JPA
	public void setId(Integer id){
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}
	//dette er kun nødvendigt, da vi genbruger de samme klasser til DirectSql og JPA
	public void setCreated(Date created){
		this.created = created;
	}

	public Color getPlayer() {
		return player;
	}

	public void setPlayer(Color player) {
		this.player = player;
	}

	@Override
	@Transient
	public int compareTo(HotgammonLog o) {
		return this.getCreated().compareTo(o.getCreated());
	}
	
}
