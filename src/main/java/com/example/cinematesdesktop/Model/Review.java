package com.example.cinematesdesktop.Model;

import java.util.Date;

public class Review {
    private String textReview, author, titleMovie, idReview;
    private Date dateAndTime;
    private boolean isInappropriate;
    private int totalLike, totalDislike, totalLove, totalClap, totalGrrr, idMovie, totalValuation, counterForSpoiler, counterForLanguage;
    private float movieValuation, rating;

    public Review() {
    }

    public Review(String textReview, String author, String titleMovie, String idReview, Date dateAndTime, int totalLike, int totalDislike, int totalLove, int totalClap, int totalGrrr, int idMovie, float movieValuation, float rating, int totalValuation, int counterForSpoiler, int counterForLanguage, boolean isInappropriate) {
        this.textReview = textReview;
        this.author = author;
        this.titleMovie = titleMovie;
        this.idReview = idReview;
        this.dateAndTime = dateAndTime;
        this.totalLike = totalLike;
        this.totalDislike = totalDislike;
        this.totalLove = totalLove;
        this.totalClap = totalClap;
        this.totalGrrr = totalGrrr;
        this.idMovie = idMovie;
        this.movieValuation = movieValuation;
        this.rating = rating;
        this.totalValuation = totalValuation;
        this.counterForSpoiler = counterForSpoiler;
        this.counterForLanguage = counterForLanguage;
        this.isInappropriate = isInappropriate;
    }


    public String getTextReview() {
        return textReview;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public String getIdReview() {
        return idReview;
    }

    public void setIdReview(String idReview) {
        this.idReview = idReview;
    }

    public Date getDateAndTime() {
        return (Date) dateAndTime.clone();
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public int getTotalDislike() {
        return totalDislike;
    }

    public void setTotalDislike(int totalDislike) {
        this.totalDislike = totalDislike;
    }

    public int getTotalLove() {
        return totalLove;
    }

    public void setTotalLove(int totalLove) {
        this.totalLove = totalLove;
    }

    public int getTotalClap() {
        return totalClap;
    }

    public void setTotalClap(int totalClap) {
        this.totalClap = totalClap;
    }

    public int getTotalGrrr() {
        return totalGrrr;
    }

    public void setTotalGrrr(int totalGrrr) {
        this.totalGrrr = totalGrrr;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public int getTotalValuation() {
        return totalValuation;
    }

    public void setTotalValuation(int totalValuation) {
        this.totalValuation = totalValuation;
    }

    public float getMovieValuation() {
        return movieValuation;
    }

    public void setMovieValuation(float movieValuation) {
        this.movieValuation = movieValuation;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCounterForSpoiler() {
        return counterForSpoiler;
    }

    public void setCounterForSpoiler(int counterForSpoiler) {
        this.counterForSpoiler = counterForSpoiler;
    }

    public int getCounterForLanguage() {
        return counterForLanguage;
    }

    public void setCounterForLanguage(int counterForLanguage) {
        this.counterForLanguage = counterForLanguage;
    }

    public boolean isIsInappropriate() {
        return isInappropriate;
    }

    public void setIsInappropriate(boolean isInappropriate) {
        this.isInappropriate = isInappropriate;
    }


    @Override
    public String toString() {
        return "Review{" +
                "textReview='" + textReview + '\'' +
                ", author='" + author + '\'' +
                ", titleMovie='" + titleMovie + '\'' +
                ", idReview='" + idReview + '\'' +
                ", dateAndTime=" + dateAndTime + '\'' +
                ", totalLike=" + totalLike + '\'' +
                ", totalDislike=" + totalDislike + '\'' +
                ", totalLove=" + totalLove + '\'' +
                ", totalClap=" + totalClap + '\'' +
                ", totalGrrr=" + totalGrrr + '\'' +
                ", idMovie=" + idMovie + '\'' +
                ", movieValuation=" + movieValuation + '\'' +
                ", rating=" + rating + '\'' +
                ", totalValuation=" + totalValuation + '\'' +
                ", counterForSpoiler=" + counterForSpoiler + '\'' +
                ", counterForLanguage=" + counterForLanguage + '\'' +
                ", isInappropriate=" + isInappropriate +
                '}';
    }

}