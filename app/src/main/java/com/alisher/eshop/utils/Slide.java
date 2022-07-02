package com.alisher.eshop.utils;

import com.alisher.eshop.R;

import java.util.ArrayList;
import java.util.List;

public class Slide {

    private Slide(){}

    private static final List<Integer> slides = new ArrayList<>();

    static {
        slides.add(R.drawable.banner1);
        slides.add(R.drawable.banner2);
        slides.add(R.drawable.banner3);
        slides.add(R.drawable.banner4);
        slides.add(R.drawable.banner6);
        slides.add(R.drawable.banner7);
    }

    public static List<Integer> getSlides() {
        return slides;
    }
}
