package com.example.sqlitetest;

import java.util.UUID;

public class gpsGeom{
	private long gpsGeom_id;
	private String gpsGeom_coord;
	
	public long getGpsGeomsId(){
		return gpsGeom_id;
	}

	public String getGpsGeomCoord() {
		return gpsGeom_coord;
	}

	public void setGpsGeomCoord(String str) {
		this.gpsGeom_coord = str;
	}

	public void setGpsGeomsId(long id) {
		this.gpsGeom_id = id;
	}
	
	//will be used by the ArayAdapter in the ListView
	@Override
	public String toString() {
		return "gpsGeom_id =" + this.gpsGeom_id + "&" + "\n gpsGeom_coord =" + this.gpsGeom_coord  + "&" + "\n position =" + this.gpsGeom_id;
		//print the uuid, need a query to get a position
	}
	
	
		
}
