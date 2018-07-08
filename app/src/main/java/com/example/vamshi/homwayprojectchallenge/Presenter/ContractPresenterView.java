package com.example.vamshi.homwayprojectchallenge.Presenter;

public interface ContractPresenterView {


    interface PresenterMainWork {

        void getPlaces(String s);

        void addFavourite(String key);

        void removeFavourite(String key);

        Boolean isFavourite(String key);

        void maStart();

        void maStop();

    }

    interface ViewMapWork {

        void displayMap();

    }

    interface ViewPlaceWork {

        void displayPlaces();
    }

}
