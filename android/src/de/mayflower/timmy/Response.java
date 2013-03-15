package de.mayflower.timmy;

import java.util.Map;
import java.util.UUID;



public class Response {
	
	public static class Track {
		public UUID id;
		public String start;
		public String end;
		public String description;
		public String duration;
		public String resource;
	}
	
	public Track track;
}
