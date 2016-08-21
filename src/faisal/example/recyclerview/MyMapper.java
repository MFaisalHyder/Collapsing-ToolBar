package faisal.example.recyclerview;

public class MyMapper {
	private String movieTitle, movieGenre, movieYear;
	
	public MyMapper(){		
	}
	
	public MyMapper(String title, String genre, String year){
		this.movieTitle = title;
		this.movieGenre = genre;
		this.movieYear = year;
	}
	
	public void setTitle(String name){
		this.movieTitle = name;
	}
	
	public String getTitle(){
		return this.movieTitle;
	}
	
	public void setGenre(String genre){
		this.movieGenre=genre;		
	}
	
	public String getGenre(){
		return this.movieGenre;
	}
	
	public void setYear(String releaseYear){
		this.movieYear = releaseYear;
	}

	public String getYear(){
		return this.movieYear;
	}
}