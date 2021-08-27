package it.univpm.FindWorkApp.model;

public class Stats {
		private String location;
		private String search;
		private boolean remote;
		private boolean htime;
		
		public Stats(String location,String search, boolean remote, boolean htime) {
			this.location=location;
			this.search=search;
			this.remote=remote;
			this.htime=htime;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getSearch() {
			return search;
		}

		public void setSearch(String search) {
			this.search = search;
		}

		public boolean isRemote() {
			return remote;
		}

		public void setRemote(boolean remote) {
			this.remote = remote;
		}

		public boolean isHtime() {
			return htime;
		}

		public void setHtime(boolean htime) {
			this.htime = htime;
		}
}
