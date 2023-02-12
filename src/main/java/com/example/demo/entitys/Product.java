package com.example.demo.entitys;

public class  Product implements Comparable<Product>{

    private String id; // номер
    private String title, // название
            link; // ссылка на картинку
    private double price; // цена

    public Product(String id, String title, String link, double price) {
        setId(id);
        setLink(link);
        setPrice(price);
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Product o) {
        if (Integer.parseInt(this.id.substring(1,this.id.length()-1)) >=
                Integer.parseInt(o.id.substring(1,o.id.length()-1))) {
            return 1;
        }
        return 0;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /*
    public void setYear(String year) throws Exception {
        if (year.isBlank()) {
            throw new Exception("Blank!");
        }
        this.year = year;
    }
    public void setRating(double rating) throws Exception {
        if (year.isBlank()) {
            throw new Exception("Blank!");
        }
        this.rating = rating;
    }
    public String getYear() {
        return year;
    }
    public double getRating() {
        return rating;
    }
    public Product(){

    }
    public String getTitle() {
        return title;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }
    public int getId() {
        return id;
    }
    public int getSeasons() {
        return seasons;
    }
    public int getSeries() {
        return series;
    }
    public int getDuration() {
        return duration;
    }
    public ArrayList<String> getGenre() {
        return genre;
    }
    public String getLink() {
        return link;
    }
    public void setTitle(String title) throws Exception {
        if (title.isBlank()) {
            throw new Exception("Blank!");
        }
        this.title = title;
    }
    public void setPhotoUrl(String photoUrl) throws Exception {
        if (!photoUrl.startsWith("https://")) {
            throw new Exception("Url must start with 'https://'");
        }
        this.photoUrl = photoUrl;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setSeasons(int seasons) throws Exception {
        if (seasons < 2) {
            throw new Exception("More seasons!");
        }
        this.seasons = seasons;
    }
    public void setSeries(int series) throws Exception {
        if (series < 5) {
            throw new Exception("More series!");
        }
        this.series = series;
    }
    public void setDuration(int duration) throws Exception {
        if (duration < 24) {
            throw new Exception("It is not anime!");
        }
        this.duration = duration;
    }
    public void setGenre(ArrayList<String> genre) throws Exception {
        if (genre.size() < 1) {
            throw new Exception("Minimum size is 1");
        }
        this.genre = genre;
    }
    public void setLink(String link) throws Exception {
        if (!link.startsWith("https://")) {
            throw new Exception("Link must start with 'https://'");
        }
        this.link = link;
    }
    @Override
    public int compareTo(Product o) {
        if (this.id >= o.id) {
            return 1;
        }
        return 0;
    }*/
}
