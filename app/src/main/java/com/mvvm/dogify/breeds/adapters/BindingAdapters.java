package com.mvvm.dogify.breeds.adapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Map;

import com.mvvm.dogify.R;

/**
 * Custom data-binding adapter
 * @author Wyatt Paro
 */
public class BindingAdapters {

    @BindingAdapter({"imageUrl", "breedName"})
    public static void bindImageUrl(ImageView imageView, Map<String, String> breedMap, String breedName) {
        if (breedMap != null && breedName != null) {
            if (breedMap.get(breedName) != null) {
                RequestOptions request = RequestOptions.placeholderOf(R.drawable.placeholder_image);
                Glide.with(imageView).load(breedMap.get(breedName)).apply(request).into(imageView);
            } else {
                imageView.setImageBitmap(null);
            }
        } else {
            imageView.setImageBitmap(null);
        }
    }

    @BindingAdapter("text")
    public static void bindText(TextView textView, String breedName) {
        if (breedName != null) {
            textView.setText(breedName);
        }
    }
}
