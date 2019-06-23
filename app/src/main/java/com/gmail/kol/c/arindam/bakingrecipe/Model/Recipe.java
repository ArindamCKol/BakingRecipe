package com.gmail.kol.c.arindam.bakingrecipe.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

//recipe details class implement parcelable
public class Recipe implements Parcelable {
    private int id;
    private String name;
    public List<Ingredient> ingredients = new ArrayList<>();
    public List<Step> steps = new ArrayList<>();
    private int servings;
    private String image;

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    protected Recipe(Parcel parcel) {
        id = parcel.readInt();
        name = parcel.readString();
        parcel.readList(ingredients,Ingredient.class.getClassLoader());
        parcel.readList(steps,Step.class.getClassLoader());
        servings = parcel.readInt();
        image = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeList(ingredients);
        parcel.writeList(steps);
        parcel.writeInt(servings);
        parcel.writeString(image);
    }

    //inner class for recipe ingredient details
    public static class Ingredient implements Parcelable {
        private float quantity;
        private String measure;
        private String ingredient;

        protected Ingredient(Parcel in) {
            quantity = in.readFloat();
            measure = in.readString();
            ingredient = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeFloat(quantity);
            dest.writeString(measure);
            dest.writeString(ingredient);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
            @Override
            public Ingredient createFromParcel(Parcel in) {
                return new Ingredient(in);
            }

            @Override
            public Ingredient[] newArray(int size) {
                return new Ingredient[size];
            }
        };

        public float getQuantity() {
            return quantity;
        }

        public String getMeasure() {
            return measure;
        }

        public String getIngredient() {
            return ingredient;
        }
    }

    //inner class for recipe steps details
    public static class Step implements Parcelable {
        private int id;
        private String shortDescription;
        private String description;
        private String videoURL;
        private String thumbnailURL;

        protected Step(Parcel in) {
            id = in.readInt();
            shortDescription = in.readString();
            description = in.readString();
            videoURL = in.readString();
            thumbnailURL = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(shortDescription);
            dest.writeString(description);
            dest.writeString(videoURL);
            dest.writeString(thumbnailURL);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Step> CREATOR = new Creator<Step>() {
            @Override
            public Step createFromParcel(Parcel in) {
                return new Step(in);
            }

            @Override
            public Step[] newArray(int size) {
                return new Step[size];
            }
        };

        public int getId() {
            return id;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public String getDescription() {
            return description;
        }

        public String getVideoURL() {
            return videoURL;
        }

        public String getThumbnailURL() {
            return thumbnailURL;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}
