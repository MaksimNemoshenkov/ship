package ship;

public abstract class Ship {
	private String name;
	private int health;
	private int shots;
	private int firingRange;
	private int travelDistance;
	private Boolean step = false;
	Ship(String name,int health, int shots,int firingRange,int travelDistance ){
		this.name = name;
		this.health = health;
		this.shots = shots;
		this.firingRange = firingRange;
		this.travelDistance=travelDistance;
	}
	
	public String getName(){
		return name;
	}
	public int getHealth(){
		return health;
	}
	public int getShots(){
		return shots;
	}
	public int getFilingRange(){
		return firingRange;
	}
	public int TravelDistance(){
		return travelDistance;
	}

	
	
	@Override
	public String toString(){
		return " name: "+name+";\n health: "+health+";\n shots: "+ shots+";\n firingRange: "+firingRange+";\n travelDistance: "+ travelDistance+";\n step:" + step+";";
	}
}
